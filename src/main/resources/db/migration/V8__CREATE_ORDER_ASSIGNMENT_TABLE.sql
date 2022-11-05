DROP TABLE IF EXISTS order_assignment CASCADE;

CREATE TABLE order_assignment
(
    id            BIGSERIAL                NOT NULL PRIMARY KEY,


    status        VARCHAR                  NOT NULL,

    assigned_time TIMESTAMP WITH TIME ZONE NOT NULL,

    updated_time  TIMESTAMP WITH TIME ZONE,

    order_id      BIGINT                  NOT NULL,

    franchisee_id BIGINT                   NOT NULL,

    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES "order" (id),

    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)

)