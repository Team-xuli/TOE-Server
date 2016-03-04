CREATE TABLE `address` (
  `addressId` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` INT NOT NULL COMMENT '用户id',
  `calledName` VARCHAR(45) NOT NULL COMMENT '称呼',
  `phoneNo` VARCHAR(45) NOT NULL COMMENT '电话号',
  `addressDesc` VARCHAR(45) NOT NULL COMMENT '地址描述',
  `addressData` VARCHAR(90) NOT NULL COMMENT '百度API地址',
  PRIMARY KEY (`addressId`),
  INDEX `USER_ID_idx` (`userId` ASC),
  CONSTRAINT `ADDRESS_USER`
    FOREIGN KEY (`userId`)
    REFERENCES `usong`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = '地址表';
