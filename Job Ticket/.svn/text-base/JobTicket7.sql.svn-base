drop table jobs;
drop table kunden;
drop table produkteigenschaften;
drop table kosten;
drop table jobbearbeiter;
drop table angestellte;
drop table angestelltenbezeichnungen;

CREATE TABLE produkteigenschaften (
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
)engine=innoDB;

CREATE TABLE angestelltenbezeichnungen (
       id INTEGER NOT NULL AUTO_INCREMENT
     , bezeichnung CHAR(50)
     , PRIMARY KEY (id)
)engine=innoDB;

CREATE TABLE kunden (
       id INTEGER NOT NULL AUTO_INCREMENT
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10)
     , PRIMARY KEY (id)
)engine=innoDB;

CREATE TABLE angestellte (
       id INTEGER NOT NULL AUTO_INCREMENT
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , angestelltenbezeichnung_id INTEGER
     , stundenlohn DECIMAL(10, 2)
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (angestelltenbezeichnung_id)
     , CONSTRAINT FK_angestellte_1 FOREIGN KEY (angestelltenbezeichnung_id)
                  REFERENCES angestelltenbezeichnungen (id)
)engine=innoDB;

CREATE TABLE kosten (
       id INTEGER NOT NULL AUTO_INCREMENT
     , angestellte_id INTEGER NOT NULL
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , kommentar TEXT
     , PRIMARY KEY (id, angestellte_id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_kosten_1 FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
)engine=innoDB;

CREATE TABLE jobs (
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
                  REFERENCES kunden (id)
     , INDEX (kosten_id)
     , CONSTRAINT FK_jobs_5 FOREIGN KEY (kosten_id)
                  REFERENCES kosten (id)    
     , INDEX (produkte_id)
     , CONSTRAINT FK_jobs_3 FOREIGN KEY (produkte_id)
                  REFERENCES produkteigenschaften (id)
)engine=innoDB;

CREATE TABLE jobbearbeiter (
       jobbearbeiter_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (jobbearbeiter_id, angestellte_id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_mitarbeiter_2 FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
     , INDEX (jobbearbeiter_id)
     , CONSTRAINT FK_jobbearbeiter_2 FOREIGN KEY (jobbearbeiter_id)
                  REFERENCES jobs (id)
)engine=innoDB;

