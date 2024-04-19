-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2024 at 07:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlthanhvien`
--

CREATE DATABASE IF NOT EXISTS `qlthanhvien` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `qlthanhvien`;-- --------------------------------------------------------

--
-- Table structure for table `thanhvien`
--

CREATE TABLE `thanhvien` (
  `MaTV` varchar(100) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `Khoa` varchar(100) DEFAULT NULL,
  `Nganh` varchar(100) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thanhvien`
--

INSERT INTO `thanhvien` (`MaTV`, `Email`, `Password`, `HoTen`, `Khoa`, `Nganh`, `SDT`) VALUES
('1120380064', '1120380064@gmail.com', '1120380064', 'Nguyễn Ngọc Quỳnh Lực', 'Ngoại Ngữ', 'NNA', '0911203800'),
('1120480015', '1120480015@gmail.com', '1120480015', 'Trần Phạm Ngọc Ly', 'Toán UD', 'Toán', '0911204800'),
('1121100003', '1121100003@gmail.com', '1121100003', 'Nguyễn Đắc Phương Linh', 'SP KHXH', 'Sử', '0911211000'),
('1121110001', '1121110001@gmail.com', '1121110001', 'Phạm Thị Lan Khôi', 'SP KHXH', 'Địa', '0911211100'),
('1121130012', '1121130012@gmail.com', '1121130012', 'Lê Ngọc Ánh', 'Ngoại Ngữ', 'Anh', '034587921');

-- --------------------------------------------------------

--
-- Table structure for table `thietbi`
--

CREATE TABLE `thietbi` (
  `MaTB` varchar(20) NOT NULL,
  `TenTB` varchar(100) NOT NULL,
  `MoTaTB` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thietbi`
--

INSERT INTO `thietbi` (`MaTB`, `TenTB`, `MoTaTB`) VALUES
('00720190001', 'Máy 1', 'Máy tính bàn'),
('00720190002', 'Máy 2', 'Máy tính bàn'),
('00720190003', 'Máy 3', 'Máy tính bàn'),
('00720190004', 'Máy 4', 'Máy tính bàn'),
('00720190005', 'Máy 5', 'Máy tính bàn'),
('00720190006', 'Máy 6', 'Máy tính bàn'),
('00720190007', 'Máy 7', 'Máy tính bàn'),
('00720190008', 'Máy 8', 'Máy tính bàn'),
('00720190009', 'Máy 9', 'Máy tính bàn'),
('00720190010', 'Máy 10', 'Máy tính bàn'),
('00720190011', 'Máy 11', 'Máy tính bàn'),
('00720190012', 'Máy 12', 'Máy tính bàn'),
('00720190013', 'Máy 13', 'Máy tính bàn'),
('00720190014', 'Máy 14', 'Máy tính bàn'),
('00720190015', 'Máy 15', 'Máy tính bàn'),
('00720190016', 'Máy 16', 'Máy tính bàn'),
('00720190017', 'Máy 17', 'Máy tính bàn'),
('00720190018', 'Máy 18', 'Máy tính bàn'),
('00720190019', 'Máy 19', 'Máy tính bàn 19'),
('00720190020', 'Máy 20', 'Máy tính bàn');

-- --------------------------------------------------------

--
-- Table structure for table `thongtinsd`
--

CREATE TABLE `thongtinsd` (
  `MaTT` int(10) NOT NULL,
  `MaTV` varchar(100) NOT NULL,
  `MaTB` varchar(20) DEFAULT NULL,
  `TGVao` datetime DEFAULT NULL,
  `TGMuon` datetime DEFAULT NULL,
  `TGTra` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongtinsd`
--

INSERT INTO `thongtinsd` (`MaTT`, `MaTV`, `MaTB`, `TGVao`, `TGMuon`, `TGTra`) VALUES
(49, '1120380064', NULL, '2024-04-01 15:40:21', NULL, NULL),
(50, '1121100003', NULL, '2024-04-19 15:40:24', NULL, NULL),
(51, '1120380064', NULL, '2024-04-19 15:40:27', NULL, NULL),
(52, '1121130012', NULL, '2024-04-19 15:40:29', NULL, NULL),
(53, '1121110001', NULL, '2024-03-04 15:40:31', NULL, NULL),
(54, '1120480015', NULL, '2024-04-19 15:40:39', NULL, NULL),
(55, '1121100003', NULL, '2024-04-03 15:40:42', NULL, NULL),
(56, '1120380064', NULL, '2024-04-19 15:40:44', NULL, NULL),
(57, '1121130012', NULL, '2024-04-19 15:40:46', NULL, NULL),
(58, '1121110001', NULL, '2024-04-19 15:40:49', NULL, NULL),
(59, '1120380064', '00720190001', NULL, '2024-04-03 15:42:13', '2024-04-03 16:42:34'),
(60, '1121100003', '00720190002', NULL, '2024-04-03 10:42:19', '2024-04-03 11:42:37'),
(61, '1121110001', '00720190003', NULL, '2024-04-03 12:42:23', '2024-04-03 15:42:39'),
(62, '1121130012', '00720190004', NULL, '2024-04-19 15:42:27', '2024-04-19 15:42:42'),
(63, '1120380064', '00720190001', NULL, '2024-04-19 15:42:49', '2024-04-19 15:43:12'),
(64, '1120480015', '00720190010', NULL, '2024-04-19 15:43:00', '2024-04-19 15:43:14'),
(65, '1121110001', '00720190011', NULL, '2024-04-19 15:43:04', '2024-04-19 15:43:16');

-- --------------------------------------------------------

--
-- Table structure for table `xuly`
--

CREATE TABLE `xuly` (
  `MaXL` int(10) NOT NULL,
  `MaTV` varchar(100) NOT NULL,
  `HinhThucXL` varchar(250) DEFAULT NULL,
  `SoTien` int(100) DEFAULT NULL,
  `NgayXL` datetime DEFAULT NULL,
  `TrangThaiXL` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `xuly`
--

INSERT INTO `xuly` (`MaXL`, `MaTV`, `HinhThucXL`, `SoTien`, `NgayXL`, `TrangThaiXL`) VALUES
(14, '1120380064', 'khóa thẻ 1 tháng', NULL, '2024-04-19 15:39:33', 0),
(15, '1120380064', 'Bồi thường mất tài sản', 100000, '2024-04-19 15:39:33', 0),
(16, '1121110001', 'Bồi thường mất tài sản', 100000, '2024-04-19 15:39:33', 0),
(17, '1121110001', 'Bồi thường mất tài sản', 100000, '2024-04-01 15:39:33', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `thanhvien`
--
ALTER TABLE `thanhvien`
  ADD PRIMARY KEY (`MaTV`);

--
-- Indexes for table `thietbi`
--
ALTER TABLE `thietbi`
  ADD PRIMARY KEY (`MaTB`);

--
-- Indexes for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD PRIMARY KEY (`MaTT`),
  ADD KEY `MaTV` (`MaTV`,`MaTB`),
  ADD KEY `MaTB` (`MaTB`);

--
-- Indexes for table `xuly`
--
ALTER TABLE `xuly`
  ADD PRIMARY KEY (`MaXL`),
  ADD KEY `MaTV` (`MaTV`),
  ADD KEY `MaTV_2` (`MaTV`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  MODIFY `MaTT` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `xuly`
--
ALTER TABLE `xuly`
  MODIFY `MaXL` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD CONSTRAINT `thongtinsd_ibfk_3` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`),
  ADD CONSTRAINT `thongtinsd_ibfk_4` FOREIGN KEY (`MaTB`) REFERENCES `thietbi` (`MaTB`);

--
-- Constraints for table `xuly`
--
ALTER TABLE `xuly`
  ADD CONSTRAINT `xuly_ibfk_1` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
