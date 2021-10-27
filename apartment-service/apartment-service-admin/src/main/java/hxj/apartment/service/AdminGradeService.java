package hxj.apartment.service;
import hxj.apartment.bean.AdminGrade;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:AdminGrade业务层接口
 *****/
public interface AdminGradeService {

    /***
     * AdminGrade多条件分页查询
     * @param adminGrade
     * @param page
     * @param size
     * @return
     */
    PageInfo<AdminGrade> findPage(AdminGrade adminGrade, int page, int size);

    /***
     * AdminGrade分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<AdminGrade> findPage(int page, int size);

    /***
     * AdminGrade多条件搜索方法
     * @param adminGrade
     * @return
     */
    List<AdminGrade> findList(AdminGrade adminGrade);

    /***
     * 删除AdminGrade
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改AdminGrade数据
     * @param adminGrade
     */
    void update(AdminGrade adminGrade);

    /***
     * 新增AdminGrade
     * @param adminGrade
     */
    void add(AdminGrade adminGrade);

    /**
     * 根据ID查询AdminGrade
     * @param id
     * @return
     */
     AdminGrade findById(Integer id);

    /***
     * 查询所有AdminGrade
     * @return
     */
    List<AdminGrade> findAll();
}
