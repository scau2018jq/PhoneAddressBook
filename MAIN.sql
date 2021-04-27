SELECT * FROM linkman INTO OUTFILE 'E:/linkman5.csv' CHARACTER SET utf8
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'E:/linkman5.csv' 
INTO TABLE `linkman` CHARACTER SET utf8
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '' LINES TERMINATED BY '\n';

SHOW VARIABLES LIKE '%secure%';

SELECT * FROM `group` ORDER BY gID;

INSERT INTO `chatpro`.`group` (`group`) VALUES('同血');

INSERT INTO `chatpro`.`group` (`group`) 
VALUES
  ('朋友') ;

DELETE FROM `chatpro`.`group` WHERE `group` = '朋友';

ALTER TABLE `group` ADD UNIQUE (groupn);

SELECT * FROM `linkman`;

INSERT INTO `chatpro`.`linkman` (
  `ID`,
  `name`,
  `sex`,
  `telephone`,
  `QQ`,
  `email`,
  `birthday`,
  `Fname`,
  `SortID`,
  `group`
) 
VALUES
  (
    '0',
    'wasd',
    '男',
    '345314143',
    '345345445',
    '3451445@qq.com',
    '1999-01-21',
    'w',
    '2',
    '家人'
  ) ;
  
  SHOW VARIABLES LIKE 'sql_mode';
  
  SET sql_mode='';

