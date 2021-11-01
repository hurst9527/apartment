package hxj.apartment.service;
import hxj.apartment.bean.RoomCategory;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:RoomCategory业务层接口
 *****/
public interface RoomCategoryService {

    /***
     * RoomCategory多条件分页查询
     * @param roomCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<RoomCategory> findPage(RoomCategory roomCategory, int page, int size);

    /***
     * RoomCategory分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<RoomCategory> findPage(int page, int size);

    /***
     * RoomCategory多条件搜索方法
     * @param roomCategory
     * @return
     */
    List<RoomCategory> findList(RoomCategory roomCategory);

    /***
     * 删除RoomCategory
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改RoomCategory数据
     * @param roomCategory
     */
    void update(RoomCategory roomCategory);

    /***
     * 新增RoomCategory
     * @param roomCategory
     */
    void add(RoomCategory roomCategory);

    /**
     * 根据ID查询RoomCategory
     * @param id
     * @return
     */
     RoomCategory findById(Integer id);

    /***
     * 查询所有RoomCategory
     * @return
     */
    List<RoomCategory> findAll();
}
