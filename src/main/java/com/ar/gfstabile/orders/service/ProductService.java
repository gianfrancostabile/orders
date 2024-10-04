package com.ar.gfstabile.orders.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ar.gfstabile.orders.dto.common.PriceDto;
import com.ar.gfstabile.orders.dto.response.ProductDataResponseDto;
import com.ar.gfstabile.orders.util.ResponseHandler;

@Service
public class ProductService {

    private static final String CODE_KEY = "{code}";
    private static final String GET_PRICE_BY_CODE = "/v1/products/" + CODE_KEY + "/price";
    private static final String FIND_BY_CODE = "/v1/products/" + CODE_KEY;

    @Value("${external.service.product.host}")
    private String serviceHost;

    @Autowired
    @Qualifier("productsHttpClient")
    private HttpClient httpClient;

    @Autowired
    private ResponseHandler responseHandler;

    public CompletableFuture<PriceDto> getPriceByCode(String code) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(serviceHost.concat(GET_PRICE_BY_CODE).replace(CODE_KEY, code))).GET().build();
            return httpClient.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body)
                    .thenApply(value -> responseHandler.map(value, PriceDto.class));
        } catch (Exception e) {
            throw new Error("is not posible to get product price");
        }
    }

    public CompletableFuture<ProductDataResponseDto> findByCode(String code) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(serviceHost.concat(FIND_BY_CODE).replace(CODE_KEY, code))).GET().build();
            return httpClient.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body)
                    .thenApply(value -> responseHandler.map(value, ProductDataResponseDto.class));
        } catch (Exception e) {
            throw new Error("is not posible to get product price");
        }
    }

}
