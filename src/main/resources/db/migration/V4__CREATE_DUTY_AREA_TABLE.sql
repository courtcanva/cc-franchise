DROP TABLE IF EXISTS duty_area CASCADE;

CREATE TABLE duty_area
(
    franchisee_id BIGINT NOT NULL,
    ssc_code      BIGINT NOT NULL,
    PRIMARY KEY (franchisee_id, ssc_code),
    CONSTRAINT fk_scc_code FOREIGN KEY (ssc_code) REFERENCES suburb (ssc_code),
    CONSTRAINT fk_franchisee_id FOREIGN KEY (franchisee_id) REFERENCES franchisee (id)
)