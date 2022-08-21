CREATE TABLE users
(
    chat_id INTEGER primary key UNIQUE,
    user_name VARCHAR(256),
    phone_number INTEGER,
    --List<Orders>
    order_number INTEGER,
    registered_at TIMESTAMP,
    version INTEGER
);


