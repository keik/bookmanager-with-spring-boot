INSERT INTO item (name) VALUES ('Spring3入門');
INSERT INTO item (name) VALUES ('計算機プログラムの構造と解釈');
INSERT INTO item (name) VALUES ('軽快なJava');
INSERT INTO item (name) VALUES ('Javaエンジニア養成読本');
INSERT INTO item (name) VALUES ('Java: The Good Parts');
INSERT INTO item (name) VALUES ('JavaScript 第6版');
INSERT INTO item (name) VALUES ('Land of Lisp');
INSERT INTO item (name) VALUES ('サーバサイドJavaScript　Node.js入門');
INSERT INTO item (name) VALUES ('Redis入門 インメモリKVSによる高速データ管理');
INSERT INTO item (name) VALUES ('フルスクラッチから1日でCMSを作る シェルスクリプト高速開発手法入門');
INSERT INTO item (name) VALUES ('増補改訂版Java言語で学ぶデザインパターン入門');
INSERT INTO item (name) VALUES ('JUnit実践入門 ~体系的に学ぶユニットテストの技法');

INSERT INTO book (id, author, publisher) VALUES ( 1, '長谷川 裕一', '技術評論社');
INSERT INTO book (id, author, publisher) VALUES ( 2, 'ジェラルド・ジェイ・サスマン', 'ピアソン');
INSERT INTO book (id, author, publisher) VALUES ( 3, 'ブルース・A. テイト', 'オライリージャパン');
INSERT INTO book (id, author, publisher) VALUES ( 4, 'きしだ なおき', '技術評論社');
INSERT INTO book (id, author, publisher) VALUES ( 5, 'Jim Waldo', 'オライリージャパン');
INSERT INTO book (id, author, publisher) VALUES ( 6, 'David Flanagan', 'オライリージャパン');
INSERT INTO book (id, author, publisher) VALUES ( 7, 'Conrad Barski, M.D.', 'O''Reilly Media');
INSERT INTO book (id, author, publisher) VALUES ( 8, '清水 俊博', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO book (id, author, publisher) VALUES ( 9, 'Josiah L.Carlson', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO book (id, author, publisher) VALUES (10, '上田 隆一', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO book (id, author, publisher) VALUES (11, '結城 浩', 'ソフトバンククリエイティブ');
INSERT INTO book (id, author, publisher) VALUES (12, '渡辺 修司', '技術評論社');

INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 11, 'book',  1, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 12, 'book',  2, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 13, 'book',  3,  true);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 14, 'book',  4, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 15, 'book',  5, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 16, 'book',  6,  true);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 17, 'book',  7, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 18, 'book',  8,  true);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES ( 19, 'book',  9, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES (110, 'book', 10, false);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES (111, 'book', 11,  true);
INSERT INTO stock (ref_no, type, item_id, is_on_loan) VALUES (112, 'book', 12, false);

INSERT INTO tag (name) VALUES ('Java')
INSERT INTO tag (name) VALUES ('Node')
INSERT INTO tag (name) VALUES ('Lisp')
INSERT INTO tag (name) VALUES ('シェルスクリプト')
INSERT INTO tag (name) VALUES ('Redis')
INSERT INTO tag (name) VALUES ('JUnit')
INSERT INTO tag (name) VALUES ('JavaScript')
INSERT INTO tag (name) VALUES ('Spring')

INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 1, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 1, 8)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 2, 3)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 3, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 4, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 5, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 6, 7)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 7, 3)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 8, 2)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 8, 7)
INSERT INTO item_to_tag (item_id, tag_id) VALUES ( 9, 5)
INSERT INTO item_to_tag (item_id, tag_id) VALUES (10, 4)
INSERT INTO item_to_tag (item_id, tag_id) VALUES (11, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES (12, 1)
INSERT INTO item_to_tag (item_id, tag_id) VALUES (12, 6)
