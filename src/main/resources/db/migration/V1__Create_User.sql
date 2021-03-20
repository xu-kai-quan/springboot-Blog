create table user(
	id int auto_increment primary key,
	username varchar(10) UNIQUE,
	encrypted_password varchar(100),
	avatar varchar(100),
	created_at datetime,
	updated_at datetime
)
