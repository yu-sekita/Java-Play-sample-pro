CREATE TABLE IF NOT EXISTS todo(
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) not null,
	text TEXT not null,
	primary key(id)
);