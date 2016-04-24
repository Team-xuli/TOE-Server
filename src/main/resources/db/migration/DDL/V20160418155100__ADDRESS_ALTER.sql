ALTER TABLE `address`
DROP COLUMN `addressData`,
ADD COLUMN `longitude` DOUBLE NOT NULL DEFAULT 0 COMMENT '经度' AFTER `addressDesc`,
ADD COLUMN `latitude` DOUBLE NOT NULL DEFAULT 0 COMMENT '纬度' AFTER `longitude`;