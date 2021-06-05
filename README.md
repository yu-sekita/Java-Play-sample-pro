# Java-Play-sample-pro

## Create Table

```bash
$ docker-compose up -d
$ mysql -u todo_user -p -h localhost -P 3306 --protocol=tcp
mysql> use java-play-sample;
mysql> CREATE TABLE IF NOT EXISTS todo(
    -> id INT NOT NULL AUTO_INCREMENT,
    -> title VARCHAR(255) not null,
    -> text TEXT not null,
    -> primary key(id)
    -> );
```

## Run
```
$ docker-compose up
```
