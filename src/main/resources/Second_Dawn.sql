drop table if exists missile_dock_order;
DROP TABLE if exists dock_order;
DROP TABLE IF EXISTS missiles;
DROP TABLE IF EXISTS hulls;
DROP TABLE IF EXISTS empires;
DROP TABLE IF EXISTS shields;
DROP TABLE IF EXISTS weapons;

CREATE TABLE weapons (
                         weapon_id int  unsigned not null auto_increment,
                         weapon_name varchar(30) not null unique,
                         energy_requirements int unsigned not null,
                         weapon_damage int unsigned not null,
                         parsecks decimal(6,2) not null,
                         primary key (weapon_id)

);


CREATE TABLE shields (
                         shield_id int unsigned not null auto_increment,
                         shield_Type varchar(30) not null unique ,
                         energy_requirements int unsigned not null,
                         parsecks decimal(9,2) not null,
                         damage_mitigation int unsigned not null,
                         primary key (shield_id)
);


CREATE TABLE empires (
                         empire_id int unsigned not null auto_increment,
                         empire_name varchar(30)not null unique,
                         sector varchar(30) not null,
                         alliance varchar(30) not null,
                         primary key (empire_id)
);

CREATE TABLE hulls (
                       hull_id int unsigned not null auto_increment,
                       hull_name varchar(30) not null unique ,
                       hull_resilience int unsigned not null,
                       energy_requirements int unsigned not null,
                       combat_speed int unsigned not null,
                       parsecks decimal(9,2),
                       primary key (hull_id)
);


CREATE TABLE missiles (
                          missile_id int unsigned not null auto_increment,
                          missile_name varchar(30) not null unique,
                          damage_output int unsigned not null,
                          energy_requirements int unsigned not null ,
                          combat_speed int unsigned not null,
                          primary key (missile_id)
);

# CREATE TABLE species (
#     species_pk int unsigned not null  AUTO_INCREMENT,
#     species_id enum('descendants_of_draco', 'mechanema', 'orion_hegemony', 'hydran_progress', 'eridani_empire', 'planta'),
#
# );
#
# CREATE TABLE ships (
#                         ship_pk int unsigned NOT NULL AUTO_INCREMENT,
#                         ship_id enum('INTERCEPTOR', 'CRUISER', 'DREADNAUGHT', 'BASE') NOT NULL,
#                         trim_level varchar(40) NOT NULL,
#                         num_doors int NOT NULL,
#                         wheel_size int NOT NULL,
#                         base_price decimal(9, 2) NOT NULL,
#                         PRIMARY KEY (ship_pk),
#                         UNIQUE KEY (ship_id)
# );

CREATE TABLE dock_order (
                            dock_id int unsigned not null auto_increment,
                            some_key varchar(100),
                            weapon_FK int unsigned not null,
                            shield_FK int unsigned not null,
                            empire_id int unsigned not null,
                            hull_id int unsigned not null,
                            parsecks decimal(9,2),
                            primary key (dock_id),

                            CONSTRAINT `dock_order_1` foreign key (weapon_FK) REFERENCES `weapons`(weapon_id) on delete cascade
                            on update cascade ,
                            CONSTRAINT `dock_order_2` foreign key (shield_FK) REFERENCES `shields`(shield_id) on delete cascade
                                on update cascade ,
                            CONSTRAINT `dock_order_3` foreign key (empire_id) REFERENCES `empires`(empire_id) on delete cascade
                                on update cascade ,
                            CONSTRAINT `dock_order_4` foreign key (hull_id) REFERENCES `hulls` (hull_id) on delete cascade
                                on update cascade
);

CREATE TABLE missile_dock_order (
                                    missile_id_fk int unsigned not null,
                                    dock_id_fk int unsigned not null,
                                    CONSTRAINT `missile_dock_order_1` foreign key (missile_id_fk) REFERENCES `missiles`(missile_id) on delete cascade
                                        on update cascade ,
                                    CONSTRAINT `missile_dock_order_2`foreign key  (dock_id_fk) REFERENCES `dock_order`(dock_id) on delete cascade
                                        on update cascade
);