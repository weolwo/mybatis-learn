package com.poplar.dao;

import com.poplar.bean.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Create BY poplar ON 2021/1/24
 */
public class DeptDaoTest {

    static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void selectEmpByEmpno() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
        Dept dept = deptDao.selectDeptByDeptno(10);
        System.out.println(dept);
        sqlSession.close();
    }

    //collection -->select测试，懒加载测试
    @Test
    public void selectEmpByDeptnoBySingle() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
        Dept dept = deptDao.selectDeptByDeptnoBySingle(10);
        System.out.println(dept.getDname());//只会执行一条sql
        System.out.println(dept.getEmps());
        sqlSession.close();
    }
}