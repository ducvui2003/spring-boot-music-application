-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.40 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for music-player
CREATE DATABASE IF NOT EXISTS `music-player` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `music-player`;

-- Dumping structure for table music-player.albums
CREATE TABLE IF NOT EXISTS `albums` (
  `artist_id` bigint DEFAULT NULL,
  `cover_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `release_date` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9517n27axi5y5nuj5jdrgjesl` (`cover_id`),
  KEY `FK72gqyi6l1j674radjyitcm86f` (`artist_id`),
  CONSTRAINT `FK72gqyi6l1j674radjyitcm86f` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `FKtgvfxsti87c6kk338erunic6k` FOREIGN KEY (`cover_id`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.albums: ~0 rows (approximately)
DELETE FROM `albums`;
INSERT INTO `albums` (`artist_id`, `cover_id`, `created_at`, `id`, `release_date`, `updated_at`, `name`) VALUES
	(3, 104, '2024-12-25 21:10:27.000000', 11, '2024-12-25 21:10:30.000000', '2024-12-25 21:10:33.000000', 'moon');

-- Dumping structure for table music-player.artists
CREATE TABLE IF NOT EXISTS `artists` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `avatar_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe5epcnqqucenx1xrah90nhntg` (`avatar_id`),
  CONSTRAINT `FKkbg1bb4y3w3tjxepa67f9anno` FOREIGN KEY (`avatar_id`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.artists: ~8 rows (approximately)
DELETE FROM `artists`;
INSERT INTO `artists` (`id`, `name`, `bio`, `avatar_id`, `created_at`, `updated_at`) VALUES
	(1, 'Đen ', 'Anh Đen', 132, '2024-12-05 21:04:39.000000', NULL),
	(2, 'Vũ', 'Anh Vũ ', 129, '2024-12-06 01:40:34.000000', NULL),
	(3, 'Dalab', NULL, 127, '2024-12-06 01:40:34.000000', NULL),
	(4, 'Kay Trần ', NULL, 131, '2024-12-06 01:40:35.000000', NULL),
	(5, 'Karik', NULL, 128, '2024-12-06 01:40:35.000000', NULL),
	(6, 'Sơn tùng MTP', NULL, 126, '2024-12-06 01:40:36.000000', NULL),
	(7, 'Jack', NULL, 125, '2024-12-06 01:40:36.000000', NULL),
	(8, 'Mono', NULL, 130, '2024-12-06 01:40:37.000000', NULL);

-- Dumping structure for table music-player.favorites
CREATE TABLE IF NOT EXISTS `favorites` (
  `song_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`song_id`,`user_id`),
  KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`),
  CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqs8yha6nwtpmpq4xa0yro7gwj` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.favorites: ~0 rows (approximately)
DELETE FROM `favorites`;

-- Dumping structure for table music-player.genres
CREATE TABLE IF NOT EXISTS `genres` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cover_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK76mow94tquv505ter690lm3py` (`cover_id`),
  CONSTRAINT `FK3bb5phvldcikw76ly6dk6e61h` FOREIGN KEY (`cover_id`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.genres: ~6 rows (approximately)
DELETE FROM `genres`;
INSERT INTO `genres` (`id`, `description`, `name`, `cover_id`, `created_at`, `updated_at`) VALUES
	(21, NULL, 'BALAB', 139, '2024-12-05 23:07:58.000000', '2024-12-05 23:08:02.000000'),
	(22, NULL, 'EDM', 135, '2024-12-28 14:15:32.000000', '2024-12-28 14:15:37.000000'),
	(23, NULL, 'KPOP', 138, '2024-12-28 14:15:33.000000', '2024-12-28 14:15:38.000000'),
	(24, NULL, 'JPOP', 136, '2024-12-28 14:15:35.000000', '2024-12-28 14:15:39.000000'),
	(25, NULL, 'RAP', 134, '2024-12-28 14:15:34.000000', '2024-12-28 14:15:40.000000'),
	(26, NULL, 'MELODY', 137, '2024-12-28 14:15:36.000000', '2024-12-28 14:15:41.000000');

-- Dumping structure for table music-player.listening_histories
CREATE TABLE IF NOT EXISTS `listening_histories` (
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `song_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm4i67tpl37vlrjsmbe55k27q0` (`song_id`),
  KEY `FK6t8x2o8v490fql36b4nq8n0cg` (`user_id`),
  CONSTRAINT `FK6t8x2o8v490fql36b4nq8n0cg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKm4i67tpl37vlrjsmbe55k27q0` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.listening_histories: ~7 rows (approximately)
DELETE FROM `listening_histories`;
INSERT INTO `listening_histories` (`created_at`, `id`, `song_id`, `updated_at`, `user_id`) VALUES
	('2024-12-25 15:06:07.710674', 1, 1, '2024-12-25 15:06:07.710716', 1),
	('2024-12-25 15:06:09.754399', 2, 1, '2024-12-25 15:06:09.754427', 1),
	('2024-12-26 11:44:54.522592', 3, 1, '2024-12-26 11:44:54.522659', 1),
	('2024-12-27 08:21:51.036905', 4, 1113, '2024-12-27 08:21:51.036958', 1),
	('2024-12-28 06:02:58.929715', 5, 1114, '2024-12-28 06:02:58.929734', 1),
	('2024-12-28 06:20:58.586504', 6, 1113, '2024-12-28 06:20:58.586516', 1),
	('2024-12-28 06:30:13.423167', 7, 1114, '2024-12-28 06:30:13.423181', 1),
	('2024-12-28 07:41:46.755671', 8, 1113, '2024-12-28 07:41:46.755688', 1),
	('2024-12-28 09:54:08.912583', 9, 1113, '2024-12-28 09:54:08.912605', 1),
	('2024-12-28 09:54:08.912583', 10, 1113, '2024-12-28 09:54:08.912605', 1),
	('2024-12-28 09:54:09.271282', 11, 1, '2024-12-28 09:54:09.271322', 1),
	('2024-12-28 09:54:09.287228', 12, 1, '2024-12-28 09:54:09.287240', 1),
	('2024-12-28 09:54:14.368587', 13, 1113, '2024-12-28 09:54:14.368611', 1),
	('2024-12-28 09:56:58.232095', 14, 1, '2024-12-28 09:56:58.232119', 1);

-- Dumping structure for table music-player.playlists
CREATE TABLE IF NOT EXISTS `playlists` (
  `is_public` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cover_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5e6k95ks9j9g2l6s0ha2d4v2a` (`cover_id`),
  KEY `FKtgjwvfg23v990xk7k0idmqbrj` (`user_id`),
  CONSTRAINT `FKa5o4oyvue8uqt95hj30kdbmnu` FOREIGN KEY (`cover_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `FKtgjwvfg23v990xk7k0idmqbrj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.playlists: ~5 rows (approximately)
DELETE FROM `playlists`;
INSERT INTO `playlists` (`is_public`, `created_at`, `id`, `updated_at`, `user_id`, `description`, `name`, `cover_id`) VALUES
	(b'1', '2024-12-25 23:58:34.000000', 3, '2024-12-25 23:58:38.000000', 1, 'Đông tới Tây, đây là những ca khúc thịnh hành nhất ở Việt Nam. Ảnh bìa: ROSÉ\n', 'Hot Hit VietName', NULL),
	(b'1', '2024-12-26 00:12:16.000000', 6, '2024-12-26 00:13:51.000000', 1, 'Đông tới Tây, đây là những ca khúc thịnh hành nhất ở Việt Nam. Ảnh bìa: ROSÉ\n', 'List Nhac cua Vu', NULL),
	(b'1', '2024-12-25 17:58:48.158026', 7, '2024-12-25 17:58:48.158064', 1, 'baiff hats hay', 'Đâu chỉ riêng em', NULL),
	(b'0', '2024-12-28 06:20:41.971709', 10, '2024-12-28 06:20:41.971730', 1, '', 'Playlist của Đức', NULL),
	(b'0', '2024-12-28 06:34:30.294603', 11, '2024-12-28 06:34:30.294632', 1, '', 'playlist cua tui', NULL);

-- Dumping structure for table music-player.playlists_songs
CREATE TABLE IF NOT EXISTS `playlists_songs` (
  `playlist_id` bigint NOT NULL,
  `song_id` bigint NOT NULL,
  PRIMARY KEY (`playlist_id`,`song_id`),
  KEY `FK5pyqf1ff6dfj1xbn5p626i0dr` (`song_id`),
  CONSTRAINT `FK2emrpqhxanqunhcodn7s5yca4` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`),
  CONSTRAINT `FK5pyqf1ff6dfj1xbn5p626i0dr` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.playlists_songs: ~2 rows (approximately)
DELETE FROM `playlists_songs`;
INSERT INTO `playlists_songs` (`playlist_id`, `song_id`) VALUES
	(3, 1113),
	(6, 1114);

-- Dumping structure for table music-player.resources
CREATE TABLE IF NOT EXISTS `resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `public_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tag` enum('ALBUM','ARTIST','AUDIO','GENRE','SONG','USER') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.resources: ~28 rows (approximately)
DELETE FROM `resources`;
INSERT INTO `resources` (`id`, `public_id`, `tag`, `created_at`, `updated_at`, `name`) VALUES
	(104, 'music-media/audio/Nếu Những Tiếc Nuối  Vũ', 'AUDIO', '2024-12-05 15:48:24', '2024-12-05 15:48:24', 'Nếu Những Tiếc Nuối'),
	(105, 'music-media/audio/NHỮNG LỜI HỨA BỎ QUÊN', 'AUDIO', '2024-12-05 15:49:49', '2024-12-05 15:49:49', 'NHỮNG LỜI HỨA BỎ QUÊN'),
	(106, 'music-media/audio/Bầu Trời Mới', 'AUDIO', '2024-12-05 16:00:05', '2024-12-05 16:00:05', 'Bầu Trời Mới'),
	(107, 'music-media/audio/THERES NO ONE AT ALL', 'AUDIO', '2024-12-05 16:00:36', '2024-12-05 16:00:36', 'THERES NO ONE AT ALL'),
	(108, 'music-media/audio/Chăm Hoa', 'AUDIO', '2024-12-05 16:01:27', '2024-12-05 16:01:27', 'Chăm Hoa'),
	(109, 'music-media/audio/THIÊN LÝ ƠI', 'AUDIO', '2024-12-05 16:01:47', '2024-12-05 16:01:47', 'THIÊN LÝ ƠI'),
	(110, 'music-media/audio/Lối Nhỏ', 'AUDIO', '2024-12-05 16:02:04', '2024-12-05 16:02:04', 'Lối Nhỏ'),
	(111, 'music-media/audio/BẠN ĐỜI', 'AUDIO', '2024-12-05 16:02:21', '2024-12-05 16:02:21', 'BẠN ĐỜI'),
	(112, 'music-media/audio/Mang Tiền Về Cho Mẹ', 'AUDIO', '2024-12-05 16:02:37', '2024-12-05 16:02:37', 'Mang Tiền Về Cho Mẹ'),
	(113, 'music-media/audio/Tết Đong Đầy', 'AUDIO', '2024-12-05 16:02:54', '2024-12-05 16:02:54', 'Tết Đong Đầy'),
	(115, 'music-media/image/song/neu-nhung-tiec-nuoi', 'SONG', '2024-12-05 16:31:12', '2024-12-05 16:31:12', 'thumb_neu-nhung-tiec-nuoi'),
	(116, 'music-media/image/song/tet-dong-day', 'SONG', '2024-12-05 16:34:07', '2024-12-05 16:34:07', 'thumb-tet-dong-day'),
	(117, 'music-media/image/song/nhung-loi-hua-bo-quen', 'SONG', '2024-12-05 16:34:31', '2024-12-05 16:34:31', 'thumb-nhung-loi-hua-bo-quen'),
	(118, 'music-media/image/song/bau-troi-moi', 'SONG', '2024-12-05 16:34:46', '2024-12-05 16:34:46', 'thumb-bau-troi-moi'),
	(119, 'music-media/image/song/loi-nho', 'SONG', '2024-12-05 16:35:04', '2024-12-05 16:35:04', 'thumb-loi-nho'),
	(120, 'music-media/image/song/dem-tien-ve-cho-me', 'SONG', '2024-12-05 16:35:22', '2024-12-05 16:35:22', 'thumb-dem-tien-ve-cho-me'),
	(121, 'music-media/image/song/cham-hoa', 'SONG', '2024-12-05 16:35:38', '2024-12-05 16:35:38', 'thumb-cham-hoa'),
	(122, 'music-media/image/song/ban-doi', 'SONG', '2024-12-05 16:35:50', '2024-12-05 16:35:50', 'thumb-ban-doi'),
	(123, 'music-media/image/song/there-no-one-at-all', 'SONG', '2024-12-05 16:36:05', '2024-12-05 16:36:05', 'thumb-there-no-one-at-all'),
	(124, 'music-media/image/song/thien-ly-oi', 'SONG', '2024-12-05 16:36:19', '2024-12-05 16:36:19', 'thumb-thien-ly-oi'),
	(125, 'music-media/image/artist/jack', 'ARTIST', '2024-12-05 18:30:33', '2024-12-05 18:30:33', 'avatar-jack'),
	(126, 'music-media/image/artist/son-tung', 'ARTIST', '2024-12-05 18:31:01', '2024-12-05 18:31:01', 'avatar-son-tung'),
	(127, 'music-media/image/artist/dalab', 'ARTIST', '2024-12-05 18:31:36', '2024-12-05 18:31:36', 'avatar-dalab'),
	(128, 'music-media/image/artist/karik', 'ARTIST', '2024-12-05 18:37:32', '2024-12-05 18:37:32', 'avatar-karik'),
	(129, 'music-media/image/artist/vu', 'ARTIST', '2024-12-05 18:38:10', '2024-12-05 18:38:10', 'avatar-vu'),
	(130, 'music-media/image/artist/mono', 'ARTIST', '2024-12-05 18:38:35', '2024-12-05 18:38:35', 'avatar-mono'),
	(131, 'music-media/image/artist/kay-tran', 'ARTIST', '2024-12-05 18:38:59', '2024-12-05 18:41:40', 'avatar-kay-tran'),
	(132, 'music-media/image/artist/den-vau', 'ARTIST', '2024-12-05 18:39:24', '2024-12-05 18:41:43', 'avatar-den-vau'),
	(133, 'music-media/image/genre/vpop', 'GENRE', '2024-12-28 07:11:45', '2024-12-28 07:11:45', 'genre-vpop'),
	(134, 'music-media/image/genre/rap', 'GENRE', '2024-12-28 07:13:09', '2024-12-28 07:13:09', 'genre-rap'),
	(135, 'music-media/image/genre/edm', 'GENRE', '2024-12-28 07:13:30', '2024-12-28 07:13:30', 'genre-edm'),
	(136, 'music-media/image/genre/jpop', 'GENRE', '2024-12-28 07:13:44', '2024-12-28 07:13:44', 'genre-jpop'),
	(137, 'music-media/image/genre/melody', 'GENRE', '2024-12-28 07:13:58', '2024-12-28 07:13:58', 'genre-melody'),
	(138, 'music-media/image/genre/kpop', 'GENRE', '2024-12-28 07:14:12', '2024-12-28 07:14:12', 'genre-kpop'),
	(139, 'music-media/image/genre/balab', 'GENRE', '2024-12-28 07:14:32', '2024-12-28 07:14:32', 'genre-balab');

-- Dumping structure for table music-player.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('ADMIN','SHIPPER','USER') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.roles: ~3 rows (approximately)
DELETE FROM `roles`;
INSERT INTO `roles` (`id`, `name`, `created_at`, `updated_at`) VALUES
	(1, 'USER', '2024-11-15 16:18:44', '2024-11-15 16:18:44'),
	(2, 'ADMIN', '2024-11-15 16:18:44', '2024-11-15 16:18:44');

-- Dumping structure for table music-player.songs
CREATE TABLE IF NOT EXISTS `songs` (
  `artist_id` bigint DEFAULT NULL,
  `cover_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `genre_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `source_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_premium` bit(1) NOT NULL DEFAULT b'0',
  `views` decimal(38,0) unsigned zerofill DEFAULT '00000000000000000000000000000000000000',
  `album_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdjq2ujqovw5rc14q60f8p6b6e` (`artist_id`),
  KEY `FKd5mor9lg3wkqhn2tp0r75nkm` (`genre_id`),
  KEY `FKte4gkb2cqtk2erfa87oopj2cj` (`album_id`),
  KEY `UKteuew6n1va5ml45y58qoq7973` (`cover_id`) USING BTREE,
  KEY `UK2yc8fnvc2rvnfuypqqi9s5l4w` (`source_id`) USING BTREE,
  CONSTRAINT `FK9qfs5x2bktudaatwf25s8bdy` FOREIGN KEY (`cover_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `FKd5mor9lg3wkqhn2tp0r75nkm` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
  CONSTRAINT `FKdjq2ujqovw5rc14q60f8p6b6e` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `FKte4gkb2cqtk2erfa87oopj2cj` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`),
  CONSTRAINT `FKtpf47kri109fymdhe28ojruvc` FOREIGN KEY (`source_id`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.songs: ~9 rows (approximately)
DELETE FROM `songs`;
INSERT INTO `songs` (`artist_id`, `cover_id`, `created_at`, `genre_id`, `id`, `source_id`, `updated_at`, `title`, `is_premium`, `views`, `album_id`) VALUES
	(2, 117, '2024-12-05 23:07:48.000000', 25, 1, 105, '2024-12-28 09:56:58.220368', 'Những lời hứa bỏ quên', b'1', 00000000000000000000000000000000000002, 11),
	(2, 115, '2024-12-05 23:07:47.000000', 22, 2, 104, NULL, 'Nếu những tiêc nuối', b'0', 00000000000000000000000000000000000000, 11),
	(1, 120, '2024-12-05 23:13:30.000000', 22, 1113, 112, '2024-12-28 09:54:14.358606', 'Mang tiền về cho mẹ ', b'0', 00000000000000000000000000000000000005, 11),
	(1, 119, '2024-12-05 23:13:47.000000', 25, 1114, 110, '2024-12-28 06:30:13.415382', 'Lối nhỏ', b'0', 00000000000000000000000000000000000002, 11),
	(8, 121, '2024-12-05 23:14:03.000000', 25, 1115, 108, NULL, 'Chăm hoa', b'0', 00000000000000000000000000000000000000, 11),
	(6, 107, '2024-12-05 23:14:14.000000', 22, 1116, 107, NULL, 'THERE’S NO ONE AT ALL', b'0', 00000000000000000000000000000000000000, 11),
	(4, 116, '2024-12-05 23:14:41.000000', 21, 1117, 113, NULL, 'Tết đong đầy', b'0', 00000000000000000000000000000000000000, 11),
	(5, 122, '2024-12-05 23:14:41.000000', 22, 1118, 111, NULL, 'Bạn đời', b'0', 00000000000000000000000000000000000000, 11),
	(7, 124, '2024-12-05 23:15:03.000000', 25, 1119, 109, NULL, 'Thiên lý ơi', b'0', 00000000000000000000000000000000000000, 11),
	(3, 118, '2024-12-27 17:59:30.000000', 25, 1120, 106, NULL, 'Bầu trời ', b'0', 00000000000000000000000000000000000000, 11);

-- Dumping structure for table music-player.users
CREATE TABLE IF NOT EXISTS `users` (
  `verified` bit(1) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `auth_type` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_premium` bit(1) NOT NULL DEFAULT b'0',
  `birth_day` date DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `sex` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.users: ~2 rows (approximately)
DELETE FROM `users`;
INSERT INTO `users` (`verified`, `id`, `role_id`, `auth_type`, `email`, `full_name`, `password`, `is_premium`, `birth_day`, `phone_number`, `sex`, `created_at`, `updated_at`, `birthday`) VALUES
	(b'1', 1, 1, 'username_password', 'ducvui2003@gmail.com', 'Lê Anh Đức', '$2a$10$ZjCmdQgwHTN45hmZ4h2cseaGHlI6D19IuxzZ8Y4RFCR72UmNRi7wm', b'1', '2024-11-05', '0865677047', b'1', '2024-11-15 23:15:22.000000', '2024-11-15 23:15:49.000000', NULL),
	(b'1', 6, 1, 'username_password', 'wiquaffecroiri-4022@yopmail.com', 'Nguyễn Đình Lam', '$2a$10$sP7xiYP2zIaDVoa2xlcQ7.5E7j2s68v4exfeO94yNg0BbIhmEZq1K', b'0', NULL, NULL, b'0', '2024-12-19 09:42:22.122191', '2024-12-19 09:42:22.122191', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
