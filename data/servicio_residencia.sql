CREATE TABLE SERVICIO_RESIDENCIA 
(
  ID NUMBER NOT NULL 
, NOMBRE VARCHAR2(20) NOT NULL 
, COSTOEXTRANOCHE NUMBER NOT NULL 
, COSTOEXTRAMES NUMBER NOT NULL 
, COSTOEXTRASEMESTRE NUMBER NOT NULL 
, CONSTRAINT SERVICIO_RESIDENCIA_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE SERVICIO_RESIDENCIA
ADD CONSTRAINT SERVICIO_RESIDENCIA_CHK1 CHECK 
(COSTOEXTRANOCHE>=0 AND COSTOEXTRAMES>=0 AND COSTOEXTRASEMESTRE>=0)
ENABLE;

