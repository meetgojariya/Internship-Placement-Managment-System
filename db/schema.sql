-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2025 at 06:13 PM
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
-- Database: `project`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `displayAllCompany` ()   BEGIN
SELECT * from company;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `displayAllStudent` ()   BEGIN
SELECT * from student;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `srno` int(10) NOT NULL,
  `Admin_Name` varchar(20) NOT NULL,
  `Admin_Email_Id` varchar(20) NOT NULL,
  `Admin_pass` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `a_id` int(11) NOT NULL,
  `a_job_id` int(11) NOT NULL,
  `a_s_id` int(11) NOT NULL,
  `a_s_name` varchar(20) NOT NULL,
  `a_s_mobileno` bigint(20) NOT NULL,
  `a_s_emailid` varchar(50) NOT NULL,
  `a_skill` varchar(20) NOT NULL,
  `a_jobtype` varchar(20) NOT NULL,
  `a_cgpa` double NOT NULL,
  `a_resume` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(50) NOT NULL,
  `c_location` varchar(20) NOT NULL,
  `c_industrytype` varchar(20) NOT NULL,
  `c_emailid` varchar(50) NOT NULL,
  `c_pass` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `company`
--
DELIMITER $$
CREATE TRIGGER `deleteCompany` BEFORE DELETE ON `company` FOR EACH ROW BEGIN
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `interview`
--

CREATE TABLE `interview` (
  `i_id` int(11) NOT NULL,
  `i_c_id` int(11) NOT NULL,
  `i_c_name` varchar(50) NOT NULL,
  `i_date` date NOT NULL,
  `i_time` time NOT NULL,
  `i_mode` varchar(50) NOT NULL,
  `i_location` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `placement`
--

CREATE TABLE `placement` (
  `job_id` int(11) NOT NULL,
  `job_c_id` int(11) NOT NULL,
  `job_c_name` varchar(20) NOT NULL,
  `job_c_email` varchar(50) NOT NULL,
  `job_title` varchar(20) NOT NULL,
  `job_description` varchar(300) NOT NULL,
  `job_type` varchar(20) NOT NULL,
  `job_location` varchar(20) NOT NULL,
  `salary` double NOT NULL,
  `cgpa_required` double NOT NULL,
  `skills` varchar(20) NOT NULL,
  `vacancies` int(11) NOT NULL,
  `date_posted` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `selected_students`
--

CREATE TABLE `selected_students` (
  `ss_id` int(11) NOT NULL,
  `ss_c_name` varchar(20) NOT NULL,
  `ss_name` varchar(20) NOT NULL,
  `ss_mobileno` bigint(10) NOT NULL,
  `ss_emailid` varchar(50) NOT NULL,
  `ss_skill` varchar(20) NOT NULL,
  `ss_jobtype` varchar(20) NOT NULL,
  `ss_cgpa` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `s_id` int(10) NOT NULL,
  `s_name` varchar(20) NOT NULL,
  `s_mobileno` bigint(10) NOT NULL,
  `s_gender` varchar(10) NOT NULL,
  `s_birthdate` date NOT NULL,
  `s_cgpa` double NOT NULL,
  `s_branch` varchar(20) NOT NULL,
  `s_mailid` varchar(50) NOT NULL,
  `s_password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `student`
--
DELIMITER $$
CREATE TRIGGER `deleteStudent` BEFORE DELETE ON `student` FOR EACH ROW BEGIN
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`srno`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`a_id`),
  ADD UNIQUE KEY `a_s_emailid` (`a_s_emailid`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `interview`
--
ALTER TABLE `interview`
  ADD PRIMARY KEY (`i_id`);

--
-- Indexes for table `placement`
--
ALTER TABLE `placement`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `selected_students`
--
ALTER TABLE `selected_students`
  ADD PRIMARY KEY (`ss_id`),
  ADD UNIQUE KEY `ss_emailid` (`ss_emailid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`s_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `srno` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `interview`
--
ALTER TABLE `interview`
  MODIFY `i_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `placement`
--
ALTER TABLE `placement`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `selected_students`
--
ALTER TABLE `selected_students`
  MODIFY `ss_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `s_id` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
