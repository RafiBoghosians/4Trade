CREATE TABLE category (
	
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45),
	description VARCHAR(255),
	image_url VARCHAR(45),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT INTO category (name, description,image_url,is_active) 
VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) 
VALUES ('Television', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) 
VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);
  

CREATE TABLE user_detail (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(45),
	last_name VARCHAR(45),
	role VARCHAR(45),
	enabled BOOLEAN,
	password VARCHAR(45),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);
   
   
 INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Jon', 'Snow', 'ADMIN', true, 'admin', 'jonsnow@gmail.com', '8888888888');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Tyrion', 'Lannister', 'SUPPLIER', true, 'TyrionLannister', 'tyrionlannister@gmail.com', '9999999999');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Jaime', 'Lannister', 'SUPPLIER', true, 'jaimelannister', 'jaimelannister@gmail.com', '7777777777');

CREATE TABLE product (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(20),
	name VARCHAR(45),
	brand VARCHAR(45),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id)
);	

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123DEFX', 'LG G5', 'lg', 'This is one of the best phone available in the market right now!', 200000, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 150000, 2, true, 3, 3 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 170000, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 2000000, 3, true, 1, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABCXYZDEFX', 'Dell XPS15', 'dell', 'This is one of the best laptop series from dell that can be used!', 1200000, 5, true, 1, 3 );