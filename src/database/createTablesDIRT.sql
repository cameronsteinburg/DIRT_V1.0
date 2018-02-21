#This is the script that needs to be run on the MySQL server to create the database and all of the tables for it.
#Once complete it will only need to be run once on a fresh MySQL server install.
#This script also updates the database whenever new tables are added to it.


#creates new user and grants them privs to do anything they want

#GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'localhost' IDENTIFIED BY 'PASS';


#Remove for update
drop table clients;
drop database DIRT;

#creates db
create database DIRT;

#enters db
use DIRT;


#creates client table
create table Clients (clientNum smallint unsigned not null auto_increment, name varchar(50) not null, description varchar(5000), phone1 varchar(10) not null, phone2 varchar(10), email varchar(30) not null, address varchar (50), isActive boolean not null, constraint pk_Client primary key (clientNum));