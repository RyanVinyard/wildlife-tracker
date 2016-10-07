--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: ryanvinyard
--

CREATE TABLE animals (
    id integer NOT NULL,
    name character varying,
    endangered boolean,
    health character varying,
    age character varying
);


ALTER TABLE animals OWNER TO ryanvinyard;

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: ryanvinyard
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_id_seq OWNER TO ryanvinyard;

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ryanvinyard
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: ryanvinyard
--

CREATE TABLE sightings (
    id integer NOT NULL,
    animal_id integer,
    location character varying,
    ranger_name character varying,
    "time" timestamp without time zone
);


ALTER TABLE sightings OWNER TO ryanvinyard;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: ryanvinyard
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO ryanvinyard;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ryanvinyard
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ryanvinyard
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ryanvinyard
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: ryanvinyard
--

COPY animals (id, name, endangered, health, age) FROM stdin;
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ryanvinyard
--

SELECT pg_catalog.setval('animals_id_seq', 11, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: ryanvinyard
--

COPY sightings (id, animal_id, location, ranger_name, "time") FROM stdin;
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ryanvinyard
--

SELECT pg_catalog.setval('sightings_id_seq', 4, true);


--
-- Name: animals_pkey; Type: CONSTRAINT; Schema: public; Owner: ryanvinyard
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: ryanvinyard
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: ryanvinyard
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM ryanvinyard;
GRANT ALL ON SCHEMA public TO ryanvinyard;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

