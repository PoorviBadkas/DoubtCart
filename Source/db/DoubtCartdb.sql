-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2022 at 11:55 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `resources`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `Id` int(11) NOT NULL,
  `Username` varchar(500) NOT NULL,
  `Description` text NOT NULL,
  `DoubtID` int(11) NOT NULL,
  `IsHelpful` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Table structure for table `badge`
--

CREATE TABLE `badge` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL,
  `Image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `CommentID` int(11) NOT NULL,
  `Comment` varchar(2000) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `cdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `doubt`
--

CREATE TABLE `doubt` (
  `Id` int(11) NOT NULL,
  `Username` varchar(500) DEFAULT NULL,
  `Title` varchar(1000) NOT NULL,
  `Description` text NOT NULL,
  `Point` int(11) NOT NULL,
  `IsClosed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `doubtcategory`
--

CREATE TABLE `doubtcategory` (
  `DoubtID` int(11) NOT NULL,
  `CatID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `doubtcomment`
--

CREATE TABLE `doubtcomment` (
  `ID` int(11) NOT NULL,
  `Comment` varchar(2000) NOT NULL,
  `DoubtID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `ddate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `doubttags`
--

CREATE TABLE `doubttags` (
  `DoubtID` int(11) NOT NULL,
  `TagID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `groupname` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`groupname`) VALUES
('Admin'),
('Moderator'),
('User');

-- --------------------------------------------------------

--
-- Table structure for table `liketb`
--

CREATE TABLE `liketb` (
  `LikeID` int(11) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `UserID` varchar(500) NOT NULL,
  `lDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

CREATE TABLE `resource` (
  `ResourceID` int(11) NOT NULL,
  `Title` varchar(2000) NOT NULL,
  `Description` varchar(4000) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Subject` varchar(1000) NOT NULL,
  `Image` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `resource_files`
--

CREATE TABLE `resource_files` (
  `Id` int(11) NOT NULL,
  `ResourceID` int(11) NOT NULL,
  `URL` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `Id` int(11) NOT NULL,
  `Name` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`Id`, `Name`) VALUES
(1, 'Urgent'),
(2, 'Exam'),
(3, 'Doubt'),
(4, 'Queue'),
(5, 'Stack'),
(6, 'Pointer'),
(7, 'DSA'),
(8, 'C++ Tamplate'),
(9, 'Generics'),
(10, 'Thread'),
(11, 'Datatypes'),
(12, 'Array'),
(13, 'FileIO'),
(14, 'String'),
(15, 'Loop'),
(16, 'Error'),
(17, 'Algorithm'),
(18, 'SwitchCase'),
(19, 'OOP'),
(20, 'Inheritance'),
(21, 'Polimorphism'),
(22, 'AbstractClass'),
(23, 'Encapsulation'),
(24, 'FriendClassORFunction'),
(25, 'Constructor'),
(26, 'Destructor'),
(27, 'Overloading'),
(28, 'Overriding'),
(29, 'Javascript'),
(30, 'Frontend'),
(31, 'Backend'),
(32, 'SQL');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Username` varchar(500) NOT NULL,
  `Email` varchar(2000) NOT NULL,
  `Password` varchar(5000) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Profile` varchar(1000) DEFAULT NULL,
  `OTP` int(11) NOT NULL,
  `Points` int(11) NOT NULL DEFAULT 0,
  `warnings` int(11) NOT NULL DEFAULT 0,
  `isBlocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `userbadge`
--

CREATE TABLE `userbadge` (
  `Username` varchar(500) NOT NULL,
  `BadgeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_groups`
--

CREATE TABLE `user_groups` (
  `groupname` varchar(50) NOT NULL,
  `Username` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `adoubt` (`DoubtID`),
  ADD KEY `auser` (`Username`);

--
-- Indexes for table `badge`
--
ALTER TABLE `badge`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`CommentID`),
  ADD KEY `comr` (`ResourceID`),
  ADD KEY `comu` (`UserID`);

--
-- Indexes for table `doubt`
--
ALTER TABLE `doubt`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `dusernm` (`Username`);

--
-- Indexes for table `doubtcategory`
--
ALTER TABLE `doubtcategory`
  ADD KEY `cat` (`CatID`),
  ADD KEY `doubtc` (`DoubtID`);

--
-- Indexes for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `cdoubt` (`DoubtID`),
  ADD KEY `cduser` (`UserID`);

--
-- Indexes for table `doubttags`
--
ALTER TABLE `doubttags`
  ADD KEY `tag` (`TagID`),
  ADD KEY `tdoubt` (`DoubtID`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`groupname`);

--
-- Indexes for table `liketb`
--
ALTER TABLE `liketb`
  ADD PRIMARY KEY (`LikeID`),
  ADD KEY `liker` (`ResourceID`),
  ADD KEY `likeu` (`UserID`);

--
-- Indexes for table `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`ResourceID`);

--
-- Indexes for table `resource_files`
--
ALTER TABLE `resource_files`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `resfile` (`ResourceID`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `userbadge`
--
ALTER TABLE `userbadge`
  ADD KEY `badge` (`BadgeID`),
  ADD KEY `buser` (`Username`);

--
-- Indexes for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD KEY `guser` (`Username`),
  ADD KEY `groups` (`groupname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `badge`
--
ALTER TABLE `badge`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `doubt`
--
ALTER TABLE `doubt`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `liketb`
--
ALTER TABLE `liketb`
  MODIFY `LikeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `resource`
--
ALTER TABLE `resource`
  MODIFY `ResourceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `resource_files`
--
ALTER TABLE `resource_files`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `adoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`),
  ADD CONSTRAINT `auser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comr` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`),
  ADD CONSTRAINT `comu` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubt`
--
ALTER TABLE `doubt`
  ADD CONSTRAINT `dusernm` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubtcategory`
--
ALTER TABLE `doubtcategory`
  ADD CONSTRAINT `cat` FOREIGN KEY (`CatID`) REFERENCES `category` (`Id`),
  ADD CONSTRAINT `doubtc` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`);

--
-- Constraints for table `doubtcomment`
--
ALTER TABLE `doubtcomment`
  ADD CONSTRAINT `cdoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`),
  ADD CONSTRAINT `cduser` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `doubttags`
--
ALTER TABLE `doubttags`
  ADD CONSTRAINT `tag` FOREIGN KEY (`TagID`) REFERENCES `tags` (`Id`),
  ADD CONSTRAINT `tdoubt` FOREIGN KEY (`DoubtID`) REFERENCES `doubt` (`Id`);

--
-- Constraints for table `liketb`
--
ALTER TABLE `liketb`
  ADD CONSTRAINT `liker` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`),
  ADD CONSTRAINT `likeu` FOREIGN KEY (`UserID`) REFERENCES `user` (`Username`);

--
-- Constraints for table `resource_files`
--
ALTER TABLE `resource_files`
  ADD CONSTRAINT `resfile` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ResourceID`);

--
-- Constraints for table `userbadge`
--
ALTER TABLE `userbadge`
  ADD CONSTRAINT `badge` FOREIGN KEY (`BadgeID`) REFERENCES `badge` (`Id`),
  ADD CONSTRAINT `buser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `groups` FOREIGN KEY (`groupname`) REFERENCES `groups` (`groupname`),
  ADD CONSTRAINT `guser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
