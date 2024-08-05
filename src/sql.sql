CREATE DATABASE book;

USE book;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    picture VARCHAR(255),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE user_info(
	user_id BIGINT PRIMARY KEY,   
	nickname varchar(300),
    email varchar(255),
    introduce varchar(255),
    area varchar(255),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_title VARCHAR(255) NOT NULL,
    post_content TEXT,
    user_id BIGINT,   
    create_at DATETIME DEFAULT NOW(), 
    FOREIGN KEY (user_id) REFERENCES user(id)
);
select * from user;

select * from post;
drop table post;
