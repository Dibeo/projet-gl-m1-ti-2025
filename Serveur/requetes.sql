--DROP DATABASE IF EXISTS projetgl;
--DROP SCHEMA projetgl_schm CASCADE;
--DROP USER IF EXISTS projetgl_server;

CREATE USER projetgl_server WITH PASSWORD 'projetgl+123';

CREATE DATABASE rojetgl_db OWNER projetgl_server;
CREATE SCHEMA IF NOT EXISTS projetgl_schm AUTHORIZATION projetgl_server;

GRANT USAGE ON SCHEMA projetgl_schm TO projetgl_server;
GRANT CREATE ON SCHEMA projetgl_schm TO projetgl_server;
ALTER USER projetgl_server SET search_path TO projetgl_schm;

ALTER DEFAULT PRIVILEGES IN SCHEMA projetgl_schm
GRANT ALL PRIVILEGES ON TABLES TO projetgl_server;

ALTER DEFAULT PRIVILEGES IN SCHEMA projetgl_schm
GRANT ALL PRIVILEGES ON SEQUENCES TO projetgl_server;

GRANT ALL ON SCHEMA projetgl_schm TO projetgl_server;
ALTER USER projetgl_server SET search_path TO projetgl_schm, PUBLIC;

show search_path;
SELECT schema_name FROM information_schema.schemata;

