package dev.psychosocial.services;

import dev.psychosocial.dto.request.UserRequest;
import dev.psychosocial.dto.response.UserResponse;
import dev.psychosocial.entities.User;
import dev.psychosocial.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.convertUserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map((UserResponse::convertUserToUserResponse)).collect(Collectors.toList());
    }

    @Transactional
    public UserResponse getOneUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(STR."\{username}" + " is not found."));
        return UserResponse.convertUserToUserResponse(foundUser);
    }

    @Transactional
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(STR."\{username}" + " is not found."));
        User requestUser = UserRequest.convertUserRequestToUser(userRequest);
        User user = updateUser(foundUser, requestUser);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    protected User updateUser(User foundUser, User requestUser) {
        Optional.ofNullable(requestUser.getFirstname()).ifPresent(foundUser::setFirstname);
        Optional.ofNullable(requestUser.getLastname()).ifPresent(foundUser::setLastname);
        Optional.ofNullable(requestUser.getBirthdate()).ifPresent(foundUser::setBirthdate);
        return foundUser;
    }
}
