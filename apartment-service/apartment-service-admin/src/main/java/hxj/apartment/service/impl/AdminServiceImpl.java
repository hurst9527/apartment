package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Admin;
import hxj.apartment.bean.AdminInfo;
import hxj.apartment.bean.Result;
import hxj.apartment.dao.AdminMapper;
import hxj.apartment.feign.FileFeign;
import hxj.apartment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Admin业务层接口实现类
 *****/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private FileFeign fileFeign;

    /**
     * 根据在职状态查找员工
     *
     * @param status
     * @return
     */
    @Override
    public List<AdminInfo> queryAdmin(String status) {
        return adminMapper.queryAdmin(status);
    }

    /**
     * Admin条件+分页查询
     *
     * @param admin 查询条件
     * @param page  页码
     * @param size  页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Admin> findPage(Admin admin, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(admin);
        //执行搜索
        return new PageInfo<Admin>(adminMapper.selectByExample(example));
    }

    /**
     * Admin分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Admin> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Admin>(adminMapper.selectAll());
    }

    /**
     * Admin条件查询
     *
     * @param admin
     * @return
     */
    @Override
    public List<Admin> findList(Admin admin) {
        //构建查询条件
        Example example = createExample(admin);
        //根据构建的条件查询数据
        return adminMapper.selectByExample(example);
    }


    /**
     * Admin构建查询对象
     *
     * @param admin
     * @return
     */
    public Example createExample(Admin admin) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if (admin != null) {
            //
            if (!StringUtils.isEmpty(admin.getId())) {
                criteria.andEqualTo("id", admin.getId());
            }
            // 用户名
            if (!StringUtils.isEmpty(admin.getName())) {
                criteria.andLike("name", "%" + admin.getName() + "%");
            }
            // 密码
            if (!StringUtils.isEmpty(admin.getPassword())) {
                criteria.andEqualTo("password", admin.getPassword());
            }
            // 管理员等级   关联admin_grade中的grade字段
            if (!StringUtils.isEmpty(admin.getGrade())) {
                criteria.andEqualTo("identity", admin.getGrade());
            }
            // 职位
            if (!StringUtils.isEmpty(admin.getPosition())) {
                criteria.andEqualTo("position", admin.getPosition());
            }
            // 头像
            if (!StringUtils.isEmpty(admin.getImage())) {
                criteria.andEqualTo("image", admin.getImage());
            }
            // 个人简介
            if (!StringUtils.isEmpty(admin.getIntroduce())) {
                criteria.andEqualTo("introduce", admin.getIntroduce());
            }
            // 0：已删除   1：审核中    2：审核通过
            if (!StringUtils.isEmpty(admin.getStatus())) {
                criteria.andEqualTo("status", admin.getStatus());
            }
            // 电话
            if (!StringUtils.isEmpty(admin.getPhoneNo())) {
                criteria.andEqualTo("phoneNo", admin.getPhoneNo());
            }
            // 邮箱
            if (!StringUtils.isEmpty(admin.getEmail())) {
                criteria.andEqualTo("email", admin.getEmail());
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
        adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Admin
     *
     * @param admin
     */
    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 增加Admin
     *
     * @param admin
     */
    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * 根据ID查询Admin
     *
     * @param id
     * @return
     */
    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Admin全部数据
     *
     * @return
     */
    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }

    @Override
    public void regist(Admin admin, MultipartFile headImg) {
        admin.setStatus("2");
        if (headImg != null) {
            Result result = fileFeign.upload(headImg);
            admin.setImage(String.valueOf(result.getResult()));
        }
        add(admin);
    }
}
