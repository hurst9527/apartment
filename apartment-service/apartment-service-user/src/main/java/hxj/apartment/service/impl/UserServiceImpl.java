package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.User;
import hxj.apartment.dao.UserMapper;
import hxj.apartment.service.UserRelationshipService;
import hxj.apartment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:User业务层接口实现类
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserRelationshipService userRelationshipService;


    @Override
    public User login(User user) {
        try {
            User searchUser = findList(user).get(0);
            return searchUser;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据用户id注销
     *
     * @param userId 用户id
     */
    @Override
    public void userCheckout(Integer userId) {
        User user = findById(userId);
        user.setCheckOutDate(new Date());
        user.setStatus("3");
        update(user);
    }


    /**
     * 根据用户id通过验证
     *
     * @param userId 用户id
     */
    @Override
    public void passVerifi(Integer userId) {
        User user = findById(userId);
        user.setStatus("1");
        update(user);
    }


    /**
     * 根据用户id不通过验证
     *
     * @param userId 用户id
     */
    @Override
    public void unPassVerifi(Integer userId) {
        User user = findById(userId);
        user.setStatus("0");
        update(user);
    }


    /**
     * User条件+分页查询
     *
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new PageInfo<User>(userMapper.selectByExample(example));
    }

    /**
     * User分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<User>(userMapper.selectAll());
    }

    /**
     * User条件查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user) {
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }


    /**
     * User构建查询对象
     *
     * @param user
     * @return
     */
    public Example createExample(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 主键
            if (!StringUtils.isEmpty(user.getId())) {
                criteria.andEqualTo("id", user.getId());
            }
            // 用户名
            if (!StringUtils.isEmpty(user.getName())) {
                criteria.andLike("name", "%" + user.getName() + "%");
            }
            // 密码
            if (!StringUtils.isEmpty(user.getPassword())) {
                criteria.andEqualTo("password", user.getPassword());
            }
            // 电话号码（用于登录）
            if (!StringUtils.isEmpty(user.getPhoneNo())) {
                criteria.andEqualTo("phoneNo", user.getPhoneNo());
            }
            // 图片
            if (!StringUtils.isEmpty(user.getImage())) {
                criteria.andEqualTo("image", user.getImage());
            }
            // 出生日期
            if (!StringUtils.isEmpty(user.getBirthday())) {
                criteria.andEqualTo("birthday", user.getBirthday());
            }
            // 籍贯
            if (!StringUtils.isEmpty(user.getNativePlace())) {
                criteria.andEqualTo("nativePlace", user.getNativePlace());
            }
            // 房间号
            if (!StringUtils.isEmpty(user.getRoomID())) {
                criteria.andEqualTo("roomID", user.getRoomID());
            }
            // 紧急联系人（人名）
            if (!StringUtils.isEmpty(user.getEmergencyContactName())) {
                criteria.andEqualTo("emergencyContactName", user.getEmergencyContactName());
            }
            // 紧急联系人（电话号码）用于登录
            if (!StringUtils.isEmpty(user.getEmergencyContactPhoneNo())) {
                criteria.andEqualTo("emergencyContactPhoneNo", user.getEmergencyContactPhoneNo());
            }
            // 紧急联系人（与本人关系）
            if (!StringUtils.isEmpty(user.getEmergencyContactRelationship())) {
                criteria.andEqualTo("emergencyContactRelationship", user.getEmergencyContactRelationship());
            }
            // 紧急联系人（地址）
            if (!StringUtils.isEmpty(user.getEmergencyContactAddress())) {
                criteria.andEqualTo("emergencyContactAddress", user.getEmergencyContactAddress());
            }
            // 注册日期
            if (!StringUtils.isEmpty(user.getRegistDate())) {
                criteria.andEqualTo("registDate", user.getRegistDate());
            }
            // 疾病
            if (!StringUtils.isEmpty(user.getDiseases())) {
                criteria.andEqualTo("diseases", user.getDiseases());
            }
            // 备注
            if (!StringUtils.isEmpty(user.getOtherDesc())) {
                criteria.andEqualTo("otherDesc", user.getOtherDesc());
            }
            // 退房日期
            if (!StringUtils.isEmpty(user.getCheckOutDate())) {
                criteria.andEqualTo("checkOutDate", user.getCheckOutDate());
            }
            // 0已注销   1：验证通过  2：验证不通过   3：等待验证
            if (!StringUtils.isEmpty(user.getStatus())) {
                criteria.andEqualTo("status", user.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改User
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 增加User
     *
     * @param user
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询User全部数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
