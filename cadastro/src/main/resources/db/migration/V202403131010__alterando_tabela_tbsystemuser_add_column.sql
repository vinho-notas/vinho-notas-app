ALTER TABLE cadastro.tbsystemuser ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE cadastro.tbsystemuser ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE cadastro.tbsystemuser ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE cadastro.tbsystemuser ADD COLUMN useralt VARCHAR(30) NULL ;