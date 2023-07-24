package com.vagnerpelais.securecapita.repository.implementation;

import com.vagnerpelais.securecapita.exception.ApiException;
import com.vagnerpelais.securecapita.model.Role;
import com.vagnerpelais.securecapita.repository.RoleRepository;
import com.vagnerpelais.securecapita.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.vagnerpelais.securecapita.enums.RoleType.ROLE_USER;
import static com.vagnerpelais.securecapita.query.RoleQuery.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImplementation implements RoleRepository<Role> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(UUID id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public void addRoleToUser(UUID userId, String roleName) {
        log.info("adding role {} to user id: {}", roleName, userId);

        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("name", roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY, Map.of("user_id", userId, "role_id", Objects.requireNonNull(role).getId()));
        } catch (EmptyResultDataAccessException exception) {
            log.error(exception.getMessage());
            throw new ApiException("No role found by name: " + ROLE_USER.name());

        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again");
        }
    }

    @Override
    public Role getRoleByUserId(UUID userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(UUID userId, String roleName) {

    }
}
