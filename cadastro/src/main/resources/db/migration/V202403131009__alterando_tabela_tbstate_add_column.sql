ALTER TABLE cadastro.tbstate ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE cadastro.tbstate ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE cadastro.tbstate ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE cadastro.tbstate ADD COLUMN useralt VARCHAR(30) NULL ;