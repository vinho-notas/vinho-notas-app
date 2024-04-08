ALTER TABLE degustacao.tbvisualinspection ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE degustacao.tbvisualinspection ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE degustacao.tbvisualinspection ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE degustacao.tbvisualinspection ADD COLUMN useralt VARCHAR(30) NULL ;