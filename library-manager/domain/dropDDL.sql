ALTER TABLE lm_tran_expire_rec DROP FOREIGN KEY FK_lm_tran_expire_rec_transaction
ALTER TABLE lm_optlog_borrowcount DROP FOREIGN KEY FK_lm_optlog_borrowcount_book_id
ALTER TABLE lm_tran DROP FOREIGN KEY FK_lm_tran_visitor
ALTER TABLE lm_tran DROP FOREIGN KEY FK_lm_tran_book_id
ALTER TABLE lm_tran_fav_rec DROP FOREIGN KEY FK_lm_tran_fav_rec_visitor
ALTER TABLE lm_tran_fav_rec DROP FOREIGN KEY FK_lm_tran_fav_rec_fav_book
ALTER TABLE lm_visitor_addr DROP FOREIGN KEY FK_lm_visitor_addr_visitor
ALTER TABLE lm_tran_lm_tran_opt DROP FOREIGN KEY FK_lm_tran_lm_tran_opt_operations_obj_id
ALTER TABLE lm_tran_lm_tran_opt DROP FOREIGN KEY FK_lm_tran_lm_tran_opt_Transaction_obj_id
ALTER TABLE lm_visitor DROP FOREIGN KEY FK_lm_visitor_default_addr
ALTER TABLE lm_visitor DROP FOREIGN KEY FK_lm_visitor_info
ALTER TABLE lm_book_stock DROP FOREIGN KEY FK_lm_book_stock_book_id
ALTER TABLE lm_book_category DROP FOREIGN KEY FK_lm_book_category_parent
ALTER TABLE lm_book DROP FOREIGN KEY FK_lm_book_category
DROP TABLE lm_tran_expire_rec
DROP TABLE lm_visitor_actrecord
DROP TABLE lm_optlog_borrowcount
DROP TABLE lm_tran
DROP TABLE lm_optlog_searchsum
DROP TABLE lm_visitor_info
DROP TABLE lm_tran_fav_rec
DROP TABLE lm_visitor_addr
DROP TABLE lm_tran_opt
DROP TABLE lm_tran_lm_tran_opt
DROP TABLE lm_visitor
DROP TABLE lm_book_tag
DROP TABLE lm_book_content
DROP TABLE lm_book_stock
DROP TABLE lm_book_category
DROP TABLE lm_book
DROP TABLE lm_optlog_search
DROP TABLE lm_book_attr
DELETE FROM lm_sequence WHERE name = 'lm_tran_opt'
DELETE FROM lm_sequence WHERE name = 'lm_visitor_info'
DELETE FROM lm_sequence WHERE name = 'lm_tran_fav_rec'
DELETE FROM lm_sequence WHERE name = 'lm_visitor_addr'
DELETE FROM lm_sequence WHERE name = 'lm_visitor'
DELETE FROM lm_sequence WHERE name = 'lm_tran'
DELETE FROM lm_sequence WHERE name = 'lm_tran_expire_rec'
