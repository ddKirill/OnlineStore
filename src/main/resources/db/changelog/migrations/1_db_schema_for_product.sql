CREATE TABLE users
(
    --Id == Telegram chatId
    chat_id INTEGER primary key UNIQUE,
    user_id INTEGER not null,
    user_name VARCHAR(256),
    phone_number INTEGER,
    registered_at TIMESTAMP,
    version INTEGER
);

CREATE TABLE orders
(
    order_number SERIAL primary key,
    status VARCHAR(20) not null,
    order_registered TIMESTAMP,
    order_price INTEGER
);

CREATE TABLE users_orders
(
  chat_id INTEGER REFERENCES users (chat_id),
  order_number INTEGER REFERENCES orders (order_number),
  PRIMARY KEY (chat_id, order_number)
);

CREATE TABLE product
(
    product_id SERIAL primary key,
    title VARCHAR(125) not null,
    price INTEGER not null,
    description VARCHAR,
    location_image VARCHAR(256)
);

CREATE TABLE product_in_order
(
    product_id INTEGER REFERENCES product (product_id),
    order_number INTEGER REFERENCES orders (order_number),
    product_amount INTEGER,
    PRIMARY KEY (order_number, product_id)
);










