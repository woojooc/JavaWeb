package study.study.model.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity                      // == table
@ToString(exclude = {"user"})
@EntityListeners(AuditingEntityListener.class)
@Builder                        //생성
@Accessors(chain = true)
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

/*
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `study` ;

-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `study` ;

-- -----------------------------------------------------
-- Table `study`.`admin_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`admin_user` ;

CREATE TABLE IF NOT EXISTS `study`.`admin_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `last_login_at` DATETIME NULL DEFAULT NULL,
  `login_fail_count` INT NULL DEFAULT NULL,
  `password_updated_at` DATETIME NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`category` ;

CREATE TABLE IF NOT EXISTS `study`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`item` ;

CREATE TABLE IF NOT EXISTS `study`.`item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `price` DECIMAL(12,4) NOT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `registered_at` VARCHAR(45) NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `partner_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 406
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`order_detail` ;

CREATE TABLE IF NOT EXISTS `study`.`order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `arrival_date` DATETIME NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `order_group_id` BIGINT NOT NULL,
  `item_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 30333
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`order_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`order_group` ;

CREATE TABLE IF NOT EXISTS `study`.`order_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `order_type` VARCHAR(50) NOT NULL,
  `rev_address` VARCHAR(100) NULL DEFAULT NULL,
  `rev_name` VARCHAR(50) NULL DEFAULT NULL,
  `payment_type` VARCHAR(50) NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `total_quantity` INT NULL DEFAULT NULL,
  `order_at` DATETIME NULL DEFAULT NULL,
  `arrival_date` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5470
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`partner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`partner` ;

CREATE TABLE IF NOT EXISTS `study`.`partner` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `call_center` VARCHAR(13) NULL DEFAULT NULL,
  `partner_number` VARCHAR(13) NULL DEFAULT NULL,
  `business_number` VARCHAR(16) NULL DEFAULT NULL,
  `ceo_name` VARCHAR(20) NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`user` ;

CREATE TABLE IF NOT EXISTS `study`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `settlement_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1003
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`settlement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`settlement` ;

CREATE TABLE IF NOT EXISTS `study`.`settlement` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(20) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`partner_copy1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`partner_copy1` ;

CREATE TABLE IF NOT EXISTS `study`.`partner_copy1` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `call_center` VARCHAR(13) NULL DEFAULT NULL,
  `partner_number` VARCHAR(13) NULL DEFAULT NULL,
  `business_number` VARCHAR(16) NULL DEFAULT NULL,
  `ceo_name` VARCHAR(20) NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`partner_copy2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`partner_copy2` ;

CREATE TABLE IF NOT EXISTS `study`.`partner_copy2` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `call_center` VARCHAR(13) NULL DEFAULT NULL,
  `partner_number` VARCHAR(13) NULL DEFAULT NULL,
  `business_number` VARCHAR(16) NULL DEFAULT NULL,
  `ceo_name` VARCHAR(20) NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

 */