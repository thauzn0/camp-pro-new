package com.campypro.campyprobackend.repository;

import com.campypro.campyprobackend.entity.Address;
import com.campypro.campyprobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
} 