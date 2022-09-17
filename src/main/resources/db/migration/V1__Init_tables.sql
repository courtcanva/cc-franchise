DROP TABLE IF EXISTS cc_employee CASCADE;
DROP TYPE IF EXISTS cc_employee_status;
DROP TYPE  IF EXISTS cc_employee_role;

CREATE TYPE cc_employee_status as enum ('INACTIVE','ACTIVE','ARCHIVED');
CREATE TYPE cc_employee_role as enum ('STAFF','ADMIN');

CREATE TABLE cc_employee
(
    id         BIGINT                   NOT NULL PRIMARY KEY,
    email      VARCHAR(100)             NOT NULL,
    password   CHAR(64)                 NOT NULL,
    first_name VARCHAR(100)             NOT NULL,
    last_name  VARCHAR(100)             NOT NULL,
    role       cc_employee_role         NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    status     cc_employee_status       NOT NULL
);

COMMENT ON COLUMN cc_employee.email IS 'Employee''s email as login username';
COMMENT ON COLUMN cc_employee.password IS 'Login password(hashcode)';
COMMENT ON COLUMN cc_employee.first_name IS 'Employee''s first name';
COMMENT ON COLUMN cc_employee.last_name IS 'Employee''s last name';
COMMENT ON COLUMN cc_employee.role IS 'Employee''s role: STAFF(default)/ADMIN';
COMMENT ON COLUMN cc_employee.created_at IS 'Employee account create time, eg:2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN cc_employee.updated_at IS 'Employee account last update time, eg:2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN cc_employee.status IS 'Employee status: INACTIVE(default)/ACTIVE/ARCHIVED';

DROP TABLE IF EXISTS franchisee CASCADE;
DROP TYPE IF EXISTS franchisee_status;

CREATE TYPE franchisee_status as enum ('UNVERIFIED','VERIFIED');

CREATE TABLE franchisee
(
    id            BIGINT                   NOT NULL PRIMARY KEY,
    abn           CHAR(11)                 NOT NULL,
    address       VARCHAR(200)             NOT NULL,
    postcode      INTEGER                  NOT NULL,
    phone_number  VARCHAR(20)              NOT NULL,
    duty_area     VARCHAR                  NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    status        franchisee_status        NOT NULL,
    approved_time TIMESTAMP WITH TIME ZONE,
    approved_by   BIGINT,
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by) REFERENCES cc_employee (id)
);

COMMENT ON COLUMN franchisee.abn IS 'Franchisee''s ABN';
COMMENT ON COLUMN franchisee.address IS 'Franchisee''s address';
COMMENT ON COLUMN franchisee.postcode IS 'Franchisee''s postcode';
COMMENT ON COLUMN franchisee.phone_number IS 'Franchisee''s contact number';
COMMENT ON COLUMN franchisee.duty_area IS 'Franchisee''s duty area, postcode list of franchisee duty area[2017,2000,2032]';
COMMENT ON COLUMN franchisee.created_at IS 'Franchisee account create time with timestamp, eg:2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN franchisee.updated_at IS 'Franchisee account last updated time with timestamp, eg:2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN franchisee.status IS 'Franchisee''s status: UNVERIFIED(default)/VERIFIED';
COMMENT ON COLUMN franchisee.approved_time IS 'Franchisee''s account approved time, eg:2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN franchisee.approved_by IS 'The staff who approved franchisee, eg:2022-09-14T19:10:25.083Z';


DROP TABLE IF EXISTS "order" CASCADE;
DROP TYPE IF EXISTS order_status;

CREATE TYPE order_status as enum ('UNASSIGNED','ACCEPTED','COMPLETED','CANCELED','ASSIGNED');

CREATE TABLE "order"
(
    id                  BIGINT                   NOT NULL PRIMARY KEY,
    order_id            VARCHAR                  NOT NULL,
    customer_id         VARCHAR                  NOT NULL,
    contact_information jsonb                    NOT NULL,
    design_information  jsonb                    NOT NULL,
    postcode            VARCHAR                  NOT NULL,
    total_amount        VARCHAR                  NOT NULL,
    paid_amount         VARCHAR                  NOT NULL,
    unpaid_amount       VARCHAR                  NOT NULL,
    status              order_status             NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    franchisee_id       BIGINT,
    invoice_link        VARCHAR,
    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)
);

COMMENT ON COLUMN "order".order_id IS 'Order''s id which is received from the user side';
COMMENT ON COLUMN "order".customer_id IS 'Customer''id which is received from the user side';
COMMENT ON COLUMN "order".contact_information IS 'The personal contact information of customer (eg. phone number, address, email)';
COMMENT ON COLUMN "order".design_information IS 'Whole description of court design';
COMMENT ON COLUMN "order".postcode IS 'Postcode for the order address';
COMMENT ON COLUMN "order".total_amount IS 'Total price for the order';
COMMENT ON COLUMN "order".paid_amount IS 'Down payment';
COMMENT ON COLUMN "order".unpaid_amount IS 'Rest amount of order price';
COMMENT ON COLUMN "order".status IS 'Order''s status: UNASSIGNED(default)/ACCEPTED/COMPLETED/CANCELED/ASSIGNED';
COMMENT ON COLUMN "order".created_at IS 'Order''s create time with timestamp, eg.2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN "order".updated_at IS 'Last updated time of the order with timestamp, eg.2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN "order".franchisee_id IS 'Franchisee id who has been assigned';
COMMENT ON COLUMN "order".invoice_link IS 'URL link to store invoice in the cloud storage, e.g. AWS S3. The user must be authenticated to provide an access token to access this link.';


DROP TABLE IF EXISTS staff CASCADE;
DROP TYPE IF EXISTS staff_status;
DROP TYPE IF EXISTS staff_role;

CREATE TYPE staff_status as enum ('UNVERIFIED','VERIFIED');
CREATE TYPE staff_role as enum ('STAFF','ADMIN');

CREATE TABLE staff
(
    id                         BIGINT                   NOT NULL PRIMARY KEY,
    first_name                 VARCHAR(100)             NOT NULL,
    last_name                  VARCHAR(100)             NOT NULL,
    email                      VARCHAR(100)             NOT NULL,
    password                   CHAR(64)                 NOT NULL,
    phone_number               VARCHAR(20)              NOT NULL,
    address                    VARCHAR(200)             NOT NULL,
    state                      CHAR(64)                 NOT NULL,
    postcode                   INTEGER                  NOT NULL,
    status                     staff_status             NOT NULL,
    franchisee_id              BIGINT                   NOT NULL,
    role                       staff_role               NOT NULL,
    verification_document_link VARCHAR                  NOT NULL,
    created_at                 TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at                 TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)
);
COMMENT ON COLUMN staff.first_name IS 'Staff''s first name';
COMMENT ON COLUMN staff.last_name IS ' Staff''s last name';
COMMENT ON COLUMN staff.email IS 'Staff''s email as login username';
COMMENT ON COLUMN staff.password IS 'Login password (hashcode)';
COMMENT ON COLUMN staff.phone_number IS 'Staff''s phone number';
COMMENT ON COLUMN staff.address IS 'Staff''s address';
COMMENT ON COLUMN staff.state IS 'Residential state';
COMMENT ON COLUMN staff.postcode IS 'Staff''s postcode';
COMMENT ON COLUMN staff.status IS 'Staff status:UNVERIFIED(default)/VERIFIED';
COMMENT ON COLUMN staff.franchisee_id IS 'The franchisee id that staff work for';
COMMENT ON COLUMN staff.role IS 'Staff''s role :STAFF(default)/ADMIN';
COMMENT ON COLUMN staff.verification_document_link IS 'URL link to store staff''s identity document';
COMMENT ON COLUMN staff.created_at IS 'Staff account create time with timestamp, eg.2022-09-14T19:10:25.083Z';
COMMENT ON COLUMN staff.updated_at IS 'staff account last update time with timestamp, eg.2022-09-14T19:10:25.083Z';
