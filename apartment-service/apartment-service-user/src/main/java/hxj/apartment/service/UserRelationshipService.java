package hxj.apartment.service;
import hxj.apartment.bean.UserRelationship;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:UserRelationship业务层接口
 *****/
public interface UserRelationshipService {

    /***
     * UserRelationship多条件分页查询
     * @param userRelationship
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserRelationship> findPage(UserRelationship userRelationship, int page, int size);

    /***
     * UserRelationship分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserRelationship> findPage(int page, int size);

    /***
     * UserRelationship多条件搜索方法
     * @param userRelationship
     * @return
     */
    List<UserRelationship> findList(UserRelationship userRelationship);

    /***
     * 删除UserRelationship
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改UserRelationship数据
     * @param userRelationship
     */
    void update(UserRelationship userRelationship);

    /***
     * 新增UserRelationship
     * @param userRelationship
     */
    void add(UserRelationship userRelationship);

    /**
     * 根据ID查询UserRelationship
     * @param id
     * @return
     */
     UserRelationship findById(Integer id);

    /***
     * 查询所有UserRelationship
     * @return
     */
    List<UserRelationship> findAll();
}
