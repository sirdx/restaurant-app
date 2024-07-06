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

CREATE TABLE restaurant_table
(
    id          UUID                        NOT NULL,
    location    VARCHAR(8)                  NOT NULL,
    description VARCHAR(200)                NOT NULL,
    seats       INTEGER                     NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT pk_restaurant_table PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id              UUID                        NOT NULL,
    customer_id     UUID                        NOT NULL,
    table_id        UUID                        NOT NULL,
    start_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_timestamp   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT pk_reservation PRIMARY KEY (id),
    CONSTRAINT FK_RESERVATION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT FK_RESERVATION_ON_TABLE FOREIGN KEY (table_id) REFERENCES restaurant_table (id)
);
