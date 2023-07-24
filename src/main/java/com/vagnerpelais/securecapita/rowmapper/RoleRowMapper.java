package com.vagnerpelais.securecapita.rowmapper;

import com.vagnerpelais.securecapita.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Role.builder()
                .id(UUID.fromString(resultSet.getString("id")))
                .name(resultSet.getString("name"))
                .permission(resultSet.getString("permission"))
                .build();
    }
}
