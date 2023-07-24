package com.vagnerpelais.securecapita.query;

public class RoleQuery {
    public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO user_roles (user_id, role_id) VALUES (:user_id, :role_id)";
    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM roles WHERE name = :name";
}
