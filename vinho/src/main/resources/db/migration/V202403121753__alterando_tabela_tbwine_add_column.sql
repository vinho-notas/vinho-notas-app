ALTER TABLE vinho.tbwine ADD COLUMN dthreg TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE vinho.tbwine ADD COLUMN userreg VARCHAR(30) null;
ALTER TABLE vinho.tbwine ADD COLUMN dthalt TIMESTAMP null;
ALTER TABLE vinho.tbwine ADD COLUMN useralt VARCHAR(30) null;