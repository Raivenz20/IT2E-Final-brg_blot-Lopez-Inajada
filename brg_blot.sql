-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 15, 2025 at 01:29 AM
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
-- Database: `brg_blot`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `a_id` int(20) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `user_type` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `pname` varchar(150) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`a_id`, `fname`, `lname`, `gender`, `user_type`, `email`, `contact`, `uname`, `pname`, `status`) VALUES
(2, 'Raven', 'Lopez', 'Male', 'Admin', 'venz@gmail.com', '9917591611', 'Venz', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active'),
(3, 'Vance', 'Inajada', 'Male', 'Staff', 'vance@gmail.com', '9971686155', 'Vance', '4k35IAeMPdTn6NJELwDlyasqIxuzkY1lzFCQbknsrvQ=', 'Active'),
(4, 'Jessa', 'Baloro', 'Female', 'Staff', 'JesD.Baloro@gmail.com', '9917686881', 'JeBaLo', 'snapv6LcwvYVDgYU+hIvEb6knRAgRbTYd1Z7duvTpQs=', 'Pending'),
(5, 'test', 'test', 'Male', 'Staff', 'test@gmail.com', '9981726377', 'test', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Active'),
(6, 'asdf', 'asdf', 'Male', 'Admin', 'asdf@gmail.com', '9999999999', 'asdf', 'JBP7NwmwWTnwTPLpL30Il/wllvmtC4qeqFXHv+uq6JI=', 'Pending'),
(7, 'Error', 'Error', 'Male', 'Admin', 'Error@gmail.com', '9917687877', 'Error', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Active'),
(8, 'Jorvin', 'Pengoc', 'Male', 'Staff', 'Testingan@gmail.com', '9917654277', 'Jorvin', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Pending');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`a_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `a_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
