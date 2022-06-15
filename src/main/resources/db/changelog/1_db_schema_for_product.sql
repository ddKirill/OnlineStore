CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
    id UUID primary key not null default uuid_generate_v4(),
    title VARCHAR(256) not null UNIQUE,
    price INTEGER not null,
    description VARCHAR,
    location_image VARCHAR(256)
);

CREATE TABLE buyer
(
    id UUID primary key not null default uuid_generate_v4(),
    name VARCHAR(256) UNIQUE not null
);

CREATE TABLE orders
(
    id UUID primary key not null default uuid_generate_v4(),
    number INTEGER not null,
    product_title VARCHAR(255) references product(title) not null,
    amount INTEGER not null,
    buyer VARCHAR(256) not null references buyer(name),
    order_times TIMESTAMP not null,
    total_cost Integer not null

)




