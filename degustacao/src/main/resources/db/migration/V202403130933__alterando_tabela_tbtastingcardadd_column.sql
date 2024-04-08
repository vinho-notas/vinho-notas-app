ALTER TABLE degustacao.tbtastingcard ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE degustacao.tbtastingcard ADD COLUMN userreg VARCHAR(30) NULL;
ALTER TABLE degustacao.tbtastingcard ADD COLUMN dthalt TIMESTAMP NULL;
ALTER TABLE degustacao.tbtastingcard ADD COLUMN useralt VARCHAR(30) NULL ;