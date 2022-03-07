insert into hulls (hull_name, hull_resilience, energy_requirements, combat_speed, parsecks)
values ('hull',10,0,0,10.0);
insert into hulls (hull_name, hull_resilience, energy_requirements, combat_speed, parsecks)
values ('improved_hull',20,0,0,25.0);
insert into hulls (hull_name, hull_resilience, energy_requirements, combat_speed, parsecks)
values ('conifold_field',30,2,0,60.0);

insert into missiles (missile_name, damage_output, energy_requirements, combat_speed)
values ('flux_missile', 20,0,10);
insert into missiles (missile_name, damage_output, energy_requirements, combat_speed)
values ('plasma_missile', 80,4,0);

insert into shields ( shield_Type, energy_requirements, parsecks, damage_mitigation)
values ('gauss_shield',0,500.0,2);
insert into shields ( shield_Type, energy_requirements, parsecks, damage_mitigation)
values ('phase_shield',1,1000.0,2);
insert into shields ( shield_Type, energy_requirements, parsecks, damage_mitigation)
values ('absorption_shield',4,4000.0,1);

insert into weapons (weapon_name, energy_requirements, weapon_damage, parsecks)
values ('ion_cannon',1,10,100.0);
insert into weapons (weapon_name, energy_requirements, weapon_damage, parsecks)
values ('plasma_cannon',2,20,200.0);
insert into weapons (weapon_name, energy_requirements, weapon_damage, parsecks)
values ('soliton_cannon',3,60,600.0);
insert into weapons (weapon_name, energy_requirements, weapon_damage, parsecks)
values ('antimatter_cannon',4,80,1000.0);