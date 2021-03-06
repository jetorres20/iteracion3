CREATE TABLE HOTELES 
(
  IDOPERARIO NUMBER NOT NULL 
, NIT NUMBER NOT NULL 
, NOMBRE VARCHAR2(20) NOT NULL 
, DIRECCION VARCHAR2(20) NOT NULL 
, TELEFONO NUMBER NOT NULL 
, ESTRELLAS NUMBER NOT NULL 
, CONSTRAINT HOTELES_PK PRIMARY KEY 
  (
    IDOPERARIO 
  )
  ENABLE 
);

ALTER TABLE HOTELES
ADD CONSTRAINT HOTELES_UK1 UNIQUE 
(
  NIT 
)
ENABLE;

ALTER TABLE HOTELES
ADD CONSTRAINT HOTELES_FK1 FOREIGN KEY
(
  IDOPERARIO 
)
REFERENCES OPERARIOS
(
  ID 
)
ENABLE;

ALTER TABLE HOTELES
ADD CONSTRAINT HOTELES_CHK1 CHECK 
(ESTRELLAS IN (1,2,3,4,5))
ENABLE;
