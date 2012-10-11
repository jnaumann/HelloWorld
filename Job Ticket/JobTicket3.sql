drop table kunden;
drop table produkteigenschaften;
drop table mitarbeiter;
drop table kosten;
drop table jobs;

CREATE TABLE mitarbeiter (
       id INTEGER NOT NULL AUTO_INCREMENT
     , vorname VARCHAR(50) NOT NULL
     , nachname VARCHAR(50) NOT NULL
     , typ INTEGER
     , stundenlohn DECIMAL(10, 2)
     , PRIMARY KEY (id)
) engine=innoDB;

CREATE TABLE kunden (
       id INTEGER NOT NULL AUTO_INCREMENT
     , kunde VARCHAR(50) NOT NULL
     , adresse VARCHAR(50)
     , telefon VARCHAR(50)
     , kundenkuerzel CHAR(10) NOT NULL
     , PRIMARY KEY (id)
) engine=innoDB;

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
     , PRIMARY KEY (id)
) engine=innoDB;

CREATE TABLE kosten (
       id INTEGER NOT NULL AUTO_INCREMENT
     , budget_in_std DECIMAL(8, 2)
     , budget_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_euro DECIMAL(10, 2)
     , arbeitsaufwand_in_std DECIMAL(10, 2)
     , kommentar TEXT
     , PRIMARY KEY (id)
) engine=innoDB;

CREATE TABLE jobs (
       id INTEGER NOT NULL AUTO_INCREMENT
     , name CHAR(20) NOT NULL
     , alte_jobnummer INTEGER
     , eingang DATE NOT NULL
     , ausgang DATE
     , empfaenger CHAR(10)
     , print CHAR(10)
     , beschreibung TEXT
     , produkte_id INTEGER NOT NULL
     , kunden_id INTEGER NOT NULL
     , mitarbeiter_id_grafiker INTEGER NOT NULL
     , mitarbeiter_id_kontakter INTEGER NOT NULL
     , kosten_id INTEGER
     , PRIMARY KEY (id)
     , INDEX (mitarbeiter_id_kontakter)
     , CONSTRAINT FK_jobs_mitarbeiter_kontakter FOREIGN KEY (mitarbeiter_id_kontakter)
                  REFERENCES mitarbeiter (id)
     , INDEX (mitarbeiter_id_grafiker)
     , CONSTRAINT FK_jobs_mitarbeiter_grafiker FOREIGN KEY (mitarbeiter_id_grafiker)
                  REFERENCES mitarbeiter (id)
     , INDEX (kunden_id)
     , CONSTRAINT FK_jobs_kunden FOREIGN KEY (kunden_id)
                  REFERENCES kunden (id)
     , INDEX (produkte_id)
     , CONSTRAINT FK_jobs_4 FOREIGN KEY (produkte_id)
                  REFERENCES produkteigenschaften (id)
     , INDEX (kosten_id)
     , CONSTRAINT FK_jobs_5 FOREIGN KEY (kosten_id)
                  REFERENCES kosten (id)
) engine=innoDB;

