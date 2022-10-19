ALTER TABLE staff
    ADD status VARCHAR(10),
    ADD verification_token VARCHAR(32),
    ADD verification_token_created_at TIMESTAMP WITH TIME ZONE;