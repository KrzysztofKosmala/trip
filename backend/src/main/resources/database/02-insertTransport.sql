--liquibase formatted sql
--changeset kkosmala:5
create table transport(
    id serial not null primary key,
    name varchar(64) not null,
    type varchar(32) not null,
    default_transport boolean default false,
    note text
);

insert into transport(id, name, type, default_transport, note)
values (1, 'Bus', 'BUS', true,'Note');

