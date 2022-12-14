create database mysqldb;
create table if not exists mysqldb.user (
                      id INT NOT NULL AUTO_INCREMENT,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      PRIMARY KEY(id)
);

create table if not exists mysqldb.user_profile (
                              id INT NOT NULL AUTO_INCREMENT,
                              name varchar(255),
                              phone_no varchar(255),
                              user_id int,
                              primary key(id),
                              foreign key (user_id) references user(id)
);

create table if not exists mysqldb.product (
                         id INT NOT NULL AUTO_INCREMENT,
                         name varchar(255),
                         sku_code varchar(255),
                         img_url varchar(255),
                         price_sale int,
                         price_discount int,
                         discount_rate int,
                         PRIMARY KEY(id),
                         index index_product_on_sku_code (sku_code)
);