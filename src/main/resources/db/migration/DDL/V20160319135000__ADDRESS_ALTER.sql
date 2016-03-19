ALTER TABLE `address`
ADD COLUMN `type` TINYINT(1) NOT NULL COMMENT '类型：0-Org,1-Dest' AFTER `userId`;
