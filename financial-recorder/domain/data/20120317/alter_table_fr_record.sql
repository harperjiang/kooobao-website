update fr_record set adjust_amount = 0 where adjust_amount is null;
alter table fr_record modify column adjust_amount decimal(10,2) not null default 0;