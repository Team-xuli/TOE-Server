CREATE TABLE `item_type_dict` (
  `itemTypeId` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(45) NOT NULL COMMENT '名称',
  `description` VARCHAR(45) NOT NULL COMMENT '描述',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-无效，1-有效',
  PRIMARY KEY (`itemTypeId`),
  UNIQUE INDEX `itemTypeId_UNIQUE` (`itemTypeId` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
COMMENT = '物品种类字典表';
