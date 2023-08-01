--liquibase formatted sql
--changeset kkosmala:4
INSERT INTO home_page_settings (product_strategy)
VALUES ('BASIC');