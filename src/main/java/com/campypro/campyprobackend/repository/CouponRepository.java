package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
} 