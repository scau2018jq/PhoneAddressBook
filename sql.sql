INSERT INTO `chatpro`.`linkman` (`name`,`sex`,`telephone`,`QQ`,`email`,`birthday`,`Fname`,`SortID`,`group`) 
VALUES('王俊钦','男','17819566267','446991363','446991363@qq.com','1999-01-21','w','0','家人') ;

INSERT INTO `chatpro`.`linkman` (`name`,`sex`,`telephone`,`QQ`,`email`,`birthday`,`Fname`,`SortID`,`group`) 
VALUES('王俊','男','13411903978','1532154121','1532154121@qq.com','1999-01-21','w','0','家人') ;

INSERT INTO `chatpro`.`linkman` (`name`,`sex`,`telephone`,`QQ`,`email`,`birthday`,`Fname`,`SortID`,`group`) 
VALUES('王俊','男','1341194522978','15321452121','15321541452@qq.com','1999-01-21','w','0','家人') ;

SELECT * FROM linkman;

SELECT * FROM linkman WHERE `name` = '阿萨德';

SELECT * FROM linkman WHERE `group` IS NULL;

SELECT * FROM linkman WHERE NAME LIKE "%俊%"
`group`KE CONCAT(CONCAT('%','俊'),'%');

DELETE FROM linkman WHERE NAME = '林欣敏'

UPDATE linkman SET `name`='林新民',`sex`='男',`telephone`='45321511',`QQ`='785271',`email`='785271@qq.com',`birthday`='1999-01-21',`Fname`='w',`SortID`='0',`group`='家人' WHERE NAME = '林新民'

SELECT * FROM linkman WHERE NAME LIKE CONCAT(CONCAT('%','俊'),'%') OR NAME LIKE CONCAT('%','俊') OR NAME LIKE CONCAT('俊','%');

INSERT INTO `chatpro`.`group` (`group`) VALUES('朋友') ;

SELECT * FROM `chatpro`.`group`;

DELETE FROM `chatpro`.`group` WHERE `group` = '朋友'