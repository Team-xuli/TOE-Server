CREATE TABLE `r_user_role` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleId` INT NOT NULL COMMENT '角色Id',
  `userId` INT NOT NULL COMMENT '用户Id',
  PRIMARY KEY (`id`),
  INDEX `USER_ID_idx` (`userId` ASC),
  INDEX `ROLE_ID_idx` (`roleId` ASC),
  CONSTRAINT `USER_ID`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ROLE_ID`
    FOREIGN KEY (`roleId`)
    REFERENCES `role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = '用户角色关系表';
