BEGIN;

DO $$
BEGIN
    IF current_database() != 'supermarket_java' THEN
        RAISE EXCEPTION 'Wrong database! Expected "supermarket_java", but connected to "%"', current_database();
END IF;
END $$;

-- INSTRUCTIONS:
-- copy this file into a new migration file
-- modify the values of my.previous and my.current
-- put your migration in between the big commented blocks
SET my.previous = '20260101_0_CreateProductRelatedTables'; -- change this
SET my.current  = '20260516_0_DropUserTable'; -- filename

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


DROP TABLE Users;

-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
-- YOUR MIGRATION CODE GOES ABOVE THIS LINE
-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

-- update database version
INSERT INTO database_version (current_version, previous_version)
VALUES (current_setting('my.current')::VARCHAR(50), current_setting('my.previous')::VARCHAR(50));


COMMIT;