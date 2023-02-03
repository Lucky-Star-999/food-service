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
