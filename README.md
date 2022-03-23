# 변경사항

***
### 210913
user 테이블 column 추가, role, user_role 테이블 생성 : spring security에서 필수 조건
###### alter table user add enabled bit;

-- Table `mydb`.`role`
###### CREATE TABLE IF NOT EXISTS `mydb`.`role` (
######   `id` INT NOT NULL AUTO_INCREMENT,
######   `name` VARCHAR(50) NULL,
######   PRIMARY KEY (`id`))
###### ENGINE = InnoDB;


-- Table `mydb`.`user_role`

###### CREATE TABLE IF NOT EXISTS `mydb`.`user_role` (
######   `user_id` BIGINT NOT NULL,
######   `role_id` INT NOT NULL,
######   PRIMARY KEY (`user_id`, `role_id`),
######   INDEX `fk_user_role_role1_idx` (`role_id` ASC) VISIBLE,
######   CONSTRAINT `fk_user_role_user`
######     FOREIGN KEY (`user_id`)
######     REFERENCES `mydb`.`user` (`id`)
######     ON DELETE NO ACTION
######     ON UPDATE NO ACTION,
######   CONSTRAINT `fk_user_role_role1`
######     FOREIGN KEY (`role_id`)
######     REFERENCES `mydb`.`role` (`id`)
######     ON DELETE NO ACTION
######     ON UPDATE NO ACTION)
###### ENGINE = InnoDB;

###### SET SQL_MODE=@OLD_SQL_MODE;
###### SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
###### SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

###### insert into role(name) values('ROLE_USER');


***
### 210912
board 테이블 column 변경, 테이블 이름 변경 ( boardComment > comment ), 컬럼 추가

###### alter table board change writer view_count bigint;
###### rename table board_comment to comment;
###### alter table comment add registered_at datetime;
