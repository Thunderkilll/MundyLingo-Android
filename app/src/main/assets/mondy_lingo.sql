-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2018 at 10:59 AM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mondy_lingo`
--

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `detail` varchar(3000) DEFAULT NULL,
  `dateDeb` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `nbrQuestion` int(11) DEFAULT NULL,
  `presRequis` int(11) DEFAULT NULL,
  `difficulte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `presrequis`
--

CREATE TABLE `presrequis` (
  `id` int(11) NOT NULL,
  `objet` varchar(20) NOT NULL,
  `text` varchar(100) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `presrequis`
--

INSERT INTO `presrequis` (`id`, `objet`, `text`, `level`) VALUES
(1, 'vocabulaire', 'vocab 1\r\nvocab 2\r\nvocab 3', 1),
(2, 'objet ', 'prerequis de cette level sont franÃ§as basics', 2);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `contenu` varchar(100) NOT NULL,
  `vocal` varchar(100) NOT NULL,
  `reponse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `level`, `contenu`, `vocal`, `reponse`) VALUES
(1, 2, 'question1', '', 1),
(2, 3, 'question2', 'url vocal 1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `text` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `vocal` varchar(100) DEFAULT NULL,
  `question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reponse`
--

INSERT INTO `reponse` (`id`, `level`, `text`, `image`, `vocal`, `question`) VALUES
(1, 3, 'reponse au question2', 'url de l image', 'url reponse for q 2', 2);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `contenue` varchar(100) NOT NULL,
  `duree` float NOT NULL,
  `isValide` tinyint(1) NOT NULL,
  `scoreTest` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `level`, `contenue`, `duree`, `isValide`, `scoreTest`) VALUES
(1, 1, 'test1', 12, 0, 0),
(2, 2, 'test level 2', 21, 1, 100),
(3, 1, 'french test 1', 12, 1, 85);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(16) NOT NULL,
  `mail` varchar(60) NOT NULL,
  `image` varchar(100) NOT NULL,
  `scoreF` int(11) NOT NULL,
  `scoreAng` int(11) NOT NULL,
  `scoreGerm` int(11) NOT NULL,
  `scoreEsp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `mail`, `image`, `scoreF`, `scoreAng`, `scoreGerm`, `scoreEsp`) VALUES
(1, 'user001', 'madara123120', 'user001.user@test.tester', 'https://www.youtube.com/watch?v=GAdGmJxfcf8', 0, 0, 0, 0),
(2, 'user002', 'password001', 'user002.user@test.tester.com', 'https://www.youtube.com/watch?v=tH-OqKJca88', 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `presrequis`
--
ALTER TABLE `presrequis`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `presrequis`
--
ALTER TABLE `presrequis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
