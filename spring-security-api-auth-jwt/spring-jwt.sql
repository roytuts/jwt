CREATE DATABASE IF NOT EXISTS `roytuts`;
USE `roytuts`;

CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_pass` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user` (`user_name`, `user_pass`, `enable`) VALUES
	('admin', '$2a$10$okLy2UqGmzjecYK.8zzOrOlYv7fd6/wx7/.MwanKyn9RupY7SCtum', 1);

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_role` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `user_name` (`user_name`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user_role` (`user_name`, `user_role`) VALUES
	('admin', 'ROLE_USER'),
	('admin', 'ROLE_ADMIN');
