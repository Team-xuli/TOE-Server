ALTER TABLE `order`
ADD COLUMN `itemTypeId` INT(11) NOT NULL DEFAULT 1 COMMENT '物品类型' AFTER `description`,
ADD COLUMN `weight` INT(11) NOT NULL DEFAULT 1 COMMENT '物品重量' AFTER `itemTypeId`,
ADD COLUMN `disdance` INT(11) NOT NULL DEFAULT 1 COMMENT '距离' AFTER `weight`,
ADD INDEX `ITEM_TYPE_FK_idx` (`itemTypeId` ASC);
ALTER TABLE `order`
ADD CONSTRAINT `ITEM_TYPE_FK`
  FOREIGN KEY (`itemTypeId`)
  REFERENCES `item_type_dict` (`itemTypeId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;