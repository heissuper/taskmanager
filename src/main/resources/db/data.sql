DELETE FROM tasks;

INSERT INTO tasks (id, gmt_create, gmt_modified, category_id, title, status, content) VALUES
  (1, now(), now(), 2, '陪女朋友看电影',   'new', '1周年纪念，千万不能忘'),
  (2, now(), now(), 3, '完成项目B的汇报PPT',         'new', '快交付了，B项目客户很看重'),
  (3, now(), now(), 3, '电话拜访客户A',    'completed', 'A客户可是VIP啊'),
  (4, now(), now(), 2, '回家买包盐',          'completed', '。。。'),
  (5, now(), now(), 1, '读书：《一个演员的自我修养》',  'new', '好好学习');


DELETE FROM categories;

INSERT INTO categories (id, gmt_create, gmt_modified, name, priority) VALUES
  (1, now(), now(), '默认',   1),
  (2, now(), now(), '生活',   2),
  (3, now(), now(), '工作',   3);