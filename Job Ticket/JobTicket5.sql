CREATE TABLE DEFAULT_SCHEMA.kunden (
       id INTEGER NOT NULL AUTO_INCREMENT
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10)
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.produkteigenschaften (
       id INTEGER NOT NULL AUTO_INCREMENT
     , fomat INTEGER
     , beschnitt INTEGER
     , seitenzahl INTEGER
     , falzung CHAR(50)
     , farbe_4c CHAR(10)
     , farbe_sw CHAR(10)
     , sonderfarbe CHAR(50)
     , bindung CHAR(50)
     , proof INTEGER
     , dummy INTEGER
     , produktbeschreibung TEXT
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.angetellte (
       id INTEGER NOT NULL AUTO_INCREMENT
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , typ INTEGER
     , stundenlohn DECIMAL(10, 2)
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.kosten (
       id INTEGER NOT NULL AUTO_INCREMENT
     , budget_in_std DECIMAL(8, 2)
     , budget_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , kommentar TEXT
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.Produkte (
       id INTEGER NOT NULL
     , eigenschaften_id INTEGER NOT NULL
     , produkt_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (produkt_id)
     , CONSTRAINT FK_Produkte_1 FOREIGN KEY (produkt_id)
                  REFERENCES DEFAULT_SCHEMA.produkteigenschaften (id)
);

CREATE TABLE DEFAULT_SCHEMA.mitarbeiter (
       id INTEGER NOT NULL AUTO_INCREMENT
     , mitarbeiter_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_mitarbeiter_2 FOREIGN KEY (angestellte_id)
                  REFERENCES DEFAULT_SCHEMA.angetellte (id)
);

CREATE TABLE DEFAULT_SCHEMA.jobs (
       id INTEGER NOT NULL AUTO_INCREMENT
     , name CHAR(20) NOT NULL
     , alte_jobnummer INTEGER
     , eingang DATE NOT NULL
     , ausgang DATE
     , empfaenger CHAR(10)
     , print CHAR(10)
     , jobbeschreibung TEXT
     , produkte_id INTEGER NOT NULL
     , kunden_id INTEGER NOT NULL
     , mitarbeiter_id_grafiker INTEGER NOT NULL
     , mitarbeiter_id_kontakter INTEGER NOT NULL
     , kosten_id INTEGER
     , PRIMARY KEY (id)
     , INDEX (kunden_id)
     , CONSTRAINT FK_jobs_kunden FOREIGN KEY (kunden_id)
                  REFERENCES DEFAULT_SCHEMA.kunden (id)
     , INDEX (kosten_id)
     , CONSTRAINT FK_jobs_5 FOREIGN KEY (kosten_id)
                  REFERENCES DEFAULT_SCHEMA.kosten (id)
     , INDEX (produkte_id)
     , CONSTRAINT FK_jobs_3 FOREIGN KEY (produkte_id)
                  REFERENCES DEFAULT_SCHEMA.Produkte (id)
     , INDEX (mitarbeiter_id_grafiker)
     , CONSTRAINT FK_jobs_4 FOREIGN KEY (mitarbeiter_id_grafiker)
                  REFERENCES DEFAULT_SCHEMA.mitarbeiter (id)
);

