package com.vagnerpelais.securecapita.repository;

import com.vagnerpelais.securecapita.model.Role;

import java.util.Collection;
import java.util.UUID;

public interface RoleRepository<T extends Role> {
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T  get(UUID id);
    T update(T data);
    Boolean delete(UUID id);

    // More complex operations
    default void addRoleToUser(UUID userId, String roleName) {

    }
}
