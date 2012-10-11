drop table kundenkontakte;
drop table produkteigenschaften;
drop table mitarbeiter;
drop table kosten;
drop table jobticket;


CREATE TABLE kundenkontakte (
	id INT NOT NULL primary key auto_increment,
	kundenname varchar(50),
	vorname VARCHAR(50),
	kundenkuerzel VARCHAR(10) NOT NULL,
	adresse VARCHAR(50),
	telefonnummer VARCHAR(50)
) engine=innoDB;

CREATE TABLE produkteigenschaften(
	id INT NOT NULL primary key auto_increment,
	format int,
	beschnitt int,
	seitenzahl int,
	falzung varchar(50),
	farbe_4c varchar(10),
	farbe_sw varchar(10),
	sonderfarbe varchar(50),
	bindung varchar(50),
	proof int,
	dummy int	
) engine=innoDB;

create table mitarbeiter(
	id int not null primary key auto_increment,
	name varchar (50),
	vorname varchar(50),
	typ int not null,
	stundenlohn float(10,2)
) engine=innoDB;

create table kosten(
	id int not null primary key auto_increment,
	budget_in_euro float(10,2),
	budget_in_std float(8,2),
	arbeitsaufwand_in_std float(8,2),
	arbeitsaufwand_in_euro float(10,2),
	kommentar text
) engine=innoDB;


CREATE TABLE jobticket (
	id INT NOT NULL primary key auto_increment,
	jobname VARCHAR(10),
	kunden_id int references kundenkontakte(id),
	Eingangsdatum DATE,
	Abgabetermin DATE,
	altejobnummer INT,
	mitarbeiter_id_kontakter int references mitarbeiter(id),
	empfänger varchar(50),
	mitarbeiter_id_grafiker int references mitarbeiter(id),
	produktname varchar(50),
	print varchar(10),
	beschreibung text
) engine=innoDB;