package com.ar.gfstabile.orders.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ar.gfstabile.orders.model.Order;

@Repository
public interface OrderRespository extends MongoRepository<Order, ObjectId> {
    
}
