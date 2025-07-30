package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Coupon;
import java.util.List;
import java.util.Optional;

public interface CouponService {
    Coupon saveCoupon(Coupon coupon);
    Optional<Coupon> getCouponById(Long id);
    List<Coupon> getAllCoupons();
    void deleteCoupon(Long id);
} 