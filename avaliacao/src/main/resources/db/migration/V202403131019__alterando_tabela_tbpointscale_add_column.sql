ALTER TABLE avaliacao.tbpointscale ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE avaliacao.tbpointscale ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE avaliacao.tbpointscale ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE avaliacao.tbpointscale ADD COLUMN useralt VARCHAR(30) NULL ;