DROP DATABASE IF EXISTS pos_aad;

CREATE DATABASE IF NOT EXISTS  pos_aad;

SHOW DATABASES;

USE pos_aad;

create table customer(
                         id VARCHAR(15) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         contact INT NOT NULL
);

create table item(
                     code VARCHAR(15) PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     qty INT NOT NULL,
                     price DECIMAL(10,2) NOT NULL
);

create table orders(
                       order_id VARCHAR(15) PRIMARY KEY,
                       date DATE NOT NULL,
                       cus_id VARCHAR(15) NOT NULL,
                       total DECIMAL(10,2) NOT NULL,
                       CONSTRAINT FOREIGN KEY (cus_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE order_details (
                               order_id VARCHAR(15) NOT NULL,
                               item_code VARCHAR(15) NOT NULL,
                               unit_price DECIMAL(10, 2) NOT NULL,
                               qty INT,
                               PRIMARY KEY (order_id, item_code),
                               CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT fk_item FOREIGN KEY (item_code) REFERENCES item(code) ON DELETE CASCADE ON UPDATE CASCADE
);
