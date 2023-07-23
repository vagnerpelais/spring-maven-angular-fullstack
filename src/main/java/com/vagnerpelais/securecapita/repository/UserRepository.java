package com.vagnerpelais.securecapita.repository;

import com.vagnerpelais.securecapita.model.User;

import java.util.Collection;
import java.util.UUID;

public interface UserRepository<T extends User> {
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T  get(UUID id);
    T update(T data);
    Boolean delete(UUID id);
}
