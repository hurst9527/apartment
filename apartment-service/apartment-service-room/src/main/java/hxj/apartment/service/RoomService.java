package hxj.apartment.service;
import hxj.apartment.bean.Room;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Room业务层接口
 *****/
public interface RoomService {

    /***
     * Room多条件分页查询
     * @param room
     * @param page
     * @param size
     * @return
     */
    PageInfo<Room> findPage(Room room, int page, int size);

    /***
     * Room分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Room> findPage(int page, int size);

    /***
     * Room多条件搜索方法
     * @param room
     * @return
     */
    List<Room> findList(Room room);

    /***
     * 删除Room
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Room数据
     * @param room
     */
    void update(Room room);

    /***
     * 新增Room
     * @param room
     */
    void add(Room room);

    /**
     * 根据ID查询Room
     * @param id
     * @return
     */
     Room findById(Integer id);

    /***
     * 查询所有Room
     * @return
     */
    List<Room> findAll();
}
