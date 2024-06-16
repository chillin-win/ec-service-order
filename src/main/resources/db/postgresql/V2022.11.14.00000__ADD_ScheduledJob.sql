CREATE TABLE IF NOT EXISTS scheduled_job (
    id VARCHAR(255) PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    url TEXT,
    method VARCHAR(255),
    header TEXT,
    body TEXT,
    cron VARCHAR(255)
);
