package com.ms.user.services;

import com.ms.user.models.AddressModel;
import com.ms.user.repositores.AddressRepository;
import com.ms.user.repositores.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AddressService {

    final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public AddressModel save(String userId, AddressModel address) {
        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var addressModel = new AddressModel();
        addressModel.setUser(user);
        addressModel.setAddress(address.getAddress());
        addressModel.setCity(address.getCity());
        addressModel.setState(address.getState());
        addressModel.setZip(address.getZip());

        return addressRepository.save(addressModel);
    }
}
