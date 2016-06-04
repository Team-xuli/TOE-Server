CREATE TABLE `user_interest_main` (
  `interestMainId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '兴趣名称',
  `userId` INT(11) NULL COMMENT '用户Id',
  `changeRate` DOUBLE NOT NULL DEFAULT 0.3 COMMENT '兴趣波动率',
  PRIMARY KEY (`interestMainId`))
COMMENT = '用户兴趣主表';