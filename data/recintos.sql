CREATE TABLE RECINTOS 
(
  ID NUMBER NOT NULL 
, CAPACIDADTOTAL NUMBER NOT NULL 
, FECHARETIROOFERTA DATE 
, CONSTRAINT RECINTOS_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
) 
;

ALTER TABLE RECINTOS
ADD CONSTRAINT CAPACIDADPOSITIVA CHECK 
(capacidadTotal>0)
ENABLE;

