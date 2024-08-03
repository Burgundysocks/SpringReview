CREATE DATABASE book;

USE book;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    picture VARCHAR(255),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_title VARCHAR(255) NOT NULL,
    post_content TEXT,
    author VARCHAR(100),
    create_at DATETIME DEFAULT NOW(),
    update_at DATETIME null
);

select * from post;
drop table post;
