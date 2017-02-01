SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `FootballAnalyzer` DEFAULT CHARACTER SET utf8 ;
USE `FootballAnalyzer`;

DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `Date` CHAR(10) NULL DEFAULT NULL,
  `Watchers` INTEGER NULL DEFAULT NULL,
  `Location` CHAR(100) NULL DEFAULT NULL,
  `Length` CHAR(6) NULL DEFAULT NULL,
  `TeamOneId` BIGINT,
  `Score` CHAR(5) NULL DEFAULT NULL,
  `TeamTwoId`BIGINT,
  `RefereeOneId` BIGINT,
  `RefereeTwoId` BIGINT,
  `MainRefereeId` BIGINT,
  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` CHAR(100) DEFAULT NULL,
  `Score` INTEGER NULL DEFAULT NULL,
  `WinsInMainTime` INTEGER NULL DEFAULT NULL,
  `LosesInMainTime` INTEGER NULL DEFAULT NULL,
  `WinsInAdditionalTime` INTEGER NULL DEFAULT NULL,
  `LosesInAdditionalTime` INTEGER NULL DEFAULT NULL,
  `ScoredGoals` INTEGER NULL DEFAULT NULL,
  `LostGoals` INTEGER NULL DEFAULT NULL,

  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `players`;
CREATE TABLE `players` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `TeamId` BIGINT,
  `Name` CHAR(30) NOT NULL,
  `Surname` CHAR(50) NOT NULL,
  `GamesPlayed` INTEGER NULL DEFAULT NULL,
  `TimePlayed` INTEGER NULL DEFAULT NULL,
  `Role` CHAR(1) NOT NULL,
  `Number` INTEGER NULL DEFAULT NULL,
  `TimesAsMainPlayer` INTEGER NULL DEFAULT NULL,
  `GoalCount` INTEGER NULL DEFAULT NULL,
  `AssistCount` INTEGER NULL DEFAULT NULL,
  `YellowCards` INTEGER NULL DEFAULT NULL,
  `RedCards` INTEGER NULL DEFAULT NULL,

  PRIMARY KEY (`Id`)
);


DROP TABLE IF EXISTS `referees`;
CREATE TABLE `referees` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` CHAR(30) NOT NULL,
  `Surname` CHAR(50) NOT NULL,
  `AssignedPenalties` INTEGER NULL DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `penalties`;
CREATE TABLE `penalties` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `GameId` BIGINT,
  `Time` CHAR(6) NULL DEFAULT NULL,
  `PlayerId` BIGINT,

  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `goals`;
CREATE TABLE `goals` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `GameId` BIGINT,
  `Time` CHAR(6) NULL DEFAULT NULL,
  `PlayerId` BIGINT ,
  `Type` CHAR(1) NULL DEFAULT NULL,
  `AssistsId` BIGINT,

  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `assists`;
CREATE TABLE `assists` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `GameId`BIGINT,
  `PlayerId` BIGINT,

  PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `replacements`;
CREATE TABLE `replacements` (
  `Id` BIGINT NOT NULL AUTO_INCREMENT,
  `GameId` BIGINT,
  `PlayerOneId` BIGINT,
  `PlayerTwoId` BIGINT,
  PRIMARY KEY (`Id`)

);

ALTER TABLE `game` ADD FOREIGN KEY (TeamOneId) REFERENCES `team` (`id`) ON DELETE CASCADE;
ALTER TABLE `game` ADD FOREIGN KEY (TeamTwoId) REFERENCES `team` (`id`) ON DELETE CASCADE;
ALTER TABLE `game` ADD FOREIGN KEY (RefereeOneId) REFERENCES `referees` (`id`) ON DELETE CASCADE;
ALTER TABLE `game` ADD FOREIGN KEY (RefereeTwoId) REFERENCES `referees` (`id`) ON DELETE CASCADE;
ALTER TABLE `game` ADD FOREIGN KEY (MainRefereeId) REFERENCES `referees` (`id`) ON DELETE CASCADE;

ALTER TABLE `players` ADD FOREIGN KEY (TeamId) REFERENCES `team` (`id`) ON DELETE CASCADE;

ALTER TABLE `goals` ADD FOREIGN KEY (AssistsId) REFERENCES `assists` (`id`) ON DELETE CASCADE;
ALTER TABLE `goals` ADD FOREIGN KEY (PlayerId) REFERENCES `Players` (`id`) ON DELETE CASCADE;
