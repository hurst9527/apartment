package hxj.apartment.service.impl;
import hxj.apartment.dao.RoomCategoryMapper;
import hxj.apartment.bean.RoomCategory;
import hxj.apartment.service.RoomCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:RoomCategory业务层接口实现类
 *****/
@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {

    @Autowired
    private RoomCategoryMapper roomCategoryMapper;


    /**
     * RoomCategory条件+分页查询
     * @param roomCategory 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<RoomCategory> findPage(RoomCategory roomCategory, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(roomCategory);
        //执行搜索
        return new PageInfo<RoomCategory>(roomCategoryMapper.selectByExample(example));
    }

    /**
     * RoomCategory分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<RoomCategory> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<RoomCategory>(roomCategoryMapper.selectAll());
    }

    /**
     * RoomCategory条件查询
     * @param roomCategory
     * @return
     */
    @Override
    public List<RoomCategory> findList(RoomCategory roomCategory){
        //构建查询条件
        Example example = createExample(roomCategory);
        //根据构建的条件查询数据
        return roomCategoryMapper.selectByExample(example);
    }


    /**
     * RoomCategory构建查询对象
     * @param roomCategory
     * @return
     */
    public Example createExample(RoomCategory roomCategory){
        Example example=new Example(RoomCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(roomCategory!=null){
            // 
            if(!StringUtils.isEmpty(roomCategory.getId())){
                    criteria.andEqualTo("id",roomCategory.getId());
            }
            // 房间分类名称
            if(!StringUtils.isEmpty(roomCategory.getCategoryName())){
                    criteria.andEqualTo("categoryName",roomCategory.getCategoryName());
            }
            // 等级  1：住宅 2：公共场所
            if(!StringUtils.isEmpty(roomCategory.getGrade())){
                    criteria.andEqualTo("grade",roomCategory.getGrade());
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
        roomCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改RoomCategory
     * @param roomCategory
     */
    @Override
    public void update(RoomCategory roomCategory){
        roomCategoryMapper.updateByPrimaryKey(roomCategory);
    }

    /**
     * 增加RoomCategory
     * @param roomCategory
     */
    @Override
    public void add(RoomCategory roomCategory){
        roomCategoryMapper.insert(roomCategory);
    }

    /**
     * 根据ID查询RoomCategory
     * @param id
     * @return
     */
    @Override
    public RoomCategory findById(Integer id){
        return  roomCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询RoomCategory全部数据
     * @return
     */
    @Override
    public List<RoomCategory> findAll() {
        return roomCategoryMapper.selectAll();
    }
}
