package hxj.apartment.service;
import hxj.apartment.bean.Area;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Area业务层接口
 *****/
public interface AreaService {

    /***
     * Area多条件分页查询
     * @param area
     * @param page
     * @param size
     * @return
     */
    PageInfo<Area> findPage(Area area, int page, int size);

    /***
     * Area分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Area> findPage(int page, int size);

    /***
     * Area多条件搜索方法
     * @param area
     * @return
     */
    List<Area> findList(Area area);

    /***
     * 删除Area
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Area数据
     * @param area
     */
    void update(Area area);

    /***
     * 新增Area
     * @param area
     */
    void add(Area area);

    /**
     * 根据ID查询Area
     * @param id
     * @return
     */
     Area findById(Integer id);

    /***
     * 查询所有Area
     * @return
     */
    List<Area> findAll();
}
