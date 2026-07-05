CREATE TABLE IF NOT EXISTS meal_entry (
                                          id BIGSERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
    count_carbs DOUBLE PRECISION,
    count_fat DOUBLE PRECISION,
    count_proteins DOUBLE PRECISION,
    favorite BOOLEAN NOT NULL DEFAULT FALSE,
    owner VARCHAR(255)
    );