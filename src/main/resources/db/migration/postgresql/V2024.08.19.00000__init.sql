CREATE TABLE IF NOT EXISTS entity_type (
    entity_type CHAR PRIMARY KEY,
    description VARCHAR(60),
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS entity (
    entity_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(60),
    entity_type CHAR,
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS orders (
    order_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(60),
    entity_id INTEGER,
    created_at TIMESTAMP,
    created_by VARCHAR(60),
    updated_at TIMESTAMP,
    updated_by VARCHAR(60),
    notation VARCHAR(255),
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS product (
    product_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_name TEXT,
    entity_id INTEGER,
    quantity BIGINT,
    price NUMERIC(18,6),
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS order_item (
    order_item_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id INTEGER,
    product_id INTEGER,
    quantity BIGINT,
    price NUMERIC(18,6),
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS delivery (
    delivery_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    entity_id INTEGER,
    address TEXT,
    order_id INTEGER,
    created_at TIMESTAMP,
    created_by VARCHAR(60),
    updated_at TIMESTAMP,
    updated_by VARCHAR(60),
    status VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS scheduled_job (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    url TEXT,
    method VARCHAR(255),
    header TEXT,
    body TEXT,
    cron VARCHAR(255)
);

-- Add constraints, foreign key
ALTER TABLE entity ADD CONSTRAINT fk_entity_entitytype FOREIGN KEY (entity_type) REFERENCES entity_type(entity_type);
CREATE INDEX idx_entity_entitytype ON entity(entity_type);

ALTER TABLE product ADD CONSTRAINT fk_product_entityid FOREIGN KEY (entity_id) REFERENCES entity(entity_id);
CREATE INDEX idx_product_entityid ON product(entity_id);

ALTER TABLE order_item ADD CONSTRAINT fk_orderitem_orderid FOREIGN KEY (order_id) REFERENCES orders(order_id);
CREATE INDEX idx_orderitem_orderid ON order_item(order_id);

ALTER TABLE order_item ADD CONSTRAINT fk_orderitem_productid FOREIGN KEY (product_id) REFERENCES product(product_id);
CREATE INDEX idx_orderitem_productid ON order_item(product_id);

ALTER TABLE delivery ADD CONSTRAINT fk_delivery_entityid FOREIGN KEY (entity_id) REFERENCES entity(entity_id);
CREATE INDEX idx_delivery_entityid ON delivery(entity_id);

ALTER TABLE delivery ADD CONSTRAINT fk_delivery_orderid FOREIGN KEY (order_id) REFERENCES orders(order_id);
CREATE INDEX idx_delivery_orderid ON delivery(order_id);
