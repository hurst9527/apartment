package hxj.apartment.service;
import hxj.apartment.bean.Usedpower;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Usedpower业务层接口
 *****/
public interface UsedpowerService {

    /***
     * Usedpower多条件分页查询
     * @param usedpower
     * @param page
     * @param size
     * @return
     */
    PageInfo<Usedpower> findPage(Usedpower usedpower, int page, int size);

    /***
     * Usedpower分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Usedpower> findPage(int page, int size);

    /***
     * Usedpower多条件搜索方法
     * @param usedpower
     * @return
     */
    List<Usedpower> findList(Usedpower usedpower);

    /***
     * 删除Usedpower
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Usedpower数据
     * @param usedpower
     */
    void update(Usedpower usedpower);

    /***
     * 新增Usedpower
     * @param usedpower
     */
    void add(Usedpower usedpower);

    /**
     * 根据ID查询Usedpower
     * @param id
     * @return
     */
     Usedpower findById(Integer id);

    /***
     * 查询所有Usedpower
     * @return
     */
    List<Usedpower> findAll();
}
