CREATE TABLE `4trade`.`category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `image_url` VARCHAR(45) NULL,
  `is_active` BOOLEAN NULL,
  PRIMARY KEY (`id`));