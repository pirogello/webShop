
CREATE TABLE roles
(
    id SERIAL PRIMARY KEY,
    role CHARACTER VARYING(30) NOT NULL
);

CREATE TABLE products
(
    id SERIAL PRIMARY KEY,
    title CHARACTER VARYING(30) NOT NULL,
    quantity INTEGER NOT NULL,
    description CHARACTER VARYING(200) NOT NULL,
    image CHARACTER VARYING(100) NOT NULL,
    price INTEGER NOT NULL,
    user_id INTEGER REFERENCES users (Id),
    highlighting BOOLEAN  DEFAULT FALSE,
    priority_in_list INTEGER DEFAULT 0
);

CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(30) NOT NULL,
    second_name CHARACTER VARYING(30),
    password CHARACTER VARYING(30) NOT NULL,
    role_id INTEGER REFERENCES roles (Id)
);

CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (Id)  NOT NULL,
    product_id INTEGER REFERENCES products (Id) NOT NULL,
    quantity_products INTEGER NOT NULL
);

CREATE TABLE comments
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (Id)  NOT NULL,
    product_id INTEGER REFERENCES products (Id) NOT NULL,
    text CHARACTER VARYING NOT NULL
);

