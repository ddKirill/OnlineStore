package com.ddkirill.strore.repository;

import com.ddkirill.strore.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {


}

