package com.vagnerpelais.securecapita.controller;

import com.vagnerpelais.securecapita.dto.UserDTO;
import com.vagnerpelais.securecapita.model.HttpResponse;
import com.vagnerpelais.securecapita.model.User;
import com.vagnerpelais.securecapita.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {
        UserDTO userDTO =  userService.createUser(user);
        return ResponseEntity.created(getUri()).body(
          HttpResponse.builder()
                  .timeStamp(now().toString())
                  .data(Map.of("user", userDTO))
                  .message("User created")
                  .status(HttpStatus.CREATED)
                  .statusCode(HttpStatus.CREATED.value())
                  .build()
        );
    }

    private URI getUri() {
        return  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }
}
