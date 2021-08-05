CREATE DATABASE 'story_db';
USE 'story_db';
CREATE TABLE 'story_type'('id' INT(2) NOT NULL, 'name' VARCHAR(25) NOT NULL,PRIMARY KEY ('id'))COLLATE='utf8mb4_general_ci';
CREATE TABLE 'story'('id' INT NOT NULL AUTO_INCREMENT, 'user_id' INT NOT NULL, 'story_type_id' INT(2)NOT NULL, 'tilte' )