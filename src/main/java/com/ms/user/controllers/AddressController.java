package com.ms.user.controllers;

import com.ms.user.dtos.AddressRecordDto;
import com.ms.user.models.AddressModel;
import com.ms.user.services.AddressService;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    final UserService userService;
    final AddressService addressService;

    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/address/{userId}")
    public ResponseEntity<Object> addAddress(@PathVariable String userId, @RequestBody @Valid AddressRecordDto addressRecordDto) {
        var user = userService.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var address = new AddressModel();
        BeanUtils.copyProperties(addressRecordDto, address);
        addressService.save(userId, address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
