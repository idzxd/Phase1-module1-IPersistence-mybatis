package com.zxd.test;

import com.zxd.dao.IUserDao;
import com.zxd.io.Resources;
import com.zxd.pojo.User;
import com.zxd.sqlSession.SqlSession;
import com.zxd.sqlSession.SqlSessionFactory;
import com.zxd.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("张三");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }


    }

    /**
     * 添加
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(13);
        user.setUsername("王五");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.saveUser(user);

    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(13);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.deleteUser(user);

    }
    @Test
    public void test4() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(4);
        user.setUsername("马六");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.updateUser(user);

    }



}
