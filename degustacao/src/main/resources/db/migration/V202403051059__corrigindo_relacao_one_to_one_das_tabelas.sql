ALTER TABLE degustacao.tbolfactoryinspection DROP CONSTRAINT fk_olfactoryinspection_tastingcard;
ALTER TABLE degustacao.tbolfactoryinspection DROP COLUMN tastingcard_id;

ALTER TABLE degustacao.tbgustatoryinspection DROP CONSTRAINT fk_gustatoryinspection_tastingcard;
ALTER TABLE degustacao.tbgustatoryinspection DROP COLUMN tastingcard_id;

ALTER TABLE degustacao.tbvisualinspection DROP CONSTRAINT fk_visualinspection_tastingcard;
ALTER TABLE degustacao.tbvisualinspection DROP COLUMN tastingcard_id;