drop table if exists food;

create table food (
	food_id int not null auto_increment,
	food_name varchar(20),
	primary key (food_id)
);