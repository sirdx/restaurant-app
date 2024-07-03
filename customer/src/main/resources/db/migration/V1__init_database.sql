CREATE TABLE customer
(
    id         UUID                        NOT NULL,
    first_name VARCHAR(30)                 NOT NULL,
    last_name  VARCHAR(30)                 NOT NULL,
    email      VARCHAR(70)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT uc_customer_email UNIQUE (email)
);
