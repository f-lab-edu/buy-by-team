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
                              primary key(id)
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

create table if not exists mysqldb.deal (
                         id INT NOT NULL AUTO_INCREMENT,
                         product_id int,
                         group_size int,
                         discount_price int,
                         status varchar(255),
                         participant_count int,
                         is_private boolean,
                         expired_at TIMESTAMP,
                         closed_at TIMESTAMP,
                         PRIMARY KEY(id),
                         index index_deal_on_status (status)
);

create table if not exists mysqldb.price_table (
                         id INT NOT NULL AUTO_INCREMENT,
                         product_id int,
                         group_size int,
                         discount_price int,
                         target_period int,
                         PRIMARY KEY(id)
);

create table if not exists mysqldb.payment (
                         id INT NOT NULL AUTO_INCREMENT,
                         user_id int,
                         order_id int,
                         method int,
                         status int,
                         PRIMARY KEY(id)
);

