--liquibase formatted sql
--changeset kkosmala:4
CREATE TABLE home_page_settings (
    id SERIAL PRIMARY KEY,
    product_strategy VARCHAR(255) NOT NULL
);
INSERT INTO home_page_settings (product_strategy)
VALUES ('BASIC');