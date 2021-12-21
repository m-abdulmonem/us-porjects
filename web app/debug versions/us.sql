-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2019 at 05:41 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `us`
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `email_verify_code` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_verify_code` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
  `phone` bigint(20) DEFAULT NULL,
  `posts` bigint(20) NOT NULL DEFAULT '0',
  `followers` int(11) NOT NULL DEFAULT '0',
  `following` int(11) NOT NULL DEFAULT '0',
  `grand` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profileCover` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ip` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `macAddress` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `faceID` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `loginAttempt` int(11) NOT NULL DEFAULT '0',
  `active` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'offline',
  `location` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  `role` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'normal',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `username`, `email_verified_at`, `email_verify_code`, `phone_verify_code`, `password`, `status`, `phone`, `posts`, `followers`, `following`, `grand`, `profileCover`, `ip`, `macAddress`, `faceID`, `loginAttempt`, `active`, `location`, `location_id`, `role`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'ashdcfi', 'SODFFJIOI', 'test', NULL, NULL, NULL, 'FSDFZFRGZ', 'pending', NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 'offline', NULL, NULL, 'normal', NULL, NULL, NULL),
(2, 'sadfasdas', 'mohamed@gmail.com', 'mohamed', NULL, '44c4c17332cace2124a1a836d9fc4b6f5cae8e7801a1f', '26685cae8e7801a21', '$2y$12$x/lnHQ1Gk1HKlihk8O9e0eC9Dycw1qiF5lt5MNYm.CU.iihO5eqiG', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:46:48', NULL),
(3, 'asadfasdas', 'mohameda@gmail.com', 'amohamed', NULL, '53e3a7161e428b65688f14b84d61c6105cae8ea81b343', '9285cae8ea81b345', '$2y$12$XCX17YRZqP6UJfaMe/j5CO1QCcoR9.j0bKVwpIPSKyg9PVeam.6Tu', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:47:36', NULL),
(4, 'asadfasdasa', 'mohamaeda@gmail.com', 'amohamaed', NULL, '3c59dc048e8850243be8079a5c74d0795cae8f1c7419d', '22925cae8f1c7419f', '$2y$12$g8Xl6zl5xt9yIvHnOovStuoBFszs3NnfoBKDMx1ymLDLUW98BgUku', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:49:32', NULL),
(5, 'asadfasdaasa', 'asa@gmail.com', 'amohamaaed', NULL, '9dcb88e0137649590b755372b040afad5cae8f359d411', '2695cae8f359d413', '$2y$12$n8KJTY60dYZvzj9HrTQKtO6pFhW0ZCqrac1rl5le5snZzn4HqA8pW', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:49:57', NULL),
(6, 'asadfasdsaasa', 'asaa@gmail.com', 'amohamsaaed', NULL, '4fac9ba115140ac4f1c22da82aa0bc7f5cae8f88de844', '43095cae8f88de846', '$2y$12$8IaB0bwUTpKbARQL0I7PC.fXvyHRjz2YRAXPIKe/KeU7xEyqI.HLC', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:51:20', NULL),
(7, 'asadfasdsaasa', 'asaaa@gmail.com', 'amohasmsaaed', NULL, 'ea5d2f1c4608232e07d3aa3d998e51355cae8fb83395d', '18925cae8fb83395e', '$2y$12$7uYgCdkp5IeHfgHSUfsnEe5N0TTcGPs5vFvQ6DxKCaeHbQk1uIJ5i', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:52:08', NULL),
(8, 'asadfasdsaasa', 'asaaaa@gmail.com', 'amohasmssaaed', NULL, '26dd0dbc6e3f4c8043749885523d6a255cae8fe60215d', '15605cae8fe60215e', '$2y$12$SCBN.11poW85O1hWmnS10eaIZexq3VhidjvFLw0zjQdreKMIZHAt2', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:52:54', NULL),
(9, 'asadfasdsdaasa', 'aasaaaa@gmail.com', 'amohasmdssaaed', NULL, 'a8e864d04c95572d1aece099af852d0a5cae903f84098', '34775cae903f8409b', '$2y$12$rQUpuipBVHVDTydmlh7IHuB2xoDu9KX3W4X4wLM6UuI4Ge4RbJogi', 'pending', NULL, 0, 0, 0, NULL, NULL, '', '', NULL, 0, 'offline', '', 0, 'normal', NULL, '2019-04-10 19:54:23', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_username_unique` (`username`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
