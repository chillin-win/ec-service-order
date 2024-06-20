IF OBJECT_ID(N'dbo.deliveries', N'U') IS NULL
BEGIN
    CREATE TABLE deliveries
    (
        id VARCHAR(255) PRIMARY KEY NOT NULL,
        name VARCHAR(255),
        address VARCHAR(255),
        created_at DATETIME2(3),
        created_by VARCHAR(255),
        updated_at DATETIME2(3),
        updated_by VARCHAR(255)
    );
END;
