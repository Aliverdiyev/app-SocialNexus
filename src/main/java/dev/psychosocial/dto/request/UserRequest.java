package dev.psychosocial.dto.request;

import dev.psychosocial.entities.User;

import java.time.LocalDate;

public record UserRequest(String userName,
                          String firstName,
                          String lastName,
                          String email,
                          String password,
                          LocalDate birthDate) {

    public static User convertUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.userName);
        user.setFirstname(userRequest.firstName);
        user.setLastname(userRequest.lastName);
        user.setEmail(userRequest.email);
        user.setPassword(userRequest.password);
        user.setBirthdate(userRequest.birthDate);
        return user;
    }
}