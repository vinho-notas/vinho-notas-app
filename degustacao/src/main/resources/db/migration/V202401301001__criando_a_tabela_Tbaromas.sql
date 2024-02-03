CREATE SCHEMA IF NOT EXISTS degustacao;

CREATE TABLE degustacao.Tbaromas (
     id UUID NOT NULL UNIQUE,
     tastingdata DATE,
     winetasted VARCHAR(255),
     fruity VARCHAR(255),
     dryfruits VARCHAR(255),
     florals VARCHAR(255),
     vegetablesandherbs VARCHAR(255),
     minerals VARCHAR(255),
     spices VARCHAR(255),
     animals VARCHAR(255),
     empireumatics VARCHAR(255),
     wood VARCHAR(255),
     chemicals VARCHAR(255),
     lacteal VARCHAR(255),
     sweets VARCHAR(255),
     classification VARCHAR(255),
     PRIMARY KEY (id)
);
