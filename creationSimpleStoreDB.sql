
/* primero crear las tablas luego ejecutar los procedimientos almacenados
/*==============================================================*/
/* Table: Creation                                              */
/*==============================================================*/

/* EJECUTAR COMO ROOT DE LA BASE DE DATOS */
DROP USER IF EXISTS 'userstore'@'localhost' ;
create user userstore@localhost identified by 'u$3r$t0r3';
DROP DATABASE IF EXISTS simplestoredba;
create database simplestoredba;
GRANT ALL PRIVILEGES ON simplestoredba.* TO 'userstore'@'localhost';
GRANT SELECT ON mysql.proc TO 'userstore'@'localhost';

USE simplestoredba;

drop table if exists available_product;

drop table if exists client_order;

drop table if exists customer;

drop table if exists exchange_rate;

drop table if exists order_detail;

drop table if exists product;

/*==============================================================*/
/* Table: available_product                                     */
/*==============================================================*/
create table available_product
(
   CUSTOMER_ID          int not null,
   PRODUCT_ID           int not null
);

/*==============================================================*/
/* Table: client_order                                          */
/*==============================================================*/
create table client_order
(
   ORDER_ID             int not null auto_increment,
   CUSTOMER_ID          int not null,
   DELIVERY_ADDRESS     char(255),
   EXCHANGE_RATE        decimal(10,5) not null,
   CREATION_DATE        datetime not null,
   primary key (ORDER_ID)
);

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   CUSTOMER_ID          int not null auto_increment,
   NAME                 char(255) not null,
   EMAIL                char(255) not null,
   primary key (CUSTOMER_ID)
);

/*==============================================================*/
/* Table: EXCHANGE_RATE                                         */
/*==============================================================*/
create table exchange_rate
(
   EXCHANGE_RATE        decimal(10,4) not null,
   EXCHANGE_DATE        datetime not null
);

/*==============================================================*/
/* Table: ORDER_DETAIL                                          */
/*==============================================================*/
create table order_detail
(
   ORDER_ID             int not null,
   PRODUCT_ID           int not null,
   PRODUCT_DESCRIPTION  char(255),
   QUANTITY             int not null
);

/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
create table product
(
   PRODUCT_ID           int not null auto_increment,
   NAME                 char(255) not null,
   PRICE                decimal(10,5) not null,
   DESCRIPTION          char(255),
   primary key (PRODUCT_ID)
);

alter table available_product add constraint FK_RELATIONSHIP_3 foreign key (CUSTOMER_ID)
      references customer (CUSTOMER_ID) on delete restrict on update restrict;

alter table available_product add constraint FK_RELATIONSHIP_4 foreign key (PRODUCT_ID)
      references product (PRODUCT_ID) on delete restrict on update restrict;

alter table client_order add constraint FK_RELATIONSHIP_1 foreign key (CUSTOMER_ID)
      references customer (CUSTOMER_ID) on delete restrict on update restrict;

alter table order_detail add constraint FK_RELATIONSHIP_2 foreign key (ORDER_ID)
      references client_order (ORDER_ID) on delete restrict on update restrict;

alter table order_detail add constraint FK_RELATIONSHIP_5 foreign key (PRODUCT_ID)
      references product (PRODUCT_ID) on delete restrict on update restrict;



/*=========================================================================================*/
/* THere we are to create all the necesary procedures to be implemented by the services    */
/*=========================================================================================*/

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`userstore`@`localhost` PROCEDURE `DELETE_ALL_THE_AVAILABLE_PRODUCTS_BY_CUSTOMER_ID` (IN `customerId` INT)  MODIFIES SQL DATA
DELETE FROM available_product where CUSTOMER_iD = customerId$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_CUSTOMERS` ()  READS SQL DATA
SELECT* FROM customer$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_CUSTOMER_BY_ID` (IN `custumerId` INT)  READS SQL DATA
SELECT * FROM customer WHERE customer.CUSTOMER_ID=custumerId$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_ORDERS_BY_DATE` (IN `initialDate` DATE, IN `finalDate` DATE, IN `clientId` INT)  READS SQL DATA
SELECT * FROM client_order WHERE client_order.CUSTOMER_ID=clientId and client_order.CREATION_DATE BETWEEN initialDate AND finalDate$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_ORDER_DETAILS` (IN `orderDetail` INT)  READS SQL DATA
SELECT * FROM order_detail WHERE order_detail.ORDER_ID = orderDetail$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_PRODUCT` (IN `productId` INT)  READS SQL DATA
SELECT * from product WHERE product.PRODUCT_ID=productId$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_PRODUCTS` ()  READS SQL DATA
SELECT * FROM product$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_PRODUCTS_BY_CUSTOMER` (IN `customerId` INT)  READS SQL DATA
SELECT * FROM product WHERE product.PRODUCT_ID IN (
SELECT PRODUCT_ID FROM available_product WHERE available_product.CUSTOMER_ID = customerId)$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `GET_RATE` ()  READS SQL DATA
SELECT exchange_rate.EXCHANGE_RATE from exchange_rate ORDER BY EXCHANGE_DATE DESC LIMIT 1$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `SET_AVAILABLE_PRODUCT` (IN `customerId` INT, IN `productId` INT)  MODIFIES SQL DATA
INSERT INTO available_product (CUSTOMER_ID, PRODUCT_ID) VALUES (customerId, productId)$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `SET_CUSTOMER_ORDER` (IN `customerId` INT, IN `exangeRate` DOUBLE, IN `deliveryAddres` VARCHAR(255) CHARSET utf8, INOUT `orderId` INT)  MODIFIES SQL DATA
BEGIN
INSERT INTO client_order (CUSTOMER_ID,DELIVERY_ADDRESS,EXCHANGE_RATE,CREATION_DATE) VALUES (customerId,deliveryAddres,exangeRate,NOW()); 
SET orderId = LAST_INSERT_ID();
END$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `SET_ORDER_DETAILS` (IN `productId` INT, IN `quantity` INT, IN `orderId` INT)  MODIFIES SQL DATA
INSERT INTO order_detail (ORDER_ID,PRODUCT_ID,QUANTITY) VALUES(orderId,productId,quantity)$$

CREATE DEFINER=`userstore`@`localhost` PROCEDURE `SET_RATE` (IN `RATE` DECIMAL(10,5))  MODIFIES SQL DATA
INSERT INTO exchange_rate (EXCHANGE_RATE,EXCHANGE_DATE) VALUES
(RATE, NOW())$$

DELIMITER ;
