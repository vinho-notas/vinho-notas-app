CREATE TABLE degustacao.Tbgustatoryinspection (
    id UUID NOT NULL UNIQUE,
    tastingdata DATE,
    winetasted VARCHAR(255),
    body VARCHAR(255),
    sweetness VARCHAR(255),
    tannin VARCHAR(255),
    classification VARCHAR(255),
    tastingcard_id UUID,
    PRIMARY KEY (id)
);
