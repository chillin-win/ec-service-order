SELECT * FROM entity_type;
INSERT INTO public.entity_type (entity_type, description, status) VALUES ('B', 'Buyer', 'Active');
INSERT INTO public.entity_type (entity_type, description, status) VALUES ('S', 'Seller', 'Active');

SELECT * FROM entity;
INSERT INTO public.entity (name, entity_type, status) VALUES ('cuongl_buyer', 'B', 'Active');
INSERT INTO public.entity (name, entity_type, status) VALUES ('cuongl_seller', 'S', 'Active');

SELECT * FROM product;
INSERT INTO public.product (product_name, entity_id, quantity, price, status) VALUES ('Iphone 12', 2, 50, 21500000.000000, 'Active');
INSERT INTO public.product (product_name, entity_id, quantity, price, status) VALUES ('Iphone 13', 2, 100, 30500000.000000, 'Active');
INSERT INTO public.product (product_name, entity_id, quantity, price, status) VALUES ('Iphone 14', 2, 150, 32500000.000000, 'Active');

SELECT * FROM orders;
INSERT INTO public.orders (name, entity_id, created_at, created_by, updated_at, updated_by, notation, status) VALUES ('order 1', 1, '2024-08-20 15:13:26.000000', null, null, null, null, 'WFP');
INSERT INTO public.orders (name, entity_id, created_at, created_by, updated_at, updated_by, notation, status) VALUES ('order 2', 1, '2024-08-20 15:15:26.000000', null, null, null, null, 'WFP');
INSERT INTO public.orders (name, entity_id, created_at, created_by, updated_at, updated_by, notation, status) VALUES ('order 3', 1, '2024-08-20 15:15:28.000000', null, null, null, null, 'WFP');

SELECT * FROM order_item;
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (1, 1, 2, 48500000.000000, 'WFP');
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (2, 2, 1, 32500000.000000, 'WFP');
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (2, 2, 1, 32500000.000000, 'WFP');
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (3, 3, 5, 17500000.000000, 'WFP');
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (3, 3, 5, 17500000.000000, 'WFP');
INSERT INTO public.order_item (order_id, product_id, quantity, price, status) VALUES (3, 3, 5, 17500000.000000, 'WFP');

