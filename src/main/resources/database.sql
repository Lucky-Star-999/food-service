-- Create user FOODSERVICEV1
CREATE USER FOODSERVICEV1 IDENTIFIED BY 123456;

-- Assign all permissions to the user
GRANT ALL PRIVILEGES TO FOODSERVICEV1;

-- Create table Admin and add some data
CREATE TABLE ADMIN (
	Email NVARCHAR2(100) PRIMARY KEY,
	Password VARCHAR2(100),
	Fullname NVARCHAR2(100),
	Phone VARCHAR2(30)
);

INSERT ALL
    INTO ADMIN ( Email, Password, Fullname, Phone ) VALUES ( 'theboost1305@gmail.com', '123456', 'Nguyễn Hoàng Linh', '0933348791' )
    INTO ADMIN ( Email, Password, Fullname, Phone ) VALUES ( 'admin2@gmail.com', '123456', 'Alice', '0123456789' )
    INTO ADMIN ( Email, Password, Fullname, Phone ) VALUES ( 'admin3@gmail.com', '123456', 'Bob', '0123456001' )
SELECT 1 FROM DUAL;

-- Create table Customer and add some data
CREATE TABLE CUSTOMER (
	Email NVARCHAR2(100) PRIMARY KEY,
	Password VARCHAR2(100),
	Fullname NVARCHAR2(100),
	Phone VARCHAR2(30)
);

INSERT ALL
    INTO CUSTOMER ( Email, Password, Fullname, Phone ) VALUES ( 'theboost1305@gmail.com', '123456', 'Nguyễn Hoàng Linh', '0933348791' )
    INTO CUSTOMER ( Email, Password, Fullname, Phone ) VALUES ( 'admin2@gmail.com', '123456', 'Alice', '0123456789' )
    INTO CUSTOMER ( Email, Password, Fullname, Phone ) VALUES ( 'admin3@gmail.com', '123456', 'Bob', '0123456001' )
SELECT 1 FROM DUAL;

-- Create table Restaurant and add some data
CREATE TABLE RESTAURANT (
	Id VARCHAR2(100) PRIMARY KEY,
	Name NVARCHAR2(200),
	Email NVARCHAR2(100),
	Password VARCHAR2(100),
	ImageLink NVARCHAR2(100),
	Address NVARCHAR2(300)
);

INSERT ALL
    INTO RESTAURANT ( Id, Name, Email, Password, ImageLink, Address )
		VALUES (
			'ebb0a197-5c9b-4aa4-b00d-237a549d65e7', 'Luck Restaurant', 'theboost1305@gmail.com', '123456',
			'https://cdn-icons-png.flaticon.com/512/5052/5052310.png',
			'Khu phố 6, phường Linh Trung, thành phố Thủ Đức, Thành phố Hồ Chí Minh'
		)
	INTO RESTAURANT ( Id, Name, Email, Password, ImageLink, Address )
		VALUES (
			'96a1b1ab-4575-427e-a4e3-f77705547b2b', 'Restaurant 2', 'admin2@gmail.com', '123456',
			'',
			'Khu phố 6, phường Linh Trung, thành phố Thủ Đức, Thành phố Hồ Chí Minh'
		)
SELECT 1 FROM DUAL;



SELECT * FROM admin WHERE Email = 'theboost1305@gmail.com'












