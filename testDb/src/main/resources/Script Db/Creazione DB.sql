--Creazione del DB
createdb testdb


--creazione tabelle
CREATE TABLE utente
(
  id numeric(16,0) NOT NULL,
  cod_utente character varying(30) NOT NULL,
  desc_utente character varying(510),
  CONSTRAINT pk_utente PRIMARY KEY (id)
);

CREATE TABLE articolo
(
  id numeric (16,0) NOT NULL,
  cod_articolo character varying(36) NOT NULL,
  desc_articolo character varying(510) NOT NULL,
  CONSTRAINT pk_articolo PRIMARY KEY (id)
)

CREATE TABLE ordine
(
  id numeric (16,0) NOT NULL,
  cod_ordine character varying(36) NOT NULL,
  desc_ordine character varying(36) NOT NULL,
  id_utente numeric (16,0) NOT NULL,
  id_articolo numeric (16,0) NOT NULL,
  quantita numeric (10,0) NOT NULL,
  CONSTRAINT pk_ordine PRIMARY KEY (id),
  CONSTRAINT fk_ordine_utente FOREIGN KEY (id_utente)
      REFERENCES utente (id) MATCH SIMPLE,
  CONSTRAINT fk_ordine_articolo FOREIGN KEY (id_articolo)
      REFERENCES articolo (id) MATCH SIMPLE
)

-- SCRIPT PER PULIRE LE TABELLE
delete from ordine;
delete from utente;
delete from articolo;