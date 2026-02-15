package com.naukri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.naukri.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
