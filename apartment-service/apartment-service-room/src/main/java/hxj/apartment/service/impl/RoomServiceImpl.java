package hxj.apartment.service.impl;
import hxj.apartment.dao.RoomMapper;
import hxj.apartment.bean.Room;
import hxj.apartment.service.RoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Room业务层接口实现类
 *****/
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;


    /**
     * Room条件+分页查询
     * @param room 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Room> findPage(Room room, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(room);
        //执行搜索
        return new PageInfo<Room>(roomMapper.selectByExample(example));
    }

    /**
     * Room分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Room> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Room>(roomMapper.selectAll());
    }

    /**
     * Room条件查询
     * @param room
     * @return
     */
    @Override
    public List<Room> findList(Room room){
        //构建查询条件
        Example example = createExample(room);
        //根据构建的条件查询数据
        return roomMapper.selectByExample(example);
    }


    /**
     * Room构建查询对象
     * @param room
     * @return
     */
    public Example createExample(Room room){
        Example example=new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        if(room!=null){
            // 
            if(!StringUtils.isEmpty(room.getId())){
                    criteria.andEqualTo("id",room.getId());
            }
            // 房间类型
            if(!StringUtils.isEmpty(room.getCategory())){
                    criteria.andEqualTo("category",room.getCategory());
            }
            // 配置参数
            if(!StringUtils.isEmpty(room.getSpec())){
                    criteria.andEqualTo("spec",room.getSpec());
            }
            // 价格（/月）
            if(!StringUtils.isEmpty(room.getPrice())){
                    criteria.andEqualTo("price",room.getPrice());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        roomMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Room
     * @param room
     */
    @Override
    public void update(Room room){
        roomMapper.updateByPrimaryKey(room);
    }

    /**
     * 增加Room
     * @param room
     */
    @Override
    public void add(Room room){
        roomMapper.insert(room);
    }

    /**
     * 根据ID查询Room
     * @param id
     * @return
     */
    @Override
    public Room findById(Integer id){
        return  roomMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Room全部数据
     * @return
     */
    @Override
    public List<Room> findAll() {
        return roomMapper.selectAll();
    }
}
