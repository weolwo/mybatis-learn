package com.poplar.dao;


import com.poplar.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create BY poplar ON 2021/1/24
 */
public class EmpDaoTest {

    static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    //默认以及缓存测试
    @Test
    public void selectEmpByEmpno() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = empDao.selectEmpByEmpno(1);
        System.out.println(emp);
        //手动清除一级缓存
        //sqlSession.clearCache();
        emp.setSal(222.1);
        empDao.update(emp);
        Emp e = empDao.selectEmpByEmpno(1);
        System.out.println(e);
        sqlSession.close();
    }

    //resultMap-->association 测试
    @Test
    public void selectEmpAndDeptByEmpno() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = empDao.selectEmpAndDeptByEmpno(7369);
        System.out.println(emp);
        sqlSession.close();
    }

    @Test
    public void selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        List<Emp> emps = empDao.selectAll();
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //使用@Param
    @Test
    public void selectEmpByEmpnoAndSal() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        List<Emp> emps = empDao.selectEmpByEmpnoAndSal(10, 500.0);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //自定义map结构
    @Test
    public void selectEmpByEmpnoAndSalMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("deptno", 10);
        map.put("sal", 500);
        List<Emp> emps = empDao.selectEmpByEmpnoAndSalMap(map);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //动态sql拼接测试
    @Test
    public void selectEmpByCondition() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = new Emp();
        //emp.setEname("kkkkk");
        emp.setEmpno(7369);
        //emp.setSal(1000.0);
        List<Emp> emps = empDao.selectEmpByCondition(emp);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //foreach 测试
    @Test
    public void selectEmpByConditionByIn() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);

        List<Emp> emps = empDao.selectEmpByConditionByIn(Arrays.asList(7369,7499));
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void save() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = new Emp();
        emp.setEname("kkkkk");
        emp.setEmpno(1);
        Integer save = empDao.save(emp);
        System.out.println(save);
        sqlSession.close();
    }

    @Test
    public void update() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = new Emp();
        emp.setEname("mmmmmmmmm");
        emp.setEmpno(1);
        Integer update = empDao.update(emp);
        System.out.println(update);
        sqlSession.close();
    }

    @Test
    public void delete() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Integer delete = empDao.delete(1);
        System.out.println(delete);
        sqlSession.close();
    }
}