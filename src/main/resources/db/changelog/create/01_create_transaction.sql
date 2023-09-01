--liquibase formatted sql

--changeset transaction:01_create_transaction.sql

CREATE table TRANSACTION(
    transaction_id uuid PRIMARY KEY,
    amount numeric(38,2),
    user_id uuid not null,
    created_date timestamp,
    modified_date timestamp
);