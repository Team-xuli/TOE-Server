-- Date to DateTime

ALTER TABLE `order`
CHANGE COLUMN `createTime` `createTime` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `deadLine` `deadLine` DATETIME NULL DEFAULT NULL COMMENT '截止时间' ,
CHANGE COLUMN `assignTime` `assignTime` DATETIME NULL DEFAULT NULL COMMENT '接单时间' ,
CHANGE COLUMN `endTime` `endTime` DATETIME NULL DEFAULT NULL COMMENT '完成时间' ;