package com.vagnerpelais.securecapita.service;

import com.vagnerpelais.securecapita.dto.UserDTO;
import com.vagnerpelais.securecapita.model.User;

public interface UserService {
    UserDTO createUser(User user);
}
