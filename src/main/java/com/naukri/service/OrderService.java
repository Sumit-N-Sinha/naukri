package com.naukri.service;

import com.naukri.entity.Order;
import com.naukri.dto.OrderDTO;
import com.naukri.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    public Order createOrder(OrderDTO orderDTO) {
        // Process payment dynamically
        boolean paymentSuccess = paymentService.pay(orderDTO.getAmount(), orderDTO.getProduct(), orderDTO.getPaymentGateway());
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setProduct(orderDTO.getProduct());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(paymentSuccess ? "PAID" : "FAILED");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        // Custom query can be added
        return orderRepository.findAll();
    }
}
