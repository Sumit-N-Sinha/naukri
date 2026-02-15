package com.naukri.repository;

import com.naukri.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods if needed
}
