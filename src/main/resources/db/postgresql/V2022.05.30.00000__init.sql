CREATE TABLE IF NOT EXISTS orders (
    id VARCHAR(255) PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    created_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP,
    updated_by VARCHAR(255)
);
