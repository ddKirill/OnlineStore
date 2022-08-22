CREATE TABLE orders
(
    order_number SERIAL primary key not null,
    buyer INTEGER REFERENCES users (chat_id),
    products UUID REFERENCES product (id) ON DELETE CASCADE,
    amount INTEGER,
    status VARCHAR(125) not null,
    order_registered TIMESTAMP,
    order_price INTEGER
);