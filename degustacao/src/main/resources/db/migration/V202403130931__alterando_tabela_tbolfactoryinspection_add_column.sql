ALTER TABLE degustacao.tbolfactoryinspection ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE degustacao.tbolfactoryinspection ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE degustacao.tbolfactoryinspection ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE degustacao.tbolfactoryinspection ADD COLUMN useralt VARCHAR(30) NULL ;