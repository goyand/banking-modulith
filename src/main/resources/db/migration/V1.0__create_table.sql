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

create table if not exists account_types (
    code varchar(255) primary key,
    description varchar(255) not null
);

create table if not exists accounts (
    id varchar(255) primary key,
    account_number varchar(255) not null,
    account_name varchar(255) not null,
    description varchar(255),
    currency varchar(255) not null,
    account_type varchar(255) not null,

    created_at timestamp not null,
    created_by varchar(255) not null,
    updated_at timestamp not null,
    updated_by varchar(255) not null,
    version timestamp not null
);

create table if not exists account_role_types (
    code varchar(255) primary key,
    description varchar(255) not null
);

create table if not exists account_roles (
    id varchar(255) primary key,
    account_id varchar(255) not null,
    customer_id varchar(255) not null,
    role varchar(255) not null,
    from_date timestamp not null,
    thru_date timestamp,

    created_at timestamp not null,
    created_by varchar(255) not null,
    updated_at timestamp not null,
    updated_by varchar(255) not null,
    version timestamp not null
);

create table if not exists account_status_types (
    code varchar(255) primary key,
    description varchar(255) not null
);

create table if not exists account_statuses (
    id varchar(255) primary key,
    account_id varchar(255) not null,
    status varchar(255) not null,
    datetime timestamp not null,

    created_at timestamp not null,
    created_by varchar(255) not null,
    updated_at timestamp not null,
    updated_by varchar(255) not null,
    version timestamp not null
);

create table account_balances (
    id varchar(255) primary key,
    account_id varchar(255) not null,
    amount decimal not null,
    datetime timestamp not null,

    created_at timestamp not null,
    created_by varchar(255) not null,
    updated_at timestamp not null,
    updated_by varchar(255) not null,
    version timestamp not null
);

alter table account_balances add constraint uk_account_balance_account unique (account_id);
alter table account_roles add constraint uk_account_role_account_customer_from_date unique (account_id, customer_id, from_date);
alter table account_statuses add constraint uk_account_status_account_datetime unique (account_id, datetime);

alter table accounts add constraint fk_account_account_type foreign key (account_type) references account_types(code);
alter table account_roles add constraint fk_account_role_account foreign key (account_id) references accounts(id);
alter table account_roles add constraint fk_account_role_account_role_type foreign key (role) references account_role_types(code);
alter table account_statuses add constraint fk_account_status_account foreign key (account_id) references accounts(id);
alter table account_statuses add constraint fk_account_status_account_status_type foreign key (status) references account_status_types(code);
alter table account_balances add constraint fk_account_balance_account foreign key (account_id) references accounts(id);

create table if not exists event_publication
(
  id               uuid not null,
  listener_id      text not null,
  event_type       text not null,
  serialized_event text not null,
  publication_date timestamp with time zone not null,
  completion_date  timestamp with time zone,
  primary key (id)
);
create index if not exists event_publication_serialized_event_hash_idx on event_publication using hash(serialized_event);
create index if not exists event_publication_by_completion_date_idx on event_publication (completion_date);