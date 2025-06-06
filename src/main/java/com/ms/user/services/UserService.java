package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositores.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserProducer userProducer;
    final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional // garantir rollback em caso de erro
    public UserModel save(UserModel user) {
        UserModel userModel = userRepository.save(user);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

    public Optional<UserModel> findById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }
}
