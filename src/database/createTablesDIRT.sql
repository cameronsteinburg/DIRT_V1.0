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
drop database DIRT;

#creates db
create database DIRT;

#enters db
use DIRT;


#creates client table
create table Clients (clientNum smallint unsigned not null auto_increment, name varchar(50) not null, description varchar(5000), phone1 varchar(11) not null, phone2 varchar(11), email varchar(30), address varchar (50), isActive boolean not null, constraint pk_Client primary key (clientNum));

#creates projects table
create table Projects (projectNum smallint unsigned not null auto_increment, clientNum smallint unsigned not null, projectName varchar(50), description varchar(5000), siteAddress varchar(100), startDate Date, estimatedEndDate Date, clientOwing numeric(8,2), clientPaid boolean, estimatedShoppingCost numeric(8,2), estimatedLabourCost numeric(8,2), estimatedDeliveryCost numeric(8,2), allowanceCost numeric(8,2), actualShoppingCost numeric(8,2), actualLabourCost numeric(8,2), actualDeliveryCost numeric(8,2), extraneousExpenses numeric(8,2), estimatedProfit numeric(8,2), actualProfit numeric(8,2), actualEndDate Date, isActive boolean not null, constraint pk_Project primary key (projectNum), constraint fk_ProjectClient foreign key (clientNum) references Clients (clientNum));

#creates labourers table
create table Labourers (labourerNum smallint unsigned not null auto_increment, fname varchar(30), lname varchar(30), title varchar(30), phone1 varchar(11), phone2 varchar(11), email varchar(30), address varchar(50), emergcontact varchar(30), emergcontactphone1 varchar(11), emergcontactphone2 varchar(11), sin varchar(9), wage numeric(4,2), isActive boolean not null, constraint pk_Labourer primary key (labourerNum));

#creates Project-Labourer bridging table
create table ProjectLabourer (projectNum smallint unsigned not null, labourerNum smallint unsigned not null, constraint fk_ProjectLabourerProject foreign key (projectNum) references Projects (projectNum), constraint fk_ProjectLabourerLabourer foreign key (labourerNum) references Labourers (labourerNum));

insert into Clients (name, phone1, isActive) values ('John Smith', 4035551234, 1);
insert into Clients (name, phone1, isActive) values ('Dana Longwang', 2284568452, 1);
insert into Clients (name, phone1, phone2, isActive) values ('Mike Hawk', 4038648645, 5874586475, 1);
insert into Clients (name, phone1, description, isActive) values ('Mike Hunt', 4038648645, 'Kind of a cunt', 1);
insert into Clients (name, phone1, phone2, description, isActive) values ('Drew Peacock', 4038648645, 5874586475,'Guy still owes me 50 bucks' , 1);