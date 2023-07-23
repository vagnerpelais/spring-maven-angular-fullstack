CREATE schema IF NOT EXISTS postgres;

SET TIMEZONE TO 'America/Sao_Paulo';

CREATE TABLE users (
    id            UUID                     PRIMARY KEY,
    first_name    VARCHAR(60)              NOT NULL,
    last_name     VARCHAR(60)              NOT NULL,
    email         VARCHAR(100)             NOT NULL UNIQUE,
    password      VARCHAR(255)             NOT NULL,
    address       VARCHAR(255)             DEFAULT NULL,
    phone         VARCHAR(30)              DEFAULT NULL,
    title         VARCHAR(60)              DEFAULT NULL,
    bio           VARCHAR(255)             DEFAULT NULL,
    enabled       BOOLEAN                  DEFAULT FALSE,
    non_locked    BOOLEAN                  DEFAULT TRUE,
    using_mfa     BOOLEAN                  DEFAULT FALSE,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    image_url     VARCHAR(255)             DEFAULT 'https://icons8.com.br/icon/9q3GMpxNIMjC/usu%C3%A1rio'
);

CREATE TABLE roles(
    id             UUID                    PRIMARY KEY,
    name           VARCHAR(60)             UNIQUE NOT NULL,
    permission     VARCHAR(255)            NOT NULL
);

CREATE TABLE user_roles (
    id            UUID                     PRIMARY KEY,
    user_id       UUID                     NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
    role_id       UUID                     NOT NULL REFERENCES roles(id) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TYPE event_type AS ENUM ('LOGIN_ATTEMPT', 'LOGIN_ATTEMPT_FAILURE', 'LOGIN_ATTEMPT_SUCCESS', 'PROFILE_UPDATE', 'PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE', 'ACCOUNT_SETTINGS_UPDATE', 'PASSWORD_UPDATE', 'MFA_UPDATE');

CREATE TABLE events(
    id             UUID                    PRIMARY KEY,
    type           event_type              NOT NULL,
    description    VARCHAR(255)            NOT NULL
);

CREATE TABLE user_events (
    id            UUID                     PRIMARY KEY,
    user_id       UUID                     NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    event_id      UUID                     NOT NULL REFERENCES events(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    device        VARCHAR(100)             DEFAULT NULL,
    ip_address    VARCHAR(100)             DEFAULT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE account_verifications (
    id            UUID                     PRIMARY KEY,
    user_id       UUID                     NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
    url           VARCHAR(255)             NOT NULL UNIQUE
);

CREATE TABLE reset_password_verifications (
    id                  UUID                     PRIMARY KEY,
    user_id             UUID                     NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
    url                 VARCHAR(255)             NOT NULL UNIQUE,
    expiration_date     TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE two_factor_verifications (
    id                  UUID                     PRIMARY KEY,
    user_id             UUID                     NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
    code                VARCHAR(255)             NOT NULL UNIQUE,
    expiration_date     TIMESTAMP WITH TIME ZONE NOT NULL
);
