-- structure
create table product (
    id varchar(6) not null,
    name varchar(255) not null,
    price double,
    quantity int not null,
	primary key(id)
);

create table orders (
    id varchar(6) not null,
	product_id varchar(6) not null,
    product_name varchar(255) not null,
    quantity int not null,
	price double,
	total_price double,
	primary key(id)
);

create sequence "seq_product_id"
minvalue 1
maxvalue 999999
increment by 1
start with 1
nocache
nocycle;

create sequence "seq_order_id"
minvalue 1
maxvalue 999999
increment by 1
start with 1
nocache
nocycle;

-- data
insert into product values (lpad(nextval('seq_product_id'), 6, '0'), 'pen', 4000, 10);
insert into product values (lpad(nextval('seq_product_id'), 6, '0'), 'pencil', 3500, 9);
insert into product values (lpad(nextval('seq_product_id'), 6, '0'), 'eraser', 4000, 8);