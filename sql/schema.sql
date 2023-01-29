create table if not exists user
(
    id       INT          NOT NULL AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists user_profile
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     varchar(255),
    phone_no varchar(255),
    user_id  int,
    primary key (id)
);

create table if not exists product
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

create table if not exists deal
(
    id                INT NOT NULL AUTO_INCREMENT,
    product_id        int,
    price_table_id    int,
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

INSERT INTO price_table
(product_id, deal_capacity, discount_price, deal_valid_period_in_days, is_deal_private, start_date,
 end_date)
VALUES (1, 2, 13000, 24, true, TIMESTAMP '2023-01-01 00:00:00', TIMESTAMP '2023-01-31 23:59:59');

create table if not exists price_table
(
    id             INT NOT NULL AUTO_INCREMENT,
    product_id     int,
    group_size     int,
    discount_price int,
    target_period  int,
    PRIMARY KEY (id)
);

