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
  
  
 CREATE SEQUENCE produtosequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 8
  CACHE 1;
ALTER TABLE produtosequence
  OWNER TO postgres;
	      
  
  CREATE TABLE produto
(
  id bigint NOT NULL DEFAULT nextval('produtosequence'::regclass),
  nome character varying(500),
  quantidade numeric(10,4),
  quantidade numeric(10,4),
  CONSTRAINT produto_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;  
  
ALTER TABLE usuario ADD COLUMN cep character varying(200);
ALTER TABLE usuario ADD COLUMN rua character varying(200);
ALTER TABLE usuario ADD COLUMN bairro character varying(200);
ALTER TABLE usuario ADD COLUMN cidade character varying(200);
ALTER TABLE usuario ADD COLUMN estado character varying(200);
ALTER TABLE usuario ADD COLUMN ibge character varying(200);

----------------------------------------------------------------

CREATE SEQUENCE public.fonesequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.fonesequence
  OWNER TO postgres;




CREATE TABLE telefone
(
  id bigint NOT NULL DEFAULT nextval('fonesequence'::regclass),
  numero character varying(500),
  tipo character varying(500),
  usuario bigint NOT NULL,
  CONSTRAINT fone_pkey PRIMARY KEY (id)
  )
WITH (
  OIDS=FALSE
);
ALTER TABLE telefone
  OWNER TO postgres;
  
ALTER TABLE usuario ADD COLUMN fotobase64 text;  
ALTER TABLE usuario ADD COLUMN contenttype text;