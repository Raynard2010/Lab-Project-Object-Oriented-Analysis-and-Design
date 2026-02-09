-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2025 at 05:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `govlash laundry`
--

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `notificationid` int(11) NOT NULL,
  `recipientid` int(11) DEFAULT NULL,
  `notificationmessage` varchar(100) DEFAULT NULL,
  `createdat` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `isread` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`notificationid`, `recipientid`, `notificationmessage`, `createdat`, `isread`) VALUES
(2, 6, 'Your order is finished and ready for pickup. Thank you for choosing our service!', '2025-12-15 13:00:17', 0),
(4, 6, 'Your order is finished and ready for pickup. Thank you for choosing our service!', '2025-12-15 13:00:20', 0);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `serviceid` int(11) NOT NULL,
  `servicename` varchar(51) DEFAULT NULL,
  `servicedescription` varchar(251) DEFAULT NULL,
  `serviceprice` double DEFAULT NULL,
  `serviceduration` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`serviceid`, `servicename`, `servicedescription`, `serviceprice`, `serviceduration`) VALUES
(1, 'Bentar', 'Ya bentar doang', 1, 1),
(2, 'Sedeng', 'Ya agak lama dikit lah', 2.5, 3),
(3, 'Lama', 'Lama banget jir', 3.333, 100),
(4, 'Premium super', 'Premium bangetttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt', 67, 30),
(5, 'ini editan', 'editan', 69, 4);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transactionid` int(11) NOT NULL,
  `serviceid` int(11) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  `receptionistid` int(11) DEFAULT NULL,
  `laundrystaffid` int(11) DEFAULT NULL,
  `transactiondate` date DEFAULT NULL,
  `transactionstatus` varchar(20) DEFAULT NULL,
  `totalweight` double DEFAULT NULL,
  `transactionnotes` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionid`, `serviceid`, `customerid`, `receptionistid`, `laundrystaffid`, `transactiondate`, `transactionstatus`, `totalweight`, `transactionnotes`) VALUES
(1, 1, 6, 12, 12, '2025-12-08', 'Finished', 100, 'Berat banget'),
(2, 3, 6, 14, 12, '2025-12-12', 'Pending', 20, 'Cepetan ya'),
(3, 2, 6, 14, 12, '2025-12-12', 'Finished', 12, 'Tes'),
(4, 2, 6, 15, 26, '2025-12-12', 'Pending', 12, 'Tes'),
(5, 2, 6, NULL, NULL, '2025-12-12', 'Pending', 23, 'TESSSSSS'),
(6, 1, 6, NULL, NULL, '2025-12-12', 'Pending', 3, 'Bentarrrrrrr'),
(7, 5, 6, NULL, NULL, '2025-12-12', 'Pending', 5, '5');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `userEmail` varchar(50) DEFAULT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `userGender` varchar(10) DEFAULT NULL,
  `userDOB` date DEFAULT NULL,
  `userRole` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userGender`, `userDOB`, `userRole`) VALUES
(3, 'aaaaaaaaa', 'aaaaa@email.com', 'admin123', 'Female', '2025-10-27', 'Customer'),
(4, 'aaaaaaaaaa', 'bbbbbbb@email.com', 'b123', 'Male', '2025-10-27', 'Customer'),
(5, 'cccccccc', 'cccccccc', 'aaaaaaaa', 'Male', '2025-12-05', 'Customer'),
(6, 'Bayu', 'a@email.com', 'admin123', 'Male', '2013-11-27', 'Customer'),
(7, 'Bayu', 'a@email.com', 'admin123', 'Male', '2013-11-27', 'Customer'),
(8, 'Bayu', 'a@email.com', 'admin123', 'Male', '2013-11-27', 'Customer'),
(9, 'Bayu', 'a@email.com', 'admin123', 'Female', '2008-11-20', 'Customer'),
(10, 'admin1', 'a1@email.com', 'admin123', 'Male', '1995-11-16', 'Admin'),
(11, 'admin2', 'a2@email.com', 'admin123', 'Male', '1995-11-16', 'Admin'),
(12, 'ls1', 'ls1@email.com', 'admin123', 'Male', '1995-11-16', 'Laundry Staff'),
(13, 'ls2', 'ls2@email.com', 'admin123', 'Male', '1995-11-16', 'Laundry Staff'),
(14, 'rece1', 'r1@email.com', 'admin123', 'Male', '1995-11-16', 'Receptionist'),
(15, 'rece2', 'r2@email.com', 'admin123', 'Male', '1995-11-16', 'Receptionist'),
(16, 'a', '', '', 'Male', '2025-11-29', 'Customer'),
(17, 'afefefefe', 'fefe@email.com', 'admin123', 'Female', '2006-11-29', 'Customer'),
(18, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaa@email.com', 'admin1', 'Male', '2007-10-16', 'Customer'),
(20, 'mimin', 'a@govlash.com', 'admin1', 'Female', '2006-11-23', 'Admin'),
(21, 'sasa', 's@govlash.com', 'admin1', 'Female', '2008-11-26', 'Laundry Staff'),
(22, 'rere', 'r@govlash.com', 'admin1', 'Female', '2008-11-26', 'Receptionist'),
(23, 'aaa', 'aa@govlash.com', 'aaaaaaaaaaaaa', 'Male', '2001-11-15', 'Admin'),
(24, 'tes', 'tes1@email.com', 'admin1', 'Male', '2007-12-11', 'Customer'),
(25, 'aaaaaaaaaaaaaaaaaaaaaaa', '3r32r23@email.com', 'admin1', 'Male', '2012-10-24', 'Customer'),
(26, 'adadadadada', 'adadadaad@govlash.com', 'admin1', 'Male', '2007-12-12', 'Laundry Staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notificationid`),
  ADD KEY `recipientid` (`recipientid`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`serviceid`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transactionid`),
  ADD KEY `serviceid` (`serviceid`),
  ADD KEY `customerid` (`customerid`),
  ADD KEY `receptionistid` (`receptionistid`),
  ADD KEY `laundrystaffid` (`laundrystaffid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `notificationid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `serviceid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transactionid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`recipientid`) REFERENCES `users` (`userID`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`serviceid`) REFERENCES `services` (`serviceid`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`customerid`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`receptionistid`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `transactions_ibfk_4` FOREIGN KEY (`laundrystaffid`) REFERENCES `users` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
