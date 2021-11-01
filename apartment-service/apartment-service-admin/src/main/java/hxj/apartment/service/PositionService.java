package hxj.apartment.service;
import hxj.apartment.bean.Position;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Position业务层接口
 *****/
public interface PositionService {

    /***
     * Position多条件分页查询
     * @param position
     * @param page
     * @param size
     * @return
     */
    PageInfo<Position> findPage(Position position, int page, int size);

    /***
     * Position分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Position> findPage(int page, int size);

    /***
     * Position多条件搜索方法
     * @param position
     * @return
     */
    List<Position> findList(Position position);

    /***
     * 删除Position
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Position数据
     * @param position
     */
    void update(Position position);

    /***
     * 新增Position
     * @param position
     */
    void add(Position position);

    /**
     * 根据ID查询Position
     * @param id
     * @return
     */
     Position findById(Integer id);

    /***
     * 查询所有Position
     * @return
     */
    List<Position> findAll();
}
