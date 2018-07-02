CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(64) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


