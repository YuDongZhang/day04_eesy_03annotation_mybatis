package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);//改成true是可以自动提交
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //提交事务
//        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 保存用户
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("张三");
        user.setAddress("南京");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }

    /**
     * 更新用户
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("老王");
        user.setAddress("南京");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setId(49);
        userDao.updateUser(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser() {
        userDao.deleteUser(49);
    }

    /**
     * 根据id查用户
     */
    @Test
    public void testFindById() {
        System.out.println(userDao.findById(48));
    }

    /**
     * 根据姓名查用户
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据姓名查用户
     */
    @Test
    public void testFindByTwoName() {
        List<User> users = userDao.findByUserName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据姓名查用户
     */
    @Test
    public void testTotalUser() {
        System.out.println(userDao.findTotalUser());
    }
}
