#This is the script that needs to be run on the MySQL server to create the database and all of the tables for it.
#Once complete it will only need to be run once on a fresh MySQL server install.
#This script also updates the database whenever new tables are added to it.


#creates new user and grants them privs to do anything they want

#GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'localhost' IDENTIFIED BY 'PASS';


#Remove for update
drop table excavationbyhandworkorder;
drop table excavationbyskidworkorder;
drop table workorders;
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
create table Projects (projectNum smallint unsigned not null auto_increment,
    clientNum smallint unsigned, 
    projectName varchar(50), 
    description varchar(5000), 
    siteAddress varchar(100),
    startDate Date,
    endDate Date, 
    clientOwing numeric(8,2), 
    clientPaid boolean, 
    allowanceCost numeric(8,2),  
    extraneousExpenses numeric(8,2), 
    quote numeric(8,2),
    actualCost numeric(8,2),
    isActive boolean not null, 
    constraint pk_Project primary key (projectNum), 
    constraint fk_ProjectClient foreign key (clientNum) references Clients (clientNum));

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

#creates workorders table
create table WorkOrders (workOrderNum mediumint unsigned not null auto_increment,
    projectNum smallint unsigned not null, 
    description varchar(5000), 
    quotedTotal numeric(8,2),
    actualTotal numeric(8,2),
    isActive boolean not null, 
    workOrderType varchar(100),
    constraint pk_WorkOrder primary key (workOrderNum), 
    constraint fk_WorkOrderProject foreign key (projectNum) references Projects (projectNum));

#creates workorder excavation by hand table
create table ExcavationByHandWorkOrder (workOrderNum mediumint unsigned not null,
    estSQFT numeric(8,2),
    estDepth numeric(8,2),
    estReqYards numeric(8,2),
    estHours numeric(8,2),
    estLabour numeric(8,2),
    estTrucking numeric(8,2),
    estDisposal numeric(8,2),
    actSQFT numeric(8,2),
    actDepth numeric(8,2),
    actReqYards numeric(8,2),
    actHours numeric(8,2),
    actLabour numeric(8,2),
    actTrucking numeric(8,2),
    actDisposal numeric(8,2), 
    constraint fk_WorkOrderExcByHand foreign key (workOrderNum) references WorkOrders (workOrderNum));

#creates workorder excavation by skid table
create table ExcavationBySkidWorkOrder (workOrderNum mediumint unsigned not null,
    estSQFT numeric(8,2),
    estDepth numeric(8,2),
    estReqYards numeric(8,2),
    estHours numeric(8,2),
    estLabour numeric(8,2),
    estTrucking numeric(8,2),
    estDisposal numeric(8,2),
    actSQFT numeric(8,2),
    actDepth numeric(8,2),
    actReqYards numeric(8,2),
    actHours numeric(8,2),
    actLabour numeric(8,2),
    actTrucking numeric(8,2),
    actDisposal numeric(8,2), 
    constraint fk_WorkOrderExcBySkid foreign key (workOrderNum) references WorkOrders (workOrderNum));

#dummy test data
insert into Labourers (fname, lname, title, phone1, phone2, email, address, sin, wage, emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Eric', 'Stillman', 'FT Labourer', 4035687426, 4286452588, 'eric.still@gmail.com', '344 Auburn St Unit #69', 111222333, '18.25', 'Phillip DeFranco', 403568521, 684525655, 1);
insert into Labourers (fname, lname, title, phone1, address,  emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Keifer', 'Hicks', 'Contracter', 587456852, '4242 Riverbend Rd SE', 'Shaizans Sister', 5874456888, 4036855477, 1);
insert into Labourers (fname, lname, title, phone1, phone2, address,  emergcontact, emergcontactphone1, isActive) values ('Jeff', 'Jefferson', ' PT Contracter', 55526589525, 4356526555, 'Homeless Weaboo', 'Al Gore', 5648264955, 1);
insert into Labourers (fname, lname, phone1, isActive) values ('Sweaty', 'Taint',  4035428977, 1);
insert into Labourers (fname, lname, title, phone1,  emergcontact, isActive) values ('Ryuji', 'Sakamoto', ' PT Punk', 55526589525, 'Ann Takamaki',  1);
insert into Labourers (fname, lname, title, phone1, phone2, email, address, sin, wage, emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Johnny', 'Pottsmokr', 'PT Bitch', 5876658942, 4035688566, 'eatassandskatefast@hotmail.com', 'P.O Box #1433', 596795325, '15.00', 'Ethan Klein', 4036754216, 5874522588, 1);
insert into Labourers (fname, lname, title, phone1, email, address,  emergcontact, emergcontactphone1, emergcontactphone2, isActive) values ('Smoky', 'McPot', 'Contracter', 4035428977, 'blzitfgt@yahoo.ca', '23 Bradberry Ln NW', 'Hila Klein', 4036754216, 5874522588, 1);

insert into Clients (clientNum, fname, lname, phone1, isActive) values (0, 'Place', 'Holder', 0000000000, 0);
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

insert into serviceconstants values('excavation','trucking by hand /2 yards',78.0,null);
insert into serviceconstants values('excavation','trucking by skid /2 yards',125.0,null);
insert into serviceconstants values('excavation','disposal',113.0,null);
insert into serviceconstants values('excavation','man hours by hand /yards',3.0,null);
insert into serviceconstants values('excavation','man hours by skid /yards',0.5,null);
insert into serviceconstants values('excavation','labour cost by hand /hours',55.0,null);
insert into serviceconstants values('excavation','labour cost by skid /hours',150.0,null);

insert into serviceconstants values('bed','hours /yards',1.0,null);
insert into serviceconstants values('bed','install /hours',55.0,null);

insert into serviceconstants values('stonewalkway','estimated man hours',4.0,null);
insert into serviceconstants values('stonewalkway','install rate /hours',55.0,null);

insert into serviceconstants values('geotextilewalkway','weed barrier cost /sq. ft',0.15,null);
insert into serviceconstants values('geotextilewalkway','fabric staples qty /1/5 sq. ft',5.0,null);
insert into serviceconstants values('geotextilewalkway','fabric staples cost /staple',0.15,null);
insert into serviceconstants values('geotextilewalkway','fabric man hours /100 sq ft',200.0,null);
insert into serviceconstants values('geotextilewalkway','fabric install rate /hours',55.0,null);

insert into serviceconstants values('walkwaybase','crushed base sq. ft/inch/yard',243.0,null);
insert into serviceconstants values('walkwaybase','crasued cost /yard',75.0,null);
insert into serviceconstants values('walkwaybase','man hours /yard',3.0,null);
insert into serviceconstants values('walkwaybase','install rate / hours',55.0,null);

insert into serviceconstants values('screedsand','depth sq.ft / yard',324.0,null);
insert into serviceconstants values('screedsand','cost /yard',85.0,null);
insert into serviceconstants values('screedsand','man hours / yard',3.0,null);
insert into serviceconstants values('screedsand','install /hours',55.0,null);

insert into serviceconstants values('edgerestraint','cost /8 ft',20.0,null);
insert into serviceconstants values('edgerestraint','nails /1nail',1.0,null);
insert into serviceconstants values('edgerestraint','man hours',50.0,null);
insert into serviceconstants values('edgerestraint','install /hours',55,null);

insert into serviceconstants values('jointingsand','QTY (kg/sf) @ 1/4 inch',0.6,null);
insert into serviceconstants values('jointingsand','cost /kg',1.5,null);
insert into serviceconstants values('jointingsand','hours /kg',0.1,null);
insert into serviceconstants values('jointingsand','install /hours',55.0,null);

insert into serviceconstants values('materials','Crushed Rock',53.10,59.00);
insert into serviceconstants values('materials','Pea Rock',58.50,65.00);
insert into serviceconstants values('materials','River Rock',58.50,65.00);
insert into serviceconstants values('materials','Mulch: Western Red Cedar',47.45,52.72);
insert into serviceconstants values('materials','Top Soil: Premium mix',35.10,39.00);
insert into serviceconstants values('materials','Crusher Dust',44.21,50.84);
insert into serviceconstants values('materials','Red Shale',85.00,97.75);
insert into serviceconstants values('materials','Sod (per 10 s.f.)',3.60,4.80);
    
insert into serviceconstants values('retainingwall','crushed base cost / yard',45.0,null);
insert into serviceconstants values('retainingwall','crushed base install hours / yard',2.0,null);
insert into serviceconstants values('retainingwall','crushed base install rate / yard',55.0,null);
insert into serviceconstants values('retainingwall','base row install hours /line feet',0.2,null);
insert into serviceconstants values('retainingwall','base row install rate / hour',55.0,null);
insert into serviceconstants values('retainingwall','block cost /line feet',13.37,null);

insert into serviceconstants values('irrigation','3/4 lining',0.60,2.5);
insert into serviceconstants values('irrigation','hose bibs',12.0,15.0);
insert into serviceconstants values('irrigation','shut off valve',20.0,20.0);
insert into serviceconstants values('irrigation','rotary head',42.0,30.0);
insert into serviceconstants values('irrigation','spray head',20.0,30.0);
insert into serviceconstants values('irrigation','drip 1/4inch /foot',1.0,1.0);
insert into serviceconstants values('irrigation','drip emmiter',2.5,1.0);
insert into serviceconstants values('irrigation','timer control',150.0,110.0);
insert into serviceconstants values('irrigation','control wire /100 feet',50.0,55.0);
insert into serviceconstants values('irrigation','valve box',40.0,25.0);
insert into serviceconstants values('irrigation','control valve',51.0,15.0);

insert into serviceconstants values('weedbarrier','man hours /500 sqft',1.0,null);
insert into serviceconstants values('weedbarrier','staples /500 sqft',60.0,null);
insert into serviceconstants values('weedbarrier','cost per staples',0.10,null);
insert into serviceconstants values('weedbarrier','barrier supply /500 sqft',55.0,null);

insert into serviceconstants values('sod','supply /yard',0.6,null);
insert into serviceconstants values('sod','man hours /10 sqft',0.12,null);
insert into serviceconstants values('sod','install rate /hours',55.0,null);

insert into serviceconstants values('snowremoval','monthly rate',105.0,null);
insert into serviceconstants values('snowremoval','additional area',26.25,null);

insert into serviceconstants values('tax','GST',0.05,null);
insert into serviceconstants values('tax','PST',0.06,null);