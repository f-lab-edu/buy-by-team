create database if not exists mysqldb;
create table if not exists mysqldb.user
(
    id       INT          NOT NULL AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists mysqldb.user_profile
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     varchar(255),
    phone_no varchar(255),
    user_id  int,
    primary key (id)
);

create table if not exists mysqldb.product
(
    id             INT NOT NULL AUTO_INCREMENT,
    name           varchar(255),
    sku_code       varchar(255),
    img_url        varchar(255),
    price_sale     int,
    price_discount int,
    discount_rate  int,
    PRIMARY KEY (id),
    index index_product_on_sku_code (sku_code)
);

create table if not exists mysqldb.deal
(
    id                INT NOT NULL AUTO_INCREMENT,
    product_id        int,
    deal_info_id      int,
    group_size        int,
    discount_price    int,
    status            varchar(255),
    participant_count int,
    is_private        boolean,
    expired_at        TIMESTAMP,
    closed_at         TIMESTAMP,
    PRIMARY KEY (id),
    index index_deal_on_status (status)
);

create table if not exists mysqldb.deal_info
(
    id           INT NOT NULL AUTO_INCREMENT,
    product_id   int,
    capacity     int,
    active_hours int,
    is_private   boolean,
    start_date   TIMESTAMP,
    end_date     TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO mysqldb.deal_info
(product_id, capacity, active_hours, is_private, start_date, end_date)
VALUES (1, 2, 24, true, TIMESTAMP '2023-01-01 00:00:00', TIMESTAMP '2023-01-31 23:59:59');

create table if not exists mysqldb.price_table
(
    id             INT NOT NULL AUTO_INCREMENT,
    product_id     int,
    group_size     int,
    discount_price int,
    target_period  int,
    PRIMARY KEY (id)
);

