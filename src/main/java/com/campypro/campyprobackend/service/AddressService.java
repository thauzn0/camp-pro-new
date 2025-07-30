package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Address;
import com.campypro.campyprobackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address saveAddress(Address address);
    Optional<Address> getAddressById(Long id);
    List<Address> getAllAddresses();
    void deleteAddress(Long id);
    List<Address> getAddressesByUser(User user);
} 