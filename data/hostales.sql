CREATE TABLE HOSTALES 
(
  IDOPERARIO NUMBER NOT NULL 
, NIT NUMBER NOT NULL 
, NOMBRE VARCHAR2(20 BYTE) NOT NULL 
, HORAABRE NUMBER NOT NULL 
, HORACIERRA NUMBER NOT NULL 
, DIRECCION VARCHAR2(35 BYTE) NOT NULL 
, TELEFONO NUMBER NOT NULL 
, CONSTRAINT HOSTALES_PK PRIMARY KEY 
  (
    IDOPERARIO 
  )
  ENABLE
);


ALTER TABLE HOSTALES
ADD CONSTRAINT HOSTALES_UK1 UNIQUE 
(
  NIT 
)
ENABLE;


ALTER TABLE HOSTALES
ADD CONSTRAINT HOSTALES_FK1 FOREIGN KEY
(
  IDOPERARIO 
)
REFERENCES OPERARIOS
(
  ID 
)
ENABLE;

ALTER TABLE HOSTALES
ADD CONSTRAINT HOSTALES_CHK1 CHECK 
(HORAABRE >= 0 AND HORAABRE<=24)
ENABLE;

ALTER TABLE HOSTALES
ADD CONSTRAINT HOSTALES_CHK2 CHECK 
(HORACIERRA >=0 AND HORACIERRA<= 24 AND HORACIERRA > HORAABRE)
ENABLE;
