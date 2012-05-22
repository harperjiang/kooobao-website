CREATE TABLE lm_optlog_borrowcount (borrow_count int(5), book_id int(10) NOT NULL, PRIMARY KEY (book_id));

ALTER TABLE lm_optlog_borrowcount ADD CONSTRAINT FK_lm_book_opt_borrowcount_book_id FOREIGN KEY (book_id) REFERENCES lm_book (obj_id);


CREATE TABLE lm_optlog_search (obj_oid int(10) auto_increment, keyword varchar(30), create_time datetime NOT NULL, PRIMARY KEY (obj_oid));
CREATE TABLE lm_optlog_searchsum (obj_oid int(10) auto_increment, keyword varchar(30), create_time datetime NOT NULL,search_count int(10) not null,PRIMARY KEY (obj_oid));
