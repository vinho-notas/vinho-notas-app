ALTER TABLE degustacao.tbgustatoryinspection ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE degustacao.tbgustatoryinspection ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE degustacao.tbgustatoryinspection ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE degustacao.tbgustatoryinspection ADD COLUMN useralt VARCHAR(30) NULL ;