-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2017 at 03:32 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dump`
--

-- --------------------------------------------------------

--
-- Table structure for table `emergency_number_details`
--

CREATE TABLE `emergency_number_details` (
  `emergency_number_id` int(11) NOT NULL,
  `emergency_number_name` varchar(255) DEFAULT NULL,
  `emergency_number_address` varchar(255) DEFAULT NULL,
  `emergency_number_contact_number1` varchar(255) DEFAULT NULL,
  `emergency_number_contact_number2` varchar(255) DEFAULT NULL,
  `emergency_number_contact_number3` varchar(255) DEFAULT NULL,
  `emergency_number_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `emergency_number_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emergency_number_details`
--

INSERT INTO `emergency_number_details` (`emergency_number_id`, `emergency_number_name`, `emergency_number_address`, `emergency_number_contact_number1`, `emergency_number_contact_number2`, `emergency_number_contact_number3`, `emergency_number_added_datetime`, `emergency_number_active`) VALUES
(1, 'Emergency number 1', 'add 1', '071123456789', '071987654321', '077123456789', '2017-11-08 19:11:18', 1),
(2, 'Emergency Number 2', 'add 2', '0711234521321', '0715132132', '143511212132', '2017-11-08 19:11:18', 1),
(3, 'Emergency Number 2', 'add 2', '0711234521321', '0715132132', '143511212132', '2017-11-08 19:11:18', 1),
(4, 'Emergency number 1', 'add 1', '071123456789', '071987654321', '077123456789', '2017-11-08 19:11:18', 1),
(5, 'Emergency Number 2', 'add 2', '0711234521321', '0715132132', '143511212132', '2017-11-08 19:11:18', 1),
(6, 'Emergency Number 2', 'add 2', '0711234521321', '0715132132', '143511212132', '2017-11-08 19:11:18', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hotspot_category_details`
--

CREATE TABLE `hotspot_category_details` (
  `hotspot_category_id` int(11) NOT NULL,
  `hotspot_category_name` varchar(255) DEFAULT NULL,
  `hotspot_category_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hotspot_category_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1= active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotspot_category_details`
--

INSERT INTO `hotspot_category_details` (`hotspot_category_id`, `hotspot_category_name`, `hotspot_category_added_datetime`, `hotspot_category_active`) VALUES
(1, 'Construction Sites', '2017-11-08 18:46:38', 1),
(2, 'Schools', '2017-11-08 18:46:38', 1),
(3, 'Residence', '2017-11-08 18:47:01', 1),
(4, 'Public Institutions', '2017-11-08 18:47:01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hotspot_details`
--

CREATE TABLE `hotspot_details` (
  `hotspot_id` int(11) NOT NULL,
  `hotspot_name` varchar(255) DEFAULT NULL,
  `hotspot_address` varchar(255) DEFAULT NULL,
  `hotspot_city` varchar(255) DEFAULT NULL,
  `hotspot_state` varchar(255) DEFAULT NULL,
  `hotspot_lat` varchar(255) DEFAULT NULL,
  `hotspot_lng` varchar(255) DEFAULT NULL,
  `hotspot_type` int(11) NOT NULL,
  `hotspot_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hotspot_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotspot_details`
--

INSERT INTO `hotspot_details` (`hotspot_id`, `hotspot_name`, `hotspot_address`, `hotspot_city`, `hotspot_state`, `hotspot_lat`, `hotspot_lng`, `hotspot_type`, `hotspot_added_datetime`, `hotspot_active`) VALUES
(1, 'hotspot1', 'a1', 'c1', 's1', '6.926524', '79.889850', 1, '2017-11-09 11:44:25', 1),
(2, 'hotspot2', 'a2', 'c2', 's2', '6.887682', '79.872911', 2, '2017-11-09 11:45:02', 1),
(3, 'hotspot3', 'a3', 'c3', 's3', '6.887650', '79.874360', 3, '2017-11-09 11:44:25', 1),
(4, 'hotspot4', 'a4', 'c4', 's4', '6.964694', '79.872856', 4, '2017-11-09 11:45:02', 1),
(5, 'hotspot1', 'a5', 'c5', 's5', '6.926524', '79.889850', 1, '2017-11-09 11:44:25', 1),
(6, 'hotspot2', 'a6', 'c6', 's6', '6.887682', '79.872911', 2, '2017-11-09 11:45:02', 1),
(7, 'hotspot3', 'a7', 'c7', 's7', '6.887650', '79.874360', 3, '2017-11-09 11:44:25', 1),
(8, 'hotspot4', 'a8', 'c8', 's8', '6.964694', '79.872856', 4, '2017-11-09 11:45:02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `municiple_council_details`
--

CREATE TABLE `municiple_council_details` (
  `municiple_council_id` int(11) NOT NULL,
  `municiple_council_name` varchar(255) DEFAULT NULL,
  `municiple_council_adress` varchar(255) DEFAULT NULL,
  `municiple_council_city` varchar(255) DEFAULT NULL,
  `municiple_council_state` varchar(255) DEFAULT NULL,
  `municiple_council_lat` varchar(255) DEFAULT NULL,
  `municiple_council_lng` varchar(255) DEFAULT NULL,
  `municiple_council_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `municiple_council_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `municiple_council_disctricts_details`
--

CREATE TABLE `municiple_council_disctricts_details` (
  `municiple_council_disctricts_id` int(11) NOT NULL,
  `municiple_council_disctricts_moh_id` int(11) NOT NULL,
  `municiple_council_disctricts_name` varchar(255) DEFAULT NULL,
  `municiple_council_disctricts_mc_id` int(11) NOT NULL,
  `municiple_council_disctricts_lat` varchar(255) DEFAULT NULL,
  `municiple_council_disctricts_lng` varchar(255) DEFAULT NULL,
  `municiple_council_disctricts_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `municiple_council_disctricts_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscribed_area_details`
--

CREATE TABLE `subscribed_area_details` (
  `subscribed_area_id` int(11) NOT NULL,
  `subscribed_hp_id` int(11) NOT NULL,
  `subscribed_user_id` int(11) NOT NULL,
  `subscribed_area_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `subscribed_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscribed_area_details`
--

INSERT INTO `subscribed_area_details` (`subscribed_area_id`, `subscribed_hp_id`, `subscribed_user_id`, `subscribed_area_added_datetime`, `subscribed_active`) VALUES
(1, 1, 2, '2017-11-09 13:55:12', 1),
(2, 2, 2, '2017-11-09 13:55:12', 1),
(3, 3, 2, '2017-11-09 13:55:12', 1),
(4, 4, 2, '2017-11-09 13:55:12', 1),
(5, 5, 2, '2017-11-09 13:55:12', 1),
(6, 6, 2, '2017-11-09 13:55:12', 1),
(7, 7, 2, '2017-11-09 13:55:12', 1),
(8, 8, 2, '2017-11-09 13:55:12', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `user_id` int(11) NOT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_email_address` varchar(255) DEFAULT NULL,
  `user_gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = Male, 0 = Female',
  `user_address` varchar(255) DEFAULT NULL,
  `user_city` varchar(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  `user_lat` varchar(255) DEFAULT NULL,
  `user_lng` varchar(255) DEFAULT NULL,
  `user_contact_number` varchar(255) DEFAULT NULL,
  `user_mobile_number` varchar(255) DEFAULT NULL,
  `user_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_device_token` longtext,
  `user_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1= active, 0 = inactive',
  `user_type` int(1) NOT NULL DEFAULT '5',
  `user_volunteer_check` tinyint(1) DEFAULT '0' COMMENT '0 - no, 1 - yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_id`, `user_first_name`, `user_last_name`, `user_name`, `user_password`, `user_email_address`, `user_gender`, `user_address`, `user_city`, `user_state`, `user_lat`, `user_lng`, `user_contact_number`, `user_mobile_number`, `user_added_datetime`, `user_device_token`, `user_active`, `user_type`, `user_volunteer_check`) VALUES
(1, '', '', '', '', '', 1, '', '', '', '', '', '', '', '2017-11-09 11:19:03', '', 1, 5, 0),
(2, 'sumudu', 'sahan', 'abc', '123', 's@a.com', 1, 'asd', 'qwe', 'qwert', '6.9264228', '79.8516415', '111', 'wwww', '2017-11-09 11:21:48', 'eK5npkx7Idk:APA91bG84-edenr8ykVxzCl6WWAcb67yjQ3yNk_kItaoigUzzHadQdspNC0cgvzkusuexj5NuYc4fnIyKHDa52keWY-fff2u6vOLYm3URtVoePHCa2BP_20r2d-dkAGwVV716S0dleLN', 1, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_role_details`
--

CREATE TABLE `user_role_details` (
  `user_role_id` int(11) NOT NULL,
  `user_role_name` varchar(255) NOT NULL,
  `user_role_aded_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_role_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1= active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role_details`
--

INSERT INTO `user_role_details` (`user_role_id`, `user_role_name`, `user_role_aded_datetime`, `user_role_active`) VALUES
(1, 'Super Admin', '2017-11-08 18:16:02', 1),
(2, 'Admin', '2017-11-08 18:16:02', 1),
(3, 'MOH', '2017-11-08 18:16:10', 1),
(4, 'PHI', '2017-11-08 18:16:10', 1),
(5, 'Public', '2017-11-08 18:16:16', 1);

-- --------------------------------------------------------

--
-- Table structure for table `victim_area_details`
--

CREATE TABLE `victim_area_details` (
  `victim_area_id` int(11) NOT NULL,
  `victim_area_hp_id` int(11) NOT NULL,
  `victim_area_level` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 to 3',
  `victim_area_radius` int(11) NOT NULL,
  `victim_area_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `victim_area_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ward_details`
--

CREATE TABLE `ward_details` (
  `ward_id` int(11) NOT NULL,
  `ward_name` varchar(255) DEFAULT NULL,
  `ward_district_id` int(11) NOT NULL,
  `ward_added_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ward_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emergency_number_details`
--
ALTER TABLE `emergency_number_details`
  ADD PRIMARY KEY (`emergency_number_id`);

--
-- Indexes for table `hotspot_category_details`
--
ALTER TABLE `hotspot_category_details`
  ADD PRIMARY KEY (`hotspot_category_id`);

--
-- Indexes for table `hotspot_details`
--
ALTER TABLE `hotspot_details`
  ADD PRIMARY KEY (`hotspot_id`);

--
-- Indexes for table `municiple_council_details`
--
ALTER TABLE `municiple_council_details`
  ADD PRIMARY KEY (`municiple_council_id`);

--
-- Indexes for table `municiple_council_disctricts_details`
--
ALTER TABLE `municiple_council_disctricts_details`
  ADD PRIMARY KEY (`municiple_council_disctricts_id`);

--
-- Indexes for table `subscribed_area_details`
--
ALTER TABLE `subscribed_area_details`
  ADD PRIMARY KEY (`subscribed_area_id`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_role_details`
--
ALTER TABLE `user_role_details`
  ADD PRIMARY KEY (`user_role_id`);

--
-- Indexes for table `victim_area_details`
--
ALTER TABLE `victim_area_details`
  ADD PRIMARY KEY (`victim_area_id`);

--
-- Indexes for table `ward_details`
--
ALTER TABLE `ward_details`
  ADD PRIMARY KEY (`ward_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emergency_number_details`
--
ALTER TABLE `emergency_number_details`
  MODIFY `emergency_number_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `hotspot_category_details`
--
ALTER TABLE `hotspot_category_details`
  MODIFY `hotspot_category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `hotspot_details`
--
ALTER TABLE `hotspot_details`
  MODIFY `hotspot_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `municiple_council_details`
--
ALTER TABLE `municiple_council_details`
  MODIFY `municiple_council_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `municiple_council_disctricts_details`
--
ALTER TABLE `municiple_council_disctricts_details`
  MODIFY `municiple_council_disctricts_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subscribed_area_details`
--
ALTER TABLE `subscribed_area_details`
  MODIFY `subscribed_area_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user_role_details`
--
ALTER TABLE `user_role_details`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `victim_area_details`
--
ALTER TABLE `victim_area_details`
  MODIFY `victim_area_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ward_details`
--
ALTER TABLE `ward_details`
  MODIFY `ward_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
