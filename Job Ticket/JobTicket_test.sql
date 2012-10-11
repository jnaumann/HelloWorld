-- one to one
-- jedes lager hat einen stock

CREATE TABLE lager (
    id INT, 
    PRIMARY KEY (id)
 ) ENGINE = InnoDB;

CREATE TABLE stock(
id INT,
UNIQUE KEY(id),
FOREIGN KEY(id) REFERENCES lager(id)
) ENGINE = InnoDB;

drop table stock;
drop table lager;

-- one to many 
-- jedes parent hat mehrere childs
CREATE TABLE parent (
id INT NOT NULL,
PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE child (
	parent_id INT,
    INDEX (parent_id),
    FOREIGN KEY (parent_id) REFERENCES parent(id)
                    
) ENGINE=INNODB;

drop table child;
drop table parent;

-- many to one
-- jedes child hat mehrere parents

create table childs(
	id int,
	PRIMARY KEY (id)
)engine=innodb;

create table parents(
	id int,
	childs_id int,
	FOREIGN KEY (childs_id) references childs(id)
)engine=INNODB;

drop table parents;
drop table childs;

-- many to many
-- mehrer fussballmannschaften haben mehrere fussballer und mehrere fussballer haben mehrere fussballmannschaften
create table fussballmannschaft(
	id int,
	mannschaft_id int,
	PRIMARY KEY (mannschaft_id)
)engine=innoDB;

create table manager(
	id_mannschaft int,
	id_fussballer int,
	CONSTRAINT FK_fussballmannschaft FOREIGN KEY (id_mannschaft) REFERENCES fussballmannschaft (mannschaft_id),
	CONSTRAINT FK_fussballer FOREIGN KEY (id_fussballer) REFERENCES fussballer (fussballer_id)
)engine=innoDB;

create table fussballer(
	id int,
	fussballer_id int,
	PRIMARY KEY (fussballer_id)
)engine=innoDB;

drop table fussballmannschaft;
drop table manager;
drop table fussballer;