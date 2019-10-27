CREATE DATABASE IF NOT EXISTS `budget_time` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `budget_time`;

CREATE TABLE IF NOT EXISTS `accounts` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO `accounts` (`username`, `password`) VALUES ('test1', 'test1');
INSERT INTO `accounts` (`username`, `password`) VALUES ('test2', 'test2');


CREATE TABLE IF NOT EXISTS `budgets` (
    budget_id int auto_increment,
    owner_username varchar(50) not null,
    PRIMARY KEY (budget_id)
);

CREATE TABLE IF NOT EXISTS `budget_entries`(
    budget_entry_id int auto_increment,
    parent_budget_id int not null,
    category varchar(255) not null,
    description varchar(255) not null,
    duration int not null,
    PRIMARY KEY (budget_entry_id),
    FOREIGN KEY (parent_budget_id) REFERENCES budgets(budget_id)
);


