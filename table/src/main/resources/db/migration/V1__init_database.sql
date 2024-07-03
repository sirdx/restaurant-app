CREATE TABLE restaurant_table
(
    id          UUID                        NOT NULL,
    location    VARCHAR(8)                  NOT NULL,
    description VARCHAR(200)                NOT NULL,
    seats       INTEGER                     NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT pk_restaurant_table PRIMARY KEY (id)
);
