CREATE TABLE `order` (
  `orderId` INT NOT NULL COMMENT '订单主键',
  `carrierId` INT NOT NULL COMMENT '派送员',
  `orgAddressId` INT NOT NULL COMMENT '起始地址',
  `destAddressId` INT NOT NULL COMMENT '目的地址',
  `description` VARCHAR(45) NOT NULL COMMENT '描述',
  `payment` INT NOT NULL COMMENT '金额',
  `status` TINYINT NULL COMMENT '状态：0-新订单，1-已接单，2-送达',
  PRIMARY KEY (`orderId`),
  INDEX `ORDER_USER_idx` (`carrierId` ASC),
  INDEX `ORG_ADDRESS_idx` (`orgAddressId` ASC),
  INDEX `DEST_ADDRESS_idx` (`destAddressId` ASC),
  CONSTRAINT `ORDER_USER`
    FOREIGN KEY (`carrierId`)
    REFERENCES `user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ORG_ADDRESS`
    FOREIGN KEY (`orgAddressId`)
    REFERENCES `address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DEST_ADDRESS`
    FOREIGN KEY (`destAddressId`)
    REFERENCES `address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = '订单表';
