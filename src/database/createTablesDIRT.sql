#This is the script that needs to be run on the MySQL server to create the database and all of the tables for it.
#Once complete it will only need to be run once on a fresh MySQL server install.
#This script also updates the database whenever new tables are added to it.


#creates new user and grants them privs to do anything they want

#GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'localhost' IDENTIFIED BY 'PASS';


#Remove for update
drop table projectlabourer;
drop table labourers;
drop table projects;
drop table clients;
drop table serviceconstants;
drop database DIRT;

#creates db
create database DIRT;

#enters db
use DIRT;


#creates client table
create table Clients (clientNum smallint unsigned not null auto_increment, 
  fname varchar(50) not null,
  lname varchar(50) not null,
  company varchar(50),
  description varchar(5000), 
  phone1 varchar(11) not null, 
  phone2 varchar(11), 
  email varchar(30), 
  address varchar (50), 
  isActive boolean not null, constraint pk_Client primary key (clientNum));

#creates projects table
create table Projects (projectNum smallint unsigned not null auto_increment, clientNum smallint unsigned not null, projectName varchar(50), description varchar(5000), siteAddress varchar(100), startDate Date, estimatedEndDate Date, clientOwing numeric(8,2), clientPaid boolean, estimatedShoppingCost numeric(8,2), estimatedLabourCost numeric(8,2), estimatedDeliveryCost numeric(8,2), allowanceCost numeric(8,2), actualShoppingCost numeric(8,2), actualLabourCost numeric(8,2), actualDeliveryCost numeric(8,2), extraneousExpenses numeric(8,2), estimatedProfit numeric(8,2), actualProfit numeric(8,2), actualEndDate Date, isActive boolean not null, constraint pk_Project primary key (projectNum), constraint fk_ProjectClient foreign key (clientNum) references Clients (clientNum));

#creates labourers table
create table Labourers (labourerNum smallint unsigned not null auto_increment, 
fname varchar(30), 
lname varchar(30), 
title varchar(30), 
phone1 varchar(11), 
phone2 varchar(11), 
email varchar(30), 
address varchar(50), 
emergcontact varchar(30), 
emergcontactphone1 varchar(11), 
emergcontactphone2 varchar(11), 
sin varchar(9), 
wage varchar(5), 
isActive boolean not null, constraint pk_Labourer primary key (labourerNum));

#creates Project-Labourer bridging table
create table ProjectLabourer (projectNum smallint unsigned not null, labourerNum smallint unsigned not null, constraint fk_ProjectLabourerProject foreign key (projectNum) references Projects (projectNum), constraint fk_ProjectLabourerLabourer foreign key (labourerNum) references Labourers (labourerNum));

#creates Service constants table for the most used values for a given service/product
create table ServiceConstants (
superService varchar(30) not null, 
subService varchar(200) not null, 
constantLow numeric(5,2) not null,
constantHigh numeric(5,2));

#dummy test data
insert into Labourers (fname, lname, title, phone1, phone2, email, address, sin, wage, emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Eric', 'Stillman', 'FT Labourer', 4035687426, 4286452588, 'eric.still@gmail.com', '344 Auburn St Unit #69', 111222333, '18.25', 'Phillip DeFranco', 403568521, 684525655, 1);
insert into Labourers (fname, lname, title, phone1, address,  emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Keifer', 'Hicks', 'Contracter', 587456852, '4242 Riverbend Rd SE', 'Shaizans Sister', 5874456888, 4036855477, 1);
insert into Labourers (fname, lname, title, phone1, phone2, address,  emergcontact, emergcontactphone1, isActive) values ('Jeff', 'Jefferson', ' PT Contracter', 55526589525, 4356526555, 'Homeless Weaboo', 'Al Gore', 5648264955, 1);
insert into Labourers (fname, lname, phone1, isActive) values ('Sweaty', 'Taint',  4035428977, 1);
insert into Labourers (fname, lname, title, phone1,  emergcontact, isActive) values ('Ryuji', 'Sakamoto', ' PT Punk', 55526589525, 'Ann Takamaki',  1);
insert into Labourers (fname, lname, title, phone1, phone2, email, address, sin, wage, emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Johnny', 'Pottsmokr', 'PT Bitch', 5876658942, 4035688566, 'eatassandskatefast@hotmail.com', 'P.O Box #1433', 596795325, '15.00', 'Ethan Klein', 4036754216, 5874522588, 1);
insert into Labourers (fname, lname, title, phone1, email, address,  emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Smoky', 'McPot', 'Contracter', 4035428977, 'blzitfgt@yahoo.ca', '23 Bradberry Ln NW', 'Hila Klein', 4036754216, 5874522588, 1);

insert into Clients (fname, lname, phone1, isActive) values ('John', 'Smith', 4035551234, 1);
insert into Clients (fname, lname, company, phone1, phone2, address, description, email, isActive) values ('Kevin', 'Brown', 'The Keg', 4035658452, 546135679 ,'2354 Plex Unit# 4', 'cool dude', 'kevin.brown@edu.sait.ca', 1); 
insert into Clients (fname, lname, phone1, address, isActive) values ('Yukiko', 'Amagi', 465813467, 'The Amagi Inn', 1);
insert into Clients (fname, lname, company, phone1, isActive) values ('Dana', 'Longwang', 'newcompanywhodis', 2284568452, 1);
insert into Clients (fname, lname, phone1, phone2, isActive) values ('Mike', 'Hawk', 4038648645, 5874586475, 1);
insert into Clients (fname, lname, company, phone1, phone2, description, email, address, isActive) values ('Sherlock', 'Holmes', 'Pet Detective', 4567895215, 6457864359, 'Elementary' , 'sassybitch@whotmail.com', '221B Baker st', 1);
insert into Clients (fname, lname, phone1, description, isActive) values ('Mike', 'Hunt', 4038648645, 'Kind of a cunt', 1);
insert into Clients (fname, lname, company, phone1, phone2, description, email, address, isActive) values ('James', 'Hetfield', 'SoundtrackBoyes', 5548761248, 46537518495, 'GIMME FUEL GIMME FIRE GIMME THAT WHICH I DESIRE OOOOH', 'james@metallica.com', 'Somewhere in colorado', 1);
insert into Clients (fname, lname, phone1, phone2, description, email, address, isActive) values ('Cameron', 'Steinburg', 4035128991, 4035546265, 'Fly as hell', 'cameronsteinburg@live.ca', '105 Aspen Ridge Pl SW', 1);
insert into Clients (fname, lname, phone1, phone2, address, description, email, isActive) values ('Jane', 'Jefferson', 123456789, 987654321, 'Gutter #276965', 'Smells like eggs', 'gamergurrllll@gmail.com', 1);
insert into Clients (fname, lname, company, phone1, description, email, isActive) values ('Billy', 'Boyd', 'Is5companiesenough', 842657952,'No Yarbles' , 'ben@jerrys.com', 1);
insert into Clients (fname, lname, phone1, phone2, description, isActive) values ('Drew', 'Peacock', 4038648645, 5874586475,'Guy still owes me 50 bucks' , 1);
insert into Clients (fname, lname, phone1, phone2, description, email, isActive) values ('Artful', 'Dodger', 47642865, 548672598, 'Welcome' , 'art@dodge.com', 1);
insert into Clients (fname, lname, phone1, phone2, email, address, isActive) values ('Gerard', 'Way', 461544286, 645824654, 'g@way.com' ,'58264 Rose Hill Dr SE', 1);
insert into Clients (fname, lname, phone1, description, email, isActive) values ('Alex', 'DeLarge', 4678154389, "Viddy Well, My Dear Brothers", 'iwascured@alright.com', 1);
insert into Clients (fname, lname, phone1, address, isActive) values ('Bill', 'gates', 55555555, '1234 Super Rich Rd', 1);
insert into Clients (fname, lname, phone1, email, address, isActive) values ('Oliver', 'Sykes', 64512437659, 'welsh@dropdead.com', '666 vegan st', 1);
insert into Clients (fname, lname, phone1, phone2, email, address, isActive) values ('Patrick', 'Stump', 5642568759, 5556894258, 'fedora@fob.com' ,'88 Coopersonte Way NE', 1);

insert into serviceconstants values('excavation','trucking /2 yards',78,null);
insert into serviceconstants values('excavation','disposal',60,null);
insert into serviceconstants values('excavation','man hours by hand /yards',3,null);
insert into serviceconstants values('excavation','man hours by skid /yards',0.5,null);
insert into serviceconstants values('excavation','labour cost by hand /hours',55,null);
insert into serviceconstants values('excavation','labour cost by skid /hours',150,null);

insert into serviceconstants values('bed','hours /yards',1,null);
insert into serviceconstants values('bed','install /hours',55,null);

insert into serviceconstants values('stonewalkway','estimated man hours',4,null);
insert into serviceconstants values('stonewalkway','install rate /hours',55,null);

insert into serviceconstants values('geotextilewalkway','weed barrier cost /sq. ft',0.15,null);
insert into serviceconstants values('geotextilewalkway','fabric staples qty /1/5 sq. ft',5,null);
insert into serviceconstants values('geotextilewalkway','fabric staples cost /staple',0.15,null);
insert into serviceconstants values('geotextilewalkway','fabric man hours /100 sq ft',200,null);
insert into serviceconstants values('geotextilewalkway','fabric install rate /hours',55,null);

insert into serviceconstants values('walkwaybase','crushed base sq. ft/inch/yard',243,null);
insert into serviceconstants values('walkwaybase','crasued cost /yard',75,null);
insert into serviceconstants values('walkwaybase','man hours /yard',3,null);
insert into serviceconstants values('walkwaybase','install rate / hours',55,null);

insert into serviceconstants values('screedsand','depth sq.ft / yard',324,null);
insert into serviceconstants values('screedsand','cost /yard',85,null);
insert into serviceconstants values('screedsand','man hours / yard',3,null);
insert into serviceconstants values('screedsand','install /hours',55,null);

insert into serviceconstants values('edgerestraint','cost /8 ft',20,null);
insert into serviceconstants values('edgerestraint','nails /1nail',1,null);
insert into serviceconstants values('edgerestraint','man hours',50,null);
insert into serviceconstants values('edgerestraint','install /hours',55,null);

insert into serviceconstants values('jointingsand','QTY (kg/sf) @ 1/4 inch',0.6,null);
insert into serviceconstants values('jointingsand','cost /kg',1.5,null);
insert into serviceconstants values('jointingsand','hours /kg',0.1,null);
insert into serviceconstants values('jointingsand','install /hours',55,null);

insert into serviceconstants values('materials','Crushed Rock',53.10,59.00);
insert into serviceconstants values('materials','Pea Rock',58.50,65.00);
insert into serviceconstants values('materials','River Rock',58.50,65.00);
insert into serviceconstants values('materials','Mulch: Western Red Cedar',47.45,52.72);
insert into serviceconstants values('materials','Top Soil: Premium mix',35.10,39.00);
insert into serviceconstants values('materials','Crusher Dust',44.21,50.84);
insert into serviceconstants values('materials','Red Shale',85.00,97.75);
insert into serviceconstants values('materials','Sod (per 10 s.f.)',3.60,4.80);
insert into serviceconstants values('materials','Sod (per 10 s.f.)',3.60,4.80);