create sequence _user_seq start with 1 increment by 50;
create sequence log_seq start with 1 increment by 50;
create sequence order_seq start with 1 increment by 50;
create sequence payment_seq start with 1 increment by 50;
create sequence transport_seq start with 1 increment by 50;

    create table _user (
       id bigint not null,
        hash_date timestamp(6),
        city varchar(255),
        email varchar(255),
        firstname varchar(255),
        gender TEXT not null,
        hash varchar(255),
        is_enabled boolean not null,
        lastname varchar(255),
        password varchar(255),
        pesel varchar(255),
        phone varchar(255),
        postal varchar(255),
        role varchar(255),
        street varchar(255),
        primary key (id)
    );

    create table customer (
       id bigserial not null,
        email TEXT not null,
        first_name TEXT not null,
        last_name TEXT not null,
        password varchar(255) not null,
        primary key (id)
    );

    create table home_page_settings (
       id bigserial not null,
        product_strategy varchar(255),
        primary key (id)
    );

    create table image (
       id bigserial not null,
        desctiption varchar(255),
        location smallint,
        name varchar(255),
        thumb_image varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table log (
       dtype varchar(31) not null,
        id bigint not null,
        created timestamp(6),
        note varchar(255),
        order_id bigint,
        primary key (id)
    );

    create table "order" (
       id bigint not null,
        city varchar(255),
        email varchar(255),
        firstname varchar(255),
        gross_value numeric(38,2),
        lastname varchar(255),
        order_status varchar(255),
        phone varchar(255),
        place_date timestamp(6),
        street varchar(255),
        zipcode varchar(255),
        postal varchar(255),
        payment_id bigint,
        product_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table payment (
       id bigint not null,
        default_payment boolean not null,
        name varchar(255),
        note varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table product (
       id bigserial not null,
        base_price numeric(38,2),
        currency TEXT not null,
        description TEXT not null,
        full_dessc varchar(255),
        name TEXT not null,
        sale_price numeric(38,2),
        show_on_homepage boolean,
        slug varchar(255),
        primary key (id)
    );

    create table product_image (
       trip_id bigint not null,
        image_id bigint not null,
        primary key (trip_id, image_id)
    );

    create table review (
       id bigserial not null,
        author_name varchar(255),
        content varchar(255),
        product_id bigint,
        primary key (id)
    );

    create table room (
       id bigserial not null,
        trip_id bigint,
        primary key (id)
    );

    create table rooms_users (
       room_id bigint not null,
        user_id bigint not null,
        primary key (room_id, user_id)
    );

    create table transport (
       id bigint not null,
        default_transport boolean not null,
        name varchar(255),
        note varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table trip (
       apartment boolean,
        destination TEXT not null,
        stop_date date,
        food boolean,
        house boolean,
        slop_nearby boolean,
        spa boolean,
        start_date date,
        wifi boolean,
        max_capacity integer,
        id bigint not null,
        primary key (id)
    );

    alter table if exists customer 
       add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);

    alter table if exists product 
       add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);

    alter table if exists product 
       add constraint UK_88yb4l9100epddqsrdvxerhq9 unique (slug);

    alter table if exists log 
       add constraint FKpgx0i2ta4nhir1k7cghea444h 
       foreign key (order_id) 
       references "order";

    alter table if exists "order" 
       add constraint FKhgqyhl4wnj9use215racmlveo 
       foreign key (payment_id) 
       references payment;

    alter table if exists "order" 
       add constraint FKknjaoi59nxmpxhr452bj95tgg 
       foreign key (product_id) 
       references product;

    alter table if exists "order" 
       add constraint FKjn7t9qjsgil7dqkr41xxo5y2s 
       foreign key (user_id) 
       references _user;

    alter table if exists product_image 
       add constraint FKbhddxsl8axd5io2wgkcoealn5 
       foreign key (image_id) 
       references image;

    alter table if exists product_image 
       add constraint FKat5i91sh2whv6pxf1nevro5br 
       foreign key (trip_id) 
       references product;

    alter table if exists review 
       add constraint FKiyof1sindb9qiqr9o8npj8klt 
       foreign key (product_id) 
       references product;

    alter table if exists room 
       add constraint FK2o7po8epcccr9b6ugt53amen7 
       foreign key (trip_id) 
       references trip;

    alter table if exists rooms_users 
       add constraint FKq9ucrwvyxc8tn662mpm64r5xd 
       foreign key (user_id) 
       references _user;

    alter table if exists rooms_users 
       add constraint FKjv5nwqwp3i3nc4nnwc2iue3np 
       foreign key (room_id) 
       references room;

    alter table if exists trip 
       add constraint FK63tyax3x89f9rce58puehd56b 
       foreign key (id) 
       references product;
create sequence _user_seq start with 1 increment by 50;
create sequence log_seq start with 1 increment by 50;
create sequence order_seq start with 1 increment by 50;
create sequence payment_seq start with 1 increment by 50;
create sequence transport_seq start with 1 increment by 50;

    create table _user (
       id bigint not null,
        hash_date timestamp(6),
        city varchar(255),
        email varchar(255),
        firstname varchar(255),
        gender TEXT not null,
        hash varchar(255),
        is_enabled boolean not null,
        lastname varchar(255),
        password varchar(255),
        pesel varchar(255),
        phone varchar(255),
        postal varchar(255),
        role varchar(255),
        street varchar(255),
        primary key (id)
    );

    create table customer (
       id bigserial not null,
        email TEXT not null,
        first_name TEXT not null,
        last_name TEXT not null,
        password varchar(255) not null,
        primary key (id)
    );

    create table home_page_settings (
       id bigserial not null,
        product_strategy varchar(255),
        primary key (id)
    );

    create table image (
       id bigserial not null,
        desctiption varchar(255),
        location smallint,
        name varchar(255),
        thumb_image varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table log (
       dtype varchar(31) not null,
        id bigint not null,
        created timestamp(6),
        note varchar(255),
        order_id bigint,
        primary key (id)
    );

    create table "order" (
       id bigint not null,
        city varchar(255),
        email varchar(255),
        firstname varchar(255),
        gross_value numeric(38,2),
        lastname varchar(255),
        order_status varchar(255),
        phone varchar(255),
        place_date timestamp(6),
        street varchar(255),
        zipcode varchar(255),
        postal varchar(255),
        payment_id bigint,
        product_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table payment (
       id bigint not null,
        default_payment boolean not null,
        name varchar(255),
        note varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table product (
       id bigserial not null,
        base_price numeric(38,2),
        currency TEXT not null,
        description TEXT not null,
        full_dessc varchar(255),
        name TEXT not null,
        sale_price numeric(38,2),
        show_on_homepage boolean,
        slug varchar(255),
        primary key (id)
    );

    create table product_image (
       trip_id bigint not null,
        image_id bigint not null,
        primary key (trip_id, image_id)
    );

    create table review (
       id bigserial not null,
        author_name varchar(255),
        content varchar(255),
        product_id bigint,
        primary key (id)
    );

    create table room (
       id bigserial not null,
        trip_id bigint,
        primary key (id)
    );

    create table rooms_users (
       room_id bigint not null,
        user_id bigint not null,
        primary key (room_id, user_id)
    );

    create table transport (
       id bigint not null,
        default_transport boolean not null,
        name varchar(255),
        note varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table trip (
       apartment boolean,
        destination TEXT not null,
        stop_date date,
        food boolean,
        house boolean,
        slop_nearby boolean,
        spa boolean,
        start_date date,
        wifi boolean,
        max_capacity integer,
        id bigint not null,
        primary key (id)
    );

    alter table if exists customer 
       add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);

    alter table if exists product 
       add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);

    alter table if exists product 
       add constraint UK_88yb4l9100epddqsrdvxerhq9 unique (slug);

    alter table if exists log 
       add constraint FKpgx0i2ta4nhir1k7cghea444h 
       foreign key (order_id) 
       references "order";

    alter table if exists "order" 
       add constraint FKhgqyhl4wnj9use215racmlveo 
       foreign key (payment_id) 
       references payment;

    alter table if exists "order" 
       add constraint FKknjaoi59nxmpxhr452bj95tgg 
       foreign key (product_id) 
       references product;

    alter table if exists "order" 
       add constraint FKjn7t9qjsgil7dqkr41xxo5y2s 
       foreign key (user_id) 
       references _user;

    alter table if exists product_image 
       add constraint FKbhddxsl8axd5io2wgkcoealn5 
       foreign key (image_id) 
       references image;

    alter table if exists product_image 
       add constraint FKat5i91sh2whv6pxf1nevro5br 
       foreign key (trip_id) 
       references product;

    alter table if exists review 
       add constraint FKiyof1sindb9qiqr9o8npj8klt 
       foreign key (product_id) 
       references product;

    alter table if exists room 
       add constraint FK2o7po8epcccr9b6ugt53amen7 
       foreign key (trip_id) 
       references trip;

    alter table if exists rooms_users 
       add constraint FKq9ucrwvyxc8tn662mpm64r5xd 
       foreign key (user_id) 
       references _user;

    alter table if exists rooms_users 
       add constraint FKjv5nwqwp3i3nc4nnwc2iue3np 
       foreign key (room_id) 
       references room;

    alter table if exists trip 
       add constraint FK63tyax3x89f9rce58puehd56b 
       foreign key (id) 
       references product;
