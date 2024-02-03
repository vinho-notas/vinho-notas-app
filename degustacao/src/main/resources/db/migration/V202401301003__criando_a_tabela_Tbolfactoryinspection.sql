CREATE TABLE degustacao.Tbolfactoryinspection (
    id UUID NOT NULL UNIQUE,
    tastingdata DATE,
    winetasted VARCHAR(255),
    intensity VARCHAR(255),
    persistence VARCHAR(255),
    quality VARCHAR(255),
    aromas_id UUID NOT NULL,
    classification VARCHAR(255),
    tastingcard_id UUID NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_olfactoryinspection_aromas FOREIGN KEY (aromas_id) REFERENCES degustacao.Tbaromas(id)
);
