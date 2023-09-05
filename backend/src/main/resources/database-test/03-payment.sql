--liquibase formatted sql
--changeset kkosmala:6
CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255) NOT NULL,
    default_payment BOOLEAN NOT NULL,
    note TEXT
);
INSERT INTO payment (name, type, default_payment, note)
VALUES ('Bank Transfer', 'BANK_TRANSFER', true, 'Default payment method for bank transfer');
