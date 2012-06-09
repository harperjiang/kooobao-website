alter table fr_record add create_time datetime;

alter table fr_file_storage add create_time datetime;
update fr_record set create_time = create_date;

alter table fr_customer add create_time datetime;
update fr_customer set create_time = create_date;
alter table fr_customer drop column create_date;

alter table fr_record drop column create_date;
alter table fr_record_history add create_time datetime;

alter table fr_attachment add create_time datetime;
update fr_attachment set create_time = create_date;
alter table fr_attachment drop column create_date;