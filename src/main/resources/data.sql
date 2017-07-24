SELECT  1 FROM DUAL;

-- 사용자
INSERT INTO user (ID, username, password, is_enabled, role) VALUES (1, 'super', '1234', true, 'ROLE_SUPER');
INSERT INTO user (ID, username, password, is_enabled, role) VALUES (2, 'admin', '1234', true, 'ROLE_ADMIN');
INSERT INTO user (ID, username, password, is_enabled, role) VALUES (3, 'user', '1234', true, 'ROLE_USER');
INSERT INTO user (ID, username, password, is_enabled, role) VALUES (4, 'one0', '1234', true, 'ROLE_USER');



INSERT INTO product (NAME) VALUES ('라면');
INSERT INTO product (NAME) VALUES ('라면');
INSERT INTO product (NAME) VALUES ('라면');
INSERT INTO product (NAME) VALUES ('라면');


INSERT INTO food (NAME) VALUES ('김치찌개');
INSERT INTO food (NAME) VALUES ('셀러드');



-- 거래처
INSERT INTO customer (ID, NAME, ADDRESS, TELEPHONE, USE_YN, UPDATED_DATE) VALUES (1, '거래처A', '경기도 부천시 원미구 중동 미리내 아파트 903동 601호', '010-0000-0001', 'Y', SYSDATE);
INSERT INTO customer (ID, NAME, ADDRESS, TELEPHONE, USE_YN, UPDATED_DATE) VALUES (2, '거래처B', '경기도 부천시 원미구 중동 미리내 아파트 903동 602호', '010-0000-0002', 'Y', SYSDATE);
INSERT INTO customer (ID, NAME, ADDRESS, TELEPHONE, USE_YN, UPDATED_DATE) VALUES (3, '거래처C', '경기도 부천시 원미구 중동 미리내 아파트 903동 603호', '010-0000-0003', 'Y', SYSDATE);
INSERT INTO customer (ID, NAME, ADDRESS, TELEPHONE, USE_YN, UPDATED_DATE) VALUES (4, '거래처D', '경기도 부천시 원미구 중동 미리내 아파트 903동 604호', '010-0000-0004', 'Y', SYSDATE);
INSERT INTO customer (ID, NAME, ADDRESS, TELEPHONE, USE_YN, UPDATED_DATE) VALUES (5, '거래처E', '경기도 부천시 원미구 중동 미리내 아파트 903동 605호', '010-0000-0005', 'Y', SYSDATE);

-- 식재료
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (1, '고추장', 1);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (2, '된장', 1);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (3, '마늘', 1);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (4, '설탕', 1);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (5, '고추장', 2);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (6, '된장', 2);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (7, '마늘', 2);
INSERT INTO ingredient (ID, NAME, CUSTOMER_ID) VALUES (8, '설탕', 2);

-- 식재료의 가격
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (1, '2017', '01', '01', 10001, 1);
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (2, '2017', '01', '02', 10010, 1);
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (3, '2017', '01', '03', 10100, 1);
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (4, '2017', '01', '04', 11000, 1);
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (5, '2017', '01', '05', 10000, 1);
INSERT INTO price_of_ingredient (ID, YEAR, MONTH, DAY, PRICE, INGREDIENT_ID) VALUES (6, '2017', '01', '06', 10000, 1);