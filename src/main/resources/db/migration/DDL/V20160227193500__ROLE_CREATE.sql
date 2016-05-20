CREATE TABLE `role` (
  `roleId` INT NOT NULL COMMENT '角色id',
  `roleName` VARCHAR(45) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(45) NOT NULL COMMENT '描述',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`roleId`))
COMMENT = '角色表';
