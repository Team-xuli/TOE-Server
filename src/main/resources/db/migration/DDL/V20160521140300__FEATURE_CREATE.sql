CREATE TABLE `user_interest_feature` (
  `interestFeatureId` INT(11) NOT NULL AUTO_INCREMENT,
  `interestMainId` INT(11) NULL COMMENT '兴趣点Id',
  `name` VARCHAR(45) NULL COMMENT '特性名称',
  `weight` DOUBLE NULL COMMENT '权重',
  PRIMARY KEY (`interestFeatureId`),
  INDEX `main_feature_idx` (`interestMainId` ASC),
  CONSTRAINT `main_feature`
    FOREIGN KEY (`interestMainId`)
    REFERENCES `user_interest_main` (`interestMainId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = '用户兴趣特性表';
