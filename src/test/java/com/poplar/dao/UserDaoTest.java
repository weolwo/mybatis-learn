package com.poplar.dao;

import com.poplar.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Create BY poplar ON 2021/1/24
 */

public class UserDaoTest {
    static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void saveUser() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUserName("猫花");
        Integer userId = userDao.saveUser(user);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void selectEmpByUserId() {
    }
}
