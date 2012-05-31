create table lm_article_news (obj_id int(10) not null, create_time datetime, title varchar(100),content text,primary key(obj_id));

insert into lm_sequence(name,count) values('lm_article_news',0);