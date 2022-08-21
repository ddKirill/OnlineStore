CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
    id UUID primary key not null default uuid_generate_v4(),
    title VARCHAR(256) not null UNIQUE,
    price INTEGER not null,
    description VARCHAR,
    location_image VARCHAR(256)
);





