CREATE TABLE degustacao.Tbtasting (
    id UUID NOT NULL UNIQUE,
    tastingdata DATE,
    tastingtype VARCHAR(255),
    PRIMARY KEY (id)
);
