-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 25, 2016 at 04:01 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `matching`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblMatched`
--

CREATE TABLE IF NOT EXISTS `tblMatched` (
`MatchedID` int(11) NOT NULL,
  `MyUserID` int(11) NOT NULL,
  `OtherUserID` int(11) NOT NULL,
  `MyLike` bit(1) NOT NULL DEFAULT b'0',
  `OtherLike` bit(1) NOT NULL DEFAULT b'0',
  `MySpecialLike` bit(1) NOT NULL DEFAULT b'0',
  `OtherSpecialLike` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `tblMatched`
--

INSERT INTO `tblMatched` (`MatchedID`, `MyUserID`, `OtherUserID`, `MyLike`, `OtherLike`, `MySpecialLike`, `OtherSpecialLike`) VALUES
(1, 1, 2, b'1', b'1', b'0', b'0'),
(2, 1, 3, b'0', b'0', b'1', b'1'),
(3, 1, 4, b'1', b'1', b'0', b'0'),
(4, 1, 6, b'1', b'1', b'0', b'0'),
(5, 1, 5, b'1', b'1', b'0', b'0'),
(6, 2, 3, b'1', b'0', b'0', b'0'),
(7, 1, 7, b'1', b'1', b'0', b'0'),
(8, 2, 6, b'0', b'0', b'1', b'0'),
(9, 2, 4, b'0', b'0', b'1', b'0'),
(10, 3, 2, b'1', b'0', b'0', b'0'),
(11, 4, 2, b'0', b'0', b'1', b'0'),
(12, 1, 8, b'1', b'0', b'0', b'0'),
(13, 1, 9, b'0', b'0', b'1', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `tblMessages`
--

CREATE TABLE IF NOT EXISTS `tblMessages` (
`MessageID` int(11) NOT NULL,
  `ChatRoomID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Message` text COLLATE utf8_unicode_ci NOT NULL,
  `CreateDateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tblMessages`
--

INSERT INTO `tblMessages` (`MessageID`, `ChatRoomID`, `UserID`, `Message`, `CreateDateTime`) VALUES
(1, 12, 2, '1234567', '2016-10-13 08:29:16'),
(2, 12, 2, '1234567', '2016-10-13 08:30:51'),
(3, 12, 2, '1234567', '2016-10-13 08:36:07'),
(4, 12, 2, '1234567', '2016-10-13 08:39:19'),
(5, 12, 2, '1234567', '2016-10-13 08:40:41'),
(6, 12, 2, '1234567', '2016-10-13 08:41:37'),
(7, 12, 2, '1234567', '2016-10-18 04:39:35');

-- --------------------------------------------------------

--
-- Table structure for table `tblMyFriends`
--

CREATE TABLE IF NOT EXISTS `tblMyFriends` (
`FriendID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FirstName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `LastName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Gender` bit(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `tblMyFriends`
--

INSERT INTO `tblMyFriends` (`FriendID`, `UserID`, `Email`, `FirstName`, `LastName`, `Name`, `Gender`) VALUES
(1, 1, '0', 'aaa', 'bbb', 'Thanh', b'1'),
(2, 1, 'aaa@aaa.com', 'aaa', 'bbb', 'Thanh', b'1'),
(3, 1, 'aaa@aaa.com', 'aaa', 'bbb', 'Thanh', b'1'),
(4, 1, 'aaa@aaa.com', 'aaa', 'bbb', 'Thanh', b'1'),
(5, 1, 'aaa@aaa.com', 'aaa', 'bbb', 'Thanh', b'1'),
(6, 1, 'aaa@aaa.com', 'aaa', 'bbb', 'Thanh', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `tblPhotoUsers`
--

CREATE TABLE IF NOT EXISTS `tblPhotoUsers` (
`PhotoID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `ImageUser` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ImageSize` int(11) DEFAULT NULL,
  `Width` int(4) DEFAULT NULL,
  `Height` int(4) DEFAULT NULL,
  `Options` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=32 ;

--
-- Dumping data for table `tblPhotoUsers`
--

INSERT INTO `tblPhotoUsers` (`PhotoID`, `UserID`, `ImageUser`, `ImageSize`, `Width`, `Height`, `Options`) VALUES
(1, 1, 't.jpg', 100, 300, 200, 0),
(2, 1, '', 100, 300, 200, 0),
(3, 1, '', 100, 300, 200, 0),
(4, 1, 'aaaa.png', 100, 300, 200, 1),
(5, 3, 'upload/11330021_715964055192779_3123308806699313430_n.jpg', 100, 300, 200, 2),
(6, 1, 'upload/11330021_715964055192779_3123308806699313430_n.jpg', 100, 300, 200, 2),
(7, 2, 'bbbb.png', 100, 300, 200, 1),
(8, 1, '', 100, 300, 200, 0),
(9, 1, 'upload/tinhdautram.png', 100, 300, 200, 2),
(10, 2, 'upload/tinhdautram.png', 100, 300, 200, 2),
(11, 8, '', NULL, NULL, NULL, 0),
(12, 8, 'aaajj.png', NULL, NULL, NULL, 1),
(13, 1, 'upload/IMG_01112016_144632.png', NULL, NULL, NULL, 2),
(14, 10, 'upload/IMG_01112016_144632.png', NULL, NULL, NULL, 2),
(15, 10, 'upload/IMG_01112016_144632.png', NULL, NULL, NULL, 2),
(16, 10, 'thanh.png', NULL, NULL, NULL, 1),
(17, 10, '', NULL, NULL, NULL, 0),
(18, 10, '', NULL, 300, 100, 0),
(19, 10, 'upload/11330021_715964055192779_3123308806699313430_n.jpg', 300, 300, 100, 2),
(20, 12, 'upload/meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(21, 12, 'upload/meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(22, 12, 'upload/meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(23, 12, 'upload/meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg1478849982', NULL, NULL, NULL, 2),
(24, 12, 'upload/1478850125meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(25, 12, 'upload/1478850136meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(26, 12, 'upload/1478855184meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(27, 12, 'upload/1478855191meo-lam-dep-da-tri-mun-voi-tinh-dau-tram.1.jpg', NULL, NULL, NULL, 2),
(28, 0, '', NULL, NULL, NULL, 0),
(29, 0, 'a', NULL, NULL, NULL, 1),
(30, 0, '', NULL, NULL, NULL, 12),
(31, 0, '', NULL, NULL, NULL, 12);

-- --------------------------------------------------------

--
-- Table structure for table `tblUsers`
--

CREATE TABLE IF NOT EXISTS `tblUsers` (
`UserID` int(11) NOT NULL,
  `FcmRegisteredID` text COLLATE utf8_unicode_ci NOT NULL,
  `AccessToken` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FacebookID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `UserName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `FirstName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `LastName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Gender` bit(1) NOT NULL DEFAULT b'0',
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `BirthDay` datetime NOT NULL,
  `Password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Height` int(11) DEFAULT NULL,
  `Weight` int(11) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ChangedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LoginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Skills` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Hobby` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `No` int(11) DEFAULT NULL,
  `Address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HomeTown` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Language` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BloodType` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Job` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Income` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Literacy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Genitive` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Extrovert` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Attraction` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Drinking` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Smoking` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Siblings` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WhoLivesWith` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Holiday` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WhenMarriage` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Requirement` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaritalStatus` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HavingChildren` bit(1) NOT NULL,
  `CriteriaConsidered` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WantAppointments` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CostForFirstAppointments` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Status` int(1) NOT NULL DEFAULT '0',
  `PushStatus` bit(1) NOT NULL DEFAULT b'0',
  `Violation` bit(1) NOT NULL DEFAULT b'0',
  `Visitors` int(11) NOT NULL DEFAULT '0',
  `IsImage` bit(1) NOT NULL DEFAULT b'0',
  `Other` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `tblUsers`
--

INSERT INTO `tblUsers` (`UserID`, `FcmRegisteredID`, `AccessToken`, `FacebookID`, `UserName`, `FirstName`, `LastName`, `Gender`, `Email`, `BirthDay`, `Password`, `Height`, `Weight`, `RegisteredDate`, `ChangedDate`, `LoginTime`, `Skills`, `Hobby`, `No`, `Address`, `HomeTown`, `Language`, `BloodType`, `Job`, `Income`, `Literacy`, `Genitive`, `Extrovert`, `Attraction`, `Drinking`, `Smoking`, `Siblings`, `WhoLivesWith`, `Holiday`, `WhenMarriage`, `Requirement`, `MaritalStatus`, `HavingChildren`, `CriteriaConsidered`, `WantAppointments`, `CostForFirstAppointments`, `Status`, `PushStatus`, `Violation`, `Visitors`, `IsImage`, `Other`) VALUES
(1, 'cfK23SLP4jo:APA91bHk1I3-Rem2oLsZ4_osrCPz2tXujziLJXZiPehla2F1GSsiutAv47ZtDuqhOz5JqYfcFqnZNkwSIdUgRx9k5yqzHGzZ6_e3oWL8cPAmai3Z5dTXQSv_6MdkBGT0WRWL2pAlqYID', '', '', '1', '2', '3', b'1', 'tmt@yahoo.com', '2016-10-01 00:00:00', '1', 100, 150, '0000-00-00 00:00:00', '2016-10-01 00:00:00', '0000-00-00 00:00:00', '100', 'ok', 0, 'Ha Noi', '0,2', 'En', 'A', 'Ha', '10', 'fff', 'aa', 'bb', 'cc', 'dd', 'ee', 'gg', 'mm', 'nnn', 'jjj', 'kkk', 'klll', b'1', 'ggg', '12', '1', 1, b'1', b'1', 8, b'1', NULL),
(2, 'ca4r9ZNyHjw:APA91bFlRK4El0WoDRO52i_UO82mwaW-YvK_PGWO9EZqMeapG7XGi2nI4SAdmmuC7sXt3tiF6-5b_YfxBuxXTdh2unuObKZF1tDtdiy20DP3dYyBkRAGDBETX_kEeqkhzOthB4T9F5uY', '2', '', 'owner', 'owner', 'owner', b'0', 'owner', '0000-00-00 00:00:00', 'c', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 2, b'1', b'0', 0, b'1', NULL),
(3, 'c4GmcLjwGMA:APA91bEC9iIW_xV-ZLgoS1mcHUtED23Rp5NFStTSpya-nEqRruprvph6HQKmLeBX4dh5eg82EC9vy1rQdqcw8BYrpBCMXZM4oRE9ddLaAgugYsSbZNWOjmswKO5uPlivZ7m0sS45aTQf', '3', '', 'eee', '', '', b'0', '', '2015-03-31 00:00:00', '', 190, 60, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', NULL, NULL, 0, NULL, '0,7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'1', NULL),
(4, 'aa', 'a', '', 'a', 'a', 'aa', b'0', 'a@a.com', '2014-11-25 00:00:00', NULL, 123, 11, '2016-10-26 15:55:56', '2016-10-26 15:55:56', '2016-10-26 15:55:56', NULL, NULL, 1, NULL, '0,4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(5, '111111111', '5', '', 'aaaabbb', 'aaaa', 'bbbs', b'1', 'a1@a.com', '2015-06-23 00:00:00', NULL, 123, 44, '2016-10-26 16:07:31', '2016-10-26 16:07:31', '2016-10-26 16:07:31', NULL, NULL, 2, NULL, '0,6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(6, '111111111', '6', '', 'aaaabbb', 'aaaa', 'bbbs', b'1', 'a2@a.com', '2014-06-18 00:00:00', NULL, 123, 22, '2016-10-26 16:10:07', '2016-10-26 16:10:07', '2016-10-26 16:10:07', NULL, NULL, 3, NULL, '0,5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(8, '111111111', '23232123ab', '', 'aaaabbb', 'aaaa', 'bbbs', b'1', 'a2@aaaa.com', '2014-11-10 00:00:00', 'd', 160, 50, '2016-10-28 16:14:40', '2016-10-28 16:14:40', '2016-10-28 16:14:40', NULL, NULL, NULL, NULL, '0,5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'1', NULL),
(10, '1', '232321', '', 'aaaa', 'bbbs', '01/01/2015', b'0', 'aaaabbb', '2011-11-11 11:01:00', '1212aaa', 150, 20, '2016-10-28 16:22:22', '2016-10-28 16:22:22', '2016-10-28 16:22:22', NULL, NULL, NULL, NULL, '0,4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'1', NULL),
(11, 'owner', '', '', 'HoÃ ng ÄÃ¬nh Tuáº¥n', 'Tuáº¥n', 'HoÃ ng', b'0', 'co0l_b0y_4n_kh04i@yahoo.com.vn', '2014-11-02 00:00:00', 'a', 180, 20, '2016-10-28 16:53:46', '2016-10-28 16:53:46', '2016-10-28 16:53:46', NULL, NULL, NULL, NULL, '0,1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(12, 'hhhh', '2323tff', '', 'ass', 'ggg', 'bbb', b'0', 'a3@aaaa.com', '2013-10-01 00:00:00', NULL, 170, 70, '2016-10-31 10:37:07', '2016-10-31 10:37:07', '2016-10-31 10:37:07', NULL, NULL, NULL, NULL, '0,1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'1', NULL),
(13, 'yes', 'yes', '', 'yes', 'yes', 'yes', b'1', 'aaa@gmail.com', '2014-11-24 00:00:00', NULL, 150, 44, '2016-11-01 16:14:42', '2016-11-01 16:14:42', '2016-11-01 16:14:42', NULL, NULL, NULL, NULL, '0,1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(14, 'a', 'a', '', 'a', 'a', 'a', b'0', 'a@yahoo.com', '0000-00-00 00:00:00', NULL, 200, 55, '2016-11-01 16:24:00', '2016-11-01 16:24:00', '2016-11-01 16:24:00', NULL, NULL, NULL, NULL, '0,3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(15, 'ds', 'ddsada', '', 'dssd', 'ds', 'ds', b'0', '2@2.com', '2016-01-01 00:00:00', NULL, 129, 66, '2016-11-04 16:20:58', '2016-11-04 16:20:58', '2016-11-04 16:20:58', NULL, NULL, 4, NULL, '0,2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(16, '1', '12', '', '21', '12', '21', b'1', '1@1.com', '2016-01-10 00:00:00', NULL, NULL, NULL, '2016-11-14 11:51:51', '2016-11-14 11:51:51', '2016-11-14 11:51:51', NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(17, '1', '12', '', '21', '12', '21', b'1', '1@12.com', '2016-01-10 00:00:00', NULL, NULL, NULL, '2016-11-14 12:00:44', '2016-11-14 12:00:44', '2016-11-14 12:00:44', NULL, NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(18, '1', '', '123', '21', '12', '21', b'1', '1@121.com', '2016-01-10 00:00:00', NULL, NULL, NULL, '2016-11-14 13:19:48', '2016-11-14 13:19:48', '2016-11-14 13:19:48', NULL, NULL, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(19, '1', '1234a', '1234', 'd', 'b', 'c', b'1', '1@1221.com', '2016-09-20 00:00:00', NULL, NULL, NULL, '2016-11-14 13:39:43', '2016-09-20 00:00:00', '2016-11-14 13:39:43', NULL, NULL, 8, '321', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', '123'),
(20, 'a', 'ab', 's', 'a', 'a', 'a', b'0', 'ab@yahoo.com', '0000-00-00 00:00:00', NULL, NULL, NULL, '2016-11-14 14:36:43', '2016-11-14 14:36:43', '2016-11-14 14:36:43', NULL, NULL, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(21, 'a', 'abc', 'sc', 'a@#$%^''()', 'a', 'a', b'0', 'abc@yahoo.com', '0000-00-00 00:00:00', NULL, NULL, NULL, '2016-11-14 14:37:14', '2016-11-14 14:37:14', '2016-11-14 14:37:14', NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(22, 'owner', 'EAAJnYwHEiPgBACNaWBnQzmiscwWcuuAaZAukpoez2l9Y8ZCd8xdhw7i7NbvmopnTyQnlzbsHwMSDVZBsp0CoNwDe58vU6y99YIP9tFhfj7GJA1ghF1RkuhCofWyifdhMH8txW8ZCL2XEV2bsTXuq3sEpLWdEkQClaMExPwji8wb2l40mXtZAJICfsKwQPGZCXM5cxxvj6ydoN9BM0RbZBZCQ', '1555367067823706', 'Hnam Qnauq Mahp', 'Hnam', 'Mahp', b'1', 'zannemo@gmail.com', '0000-00-00 00:00:00', NULL, -1, -1, '2016-11-15 09:46:56', '1799-01-01 00:00:00', '2016-11-15 09:46:56', NULL, NULL, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '-1', '-1', NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(23, 'owner', 'EAAJnYwHEiPgBAJCATsGH2ZCP5J4UbaBZCLecZARUkZCytlXaqmaNHVEZBvwXtehlP9w26bMZCxlIeL2TgJFYsjL2d1xeNnDnGnl1PwiPfpbgbdilHMpkZCpRUozToT9wr5Fy8m3uLgLyGkG3cb8XnGJLx2lswHxhSvoviBgmvHv21I11EQOSdk12rrfmahRDiOMOxP0gCL0d66SCiX9e3Yv', '1490609404289490', 'Thiáº¿t Nguyá»…n Tháº¡c', 'Thiáº¿t', 'Nguyá»…n Tháº¡c', b'0', 'thacthiet_acmilan@yahoo.com', '0000-00-00 00:00:00', NULL, NULL, NULL, '2016-11-15 09:57:10', '2016-11-15 09:57:10', '2016-11-15 09:57:10', NULL, NULL, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(24, 'owner', 'EAAJnYwHEiPgBAK2QS6cegkERVLbursv4Gr07Qk0gGCktXuNG14sBGXpMc0PrY8bIQRhCAMrFdDQV7ys6M9EATtrmnsrTGZA8LPJndIqTpKtqYFZAn5ZBZCaEr968w3xI0UC458dixfE4oUpTDWvZCY8PKQkZBkN5ZAxhKJ5wzGqBE1hTiZAxlXZAs1T4Q6n4EmblZAXcKtdvwRTnryQk7p14P4', '1718897855096859', 'HoÃ ng Tuáº¥n', 'HoÃ ng', 'Tuáº¥n', b'1', 'hbinh08@gmail.com', '0000-00-00 00:00:00', NULL, 369, 365, '2016-11-15 11:27:17', '1799-01-01 00:00:00', '2016-11-15 11:27:17', NULL, 'sport', 13, 'á»ž Ä‘Ã¢u cÃ²n lÃ¢u má»›i nÃ³i', NULL, '2,1', 'AB', 'CÅ©ng nhÆ° trÃªn luÃ´n(Ä‘á»‹a chá»‰)', '1500', NULL, '8,7,5,3,1', 'Táº¥t cáº£', '1,6,7,8,9', '0', '1', NULL, NULL, NULL, 'DÆ°á»›i 3 thÃ¡ng', NULL, NULL, b'0', NULL, NULL, '300000', 0, b'0', b'0', 0, b'0', 'HoÃ i nghi táº¥t cáº£!\nðŸ˜‡ðŸ˜†ðŸ˜…ðŸ˜„ðŸ˜ƒðŸ˜ðŸ˜™\nNgÃ y mai sáº½ láº¡i Ä‘áº¿n, hay cá»© vui lÃªn cho dÃ¹ mai báº¡n khÃ´ng cÃ²n gÃ¬ trong tay cáº£.\n\n\nðŸŒ»ðŸŒ»ðŸŒ»ðŸŒ»ðŸŒ»'),
(25, 'vvv', '76', '1a1a1', 'mm', 'nn', 'gg', b'1', 'w@w.com', '0000-00-00 00:00:00', NULL, NULL, NULL, '2016-11-15 17:28:00', '2016-11-15 17:28:00', '2016-11-15 17:28:00', NULL, NULL, 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', NULL),
(26, 'owner', 'EAAJnYwHEiPgBAHkQOyTcg9XqaZB7fGS0tv3c1iitusRj62bt5krm8PhZBuzUOtGwkfLZCzo1v5NbRjoahTqmr2ZBTeomDaZCq7ac9FdbrGzNNT5FxH671gARSaNfvBzj4tN7Bart4BGJh6VDmKHVWEEtNqxsKxMIaSe2pri0SW7gC6QHbBbzoVljnOWMyZANy0d7kzWRrkFLXCuU1a1Jjz', '126370267843451', 'Nam Nguyen', 'Nam', 'Nguyen', b'1', 'ienterasia.test1@gmail.com', '0000-00-00 00:00:00', NULL, -1, -1, '2016-11-16 09:39:34', '1799-01-01 00:00:00', '2016-11-16 09:39:34', NULL, 'bullshit', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '-1', '-1', NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, 0, b'0', b'0', 0, b'0', 'hahahahaha\nðŸ˜ŽðŸ˜›ðŸ˜žðŸ˜’ðŸ˜‘ðŸ˜ðŸ˜™');

-- --------------------------------------------------------

--
-- Table structure for table `tblViolations`
--

CREATE TABLE IF NOT EXISTS `tblViolations` (
`ViolationID` int(11) NOT NULL,
  `MyUserID` int(11) NOT NULL,
  `OtherUserID` int(11) NOT NULL,
  `Content` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tblViolations`
--

INSERT INTO `tblViolations` (`ViolationID`, `MyUserID`, `OtherUserID`, `Content`) VALUES
(1, 2, 1, 'OK'),
(2, 2, 1, 'OK'),
(3, 2, 1, 'OK1'),
(4, 2, 1, 'OK1');

-- --------------------------------------------------------

--
-- Table structure for table `tblVisitors`
--

CREATE TABLE IF NOT EXISTS `tblVisitors` (
`VisitedID` int(11) NOT NULL,
  `MyUserID` int(11) NOT NULL,
  `OtherUserID` int(11) NOT NULL,
  `VisitorDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=189 ;

--
-- Dumping data for table `tblVisitors`
--

INSERT INTO `tblVisitors` (`VisitedID`, `MyUserID`, `OtherUserID`, `VisitorDate`) VALUES
(1, 1, 2, '2016-11-04 16:03:38'),
(2, 2, 1, '2016-11-04 16:03:38'),
(3, 3, 1, '2016-11-07 09:52:17'),
(4, 3, 1, '2016-11-07 09:55:54'),
(5, 3, 2, '2016-11-07 09:56:06'),
(6, 2, 2, '2016-11-17 11:35:57'),
(7, 2, 2, '2016-11-17 11:48:27'),
(8, 2, 2, '2016-11-17 11:50:31'),
(9, 2, 2, '2016-11-17 11:52:21'),
(10, 2, 2, '2016-11-17 11:54:29'),
(11, 2, 2, '2016-11-17 11:56:53'),
(12, 2, 2, '2016-11-17 11:57:16'),
(13, 2, 2, '2016-11-17 13:16:52'),
(14, 2, 2, '2016-11-17 13:17:04'),
(15, 2, 2, '2016-11-17 15:51:00'),
(16, 2, 2, '2016-11-17 15:55:40'),
(17, 26, 26, '2016-11-17 15:58:54'),
(18, 26, 26, '2016-11-17 16:02:19'),
(19, 24, 24, '2016-11-17 16:03:42'),
(20, 24, 24, '2016-11-17 16:10:30'),
(21, 24, 24, '2016-11-17 16:16:59'),
(22, 24, 24, '2016-11-17 16:25:34'),
(23, 24, 24, '2016-11-17 16:29:07'),
(24, 24, 24, '2016-11-17 16:35:54'),
(25, 24, 24, '2016-11-17 16:37:58'),
(26, 24, 24, '2016-11-17 16:40:04'),
(27, 24, 24, '2016-11-17 16:42:01'),
(28, 24, 24, '2016-11-17 16:43:11'),
(29, 24, 24, '2016-11-17 16:47:30'),
(30, 24, 24, '2016-11-17 16:49:52'),
(31, 24, 24, '2016-11-17 16:52:14'),
(32, 24, 24, '2016-11-17 16:53:49'),
(33, 24, 24, '2016-11-17 16:55:24'),
(34, 24, 24, '2016-11-17 16:56:01'),
(35, 24, 24, '2016-11-17 16:58:49'),
(36, 24, 24, '2016-11-17 16:59:25'),
(37, 24, 24, '2016-11-17 17:01:24'),
(38, 24, 24, '2016-11-17 17:08:26'),
(39, 24, 24, '2016-11-17 17:09:00'),
(40, 24, 24, '2016-11-17 17:12:39'),
(41, 24, 24, '2016-11-17 17:13:38'),
(42, 24, 24, '2016-11-17 17:15:56'),
(43, 24, 24, '2016-11-17 17:18:43'),
(44, 24, 24, '2016-11-17 17:21:03'),
(45, 24, 24, '2016-11-17 17:23:16'),
(46, 24, 24, '2016-11-17 17:24:42'),
(47, 24, 24, '2016-11-17 17:25:54'),
(48, 24, 24, '2016-11-17 17:27:59'),
(49, 24, 24, '2016-11-17 17:28:22'),
(50, 24, 24, '2016-11-17 17:32:11'),
(51, 24, 24, '2016-11-17 17:33:37'),
(52, 24, 24, '2016-11-17 17:35:25'),
(53, 24, 24, '2016-11-17 17:38:31'),
(54, 24, 24, '2016-11-17 17:38:49'),
(55, 24, 24, '2016-11-17 17:39:43'),
(56, 24, 24, '2016-11-17 17:42:27'),
(57, 24, 24, '2016-11-17 17:45:33'),
(58, 24, 24, '2016-11-17 17:52:36'),
(59, 24, 24, '2016-11-17 17:53:37'),
(60, 24, 24, '2016-11-17 17:55:57'),
(61, 24, 24, '2016-11-17 17:58:38'),
(62, 24, 24, '2016-11-17 17:59:27'),
(63, 24, 24, '2016-11-17 18:00:19'),
(64, 24, 24, '2016-11-17 18:00:56'),
(65, 24, 24, '2016-11-17 18:02:20'),
(66, 24, 24, '2016-11-17 18:03:31'),
(67, 24, 24, '2016-11-17 18:04:55'),
(68, 24, 24, '2016-11-17 18:06:44'),
(69, 24, 24, '2016-11-17 18:09:07'),
(70, 24, 24, '2016-11-17 18:11:05'),
(71, 24, 24, '2016-11-21 10:00:00'),
(72, 24, 24, '2016-11-21 10:02:51'),
(73, 24, 24, '2016-11-21 10:05:20'),
(74, 24, 24, '2016-11-21 10:16:52'),
(75, 24, 24, '2016-11-21 10:19:14'),
(76, 24, 24, '2016-11-21 10:22:43'),
(77, 24, 24, '2016-11-21 10:25:57'),
(78, 24, 24, '2016-11-21 10:26:15'),
(79, 24, 24, '2016-11-21 10:31:31'),
(80, 24, 24, '2016-11-21 10:34:30'),
(81, 24, 24, '2016-11-21 10:37:20'),
(82, 24, 24, '2016-11-21 10:41:29'),
(83, 24, 24, '2016-11-21 10:43:11'),
(84, 24, 24, '2016-11-21 10:44:56'),
(85, 24, 24, '2016-11-21 10:45:48'),
(86, 24, 24, '2016-11-21 10:51:28'),
(87, 24, 24, '2016-11-21 10:52:44'),
(88, 24, 24, '2016-11-21 10:54:41'),
(89, 24, 24, '2016-11-21 10:56:30'),
(90, 24, 24, '2016-11-21 10:57:42'),
(91, 24, 24, '2016-11-21 10:59:30'),
(92, 24, 24, '2016-11-21 11:01:00'),
(93, 26, 26, '2016-11-21 11:21:23'),
(94, 24, 24, '2016-11-21 11:33:02'),
(95, 24, 24, '2016-11-21 11:36:01'),
(96, 24, 24, '2016-11-21 11:40:35'),
(97, 24, 24, '2016-11-21 11:43:37'),
(98, 24, 24, '2016-11-21 11:44:45'),
(99, 24, 24, '2016-11-21 11:47:35'),
(100, 24, 24, '2016-11-21 13:17:27'),
(101, 24, 24, '2016-11-21 14:17:38'),
(102, 24, 24, '2016-11-21 14:18:28'),
(103, 24, 24, '2016-11-21 14:19:48'),
(104, 24, 24, '2016-11-21 14:27:52'),
(105, 24, 24, '2016-11-21 14:32:54'),
(106, 24, 24, '2016-11-21 14:36:54'),
(107, 24, 24, '2016-11-21 14:47:10'),
(108, 24, 24, '2016-11-21 14:52:21'),
(109, 24, 24, '2016-11-21 14:54:10'),
(110, 24, 24, '2016-11-21 14:58:04'),
(111, 24, 24, '2016-11-21 15:00:12'),
(112, 24, 24, '2016-11-21 15:05:34'),
(113, 24, 24, '2016-11-21 15:39:34'),
(114, 24, 24, '2016-11-21 15:52:17'),
(115, 24, 24, '2016-11-21 15:56:40'),
(116, 24, 24, '2016-11-21 16:04:23'),
(117, 24, 24, '2016-11-21 16:04:50'),
(118, 24, 24, '2016-11-21 16:09:16'),
(119, 24, 24, '2016-11-21 16:10:10'),
(120, 24, 24, '2016-11-21 16:13:53'),
(121, 24, 24, '2016-11-21 16:20:07'),
(122, 24, 24, '2016-11-21 16:28:53'),
(123, 24, 24, '2016-11-21 16:32:24'),
(124, 24, 24, '2016-11-21 16:35:45'),
(125, 24, 24, '2016-11-21 16:36:54'),
(126, 24, 24, '2016-11-21 16:38:32'),
(127, 24, 24, '2016-11-21 16:40:09'),
(128, 24, 24, '2016-11-21 16:55:08'),
(129, 24, 24, '2016-11-21 17:10:35'),
(130, 24, 24, '2016-11-22 09:29:25'),
(131, 26, 26, '2016-11-22 09:59:18'),
(132, 24, 24, '2016-11-22 15:44:18'),
(133, 24, 24, '2016-11-22 15:53:57'),
(134, 24, 24, '2016-11-22 16:12:15'),
(135, 24, 24, '2016-11-22 16:15:00'),
(136, 24, 24, '2016-11-22 16:17:21'),
(137, 24, 24, '2016-11-22 16:18:16'),
(138, 24, 24, '2016-11-22 16:34:48'),
(139, 24, 24, '2016-11-22 16:38:47'),
(140, 24, 24, '2016-11-22 16:40:17'),
(141, 24, 24, '2016-11-22 16:41:17'),
(142, 24, 24, '2016-11-22 16:51:13'),
(143, 24, 24, '2016-11-22 17:08:09'),
(144, 26, 26, '2016-11-22 17:32:14'),
(145, 24, 24, '2016-11-24 09:06:57'),
(146, 24, 24, '2016-11-24 09:09:48'),
(147, 24, 24, '2016-11-24 09:36:41'),
(148, 24, 24, '2016-11-24 10:03:40'),
(149, 21, 0, '2016-11-24 10:26:49'),
(150, 22, 0, '2016-11-24 10:26:56'),
(151, 21, 0, '2016-11-24 10:27:05'),
(152, 0, 0, '2016-11-24 10:27:15'),
(153, 0, 0, '2016-11-24 10:28:13'),
(154, 24, 24, '2016-11-24 10:35:53'),
(155, 26, 26, '2016-11-24 10:38:46'),
(156, 24, 24, '2016-11-24 10:39:24'),
(157, 24, 24, '2016-11-24 10:41:28'),
(158, 24, 24, '2016-11-24 10:49:04'),
(159, 24, 24, '2016-11-24 11:03:17'),
(160, 24, 24, '2016-11-24 11:04:37'),
(161, 24, 24, '2016-11-24 11:11:33'),
(162, 24, 24, '2016-11-24 11:13:43'),
(163, 24, 24, '2016-11-24 11:14:33'),
(164, 24, 24, '2016-11-24 11:18:02'),
(165, 26, 26, '2016-11-24 11:20:17'),
(166, 24, 24, '2016-11-24 11:22:06'),
(167, 24, 24, '2016-11-24 13:02:59'),
(168, 24, 24, '2016-11-24 13:03:21'),
(169, 24, 24, '2016-11-24 13:05:16'),
(170, 24, 24, '2016-11-24 13:06:55'),
(171, 24, 24, '2016-11-24 13:10:12'),
(172, 24, 24, '2016-11-24 13:11:39'),
(173, 24, 24, '2016-11-24 13:13:26'),
(174, 24, 24, '2016-11-24 13:15:43'),
(175, 24, 24, '2016-11-24 14:06:30'),
(176, 24, 24, '2016-11-24 14:16:58'),
(177, 24, 24, '2016-11-24 14:18:54'),
(178, 24, 24, '2016-11-24 14:21:38'),
(179, 24, 24, '2016-11-24 14:22:48'),
(180, 24, 24, '2016-11-24 14:24:58'),
(181, 24, 24, '2016-11-24 14:31:04'),
(182, 24, 24, '2016-11-24 17:24:31'),
(183, 24, 24, '2016-11-24 17:38:05'),
(184, 24, 24, '2016-11-24 17:42:39'),
(185, 24, 24, '2016-11-24 17:43:55'),
(186, 24, 24, '2016-11-24 17:47:35'),
(187, 22, 22, '2016-11-25 09:59:42'),
(188, 22, 22, '2016-11-25 10:00:42');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblMatched`
--
ALTER TABLE `tblMatched`
 ADD PRIMARY KEY (`MatchedID`);

--
-- Indexes for table `tblMessages`
--
ALTER TABLE `tblMessages`
 ADD PRIMARY KEY (`MessageID`);

--
-- Indexes for table `tblMyFriends`
--
ALTER TABLE `tblMyFriends`
 ADD PRIMARY KEY (`FriendID`);

--
-- Indexes for table `tblPhotoUsers`
--
ALTER TABLE `tblPhotoUsers`
 ADD PRIMARY KEY (`PhotoID`);

--
-- Indexes for table `tblUsers`
--
ALTER TABLE `tblUsers`
 ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `tblViolations`
--
ALTER TABLE `tblViolations`
 ADD PRIMARY KEY (`ViolationID`);

--
-- Indexes for table `tblVisitors`
--
ALTER TABLE `tblVisitors`
 ADD PRIMARY KEY (`VisitedID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblMatched`
--
ALTER TABLE `tblMatched`
MODIFY `MatchedID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `tblMessages`
--
ALTER TABLE `tblMessages`
MODIFY `MessageID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `tblMyFriends`
--
ALTER TABLE `tblMyFriends`
MODIFY `FriendID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `tblPhotoUsers`
--
ALTER TABLE `tblPhotoUsers`
MODIFY `PhotoID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `tblUsers`
--
ALTER TABLE `tblUsers`
MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `tblViolations`
--
ALTER TABLE `tblViolations`
MODIFY `ViolationID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tblVisitors`
--
ALTER TABLE `tblVisitors`
MODIFY `VisitedID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=189;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
