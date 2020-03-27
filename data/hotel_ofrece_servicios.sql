CREATE TABLE HOTEL_OFRECE_SERVICIO 
(
  IDHOTEL NUMBER NOT NULL 
, IDSERVICIO NUMBER NOT NULL 
, CONSTRAINT HOTEL_OFRECE_SERVICIO_PK PRIMARY KEY 
  (
    IDHOTEL 
  , IDSERVICIO 
  )
  ENABLE 
);

ALTER TABLE HOTEL_OFRECE_SERVICIO
ADD CONSTRAINT HOTEL_OFRECE_SERVICIO_FK1 FOREIGN KEY
(
  IDHOTEL 
)
REFERENCES HOTELES
(
  IDOPERARIO 
)
ENABLE;

ALTER TABLE HOTEL_OFRECE_SERVICIO
ADD CONSTRAINT HOTEL_OFRECE_SERVICIO_FK2 FOREIGN KEY
(
  IDSERVICIO 
)
REFERENCES SERVICIOS_HOTEL
(
  ID 
)
ENABLE;