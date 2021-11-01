package hxj.apartment.service.impl;
import hxj.apartment.dao.PositionMapper;
import hxj.apartment.bean.Position;
import hxj.apartment.service.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Position业务层接口实现类
 *****/
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;


    /**
     * Position条件+分页查询
     * @param position 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Position> findPage(Position position, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(position);
        //执行搜索
        return new PageInfo<Position>(positionMapper.selectByExample(example));
    }

    /**
     * Position分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Position> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Position>(positionMapper.selectAll());
    }

    /**
     * Position条件查询
     * @param position
     * @return
     */
    @Override
    public List<Position> findList(Position position){
        //构建查询条件
        Example example = createExample(position);
        //根据构建的条件查询数据
        return positionMapper.selectByExample(example);
    }


    /**
     * Position构建查询对象
     * @param position
     * @return
     */
    public Example createExample(Position position){
        Example example=new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        if(position!=null){
            // 
            if(!StringUtils.isEmpty(position.getId())){
                    criteria.andEqualTo("id",position.getId());
            }
            // 职位
            if(!StringUtils.isEmpty(position.getPosition())){
                    criteria.andEqualTo("position",position.getPosition());
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
        positionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Position
     * @param position
     */
    @Override
    public void update(Position position){
        positionMapper.updateByPrimaryKey(position);
    }

    /**
     * 增加Position
     * @param position
     */
    @Override
    public void add(Position position){
        positionMapper.insert(position);
    }

    /**
     * 根据ID查询Position
     * @param id
     * @return
     */
    @Override
    public Position findById(Integer id){
        return  positionMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Position全部数据
     * @return
     */
    @Override
    public List<Position> findAll() {
        return positionMapper.selectAll();
    }
}
