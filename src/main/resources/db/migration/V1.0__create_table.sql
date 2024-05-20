create table if not exists customers (
    id varchar(255) primary key,
    customer_number varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    middle_name varchar(255),

    created_at timestamp not null,
    created_by varchar(255) not null,
    updated_at timestamp not null,
    updated_by varchar(255) not null,
    version timestamp not null
);