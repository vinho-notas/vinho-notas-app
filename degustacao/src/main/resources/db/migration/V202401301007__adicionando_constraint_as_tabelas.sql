-- Tabela: degustacao.Tbvisualinspection
ALTER TABLE degustacao.Tbvisualinspection
ADD CONSTRAINT fk_visualinspection_tastingcard
FOREIGN KEY (tastingcard_id)
REFERENCES degustacao.Tbtastingcard(id);

-- Tabela: degustacao.Tbolfactoryinspection
ALTER TABLE degustacao.Tbolfactoryinspection
ADD CONSTRAINT fk_olfactoryinspection_tastingcard
FOREIGN KEY (tastingcard_id)
REFERENCES degustacao.Tbtastingcard(id);

-- Tabela: degustacao.Tbgustatoryinspection
ALTER TABLE degustacao.Tbgustatoryinspection
ADD CONSTRAINT fk_gustatoryinspection_tastingcard
FOREIGN KEY (tastingcard_id)
REFERENCES degustacao.Tbtastingcard(id);

-- Tabela: degustacao.Tbtastingcard
ALTER TABLE degustacao.Tbtastingcard
ADD CONSTRAINT fk_tastingcard_tasting
FOREIGN KEY (tasting_id)
REFERENCES degustacao.Tbtasting(id);
