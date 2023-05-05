INSERT INTO role VALUES (1111, 'Admin');
INSERT INTO role VALUES (3333, 'Staff');
INSERT INTO role VALUES (2222, 'Manager');
--
INSERT INTO bank_user VALUES (111,  'Super', 'admin', 'user1@miu.edu', '$2a$12$uKceNi9oD/.HT0PFlLo9GuyVArSp2g6AWRFeD/QZ0lLNXYriv8/t6');
INSERT INTO bank_user VALUES (222,  'San', 'Sar', 'user2@miu.edu', '$2a$12$uKceNi9oD/.HT0PFlLo9GuyVArSp2g6AWRFeD/QZ0lLNXYriv8/t6');
--
INSERT INTO bank_user_role VALUES (111, 1111);
INSERT INTO bank_user_role VALUES (111, 3333);
INSERT INTO bank_user_role VALUES (222, 2222);
