CREATE TABLE IF NOT EXISTS country (
     id uuid NOT NULL,
     country varchar(255) NOT NULL,
     continent varchar(255) NOT NULL,
     CONSTRAINT newtable_pk PRIMARY KEY (id)
);


