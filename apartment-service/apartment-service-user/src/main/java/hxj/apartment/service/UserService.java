package hxj.apartment.service;
import hxj.apartment.bean.User;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Users业务层接口
 *****/
public interface UserService {
    /**
     * 用户登录
     * @param user 用户电话号码
     * @return
     */
    User login(User user);


    /**
     * 用户注销
     * @param userId 用户id
     */
    void userCheckout(Integer userId);


    /***
     * Users多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(User user, int page, int size);

    /***
     * Users分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(int page, int size);

    /***
     * Users多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除Users
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Users数据
     * @param user
     */
    void update(User user);

    /***
     * 新增Users
     * @param user
     */
    void add(User user);

    /**
     * 根据ID查询Users
     * @param id
     * @return
     */
     User findById(Integer id);

    /***
     * 查询所有Users
     * @return
     */
    List<User> findAll();
}
