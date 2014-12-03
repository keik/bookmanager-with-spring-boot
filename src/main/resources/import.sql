INSERT INTO books (title, author, publisher) VALUES ('Spring3入門', '長谷川 裕一', '技術評論社');
INSERT INTO books (title, author, publisher) VALUES ('計算機プログラムの構造と解釈', 'ジェラルド・ジェイ・サスマン', 'ピアソン');
INSERT INTO books (title, author, publisher) VALUES ('軽快なJava', 'ブルース・A. テイト', 'オライリージャパン');
INSERT INTO books (title, author, publisher) VALUES ('Javaエンジニア養成読本', 'きしだ なおき', '技術評論社');
INSERT INTO books (title, author, publisher) VALUES ('Java: The Good Parts', 'Jim Waldo', 'オライリージャパン');
INSERT INTO books (title, author, publisher) VALUES ('JavaScript 第6版', 'David Flanagan', 'オライリージャパン');
INSERT INTO books (title, author, publisher) VALUES ('Land of Lisp', 'Conrad Barski, M.D.', 'O''Reilly Media');
INSERT INTO books (title, author, publisher) VALUES ('サーバサイドJavaScript　Node.js入門', '清水 俊博', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO books (title, author, publisher) VALUES ('Redis入門 インメモリKVSによる高速データ管理', 'Josiah L.Carlson', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO books (title, author, publisher) VALUES ('フルスクラッチから1日でCMSを作る シェルスクリプト高速開発手法入門', '上田 隆一', 'KADOKAWA / アスキー・メディアワークス');
INSERT INTO books (title, author, publisher) VALUES ('増補改訂版Java言語で学ぶデザインパターン入門', '結城 浩', 'ソフトバンククリエイティブ');
INSERT INTO books (title, author, publisher) VALUES ('JUnit実践入門 ~体系的に学ぶユニットテストの技法', '渡辺 修司', '技術評論社');

INSERT INTO tags (name) VALUES ('Java')
INSERT INTO tags (name) VALUES ('Node')
INSERT INTO tags (name) VALUES ('Lisp')
INSERT INTO tags (name) VALUES ('シェルスクリプト')
INSERT INTO tags (name) VALUES ('Redis')
INSERT INTO tags (name) VALUES ('JUnit')
INSERT INTO tags (name) VALUES ('JavaScript')
INSERT INTO tags (name) VALUES ('Spring')

INSERT INTO books_tags (book_id, tag_id) VALUES (1, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (1, 8)
INSERT INTO books_tags (book_id, tag_id) VALUES (2, 3)
INSERT INTO books_tags (book_id, tag_id) VALUES (3, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (4, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (5, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (6, 7)
INSERT INTO books_tags (book_id, tag_id) VALUES (7, 3)
INSERT INTO books_tags (book_id, tag_id) VALUES (8, 2)
INSERT INTO books_tags (book_id, tag_id) VALUES (8, 7)
INSERT INTO books_tags (book_id, tag_id) VALUES (9, 5)
INSERT INTO books_tags (book_id, tag_id) VALUES (10, 4)
INSERT INTO books_tags (book_id, tag_id) VALUES (11, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (12, 1)
INSERT INTO books_tags (book_id, tag_id) VALUES (12, 6)