IF OBJECT_ID(N'dbo.scheduled_job', N'U') IS NULL
BEGIN
    CREATE TABLE scheduled_job
    (
        id VARCHAR(255) PRIMARY KEY NOT NULL,
        name VARCHAR(255),
        description VARCHAR(255),
        url TEXT,
        method VARCHAR(255),
        header TEXT,
        body TEXT,
        cron VARCHAR(255)
    );
END;
