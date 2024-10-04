package com.ar.gfstabile.orders.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ar.gfstabile.orders.builder.OrderStateBuilder;
import com.ar.gfstabile.orders.constant.Constant;
import com.ar.gfstabile.orders.constant.PendingOrderState;
import com.ar.gfstabile.orders.constant.State;
import com.ar.gfstabile.orders.dto.common.PersonDto;
import com.ar.gfstabile.orders.dto.common.PriceDto;
import com.ar.gfstabile.orders.dto.request.OrderRequestDto;
import com.ar.gfstabile.orders.dto.response.OrderResponseDto;
import com.ar.gfstabile.orders.mapper.OrderMapper;
import com.ar.gfstabile.orders.mapper.PeopleMapper;
import com.ar.gfstabile.orders.model.Order;
import com.ar.gfstabile.orders.model.Product;
import com.ar.gfstabile.orders.repository.OrderRespository;
import com.ar.gfstabile.orders.util.PrintInvoice;

@Service
public class OrderService {

    @Autowired
    private OrderRespository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PrintInvoice printInvoice;

    @Autowired
    private OrderStateBuilder orderStateBuilder;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PeopleMapper peopleMapper;

    public OrderResponseDto create(OrderRequestDto request) {
        return this.saveOrder(null, request);
    }

    public void update(String id, OrderRequestDto request) {
        if (!StringUtils.hasText(id)) {
            throw new Error("order id is required");
        }
        ObjectId objectId = new ObjectId(id);
        if (!repository.existsById(objectId)) {
            throw new Error("order id does not exists");
        }
        this.saveOrder(objectId, request);
    }

    private OrderResponseDto saveOrder(@Nullable ObjectId id, OrderRequestDto request) {
        Order orderToSave = orderMapper.mapRequestToModel(request, new PendingOrderState());
        if (Objects.isNull(id)) {
            orderToSave.setId(id);
        }
        mapClientData(request, orderToSave);
        mapProductsAndTotalPrice(orderToSave);

        Order orderToReturn = repository.save(orderToSave);
        return orderMapper.mapModelToResponse(orderToReturn);
    }

    private void mapClientData(OrderRequestDto request, Order orderToSave) throws Error {
        Optional<PersonDto> personData = peopleService.getByDocument(request.clientDocument());
        if (!personData.isPresent()) {
            throw new Error("person data not found");
        }
        orderToSave.setClient(peopleMapper.map(personData.get()));
    }

    private void mapProductsAndTotalPrice(Order orderToSave) {
        List<Product> products = orderToSave.getProducts();
        List<CompletableFuture<PriceDto>> futuresWithPrices = products.stream().map(Product::getCode)
                .map(productService::getPriceByCode).collect(Collectors.toList());

        CompletableFuture.allOf(futuresWithPrices.toArray(new CompletableFuture[0]));
        futuresWithPrices.stream().map(CompletableFuture::join)
                .forEach(price -> {
                    Optional<Product> matchedProduct = products.stream()
                            .filter(product -> product.getCode().equals(price.code())).findFirst();
                    matchedProduct.ifPresent(product -> product.setUnitPrice(price.price()));
                });
        Double totalPrice = products.stream().map(Product::getTotalPrice).reduce((a, b) -> a + b)
                .orElse(Constant.MINIMUN_PRICE);
        orderToSave.setTotalPrice(totalPrice);
    }

    public void process(String id) {
        orderStateBuilder.build(State.PROCESSED).changeState(id);
    }

    public void cancel(String id) {
        orderStateBuilder.build(State.CANCELED).changeState(id);
    }

    public Optional<OrderResponseDto> findById(String id) {
        if (!StringUtils.hasText(id)) {
            return Optional.empty();
        }
        return repository.findById(new ObjectId(id)).map(orderMapper::mapModelToResponse);
    }

    public List<OrderResponseDto> findAll() {
        return repository.findAll().stream().map(orderMapper::mapModelToResponse).collect(Collectors.toList());
    }

    public Optional<String> print(String id) {
        if (!StringUtils.hasText(id)) {
            return Optional.empty();
        }
        Optional<Order> orderSavedOptional = repository.findById(new ObjectId(id));
        if (!orderSavedOptional.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(printInvoice.print(orderSavedOptional.get()));
    }
}
