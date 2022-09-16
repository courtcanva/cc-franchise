DROP TABLE IF EXISTS cc_employee CASCADE;
CREATE TABLE cc_employee
(
    id         BIGINT       NOT NULL PRIMARY KEY,
    email      VARCHAR(100) NOT NULL,
    password   CHAR(64)     NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    role       VARCHAR(10)  NOT NULL,
    created_at timestamptz  NOT NULL,
    updated_at timestamptz,
    status     VARCHAR(10)  NOT NULL
);


DROP TABLE IF EXISTS franchisee CASCADE;
CREATE TABLE franchisee
(
    id            BIGINT       NOT NULL PRIMARY KEY,
    abn           CHAR(11)     NOT NULL,
    state         CHAR(10)     NOT NULL,
    address       VARCHAR(200) NOT NULL,
    postcode      INTEGER      NOT NULL,
    phone_number  VARCHAR(20)  NOT NULL,
    duty_area     VARCHAR      NOT NULL,
    created_at    timestamptz  NOT NULL,
    updated_at    timestamptz,
    status        VARCHAR(10)  NOT NULL,
    approved_time timestamptz,
    approved_by   BIGINT,
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by) REFERENCES cc_employee (id)
);

DROP TABLE IF EXISTS "order" CASCADE;
CREATE TABLE "order"
(
    id                  BIGINT      NOT NULL PRIMARY KEY,
    order_id            VARCHAR     NOT NULL,
    customer_id         VARCHAR     NOT NULL,
    contact_information jsonb       NOT NULL,
    design_information  jsonb       NOT NULL,
    postcode            VARCHAR     NOT NULL,
    total_amount        VARCHAR     NOT NULL,
    paid_amount         VARCHAR     NOT NULL,
    unpaid_amount       VARCHAR     NOT NULL,
    status              VARCHAR(10) NOT NULL,
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz,
    franchisee_id       BIGINT,
    invoice_link        VARCHAR,
    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)
);

DROP TABLE IF EXISTS staff CASCADE;
CREATE TABLE staff
(
    id                         BIGINT       NOT NULL PRIMARY KEY,
    first_name                 VARCHAR(100) NOT NULL,
    last_name                  VARCHAR(100) NOT NULL,
    email                      VARCHAR(100) NOT NULL,
    password                   CHAR(64)     NOT NULL,
    phone_number               VARCHAR(20)  NOT NULL,
    address                    VARCHAR(200) NOT NULL,
    postcode                   INTEGER      NOT NULL,
    status                     VARCHAR(10)  NOT NULL,
    franchisee_id              BIGINT       NOT NULL,
    role                       VARCHAR(10)  NOT NULL,
    verification_document_link VARCHAR      NOT NULL,
    created_at                 timestamptz  NOT NULL,
    updated_at                 timestamptz,
    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)
);