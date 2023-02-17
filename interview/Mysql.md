## 6.4 Hash索引和B+树索引的区别
+ 1.Hash索引不适合范围查找，但B+树适合，Hash索引映射的数据是无序的数据，B+树的叶子节点是有序的链表
+ 2.Hash索引不能使用联合索引中的最左原则，Hash索引在计算hash值得时候，是将索引字段合并起来计算hash值，hash索引没有为每个索引字段单独计算hash值
+ 所以当使用联合索引中的单个或多个索引的时候，联合索引无法被使用
+ 3.Hash索引不能使用order by，但B+树可以，Hash映射的是无序的数据，不适合排序的优化
+ 4.Hash索引不能使用模糊查询，Hash索引是等值查找
+ 5.Hash索引适合索引字段的值重复率小，等值查找的场景，由于Hash索引有很多限制，所以InnoDB采取的是B+树索引
## 6.3 mysql索引怎么实现，最左前缀
+ 索引是一种用来快速查询和检索数据的数据结构，它的本质可以看作是一种排序好的数据结构
+ 有许多种类型的数据结构，常见的索引结构有：B树，B+树，Hash，红黑树，在Mysql种Innodb和MyIsam用的都是B+树作为索引结构
+ 索引的优点：
+ 索引可大大加快检索数据的速度；通过创建唯一索引，你可以保证数据表种每一行数据的唯一性
+ 索引的缺点：
+ 索引需要花费大量时间创建和维护，当数据表插入删除操作的时候，如果数据有索引，这些索引也需要动态修改，降低了sql的执行效率
+ 索引需要保存在磁盘上，会占用一定的空间
+ 使用索引总会提高查询性能吗？
+ 大多数情况是这样的，索引要比全表扫描快，但是如果你的数据量很少，索引就不一定带来很大的提升了
+ 联合索引：索引在一张表种使用了超过一个字段就称为联合索引，也被称为复合索引
+ 最左匹配原则是使用在联合索引的时候，MySQL会根据联合索引中排序好的字段，到查询条件中从左到右进行匹配，如果查询条件中，存在
+ 与联合索引中，最左侧字段相匹配的字段，就会使用该字段过滤掉一批数据，匹配直到联合索引中所有字段都被匹配过才会停止
+ 或者遇到范围查询符>，<，between，like以%为开始
## 6.2 MySQL的两种存储引擎 InnoDB 和 MyISAM 的区别
+ 1.InnoDB支持外键和事务，MyISAM都不支持
+ 2.InnoDB支持行锁和表锁，MyISAM只支持表锁
+ 3.InnoDB数据库文件和索引文件，是放在一起的，MyISAM的数据文件和索引文件是分开放的
+ 4.InnoDB是聚簇索引，叶子节点存放的是数据，MyISAM是非聚簇索引，叶子节点存放的是主键值
## 6.1 介绍下 MySQL 聚簇索引与非聚簇索引的区别
+ 聚簇索引的叶子节点存放的是实际的数据，所有用户真实的记录都存在叶子节点上，非聚簇索引的叶子节点存储的是主键值
+ 由于数据在物理上只有一份，随意一个表只能有一个聚簇索引，聚簇索引默认是使用主键作为索引，如果没有主键则会从左往右，选择一个不含null值得唯一列作为索引
+ 如果以上两种情况都没有，就会默认创建一个隐式自增的字段作为索引，而针对非主键字段的查找引用了非聚簇索引，非聚簇索引字段的选择，取决于场景的不同，非聚簇索引可以有多个
+ 如果查询语句中有非聚簇索引，如果查询的值不是主键，则需要再到聚簇索引中查找数据，需要通过查两个B+树来查数据，这一过程叫回表，如果查的是主键，只要查一棵B+树，这一过程叫索引覆盖
## InnoDB如何选择聚簇索引
+ 如果表里有主键，就会默认选择主键所在的列，作为聚簇索引的索引键
+ 如果表里没有主键，会选择非null值的所在列，作为聚簇索引的索引键
+ 如果两者都没有，会自动创建一个隐式的自增id列，作为聚簇索引的索引键
- 通过二次索引查找数据，需要再次查询主键索引的B+树，这叫回表
- 在二级索引里面，就能查找到数据，不用回表，就称为覆盖索引
- 利用索引的前提，是索引里的key值是有序的

## 什么时候适用索引？
+ 可以区分唯一性的字段
+ where条件中用到的字段
+ Group by 和 Order by中用到的字段
- 经常给更新的数据，比如用户余额，不适合建索引，维护B+树的有序性，需要不断重建索引
- 数据量少的表，存在大量重复数据的列，经常更新数据的列，不适合建立索引

## 什么时候索引会失效？
+ 当查询条件中，出现左like %条件或者左右like %字段%的时候，索引会失效
+ 当查询条件中，对索引列做计算，函数操作，类型转换的时候，索引会失效
+ 当查询条件中or的前一个是索引列，后一个不是索引列，索引会失效
+ 当联合索引查询的时候，没有遵循最左原则，索引会失效

## B+树的特点
+ 非叶子节点，只存储索引，叶子节点存放索引和记录
+ 所有的非叶子节点的索引，都会出现在叶子节点，所有的叶子节点之间通过链表连接

## InnoDB 是如何存储数据的？
+ 数据库表中的记录的读取是以行为单位的，每一次读取就是一次IO操作，如果每次只读一行，效率太低了
+ InnoDB每次从磁盘读取数据到内存中，或从内存刷新数据到磁盘是以数据页为单位的，页大小为16KB
+ 数据页按照主键的顺序，采用单向链表连接，链表不要求磁盘上连续的空间，只要求逻辑上连续
+ 链表插入删除效率高，但检索效率低，最差的情况，需要遍历链表上所有的节点
+ 数据页中的页目录，相当于数据表中索引的作用，帮助我们快速找到记录
+ 数据页将记录分为不同的组，包括最小值，最大值，每个组的最后一条记录，就是本组的最大记录
+ 目录页中的槽，指向每个分组的最后一条记录的偏移量，最后一条记录，保存了本组所有的记录数
+ 通过二分法，先搜索槽，再通过槽，找到要找的记录数，槽相当于目录页的索引
+ InnoDB对数据分组中的记录数是有规定的：第一个分组只能有一条记录
+ 最后一个分组的记录数范围是1-8，剩余的分组记录数范围是4-8
+ 单个数据页，是通过将记录分组的方法，通过目录页中的槽号指向组中最后一条记录偏离量建立索引
+ 通过二分法，快速找到要查找的记录，当需要存储大量记录时，就需要大量的数据页
+ InnoDB需要为数据页建立索引，采用了矮胖的B+树，减少了I/O的磁盘操作次数


## MySQL的两种存储引擎 InnoDB 和 MyISAM 的区别？
+ InnoDB支持外键，支持事务，MyISAM都不支持
+ InnoDB支持行锁，支持表锁，MyISAM只支持表锁
+ InnoDB的数据库文件和索引文件，放在一起，MyISAM的数据库文件和索引文件是分开放的
+ InnoDB是聚簇索引，数据文件保存在主键索引的叶子节点上，MyISAM是非聚簇索引，叶子节点保存的是指向数据的指针
+ InnoDB不保存总行数，执行select count(*)要进行全表扫描，MyISAM用一个变量保存总行数，查询速度快
+ InnoDB的底层实现是用的B+数，只有叶子节点保存数据，非叶子节点保存索引

# 79-存储过程的创建与调用
```mysql
#存储过程与存储函数

#0.准备工作

CREATE DATABASE dbtest15

USE dbtest15

CREATE TABLE employees
AS
SELECT * FROM atguigudb.`employees`;

CREATE TABLE departments
AS
SELECT * FROM atguigudb.`departments`;

#1.创建存储过程

#类型1：无参数，无返回值

#举例1：创建存储过程select_all_data()，查看employees表的所有数据

DELIMITER $

CREATE PROCEDURE select_all_data()

BEGIN
  SELECT * FROM employess;
END $

DELIMITER ;

#2. 存储过程的调用

CALL select_all_data();

#举例2：创建存储过程avg_employee_salary()，返回所有员工的平均工资

DELIMITER //

CREATE PROCEDURE avg_employee_salary()

BEGIN
   SELECT AVG(salary) FROM empolyees;
END //

DELIMITER ;

#举例3：创建存储过程show_max_salary()。用来查看"emps"表的最高薪资值。

DELIMITER //

CREATE PROCEDURE show_max_salary()
BEGIN
   SELECT MAX(salary) FEOM employees;
END //

DELIMITER ;

#类型2：带 OUT
#举例4：创建存储过程show_min_salary()。查看“emps”表的最低薪资值
#并将最低薪资值，通过OUT参数“ms”输出

DESC employees;

DELIMETER //

CREATE PROCEDURE show_min_salary(OUT ms DOUBLE)
BEGIN
   SELECT MIN(salary) INTO ms from employees;
END //

DELIMIETER ;

#调用

CALL show_min_salary(@ms);

#查看变量值

SELECT @ms;

#举例5：创建存储过程show_someone_salary()，查看”emps“表的某个员工的薪资
#并用IN参数empname输入员工姓名。

DELIMIETER //

CREATE PROCEDURE show_someone_salary(IN empname VARCHAR(20))
BEGIN
   SELECT salary FROM employees where last_name=empname;
END //

DELIMITER ;

#调用
CALL show_some_one_salary("Abel");

##类型4：带IN和OUT
#举例6：创建存储过程show_someone_salary2()，查看“emps”表的某个员工的薪资
#并用IN参数empname输入员工姓名，用OUT参数empsalary输出员工薪资

DELIMITER //

CREATE PROCEDURE show_someone_salary2(IN empname VARCHAR(20),OUT empsalary DECIMAL(10,2))
BEGIN
   SELECT salary INTO empsalary
   FROM employees
   where last_name=empname;

DELIMITER ;

#调用
SET @empname='Abel';
CALL show_someone_salary2(@empname,@empsalary);

select @empsalary;
```
