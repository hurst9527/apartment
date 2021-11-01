package hxj.apartment.service;
import hxj.apartment.bean.Powerprice;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Powerprice业务层接口
 *****/
public interface PowerpriceService {

    /***
     * Powerprice多条件分页查询
     * @param powerprice
     * @param page
     * @param size
     * @return
     */
    PageInfo<Powerprice> findPage(Powerprice powerprice, int page, int size);

    /***
     * Powerprice分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Powerprice> findPage(int page, int size);

    /***
     * Powerprice多条件搜索方法
     * @param powerprice
     * @return
     */
    List<Powerprice> findList(Powerprice powerprice);

    /***
     * 删除Powerprice
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Powerprice数据
     * @param powerprice
     */
    void update(Powerprice powerprice);

    /***
     * 新增Powerprice
     * @param powerprice
     */
    void add(Powerprice powerprice);

    /**
     * 根据ID查询Powerprice
     * @param id
     * @return
     */
     Powerprice findById(Integer id);

    /***
     * 查询所有Powerprice
     * @return
     */
    List<Powerprice> findAll();
}
