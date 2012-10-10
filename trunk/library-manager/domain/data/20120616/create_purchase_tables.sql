create table lm_purchase (obj_id int(10), obj_version int(10), create_time datetime, visitor_id int(10), state varchar(20), delivery_method varchar(20), total_price decimal(10,2), net_weight decimal(10,2), discount decimal(10,2), delivery_fee decimal(10,2), comment varchar(500), addr_name varchar(50), addr_loc varchar(500), addr_phone varchar(100), primary key(obj_id));
create table lm_purchase_item (obj_id int(10), create_time datetime, header int(10), book_id int(10), book_count integer(5), primary key(obj_id));
create table lm_purchase_log (obj_id int(10), create_time datetime, operator_id varchar(30), from_state varchar(20), to_state varchar(20), desc_text varchar(500), header int(10), primary key(obj_id));
insert into lm_sequence (name,count) values('lm_purchase',1),('lm_purchase_item',1),('lm_purchase_log',1);