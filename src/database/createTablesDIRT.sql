#creates new user and grants them privs to do anything they want

#GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'localhost' IDENTIFIED BY 'PASS';


#creates db
create database DIRT;

#enters db
use DIRT;


#creates client table
create table Clients (clientNum smallint unsigned not null auto_increment, name varchar(50) not null, description varchar(5000), phone1 varchar(10) not null, phone2 varchar(10), email varchar(30) not null, address varchar (50), isActive boolean not null, constraint pk_Client primary key (clientNum));