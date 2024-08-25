IF OBJECT_ID(N'dbo.orders', N'U') IS NULL
BEGIN
    CREATE TABLE orders
    (
        order_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
        name       VARCHAR(60),
        entity_id       INT,
        created_at DATETIME2(3),
        created_by VARCHAR(255),
        updated_at DATETIME2(3),
        updated_by VARCHAR(255),
        notation VARCHAR(255),
        status VARCHAR(60)
    );
END;
