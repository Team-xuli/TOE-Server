CREATE TABLE `user` (
  `userId` INT NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `credit` INT NOT NULL COMMENT '信用值',
  `money` FLOAT NOT NULL COMMENT '账户余额',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用,0代表无效，1代表有效',
  `accountNonExpired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号有没有过期,0过期,1未过期',
  `accountNonLocked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号有没有被锁定,0锁定,1未锁定',
  `credentialsNonExpired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '凭证有没有过期,0过期,1未过期',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `USERNAME_UNIQUE` (`username`))
COMMENT = '用户表';
