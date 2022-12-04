CREATE DATABASE IF NOT EXISTS mysqldb;

USE mysqldb;

CREATE TABLE users(
                      id int NOT NULL AUTO_INCREMENT,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      PRIMARY KEY(id)
);

INSERT INTO user(email, password) VALUES ("jin@gmail.com", "flab-bbt");
INSERT INTO user(email, password) VALUES ("hoon@gmail.com", "flab-bbt");
INSERT INTO user(email, password) VALUES ("lion@gmail.com", "flab-bbt");

INSERT INTO user_profile(name, phone_no) VALUES ("Jin", "010-7607-7191");