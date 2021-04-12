CONN / AS SYSDBA;

CONN system/1234qwer@XEPDB1;

CREATE USER GUEST_BOOK IDENTIFIED BY "1234qwer";

GRANT CONNECT TO GUEST_BOOK;
GRANT CREATE SESSION TO GUEST_BOOK;
GRANT CREATE TABLE TO GUEST_BOOK;
GRANT CREATE VIEW TO GUEST_BOOK;
GRANT CREATE ANY TRIGGER TO GUEST_BOOK;
GRANT CREATE ANY PROCEDURE TO GUEST_BOOK;
GRANT CREATE SEQUENCE TO GUEST_BOOK;
GRANT CREATE SYNONYM TO GUEST_BOOK;

ALTER USER GUEST_BOOK QUOTA UNLIMITED ON USERS;
