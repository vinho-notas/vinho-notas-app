ALTER TABLE vinho.tbwine ALTER COLUMN alcoholcontent TYPE varchar(5) USING alcoholcontent::varchar(5);
ALTER TABLE vinho.tbwine ALTER COLUMN servicetemperature TYPE varchar(5) USING servicetemperature::varchar(5);
