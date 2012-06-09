create table lm_tran_comment(obj_id int(10), tran_id int(10),create_time datetime, content text,primary key(obj_id));
insert into lm_sequence (name,count) values('lm_tran_comment',0);
alter table lm_tran_comment add operator_id varchar(30);