CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
    id UUID primary key not null default uuid_generate_v4(),
    title VARCHAR(256) not null,
    price INTEGER not null,
    description VARCHAR(256),
    category VARCHAR(256) not null
);

CREATE TABLE users
(
    id UUID primary key not null,
    name VARCHAR(256) not null,
    lastname VARCHAR(256),
    email VARCHAR(256) not null,
    login VARCHAR(256) not null,
    password VARCHAR(256) not null,
    activity BOOLEAN not null
);

CREATE TABLE orders
(
    id UUID primary key not null,
    item UUID references product,
    amount INTEGER not null,
    status VARCHAR(125) not null,
    buyer UUID not null references users
);


