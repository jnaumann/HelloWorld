drop table kosten;
drop table jobbearbeiter;
drop table produkteigenschaften;
drop table angestellte;
drop table jobs;
drop table angestelltenbezeichnungen;
drop table kunden;

commit;

CREATE TABLE kunden (
       id INTEGER NOT NULL AUTO_INCREMENT
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10) NOT NULL
     , PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE angestelltenbezeichnungen (
       id INTEGER NOT NULL AUTO_INCREMENT
     , bezeichnung CHAR(50)
     , PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE jobs (
       budget_in_std DECIMAL(10, 2)
     , budget_in_euro DECIMAL(10, 2)
     , id INTEGER NOT NULL AUTO_INCREMENT
     , name CHAR(30) NOT NULL
     , alte_jobnummer INTEGER
     , jobbeschreibung TEXT
     , kunden_id INTEGER NOT NULL
     , erledigt INTEGER
     , PRIMARY KEY (id)
     , INDEX (kunden_id)
     , CONSTRAINT FK_jobs_1 FOREIGN KEY (kunden_id)
                  REFERENCES kunden (id)
) ENGINE = InnoDB;

CREATE TABLE angestellte (
       id INTEGER NOT NULL AUTO_INCREMENT
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , angestelltenbezeichnung_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (angestelltenbezeichnung_id)
     , CONSTRAINT FK_angestellte_1 FOREIGN KEY (angestelltenbezeichnung_id)
                  REFERENCES angestelltenbezeichnungen (id)
) ENGINE = InnoDB;

CREATE TABLE produkteigenschaften (
       id INTEGER NOT NULL AUTO_INCREMENT
     , eingang DATE NOT NULL
     , vorlage DATE
     , ausgang DATE
     , print CHAR(10)
     , format CHAR(50)
     , beschnitt INTEGER
     , seitenzahl INTEGER
     , falzung CHAR(50)
     , farbe CHAR(10)
     , sonderfarbe CHAR(50)
     , bindung CHAR(50)
     , proof INTEGER
     , dummy INTEGER
     , produktbeschreibung TEXT
     , jobs_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (jobs_id)
     , CONSTRAINT FK_produkteigenschaften_1 FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
) ENGINE = InnoDB;

CREATE TABLE jobbearbeiter (
	   id INTEGER NOT NULL AUTO_INCREMENT
     , jobs_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (jobs_id)
     , CONSTRAINT FK_jobbearbeiter_1 FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_jobbearbeiter_2 FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
) ENGINE = InnoDB;

CREATE TABLE kosten (
       id INTEGER NOT NULL AUTO_INCREMENT
     , angestellte_id INTEGER NOT NULL
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , stundenlohn DECIMAL(10, 2)
     , kommentar TEXT
     , jobs_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , INDEX (jobs_id)
     , CONSTRAINT FK_kosten_2 FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
     , INDEX (angestellte_id)
     , CONSTRAINT FK_kosten_1 FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
) ENGINE = InnoDB;


