
drop table kosten;
drop table jobbearbeiter;
drop table produkteigenschaften;
drop table angestellte;
drop table jobs;
drop table angestelltenbezeichnungen;
drop table kunden;

CREATE TABLE kunden (
       id INTEGER NOT NULL generated always as identity
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE angestelltenbezeichnungen (
       id INTEGER NOT NULL generated always as identity
     , bezeichnung CHAR(50)
     , PRIMARY KEY (id)
);

CREATE TABLE jobs (
       id INTEGER NOT NULL generated always as identity
     , budget_in_std DECIMAL(10, 2)
     , budget_in_euro DECIMAL(10, 2)
     , name CHAR(30) NOT NULL
     , alte_jobnummer INTEGER
     , jobbeschreibung CLOB
     , kunden_id INTEGER NOT NULL
     , erledigt INTEGER
     , PRIMARY KEY (id)
     , CONSTRAINT FK_jobs_kunden FOREIGN KEY (kunden_id)
                  REFERENCES kunden (id)
);

CREATE TABLE angestellte (
       id INTEGER NOT NULL generated always as identity
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , angestelltenbezeichnung_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_angestellte_angestelltenbezeichnungen FOREIGN KEY (angestelltenbezeichnung_id)
                  REFERENCES angestelltenbezeichnungen (id)
);

CREATE TABLE produkteigenschaften (
       id INTEGER NOT NULL generated always as identity
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
     , produktbeschreibung CLOB
     , jobs_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_produkteigenschaften_jobs FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
);

CREATE TABLE jobbearbeiter (
	   id INTEGER NOT NULL generated always as identity
     , jobs_id INTEGER NOT NULL
     , angestellte_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_jobbearbeiter_jobs FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
     , CONSTRAINT FK_jobbearbeiter_angestellte FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
);

CREATE TABLE kosten (
       id INTEGER NOT NULL generated always as identity
     , angestellte_id INTEGER NOT NULL
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , stundenlohn DECIMAL(10, 2)
     , kommentar CLOB
     , jobs_id INTEGER NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_kosten_jobs FOREIGN KEY (jobs_id)
                  REFERENCES jobs (id)
     , CONSTRAINT FK_kosten_angestellte FOREIGN KEY (angestellte_id)
                  REFERENCES angestellte (id)
);


