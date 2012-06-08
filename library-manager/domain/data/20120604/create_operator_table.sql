create table lm_operator (obj_id int(10) not null, create_time datetime, id varchar(30) not null, primary key(obj_id));
create unique index lm_operator_id on lm_operator(id);

alter table lm_tran_opt add operator_id varchar(30);