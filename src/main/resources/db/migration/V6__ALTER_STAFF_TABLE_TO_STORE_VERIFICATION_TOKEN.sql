ALTER TABLE staff
    ADD status VARCHAR(10),
    ADD verification_token CHAR(36),
    ADD verification_token_created_at TIMESTAMP WITH TIME ZONE;