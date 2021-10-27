package hxj.apartment.service;
import hxj.apartment.bean.Repair;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Repair业务层接口
 *****/
public interface RepairService {

    /***
     * Repair多条件分页查询
     * @param repair
     * @param page
     * @param size
     * @return
     */
    PageInfo<Repair> findPage(Repair repair, int page, int size);

    /***
     * Repair分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Repair> findPage(int page, int size);

    /***
     * Repair多条件搜索方法
     * @param repair
     * @return
     */
    List<Repair> findList(Repair repair);

    /***
     * 删除Repair
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Repair数据
     * @param repair
     */
    void update(Repair repair);

    /***
     * 新增Repair
     * @param repair
     */
    void add(Repair repair);

    /**
     * 根据ID查询Repair
     * @param id
     * @return
     */
     Repair findById(Integer id);

    /***
     * 查询所有Repair
     * @return
     */
    List<Repair> findAll();
}
