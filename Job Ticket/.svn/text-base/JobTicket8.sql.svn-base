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

CREATE TABLE DEFAULT_SCHEMA.angestelltenbezeichnungen (
       id INTEGER NOT NULL AUTO_INCREMENT
     , bezeichnung CHAR(50)
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.kunden (
       id INTEGER NOT NULL AUTO_INCREMENT
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10)
     , PRIMARY KEY (id)
);

CREATE TABLE DEFAULT_SCHEMA.angestellte (
       id INTEGER NOT NULL AUTO_INCREMENT
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , angestelltenbezeichnung_id INTEGER
     , stundenlohn DECIMAL(10, 2)
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (angestelltenbezeichnung_id)
     , CONSTRAINT FK_angestellte_1 FOREIGN KEY (angestelltenbezeichnung_id)
                  REFERENCES DEFAULT_SCHEMA.angestelltenbezeichnungen (id)
);

CREATE TABLE DEFAULT_SCHEMA.kosten (
       id INTEGER NOT NULL AUTO_INCREMENT
     , angestellte_id INTEGER NOT NULL
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , kommentar TEXT
     , PRIMARY KEY (id, angestellte_id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_kosten_1 FOREIGN KEY (angestellte_id)
                  REFERENCES DEFAULT_SCHEMA.angestellte (id)
);

CREATE TABLE DEFAULT_SCHEMA.jobs (
       budget_in_std DECIMAL(10, 2)
     , budget_in_euro DECIMAL(10, 2)
     , vorlage DATE
     , id INTEGER NOT NULL AUTO_INCREMENT
     , name CHAR(20) NOT NULL
     , alte_jobnummer INTEGER
     , eingang DATE NOT NULL
     , ausgang DATE
     , empfaenger CHAR(10)
     , print CHAR(10)
     , jobbeschreibung TEXT
     , produkte_id INTEGER NOT NULL
     , kunden_id INTEGER NOT NULL
     , kosten_id INTEGER
     , jobbearbeiter_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (kunden_id)
     , CONSTRAINT FK_jobs_kunden FOREIGN KEY (kunden_id)
                  REFERENCES DEFAULT_SCHEMA.kunden (id)
     , INDEX (angestellte_id, kosten_id)
     , CONSTRAINT FK_jobs_5 FOREIGN KEY (angestellte_id, kosten_id)
                  REFERENCES DEFAULT_SCHEMA.kosten (angestellte_id, id)
     , INDEX (produkte_id)
     , CONSTRAINT FK_jobs_3 FOREIGN KEY (produkte_id)
                  REFERENCES DEFAULT_SCHEMA.produkteigenschaften (id)
);

CREATE TABLE DEFAULT_SCHEMA.jobbearbeiter (
       jobbearbeiter_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (jobbearbeiter_id, angestellte_id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_mitarbeiter_2 FOREIGN KEY (angestellte_id)
                  REFERENCES DEFAULT_SCHEMA.angestellte (id)
     , INDEX (jobbearbeiter_id)
     , CONSTRAINT FK_jobbearbeiter_2 FOREIGN KEY (jobbearbeiter_id)
                  REFERENCES DEFAULT_SCHEMA.jobs (id)
);

