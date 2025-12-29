BEGIN;

-- INSTRUCTIONS:
-- copy this file into a new migration file
-- modify the values of my.previous and my.current
-- put your migration in between the big commented blocks
SET my.previous = '20251229_0_PrepareDatabaseVersion'; -- change this
SET my.current  = '20251229_1_CreateUserTable'; -- filename

-- sanity check and validation
DO $$
DECLARE
    latest_current_version VARCHAR(50);
BEGIN
    SELECT current_version INTO latest_current_version
    FROM database_version
    ORDER BY id DESC LIMIT 1;

    IF latest_current_version != current_setting('my.previous') THEN
            RAISE EXCEPTION 'Migration version mismatch: expected %, got %',
                current_setting('my.previous'),
                latest_current_version;
    END IF;
END $$;


-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
-- YOUR MIGRATION CODE GOES BELOW THIS LINE
-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv


CREATE TABLE users
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    birth_date DATE,
    last_login TIMESTAMP,
    created_on TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- YOUR MIGRATION CODE GOES ABOVE THIS LINE
-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

-- update database version
INSERT INTO database_version (current_version, previous_version)
VALUES (current_setting('my.current')::VARCHAR(50), current_setting('my.previous')::VARCHAR(50));


COMMIT;