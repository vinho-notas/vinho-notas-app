CREATE TABLE degustacao.Tbvisualinspection (
    id UUID NOT NULL UNIQUE,
    tastingdata DATE,
    winetasted VARCHAR(255),
    clarity VARCHAR(255),
    brightness VARCHAR(255),
    viscosity VARCHAR(255),
    colorred VARCHAR(255),
    colorwhite VARCHAR(255),
    colorrose VARCHAR(255),
    classification VARCHAR(255),
    tastingcard_id UUID,
    PRIMARY KEY (id)
);
