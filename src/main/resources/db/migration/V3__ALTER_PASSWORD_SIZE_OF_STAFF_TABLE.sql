ALTER TABLE staff
    ALTER COLUMN password TYPE CHAR(60) USING (password::CHAR(60));