package hxj.apartment.service.impl;
import hxj.apartment.dao.AreaMapper;
import hxj.apartment.bean.Area;
import hxj.apartment.service.AreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Area业务层接口实现类
 *****/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;


    /**
     * Area条件+分页查询
     * @param area 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Area> findPage(Area area, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(area);
        //执行搜索
        return new PageInfo<Area>(areaMapper.selectByExample(example));
    }

    /**
     * Area分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Area> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Area>(areaMapper.selectAll());
    }

    /**
     * Area条件查询
     * @param area
     * @return
     */
    @Override
    public List<Area> findList(Area area){
        //构建查询条件
        Example example = createExample(area);
        //根据构建的条件查询数据
        return areaMapper.selectByExample(example);
    }


    /**
     * Area构建查询对象
     * @param area
     * @return
     */
    public Example createExample(Area area){
        Example example=new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        if(area!=null){
            // 
            if(!StringUtils.isEmpty(area.getId())){
                    criteria.andEqualTo("id",area.getId());
            }
            // 
            if(!StringUtils.isEmpty(area.getPid())){
                    criteria.andEqualTo("pid",area.getPid());
            }
            // 
            if(!StringUtils.isEmpty(area.getName())){
                    criteria.andLike("name","%"+area.getName()+"%");
            }
            // 
            if(!StringUtils.isEmpty(area.getType())){
                    criteria.andEqualTo("type",area.getType());
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
        areaMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Area
     * @param area
     */
    @Override
    public void update(Area area){
        areaMapper.updateByPrimaryKey(area);
    }

    /**
     * 增加Area
     * @param area
     */
    @Override
    public void add(Area area){
        areaMapper.insert(area);
    }

    /**
     * 根据ID查询Area
     * @param id
     * @return
     */
    @Override
    public Area findById(Integer id){
        return  areaMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Area全部数据
     * @return
     */
    @Override
    public List<Area> findAll() {
        return areaMapper.selectAll();
    }
}
