CREATE TABLE `user` (
  `userId` INT NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `userName` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `credit` INT NOT NULL COMMENT '信用值',
  `money` FLOAT NOT NULL COMMENT '账户余额',
  PRIMARY KEY (`userId`))
COMMENT = '用户表';
