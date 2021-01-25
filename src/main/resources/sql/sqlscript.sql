

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 24/01/2021 09:28:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
`deptno` int(11) NOT NULL,
`dname` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
`loc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 24/01/2021 09:28:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
`empno` int(11) NOT NULL,
`ename` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
`job` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
`mgr` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
`hiredate` datetime(0) NULL DEFAULT NULL,
`sal` decimal(10, 2) NULL DEFAULT NULL,
`comm` decimal(10, 2) NULL DEFAULT NULL,
`deptno` int(11) NULL DEFAULT NULL,
PRIMARY KEY (`empno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



-- Insert row into DEPT table using named columns.
insert into DEPT (DEPTNO, DNAME, LOC)
values(10, 'ACCOUNTING', 'NEW YORK');

-- Insert a row into DEPT table by column position.
insert into dept
values(20, 'RESEARCH', 'DALLAS');

insert into dept
values(30, 'SALES', 'CHICAGO');

insert into dept
values(40, 'OPERATIONS', 'BOSTON');

-- Insert EMP row, using TO_DATE function to cast string literal into an oracle DATE format.
insert into emp
values(
          7839, 'KING', 'PRESIDENT', null,
          '1981-11-17',
          5000, null, 10
      );

insert into emp
values(
          7698, 'BLAKE', 'MANAGER', 7839,
          '1981-5-1',
          2850, null, 30
      );

insert into emp
values(
          7782, 'CLARK', 'MANAGER', 7839,
          '1981-6-9',
          2450, null, 10
      );

insert into emp
values(
          7566, 'JONES', 'MANAGER', 7839,
          '1981-4-2',
          2975, null, 20
      );

insert into emp
values(
          7788, 'SCOTT', 'ANALYST', 7566,
          '1987-6-13',
          3000, null, 20
      );

insert into emp
values(
          7902, 'FORD', 'ANALYST', 7566,
          '1981-12-3',
          3000, null, 20
      );

insert into emp
values(
          7369, 'SMITH', 'CLERK', 7902,
          '1980-12-17',
          800, null, 20
      );

insert into emp
values(
          7499, 'ALLEN', 'SALESMAN', 7698,
          '1981-12-20',
          1600, 300, 30
      );

insert into emp
values(
          7521, 'WARD', 'SALESMAN', 7698,
          '1981-2-22',
          1250, 500, 30
      );

insert into emp
values(
          7654, 'MARTIN', 'SALESMAN', 7698,
          '1981-9-28',
          1250, 1400, 30
      );

insert into emp
values(
          7844, 'TURNER', 'SALESMAN', 7698,
          '1981-9-8',
          1500, 0, 30
      );

insert into emp
values(
          7876, 'ADAMS', 'CLERK', 7788,
          '1987-7-13',
          1100, null, 20
      );

insert into emp
values(
          7900, 'JAMES', 'CLERK', 7698,
          '1981-12-3',
          950, null, 30
      );

insert into emp
values(
          7934, 'MILLER', 'CLERK', 7782,
          '1982-1-23',
          1300, null, 10
      );

-- Simple natural join between DEPT and EMP tables based on the primary key of the DEPT table DEPTNO, and the DEPTNO foreign key in the EMP table.
select ename, dname, job, empno, hiredate, loc
from emp, dept
where emp.deptno = dept.deptno
order by ename;

-- The GROUP BY clause in the SQL statement allows aggregate functions of non grouped columns.  The join is an inner join thus departments with no employees are not displayed.
select dname, count(*) count_of_employees
from dept, emp
where dept.deptno = emp.deptno
group by DNAME
order by 2 desc