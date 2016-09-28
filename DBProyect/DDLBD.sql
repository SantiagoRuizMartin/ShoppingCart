/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     18/07/2016 9:30:29 p. m.                     */
/*==============================================================*/


drop table if exists AVAILABLE_PRODUCT;

drop table if exists CLIENT_ORDER;

drop table if exists CUSTOMER;

drop table if exists EXCHANGE_RATE;

drop table if exists ORDER_DETAIL;

drop table if exists PRODUCT;

/*==============================================================*/
/* Table: AVAILABLE_PRODUCT                                     */
/*==============================================================*/
create table AVAILABLE_PRODUCT
(
   CUSTOMER_ID          int not null,
   PRODUCT_ID           int not null
);

/*==============================================================*/
/* Table: CLIENT_ORDER                                          */
/*==============================================================*/
create table CLIENT_ORDER
(
   ORDER_ID             int not null auto_increment,
   CUSTOMER_ID          int not null,
   DELIVERY_ADDRESS     char(255),
   EXCHANGE_RATE        decimal(10,5) not null,
   CREATION_DATE        datetime not null,
   primary key (ORDER_ID)
);

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
create table CUSTOMER
(
   CUSTOMER_ID          int not null auto_increment,
   NAME                 char(255) not null,
   EMAIL                char(255) not null,
   primary key (CUSTOMER_ID)
);

/*==============================================================*/
/* Table: EXCHANGE_RATE                                         */
/*==============================================================*/
create table EXCHANGE_RATE
(
   EXCHANGE_RATE        decimal(10,4) not null,
   EXCHANGE_DATE        datetime not null
);

/*==============================================================*/
/* Table: ORDER_DETAIL                                          */
/*==============================================================*/
create table ORDER_DETAIL
(
   ORDER_ID             int not null,
   PRODUCT_ID           int not null,
   PRODUCT_DESCRIPTION  char(255),
   QUANTITY             int not null
);

/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
create table PRODUCT
(
   PRODUCT_ID           int not null auto_increment,
   NAME                 char(255) not null,
   PRICE                decimal(10,5) not null,
   DESCRIPTION          char(255),
   primary key (PRODUCT_ID)
);

alter table AVAILABLE_PRODUCT add constraint FK_RELATIONSHIP_3 foreign key (CUSTOMER_ID)
      references CUSTOMER (CUSTOMER_ID) on delete restrict on update restrict;

alter table AVAILABLE_PRODUCT add constraint FK_RELATIONSHIP_4 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table CLIENT_ORDER add constraint FK_RELATIONSHIP_1 foreign key (CUSTOMER_ID)
      references CUSTOMER (CUSTOMER_ID) on delete restrict on update restrict;

alter table ORDER_DETAIL add constraint FK_RELATIONSHIP_2 foreign key (ORDER_ID)
      references CLIENT_ORDER (ORDER_ID) on delete restrict on update restrict;

alter table ORDER_DETAIL add constraint FK_RELATIONSHIP_5 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

