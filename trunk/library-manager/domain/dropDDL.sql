ALTER TABLE lm_visitor DROP FOREIGN KEY FK_lm_visitor_default_addr
ALTER TABLE lm_visitor DROP FOREIGN KEY FK_lm_visitor_info
ALTER TABLE lm_book_stock DROP FOREIGN KEY FK_lm_book_stock_book_id
ALTER TABLE lm_book_category DROP FOREIGN KEY FK_lm_book_category_parent
ALTER TABLE lm_book_opt_borrowcount DROP FOREIGN KEY FK_lm_book_opt_borrowcount_book_id
ALTER TABLE lm_book DROP FOREIGN KEY FK_lm_book_category
ALTER TABLE lm_visitor_addr DROP FOREIGN KEY FK_lm_visitor_addr_visitor
DROP TABLE lm_visitor
DROP TABLE lm_visitor_actrecord
DROP TABLE lm_book_tag
DROP TABLE lm_book_stock
DROP TABLE lm_book_category
DROP TABLE lm_book_opt_borrowcount
DROP TABLE lm_book
DROP TABLE lm_visitor_info
DROP TABLE lm_book_attr
DROP TABLE lm_visitor_addr
DELETE FROM lm_sequence WHERE name = 'lm_visitor'
DELETE FROM lm_sequence WHERE name = 'lm_visitor_addr'
DELETE FROM lm_sequence WHERE name = 'lm_visitor_info'
