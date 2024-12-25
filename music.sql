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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.albums: ~0 rows (approximately)

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
INSERT INTO `artists` (`id`, `name`, `bio`, `avatar_id`, `created_at`, `updated_at`) VALUES
	(1, 'Đen ', 'Anh Đen', 132, '2024-12-05 21:04:39.000000', NULL),
	(2, 'Vũ', NULL, 129, '2024-12-06 01:40:34.000000', NULL),
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
INSERT INTO `genres` (`id`, `description`, `name`, `cover_id`, `created_at`, `updated_at`) VALUES
	(21, NULL, 'BALAB', NULL, '2024-12-05 23:07:58.000000', '2024-12-05 23:08:02.000000'),
	(22, NULL, 'EDM', NULL, NULL, NULL),
	(23, NULL, 'KPOP', NULL, NULL, NULL),
	(24, NULL, 'JPOP', NULL, NULL, NULL),
	(25, NULL, 'RAP', NULL, NULL, NULL),
	(26, NULL, 'MELODY', NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.listening_histories: ~0 rows (approximately)

-- Dumping structure for table music-player.permissions
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.permissions: ~0 rows (approximately)

-- Dumping structure for table music-player.permission_role
CREATE TABLE IF NOT EXISTS `permission_role` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK6mg4g9rc8u87l0yavf8kjut05` (`permission_id`),
  CONSTRAINT `FK3vhflqw0lwbwn49xqoivrtugt` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK6mg4g9rc8u87l0yavf8kjut05` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.permission_role: ~0 rows (approximately)

-- Dumping structure for table music-player.playlists
CREATE TABLE IF NOT EXISTS `playlists` (
  `is_public` bit(1) NOT NULL,
  `cover_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5e6k95ks9j9g2l6s0ha2d4v2a` (`cover_id`),
  KEY `FKtgjwvfg23v990xk7k0idmqbrj` (`user_id`),
  CONSTRAINT `FKpja54sfifmddiijlb4upigvqt` FOREIGN KEY (`cover_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `FKtgjwvfg23v990xk7k0idmqbrj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.playlists: ~1 rows (approximately)

-- Dumping structure for table music-player.playlists_songs
CREATE TABLE IF NOT EXISTS `playlists_songs` (
  `playlist_id` bigint NOT NULL,
  `song_id` bigint NOT NULL,
  PRIMARY KEY (`playlist_id`,`song_id`),
  KEY `FK5pyqf1ff6dfj1xbn5p626i0dr` (`song_id`),
  CONSTRAINT `FK2emrpqhxanqunhcodn7s5yca4` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`),
  CONSTRAINT `FK5pyqf1ff6dfj1xbn5p626i0dr` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.playlists_songs: ~0 rows (approximately)

-- Dumping structure for table music-player.resources
CREATE TABLE IF NOT EXISTS `resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `public_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tag` enum('ALBUM','ARTIST','AUDIO','GENRE','SONG','USER') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.resources: ~20 rows (approximately)
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
	(132, 'music-media/image/artist/den-vau', 'ARTIST', '2024-12-05 18:39:24', '2024-12-05 18:41:43', 'avatar-den-vau');

-- Dumping structure for table music-player.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('ADMIN','SHIPPER','USER') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.roles: ~3 rows (approximately)
INSERT INTO `roles` (`id`, `name`, `created_at`, `updated_at`) VALUES
	(1, 'USER', '2024-11-15 16:18:44', '2024-11-15 16:18:44'),
	(2, 'ADMIN', '2024-11-15 16:18:44', '2024-11-15 16:18:44'),
	(3, 'SHIPPER', '2024-11-15 16:18:44', '2024-11-15 16:18:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=1120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.songs: ~9 rows (approximately)
INSERT INTO `songs` (`artist_id`, `cover_id`, `created_at`, `genre_id`, `id`, `source_id`, `updated_at`, `title`, `is_premium`, `views`, `album_id`) VALUES
	(2, 117, '2024-12-05 23:07:48.000000', 25, 1, 105, NULL, 'Những lời hứa bỏ quên', b'1', 00000000000000000000000000000000000000, NULL),
	(2, 115, '2024-12-05 23:07:47.000000', 22, 2, 104, NULL, 'Nếu những tiêc nuối', b'0', 00000000000000000000000000000000000000, NULL),
	(1, 120, '2024-12-05 23:13:30.000000', 22, 1113, 112, NULL, 'Mang tiền về cho mẹ ', b'0', 00000000000000000000000000000000000000, NULL),
	(1, 119, '2024-12-05 23:13:47.000000', 25, 1114, 110, NULL, 'Lối nhỏ', b'0', 00000000000000000000000000000000000000, NULL),
	(8, 121, '2024-12-05 23:14:03.000000', 25, 1115, 108, NULL, 'Chăm hoa', b'0', 00000000000000000000000000000000000000, NULL),
	(6, 107, '2024-12-05 23:14:14.000000', 22, 1116, 107, NULL, 'THERE’S NO ONE AT ALL', b'0', 00000000000000000000000000000000000000, NULL),
	(4, 116, '2024-12-05 23:14:41.000000', 21, 1117, 113, NULL, 'Tết đong đầy', b'0', 00000000000000000000000000000000000000, NULL),
	(5, 122, '2024-12-05 23:14:41.000000', 22, 1118, 111, NULL, 'Bạn đời', b'0', 00000000000000000000000000000000000000, NULL),
	(7, 124, '2024-12-05 23:15:03.000000', 25, 1119, 109, NULL, 'Thiên lý ơi', b'0', 00000000000000000000000000000000000000, NULL);

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
  PRIMARY KEY (`id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table music-player.users: ~1 rows (approximately)
INSERT INTO `users` (`verified`, `id`, `role_id`, `auth_type`, `email`, `full_name`, `password`, `is_premium`, `birth_day`, `phone_number`, `sex`, `created_at`, `updated_at`) VALUES
	(b'1', 1, 1, 'username_password', 'ducvui2003@gmail.com', 'Lê Anh Đức', '$2a$10$ZjCmdQgwHTN45hmZ4h2cseaGHlI6D19IuxzZ8Y4RFCR72UmNRi7wm', b'1', '2024-11-05', '0865677047', b'1', '2024-11-15 23:15:22.000000', '2024-11-15 23:15:49.000000');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
