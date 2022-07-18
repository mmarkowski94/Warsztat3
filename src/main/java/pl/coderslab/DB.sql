CREATE DATABASE warsztat3
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
use warsztat3;

CREATE TABLE users (
                       id INT AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       username VARCHAR(255) NOT NULL ,
                       password VARCHAR(60) NOT NULL ,
                       PRIMARY KEY(id)
);