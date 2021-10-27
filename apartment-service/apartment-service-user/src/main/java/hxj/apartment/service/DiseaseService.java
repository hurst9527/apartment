package hxj.apartment.service;
import hxj.apartment.bean.Disease;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Disease业务层接口
 *****/
public interface DiseaseService {

    /***
     * Disease多条件分页查询
     * @param disease
     * @param page
     * @param size
     * @return
     */
    PageInfo<Disease> findPage(Disease disease, int page, int size);

    /***
     * Disease分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Disease> findPage(int page, int size);

    /***
     * Disease多条件搜索方法
     * @param disease
     * @return
     */
    List<Disease> findList(Disease disease);

    /***
     * 删除Disease
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Disease数据
     * @param disease
     */
    void update(Disease disease);

    /***
     * 新增Disease
     * @param disease
     */
    void add(Disease disease);

    /**
     * 根据ID查询Disease
     * @param id
     * @return
     */
     Disease findById(Integer id);

    /***
     * 查询所有Disease
     * @return
     */
    List<Disease> findAll();
}
