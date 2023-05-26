CREATE TABLE public.quiz
(
    id serial,
    question text NOT NULL,
    answer boolean NOT NULL,
    author character varying(20) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.quiz
    OWNER to postgres;