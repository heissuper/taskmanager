DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
  id INT auto_increment NOT NULL COMMENT '主键ID',
  gmt_create TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  gmt_modified TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  title varchar(128) NULL DEFAULT NULL COMMENT '任务名称',
  content TEXT NULL DEFAULT NULL COMMENT '任务内容',
  category_id INT NULL DEFAULT NULL COMMENT '任务分类',
  status varchar(24) NULL DEFAULT NULL COMMENT '任务状态',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS categories;

CREATE TABLE categories
(
  id INT auto_increment NOT NULL COMMENT '主键ID',
  gmt_create TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  gmt_modified TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  name varchar(128) NULL DEFAULT NULL COMMENT '分类名称',
  priority INT NULL DEFAULT NULL COMMENT '分类排序',
  PRIMARY KEY (id)
);