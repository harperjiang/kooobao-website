CREATE TABLE lm_tran_expire_rec (obj_id int(10) NOT NULL, due_time DATETIME, create_time datetime, return_time DATETIME, active TINYINT(1) default 0, penalty DECIMAL(38), obj_version decimal(10), transaction int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_visitor_actrecord (visitor_id VARCHAR(100) NOT NULL, activation_id VARCHAR(100) NOT NULL, PRIMARY KEY (visitor_id, activation_id));
CREATE TABLE lm_optlog_borrowcount (borrow_count INTEGER, book_id int(10) NOT NULL, PRIMARY KEY (book_id));
CREATE TABLE lm_tran (obj_id int(10) NOT NULL, due_time datetime, create_time datetime, state varchar(20), delivery_mthd VARCHAR(10), obj_version decimal(10), book_id int(10), visitor int(10), addr_phone VARCHAR(20), addr_loc VARCHAR(200), addr_name VARCHAR(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_optlog_searchsum (obj_id int(10) NOT NULL, create_time datetime, keyword VARCHAR(50), search_count INTEGER, PRIMARY KEY (obj_id));
CREATE TABLE lm_visitor_info (obj_id int(10) NOT NULL, first_child_year VARCHAR(5), create_time datetime, born_year VARCHAR(5), last_child_year VARCHAR(5), gender VARCHAR(10), education VARCHAR(20), like_area INTEGER, kid_count INTEGER, PRIMARY KEY (obj_id));
CREATE TABLE lm_tran_fav_rec (obj_id int(10) NOT NULL, create_time datetime, obj_version decimal(10), fav_book int(10), visitor int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_visitor_addr (obj_id int(10) NOT NULL, create_time datetime, phone VARCHAR(20), location VARCHAR(200), name VARCHAR(10), visitor int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_tran_opt (obj_id int(10) NOT NULL, from_state VARCHAR(20), create_time datetime, to_state VARCHAR(20), comment VARCHAR(255), PRIMARY KEY (obj_id));
CREATE TABLE lm_tran_lm_tran_opt (Transaction_obj_id int(10) NOT NULL, operations_obj_id int(10) NOT NULL, PRIMARY KEY (Transaction_obj_id, operations_obj_id));
CREATE TABLE lm_visitor (obj_id int(10) NOT NULL, id VARCHAR(255), create_time datetime, level INTEGER, status VARCHAR(20), deposit DECIMAL(10,2), name VARCHAR(10), obj_version decimal(10), default_addr int(10), info int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_book_tag (book_id int(10) NOT NULL, tag varchar(25));
CREATE TABLE lm_book_content (book_id int(10) NOT NULL, content text);
CREATE TABLE lm_book_stock (obj_id int(10) NOT NULL, create_time datetime, stock_count INTEGER, avail_count INTEGER, obj_version decimal(10), book_id int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_book_category (obj_id int(10) NOT NULL, create_time datetime, name VARCHAR(255), parent int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_book (obj_id int(10) NOT NULL, content text, create_time datetime, picture_url VARCHAR(255), list_price decimal(10,2), name VARCHAR(255), rating INTEGER, category int(10), PRIMARY KEY (obj_id));
CREATE TABLE lm_optlog_search (obj_id int(10) NOT NULL, create_time datetime, keyword VARCHAR(50), PRIMARY KEY (obj_id));
CREATE TABLE lm_book_attr (book_id int(10) NOT NULL, attr varchar(50));
ALTER TABLE lm_tran_expire_rec ADD CONSTRAINT FK_lm_tran_expire_rec_transaction FOREIGN KEY (transaction) REFERENCES lm_tran (obj_id);
ALTER TABLE lm_optlog_borrowcount ADD CONSTRAINT FK_lm_optlog_borrowcount_book_id FOREIGN KEY (book_id) REFERENCES lm_book (obj_id);
ALTER TABLE lm_tran ADD CONSTRAINT FK_lm_tran_visitor FOREIGN KEY (visitor) REFERENCES lm_visitor (obj_id);
ALTER TABLE lm_tran ADD CONSTRAINT FK_lm_tran_book_id FOREIGN KEY (book_id) REFERENCES lm_book (obj_id);
ALTER TABLE lm_tran_fav_rec ADD CONSTRAINT FK_lm_tran_fav_rec_visitor FOREIGN KEY (visitor) REFERENCES lm_visitor (obj_id);
ALTER TABLE lm_tran_fav_rec ADD CONSTRAINT FK_lm_tran_fav_rec_fav_book FOREIGN KEY (fav_book) REFERENCES lm_book (obj_id);
ALTER TABLE lm_visitor_addr ADD CONSTRAINT FK_lm_visitor_addr_visitor FOREIGN KEY (visitor) REFERENCES lm_visitor (obj_id);
ALTER TABLE lm_tran_lm_tran_opt ADD CONSTRAINT FK_lm_tran_lm_tran_opt_operations_obj_id FOREIGN KEY (operations_obj_id) REFERENCES lm_tran_opt (obj_id);
ALTER TABLE lm_tran_lm_tran_opt ADD CONSTRAINT FK_lm_tran_lm_tran_opt_Transaction_obj_id FOREIGN KEY (Transaction_obj_id) REFERENCES lm_tran (obj_id);
ALTER TABLE lm_visitor ADD CONSTRAINT FK_lm_visitor_default_addr FOREIGN KEY (default_addr) REFERENCES lm_visitor_addr (obj_id);
ALTER TABLE lm_visitor ADD CONSTRAINT FK_lm_visitor_info FOREIGN KEY (info) REFERENCES lm_visitor_info (obj_id);
ALTER TABLE lm_book_stock ADD CONSTRAINT FK_lm_book_stock_book_id FOREIGN KEY (book_id) REFERENCES lm_book (obj_id);
ALTER TABLE lm_book_category ADD CONSTRAINT FK_lm_book_category_parent FOREIGN KEY (parent) REFERENCES lm_book_category (obj_id);
ALTER TABLE lm_book ADD CONSTRAINT FK_lm_book_category FOREIGN KEY (category) REFERENCES lm_book_category (obj_id);
CREATE TABLE lm_sequence (name VARCHAR(50) NOT NULL, count DECIMAL(38), PRIMARY KEY (name));
INSERT INTO lm_sequence(name, count) values ('lm_visitor_info', 0);
INSERT INTO lm_sequence(name, count) values ('lm_tran', 0);
INSERT INTO lm_sequence(name, count) values ('lm_book', 0);
INSERT INTO lm_sequence(name, count) values ('lm_book_category', 0);
INSERT INTO lm_sequence(name, count) values ('lm_book_stock', 0);
INSERT INTO lm_sequence(name, count) values ('lm_visitor', 0);
INSERT INTO lm_sequence(name, count) values ('lm_tran_opt', 0);
INSERT INTO lm_sequence(name, count) values ('lm_tran_expire_rec', 0);
INSERT INTO lm_sequence(name, count) values ('lm_address', 0);
INSERT INTO lm_sequence(name, count) values ('lm_tran_fav_rec', 0);
