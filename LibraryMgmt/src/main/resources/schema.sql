CREATE TABLE ADDRESS(
	ADDR_ID INT AUTO_INCREMENT  PRIMARY KEY,
	LOCATION VARCHAR(250) NOT NULL	
);

CREATE TABLE LIBRARY(
	LIB_ID INT AUTO_INCREMENT  PRIMARY KEY,
	NAME VARCHAR(250) NOT NULL,
	NUM VARCHAR(250) NOT NULL,
	ADDR_ID INT,
	FOREIGN KEY (ADDR_ID) REFERENCES ADDRESS(ADDR_ID)
);
	
CREATE TABLE BOOK(
	BOOK_ID INT AUTO_INCREMENT  PRIMARY KEY,
	BOOK_NAME VARCHAR(250) NOT NULL,
	PUBLISHER VARCHAR(250),
	ISBN VARCHAR(250) NOT NULL,
	LIB_ID INT,
	FOREIGN KEY (LIB_ID) REFERENCES LIBRARY(LIB_ID)
);
	