-- Database: "curso-jsp"

-- DROP DATABASE "curso-jsp";

CREATE DATABASE "curso-jsp"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;


-- Sequence: public.serialuser

-- DROP SEQUENCE public.serialuser;

CREATE SEQUENCE public.serialuser
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 89
  CACHE 1;
ALTER TABLE public.serialuser
  OWNER TO postgres;

       
-- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
  login character varying,
  senha character varying,
  id bigint NOT NULL DEFAULT nextval('serialuser'::regclass),
  nome character varying(500)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;    
  
  
  ALTER TABLE usuario ADD COLUMN fone character varying(50);