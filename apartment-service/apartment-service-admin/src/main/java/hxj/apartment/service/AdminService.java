package hxj.apartment.service;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Admin;
import hxj.apartment.bean.AdminInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/****
 * @Author:HXJ
 * @Description:Admin业务层接口
 *****/
public interface AdminService {

    /**
     * 根据在职状态查找员工
     * @param status
     * @return
     */
    List<AdminInfo> queryAdmin(String status);

    /***
     * Admin多条件分页查询
     * @param admin
     * @param page
     * @param size
     * @return
     */
    PageInfo<Admin> findPage(Admin admin, int page, int size);

    /***
     * Admin分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Admin> findPage(int page, int size);

    /***
     * Admin多条件搜索方法
     * @param admin
     * @return
     */
    List<Admin> findList(Admin admin);

    /***
     * 删除Admin
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Admin数据
     * @param admin
     */
    void update(Admin admin);

    /***
     * 新增Admin
     * @param admin
     */
    void add(Admin admin);

    /**
     * 根据ID查询Admin
     * @param id
     * @return
     */
    Admin findById(Integer id);

    /***
     * 查询所有Admin
     * @return
     */
    List<Admin> findAll();

    /**
     * 管理员注册
     *
     * @param admin   管理员信息
     * @param headImg 管理员头像
     */
    void regist(Admin admin, MultipartFile headImg);
}
