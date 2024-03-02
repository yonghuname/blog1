/*
 Navicat Premium Data Transfer

 Source Server         : mysql02
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : blog1

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 02/03/2024 13:16:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `views` int NULL DEFAULT NULL,
  `type_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id` ASC) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (3, b'0', b'0', '草稿', NULL, 'https://i2.hdslb.com/bfs/archive/4758b63870d9d0e5f62221733e80e93a0f5a8416.jpg', '转载', b'0', b'0', b'0', '草稿', NULL, 19, 2, '2024-03-02 04:31:34.602000', '233');
INSERT INTO `t_blog` VALUES (4, b'0', b'0', '1', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'1', b'0', b'0', 'todo，编辑博客debug', NULL, 19, 2, '2024-02-29 21:33:50.094000', '234');
INSERT INTO `t_blog` VALUES (5, b'0', b'0', '1', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'1', b'1', b'0', 'todo 是什么导致了后缀添加字符串的地址访问', NULL, 19, 2, '2024-02-29 21:33:43.842000', '233');
INSERT INTO `t_blog` VALUES (6, b'0', b'0', '1', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'0', b'0', b'1', '6', NULL, 19, 2, '2024-02-29 21:33:18.434000', '去');
INSERT INTO `t_blog` VALUES (7, b'0', b'0', '  。相对路径坑，还是绝对路径可靠，因为相对路径找不到。在复杂的可能会生成多级目录的情况下就找不到祖宗了，太悲哀了。。你们这是个什么thymeleaf啊，害人不浅啊。', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'1', b'1', b'0', 'thymeleaf害人不浅', NULL, 19, 2, '2024-03-02 13:04:31.892000', '这是一篇痛斥themeleaf的文章');
INSERT INTO `t_blog` VALUES (9, b'0', b'0', '1', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'0', b'0', b'0', '1', NULL, 19, 2, '2024-02-29 21:33:36.673000', '1');
INSERT INTO `t_blog` VALUES (10, b'0', b'0', 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'1', b'0', b'0', ' 23', NULL, 19, 2, '2024-02-29 21:34:52.726000', 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg');
INSERT INTO `t_blog` VALUES (11, b'0', b'0', 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', NULL, 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg', '', b'1', b'0', b'0', '24123', NULL, 19, 2, '2024-02-29 21:34:58.015000', 'https://img14.360buyimg.com/pop/jfs/t1/174609/18/38571/30241/65321feaF0cca8d35/e3fab094c9b8eeec.jpg');

-- ----------------------------
-- Table structure for t_blog_seq
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_seq`;
CREATE TABLE `t_blog_seq`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_seq
-- ----------------------------
INSERT INTO `t_blog_seq` VALUES (51);

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blogs_id` bigint NOT NULL,
  `tags_id` bigint NOT NULL,
  INDEX `FK5feau0gb4lq47fdb03uboswm8`(`tags_id` ASC) USING BTREE,
  INDEX `FKh4pacwjwofrugxa9hpwaxg6mr`(`blogs_id` ASC) USING BTREE,
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES (6, 3);
INSERT INTO `t_blog_tags` VALUES (6, 7);
INSERT INTO `t_blog_tags` VALUES (9, 6);
INSERT INTO `t_blog_tags` VALUES (5, 3);
INSERT INTO `t_blog_tags` VALUES (4, 3);
INSERT INTO `t_blog_tags` VALUES (10, 6);
INSERT INTO `t_blog_tags` VALUES (11, 3);
INSERT INTO `t_blog_tags` VALUES (3, 3);
INSERT INTO `t_blog_tags` VALUES (3, 6);
INSERT INTO `t_blog_tags` VALUES (3, 7);
INSERT INTO `t_blog_tags` VALUES (7, 3);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `blog_id` bigint NULL DEFAULT NULL,
  `parent_comment_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKke3uogd04j4jx316m1p51e05u`(`blog_id` ASC) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id` ASC) USING BTREE,
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment_reply_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_reply_comment`;
CREATE TABLE `t_comment_reply_comment`  (
  `comment_id` bigint NOT NULL,
  `reply_comment_id` bigint NOT NULL,
  UNIQUE INDEX `UK_tnswom9empncn1dt8pn9b5ain`(`reply_comment_id` ASC) USING BTREE,
  INDEX `FK7lpf2g1d4qew1jm0vngggy2a4`(`comment_id` ASC) USING BTREE,
  CONSTRAINT `FK5lcndoa674qo2anwtnb1r4e99` FOREIGN KEY (`reply_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK7lpf2g1d4qew1jm0vngggy2a4` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment_reply_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (3, '吐槽');
INSERT INTO `t_tag` VALUES (6, '其他');
INSERT INTO `t_tag` VALUES (7, 'Java');
INSERT INTO `t_tag` VALUES (8, 'Python');
INSERT INTO `t_tag` VALUES (9, 'C/C++');
INSERT INTO `t_tag` VALUES (10, '算法竞赛');
INSERT INTO `t_tag` VALUES (11, '数学');
INSERT INTO `t_tag` VALUES (12, '英语');
INSERT INTO `t_tag` VALUES (13, '琐事');
INSERT INTO `t_tag` VALUES (14, '作业');
INSERT INTO `t_tag` VALUES (15, '考研');
INSERT INTO `t_tag` VALUES (16, '开发 ');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (19, '写文');
INSERT INTO `t_type` VALUES (20, '作业');
INSERT INTO `t_type` VALUES (21, '深度学习');
INSERT INTO `t_type` VALUES (22, '开发');
INSERT INTO `t_type` VALUES (23, '考研');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '5zWaHfjXKV', '2003-06-16 23:23:11.000000', 'anqiqin@icloud.com', 'Qin Anqi', '123456e', 762, '2010-08-20 10:29:46.000000', 'Qin Anqi');
INSERT INTO `t_user` VALUES (2, 'https://img2.baidu.com/it/u=1024056879,1494797032&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500}', '2024-02-23 02:50:28.000000', 'oftang@foxmail.com', 'eeettt', '8cef992a5af6b216dd3c62ae39f4a13b', 1, '2024-02-23 02:51:13.000000', 'eeettt');

SET FOREIGN_KEY_CHECKS = 1;
