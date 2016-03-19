ALTER TABLE `order`
ADD COLUMN `createTime` DATE NOT NULL COMMENT '创建时间' AFTER `status`,
ADD COLUMN `deadLine` DATE NOT NULL COMMENT '截止时间' AFTER `createTime`,
ADD COLUMN `assignTime` DATE NULL COMMENT '接单时间' AFTER `deadLine`,
ADD COLUMN `endTime` DATE NULL COMMENT '完成时间' AFTER `assignTime`;
