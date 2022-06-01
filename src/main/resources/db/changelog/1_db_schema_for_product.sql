CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
    id UUID primary key not null default uuid_generate_v4(),
    title VARCHAR(256) not null,
    price INTEGER not null,
    description VARCHAR,
    location_image VARCHAR(256)
);

CREATE TABLE orders
(
    id UUID primary key not null default uuid_generate_v4(),
    number INTEGER not null,
    item UUID references product,
    amount INTEGER not null,
    status VARCHAR(125) not null,
    buyer VARCHAR(125) not null
);

INSERT INTO product(title, price, description, location_image)
 VALUES ('testProduct', '1000', 'example description', 'resources/static/image/spices.jpg');


