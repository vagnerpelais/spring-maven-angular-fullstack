package com.vagnerpelais.securecapita.service.implementation;

import com.vagnerpelais.securecapita.dto.UserDTO;
import com.vagnerpelais.securecapita.dto.dtomapper.UserDTOMapper;
import com.vagnerpelais.securecapita.model.User;
import com.vagnerpelais.securecapita.repository.UserRepository;
import com.vagnerpelais.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository<User> userUserRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(this.userUserRepository.create(user));
    }
}
