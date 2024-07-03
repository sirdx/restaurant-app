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
);
