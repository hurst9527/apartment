package hxj.apartment.service;
import hxj.apartment.bean.Good;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Good业务层接口
 *****/
public interface GoodService {

    /***
     * Good多条件分页查询
     * @param good
     * @param page
     * @param size
     * @return
     */
    PageInfo<Good> findPage(Good good, int page, int size);

    /***
     * Good分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Good> findPage(int page, int size);

    /***
     * Good多条件搜索方法
     * @param good
     * @return
     */
    List<Good> findList(Good good);

    /***
     * 删除Good
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Good数据
     * @param good
     */
    void update(Good good);

    /***
     * 新增Good
     * @param good
     */
    void add(Good good);

    /**
     * 根据ID查询Good
     * @param id
     * @return
     */
     Good findById(Integer id);

    /***
     * 查询所有Good
     * @return
     */
    List<Good> findAll();
}
