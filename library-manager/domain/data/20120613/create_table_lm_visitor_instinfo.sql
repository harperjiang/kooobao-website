create table lm_visitor_instinfo(obj_id int(10), create_time datetime,name varchar(30),address varchar(300),contact varchar(50), phone varchar(120), desc_text varchar(500), url varchar(100), primary key (obj_id));

insert into lm_sequence (name, count) values ('lm_visitor_instinfo',1);

alter table lm_visitor add inst_info int(10);