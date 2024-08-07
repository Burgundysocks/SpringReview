CREATE DATABASE book;

USE book;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nickname varchar(255) NULL,
    password varchar(255) NULL,
    picture VARCHAR(255),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_title VARCHAR(255) NOT NULL,
    post_content LONGTEXT,
    user_id BIGINT,   
    create_at DATETIME DEFAULT NOW(), 
    like_cnt BIGINT DEFAULT 0,
    view_cnt BIGINT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
select * from user;
drop table user;

select * from post;
drop table post;
