CREATE TABLE degustacao.Tbtastingcard (
    id UUID NOT NULL UNIQUE,
    tastingdata DATE,
    winetasted VARCHAR(255),
    harvest INT,
    grapes VARCHAR(255),
    country VARCHAR(255),
    region VARCHAR(255),
    visualinspection_id UUID,
    olfactoryinspection_id UUID,
    gustatoryinspection_id UUID,
    opinion VARCHAR(255),
    pointscale VARCHAR(255),
    tasting_id UUID,
    PRIMARY KEY (id),
    CONSTRAINT fk_tastingcard_visualinspection FOREIGN KEY (visualinspection_id) REFERENCES degustacao.Tbvisualinspection(id),
    CONSTRAINT fk_tastingcard_olfactoryinspection FOREIGN KEY (olfactoryinspection_id) REFERENCES degustacao.Tbolfactoryinspection(id),
    CONSTRAINT fk_tastingcard_gustatoryinspection FOREIGN KEY (gustatoryinspection_id) REFERENCES degustacao.Tbgustatoryinspection(id)
);
