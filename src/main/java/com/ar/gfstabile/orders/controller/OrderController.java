package com.ar.gfstabile.orders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gfstabile.orders.dto.request.OrderRequestDto;
import com.ar.gfstabile.orders.dto.response.OrderResponseDto;
import com.ar.gfstabile.orders.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto request) {
        return ResponseEntity.created(null).body(
                service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable String id, @RequestBody OrderRequestDto request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/process")
    public ResponseEntity<Void> process(@PathVariable String id) {
        service.process(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        service.cancel(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable String id) {
        Optional<OrderResponseDto> response = service.findById(id);
        if (!response.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.get());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}/print")
    public ResponseEntity<String> printByCode(@PathVariable String id) {
        Optional<String> response = service.print(id);
        if (!response.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.get());
    }
}
