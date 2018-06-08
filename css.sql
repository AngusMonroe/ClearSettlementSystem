CREATE DATABASE `css` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `recharge` (
  `requestID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `requestTime` datetime NOT NULL,
  `amount` float NOT NULL,
  `method` tinyint(4) NOT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `trade` (
  `requestID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `merchantID` varchar(50) NOT NULL,
  `requestTime` datetime NOT NULL,
  `amount` float NOT NULL,
  `operateStatus` tinyint(4) NOT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `withdraw` (
  `requestID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `requestTime` datetime NOT NULL,
  `amount` float NOT NULL,
  `method` tinyint(4) NOT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
