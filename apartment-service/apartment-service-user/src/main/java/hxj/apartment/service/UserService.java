package hxj.apartment.service;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.User;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:User业务层接口
 *****/
public interface UserService {
    /***
     * 获取所有系统中有注册日期，且注销日期为空，状态为1（审核通过）的所有住户。
     * @return
     */
    List<User> getAllUsersInSystem();


    User login(User user);


    void userCheckout(Integer userId);


    /**
     * 根据用户id通过验证
     *
     * @param userId 用户id
     */
    void passVerifi(Integer userId);


    /**
     * 根据用户id不同过验证
     *
     * @param userId 用户id
     */
    void unPassVerifi(Integer userId);


    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改User数据
     * @param user
     */
    void update(User user);

    /***
     * 新增User
     * @param user
     */
    void add(User user);

    /**
     * 根据ID查询User
     *
     * @param id
     * @return
     */
     User findById(Integer id);

    /***
     * 查询所有User
     * @return
     */
    List<User> findAll();
}
