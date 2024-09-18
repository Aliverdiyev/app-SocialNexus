package dev.psychosocial.dto.response;

import dev.psychosocial.entities.User;

import java.time.LocalDate;

public record UserResponse(String userName,
                           String firstName,
                           String lastName,
                           String email,
                           LocalDate birthDate) {

    public static UserResponse convertUserToUserResponse(User savedUser) {
      return  new UserResponse(savedUser.getUsername(), savedUser.getFirstname(), savedUser.getLastname(), savedUser.getEmail(), savedUser.getBirthdate());
    }
}