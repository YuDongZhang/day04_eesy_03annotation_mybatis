package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mybatis中正对crud 的四个注解
 */
public interface IUserDao {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values (#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Insert("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    /**
     * 根据名称查询用户
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findByName(String name);

    /**
     * 根据名称查询用户,第二中方式
     * @return
     */
    @Select("select * from user where username like '%${value}%' ")
    List<User> findByUserName(String name);

    @Select("select count(*) from user")
    int findTotalUser();
}
