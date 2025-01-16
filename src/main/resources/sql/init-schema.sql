DROP SCHEMA IF EXISTS "accounts" CASCADE;
CREATE SCHEMA IF NOT EXISTS "accounts";

DROP TABLE IF EXISTS "accounts".users CASCADE;
CREATE TABLE IF NOT EXISTS "accounts".users
(
    id       SERIAL                                         NOT NULL,
    name     CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    email    CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    password CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "accounts".roles CASCADE;
CREATE TABLE IF NOT EXISTS "accounts".roles
(
    id   SERIAL                                         NOT NULL,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "accounts".user_roles CASCADE;
CREATE TABLE IF NOT EXISTS "accounts".user_roles
(
    id      SERIAL  NOT NULL,
    user_id NUMERIC NOT NULL,
    role_id NUMERIC NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS "accounts".user_roles
    ADD CONSTRAINT "FK_USER_ID" FOREIGN KEY (id)
        REFERENCES "accounts".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
        NOT VALID;

ALTER TABLE IF EXISTS "accounts".user_roles
    ADD CONSTRAINT "FK_ROLE_ID" FOREIGN KEY (id)
        REFERENCES "accounts".roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
        NOT VALID;

CREATE MATERIALIZED VIEW IF NOT EXISTS "accounts".user_roles_m_view
    TABLESPACE pg_default AS
SELECT u.id    AS user_id,
       u.email AS user_email,
       r.name  AS role_name
FROM "accounts".users u,
     "accounts".roles r,
     "accounts".user_roles ur
WHERE u.id = ur.user_id
  AND r.id = ur.role_id
WITH DATA;

REFRESH MATERIALIZED VIEW "accounts".user_roles_m_view;
CREATE OR REPLACE FUNCTION "accounts".refresh_user_roles_m_view()
    RETURNS TRIGGER
AS
'
    BEGIN
        REFRESH MATERIALIZED VIEW "accounts".user_roles_m_view;
        RETURN NULL;
    END;
'
    LANGUAGE plpgsql;

CREATE TRIGGER refresh_user_roles_m_view
    AFTER INSERT OR UPDATE OR DELETE OR TRUNCATE
    ON "accounts".user_roles
    FOR EACH STATEMENT
EXECUTE PROCEDURE "accounts".refresh_user_roles_m_view();