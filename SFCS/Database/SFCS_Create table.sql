-- ----------------------------------------------------------------------------
-- MySQL Script
-- Schemata: SFCS
-- Created by: Le Trung Son
-- Created: Tue Nov 10 11:03:18 2020
-- Latest Edited: Tue Nov 10 11:03:18 2020
-- Workbench Version: 8.0.20
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema SFCS
-- ----------------------------------------------------------------------------

DROP DATABASE IF EXISTS SFCS;
CREATE DATABASE SFCS;
USE SFCS;

-- ----------------------------------------------------------------------------
-- Table SFCS.roles
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.roles (
	role_id int NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL,
    PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO roles
VALUES 
	(1, 'customer'), (2, 'admin'), (3, 'cook'), (4, 'vendor'), (5, 'manager');

-- ----------------------------------------------------------------------------
-- Table SFCS.users
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.users (
	user_id int NOT NULL AUTO_INCREMENT,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	birth_date date DEFAULT NULL,
    gender enum('M','F') DEFAULT NULL,
	phone varchar(50) DEFAULT NULL UNIQUE,
	email varchar(50) DEFAULT NULL UNIQUE,
	address varchar(2000) DEFAULT NULL,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	avatar varchar(50) DEFAULT NULL,
	role_id int NOT NULL DEFAULT '1', /* customer */
	balance int DEFAULT '0',
	PRIMARY KEY (user_id),
	UNIQUE INDEX UQ_username (username ASC) VISIBLE, /* ASC xếp tăng dần, UNIQUE username là giá trị duy nhât không trùng lặp */
    KEY fk_users_roles_idx (role_id),
    CONSTRAINT fk_users_roles
		FOREIGN KEY (role_id) REFERENCES SFCS.roles (role_id) ON UPDATE CASCADE,
	CONSTRAINT CHK_User CHECK (balance >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (first_name, last_name, username, password, role_id) 
VALUES 
	('admin', 'admin', 'admin', '123456', 2);

-- ----------------------------------------------------------------------------
-- Table SFCS.stalls
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.stalls (
	stall_id int NOT NULL AUTO_INCREMENT,
	stall_name varchar(255) NOT NULL,
    item_quantity int NOT NULL DEFAULT '0',
	description varchar(2000) DEFAULT NULL,
	image mediumblob DEFAULT NULL,
	PRIMARY KEY (stall_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*INSERT INTO stalls (stall_name, image) 
VALUES 
    ('Trà Phúc Long', LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/phuclong.png'));
    
INSERT INTO stalls (stall_name, image) 
VALUES 
    ('Trà Phúc Long', LOAD_FILE('D:/phuclong.png'));*/
    
INSERT INTO stalls (stall_name) 
VALUES 
	('Cơm Nguyên Ký'), 
    ('Phở 10 Lý Quốc Sư'),
    ('Hoàng Yến Cuisine'),
    ('KFC'),
    ('Pizza Hut'),
    ("McDonald's"),
    ('Hotto'),
    ('Hanuri'),
    ('Tous Les Jours'),
    ('The Royal Tea'),
    ('Phúc Long Coffee & Tea'), 
    ('Trà sữa Toco Toco');
    
-- ----------------------------------------------------------------------------
-- Table SFCS.categories
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.categories (
	category_id int NOT NULL AUTO_INCREMENT,
	category_name varchar(50) NOT NULL,
	PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO categories
VALUES (1, 'Ẩm thực Việt'), (2, 'Thức ăn nhanh'), (3, 'Lẩu & Nướng'), (4, 'Món tráng miệng'), (5, 'Thức uống'), (6, 'Món Khác');

-- ----------------------------------------------------------------------------
-- Table SFCS.products
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.products (
	product_id int NOT NULL AUTO_INCREMENT,
	product_name varchar(255) NOT NULL,
	price int NOT NULL DEFAULT '0',
	quantity int NOT NULL DEFAULT '0',
	discount float DEFAULT '0',
	category_id int NOT NULL,
	stall_id int NOT NULL,
	product_status_id int NOT NULL DEFAULT '1', /* Còn hàng */
	description varchar(2000) DEFAULT NULL,
	image mediumblob DEFAULT NULL,
	PRIMARY KEY (product_id),
	KEY fk_products_categories_idx (category_id),
	KEY fk_products_stalls_idx (stall_id),
	KEY fk_products_product_statuses_idx (product_status_id),
	CONSTRAINT fk_products_product_statuses
		FOREIGN KEY (product_status_id) REFERENCES SFCS.product_statuses (product_status_id) ON UPDATE CASCADE,
	CONSTRAINT fk_products_categories
		FOREIGN KEY (category_id) REFERENCES SFCS.categories (category_id) ON UPDATE CASCADE,
	CONSTRAINT fk_products_stalls
		FOREIGN KEY (stall_id) REFERENCES SFCS.stalls (stall_id) ON UPDATE CASCADE,
	CONSTRAINT CHK_Product CHECK (price >= 0 AND quantity >= 0 AND discount >=0 AND discount <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO products (product_name, price, quantity, discount, category_id, stall_id) 
VALUES 
	('Phở Bò Tái Chín', 60000, 50, 20, 1, 2),
	('Cơm Gà Xối Mỡ', 25000, 40, DEFAULT, 1, 1),
    ('Kimbap', 35000, 40, DEFAULT, 6, 8),
    ('Lẩu Cua Cà Ri', 73000, 20, 10, 3, 3),
    ('Bò Ba Chỉ Với Trứng', 99000, 30, 25, 6, 7),
    ('Combo Gà Giòn Cay', 81000, 30, 10, 2, 4),
    ('Pizza Hải Sản', 53000, 35, 15, 2, 5),
    ('Burger Bò Phô Mai', 40000, 60, DEFAULT, 2, 6),
    ('Bánh Crepe Chuối', 39000, 35, DEFAULT, 4, 9),
    ('Trà Đào Cam Sả', 45000, 40, DEFAULT, 5, 10),
    ('Trà Sữa Phúc Long (Lạnh)', 45000, 60, DEFAULT, 5, 11),
    ('Sữa Tươi Trân Châu Đường Hổ', 49000, 45, 28, 5, 12);

-- ----------------------------------------------------------------------------
-- Table SFCS.product_statuses
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.product_statuses (
  product_status_id int NOT NULL AUTO_INCREMENT,
  product_status_name varchar(50) NOT NULL,
  PRIMARY KEY (product_status_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO product_statuses
VALUES 
	(1, 'Còn hàng'), (2, 'Hết hàng'), (3, 'Ngừng kinh doanh'), (4, 'Tạm ngừng bán');

-- ----------------------------------------------------------------------------
-- Table SFCS.order_statuses
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.order_statuses (
  order_status_id int NOT NULL AUTO_INCREMENT,
  order_status_name varchar(50) NOT NULL,
  PRIMARY KEY (order_status_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO order_statuses
VALUES (1, 'Đang Xử Lý'), (2, 'Đã Sẵn Sàng'), (3, 'Hết Hàng'), (4, 'Đã Nhận Hàng');

-- ----------------------------------------------------------------------------
-- Table SFCS.orders
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.orders (
	order_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	order_time time NOT NULL,
	order_date date NOT NULL,
	order_status_id int NOT NULL DEFAULT '1', /* Đang Xử Lý */
	PRIMARY KEY (order_id),
    KEY fk_orders_users_idx (user_id),
    KEY fk_orders_order_statuses_idx (order_status_id),
	CONSTRAINT fk_orders_users
		FOREIGN KEY (user_id) REFERENCES SFCS.users (user_id) ON UPDATE CASCADE,
	CONSTRAINT fk_orders_order_statuses 
		FOREIGN KEY (order_status_id) REFERENCES SFCS.order_statuses (order_status_id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table SFCS.order_items
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.order_items (
	order_id int NOT NULL,
	product_id int NOT NULL,
	quantity int NOT NULL DEFAULT '0',
	unit_price int NOT NULL DEFAULT '0',
	PRIMARY KEY (order_id, product_id),
	KEY fk_order_items_orders_idx (order_id),
    KEY fk_order_items_products_idx (product_id),
	CONSTRAINT fk_order_items_orders 
		FOREIGN KEY (order_id) REFERENCES SFCS.orders (order_id) ON UPDATE CASCADE,
	CONSTRAINT fk_order_items_products 
		FOREIGN KEY (product_id) REFERENCES SFCS.products (product_id) ON UPDATE CASCADE,
	CONSTRAINT CHK_Order_item CHECK (unit_price >= 0 AND quantity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table SFCS.invoices
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.invoices (
	invoice_id int NOT NULL AUTO_INCREMENT,
	order_id int NOT NULL UNIQUE,
	invoice_total int NOT NULL DEFAULT '0',
	invoice_date date NOT NULL,
	invoice_time time NOT NULL,
	code varchar(20) DEFAULT NULL,
	payment_id int NOT NULL DEFAULT '1', /* Tiền mặt */
	PRIMARY KEY (invoice_id),
	KEY fk_invoices_orders_idx (order_id),
	KEY fk_invoices_payments_idx (payment_id),
	KEY fk_invoices_vouchers_codex (code),
	CONSTRAINT fk_invoices_orders
		FOREIGN KEY (order_id) REFERENCES SFCS.orders (order_id) ON UPDATE CASCADE,
	CONSTRAINT fk_invoices_payments
		FOREIGN KEY (payment_id) REFERENCES SFCS.payments (payment_id) ON UPDATE CASCADE,
	CONSTRAINT fk_invoices_vouchers
		FOREIGN KEY (code) REFERENCES SFCS.vouchers (code) ON UPDATE CASCADE,
	CONSTRAINT CHK_Invoice CHECK (invoice_total >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
-- ----------------------------------------------------------------------------
-- Table SFCS.payments
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.payments (
  payment_id int NOT NULL AUTO_INCREMENT,
  payment_method varchar(50) NOT NULL,
  PRIMARY KEY (payment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO payments
VALUES (1, 'Tài Khoản SFCS'), (2, 'Ví Momo'), (3, 'Tiền mặt');

-- ----------------------------------------------------------------------------
-- Table SFCS.vouchers
-- ----------------------------------------------------------------------------

CREATE TABLE SFCS.vouchers (
	code varchar(20) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	discount float NOT NULL DEFAULT '0',
	quantity int NOT NULL DEFAULT '0',
	max_value int NOT NULL DEFAULT '0',
	PRIMARY KEY (code),
	CONSTRAINT CHK_Voucher CHECK (max_value >= 0 AND quantity >= 0 AND discount >=0 AND discount <= 100 AND start_date <= end_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO vouchers
VALUES ('WELCOMESFCS', '2020-11-10', '2020-11-15', '50', '100', '200000');

SET FOREIGN_KEY_CHECKS = 1;