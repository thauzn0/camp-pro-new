package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
} 