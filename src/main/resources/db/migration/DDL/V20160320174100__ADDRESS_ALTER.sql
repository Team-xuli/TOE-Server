ALTER TABLE `address` 
DROP FOREIGN KEY `ADDRESS_USER`;
ALTER TABLE `address` 
CHANGE COLUMN `userId` `userId` INT(11) NULL COMMENT '用户id' ;
ALTER TABLE `address` 
ADD CONSTRAINT `ADDRESS_USER`
  FOREIGN KEY (`userId`)
  REFERENCES `user` (`userId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
