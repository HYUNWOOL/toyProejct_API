DROP TABLE product IF EXISTS ;

CREATE TABLE product(
    id BIGINT NOT NULL,
    name VARCHAR(255),
    price INTEGER,
    PRIMARY KEY (id)
);


