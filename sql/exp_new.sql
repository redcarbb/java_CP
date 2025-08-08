CREATE TABLE `todo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userId` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `memo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 