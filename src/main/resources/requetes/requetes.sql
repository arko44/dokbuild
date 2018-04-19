
-- extremes : pui 13, end 14, int 12, tec 11, agi 10 
-- super : pui 3, end 14, int 2, tec 11, agi 10 
-- elements : 0 agi, 1 tec, 2 int, pui 3, end 4, 

SELECT distinct name, cost, element
FROM cards where element in (20) order by cost desc

SELECT id, name, character_id, awaked_card_id, passive_skill_set_id, cost, atk_max, link_skill2_id 
FROM cards where name like '%Super Saiyan God SS Vegeta%';
-- 1008411 : SV UR
-- Vegeta ssj4 : 1009820 => 1009821 dokkan 1009830 => 1009831
-- vegeta ssbss : element 12 : int


SELECT * FROM cards where name like '%4 Vege%';
SELECT * FROM cards where name like '%Super Saiyan God SS Vegeta%' and element=12
SELECT * FROM cards where name like '%Super Saiyan Vegeta%' order by rarity desc;

select * from card_awakening_routes where card_id in (1009820, 1009821, 1009830, 1009831)
-- eveil Z, dokkan, reverse

select * from card_awakenings where card_awakening_set_id in (1, 207, 174)
-- item nécessaires pour réaliser un éveil

select * from awakening_items where id in (1,8,13,14,20,900000,5261)
-- description des items

SELECT * FROM card_specials where card_id in (1009820, 1009821, 1009830, 1009831)
SELECT * FROM card_specials where card_id in (1005570)
-- 585 & 586
-- eball_num_start : nombre de ki pour déclencher l'attaque

SELECT * FROM card_training_skill_lvs where card_id in (1005570)

select * from specials where id in (585, 586)
select * from specials where name ='Prelude to Destruction'
-- 585 : efficacy type 2, target type 3, calc option 3, turn 3, increase rate 180, lv init 1, lv max 4, lv bonus 25, eff value 50

-- test avec 1005570 : passif 869
select efficacy_type, count(*) from passive_skills group by efficacy_type order by count(*) desc
select * from passive_skills where id in (780, 781)
select * from passive_skills where id in (869)
select * from passive_skills where id in (56, 66, 71, 84)


select id, description, causality_type, calc_option, efficacy_type from passive_skills where efficacy_type=20
select * from passive_skills where efficacy_type=2
select * from passive_skills where name like '%Measure of Power%'
select * from passive_skills where description like '%10000%'
-- passif de vegeta ssj4 : 780
-- bonus ki : efficacy_type=5 ou 20 (20 => certains types seulements
-- target_type: 4:ennemi, 1: soit-même, 2:alliés
-- causality : 0=sans condition, 1:HP supérieur a , 2:Hp inférieur a, 3:ki, 16: facing 1 enemy
-- efficacy_type : 3=att&def, 1:att, 2:def, 5:ki, 20:ki sur type
-- calc option : 0:absolue, 2:pourcentage


select * from passive_skill_set_relations WHERE passive_skill_set_id=869
select * from passive_skill_set_relations WHERE passive_skill_id=869
select * from passive_skill_sets WHERE id=869
select * from link_skills where description like '%10000%'

select * from specials where id=479

select * from card_specials where card_id=1009231

SELECT id, passive_skill_set_id, leader_skill_id FROM cards where name like '%Piccolo%' and cost=99;
select * from leader_skills where id=100923
select * from passive_skills where id in (709)
select * from passive_skills where id in (1000709)
select * from passive_skill_set_relations where passive_skill_set_id=709


Salut ! j'ai pris le temps de fouiller un peu dans la bdd, je pense avoir compris comment interpréter les données.

On retrouve le passif "principal" d'une carte via la requete suivante : 
SELECT id, passive_skill_set_id, leader_skill_id FROM cards where name like '%Piccolo%' and cost=99;

Dans le cas du Piccolo LR, il y a 2 passif donc j'utilise la table passive_skill_set_relations pour retrouver le second :
select * from passive_skill_set_relations where passive_skill_set_id=709

Et d'ailleurs le second est pas traduit mais comme seul l'effet est utile c'est pas grave :
select * from passive_skills where id in (1000709)

Par exemple, pour le passif 869, "ATK & DEF +80% when HP is 80% or below" :

La colonne causality type indique qu'il faut un certain % de PV pour valider le passif.
La colonne cau_value1 indique qu'il faut au moins 80% des PV (en rapport avec causality_type.
eff_value1 => 80 d'attaque
eff_value2 => 80 de defense

Poour savoir qu'il s'agit de 80% d'attaque et de défense : avec efficacy type (1:attaque, 3: att & def, ...)

Savoir qu'il s'agit de % et pas de valeur absolue : calc_option ? (0:valeur absolue, 1:val absolue negative, 2=%, ...)


