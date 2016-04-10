ALTER TABLE `address`
ADD COLUMN `status` TINYINT(1) NOT NULL AFTER `addressData`,
ADD COLUMN `addresscol` VARCHAR(45) NOT NULL DEFAULT 1 COMMENT '0-无效 1-有效 ' AFTER `status`;
