package hxj.apartment.service.impl;
import hxj.apartment.dao.UsedpowerMapper;
import hxj.apartment.bean.Usedpower;
import hxj.apartment.service.UsedpowerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Usedpower业务层接口实现类
 *****/
@Service
public class UsedpowerServiceImpl implements UsedpowerService {

    @Autowired
    private UsedpowerMapper usedpowerMapper;


    /**
     * Usedpower条件+分页查询
     * @param usedpower 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Usedpower> findPage(Usedpower usedpower, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(usedpower);
        //执行搜索
        return new PageInfo<Usedpower>(usedpowerMapper.selectByExample(example));
    }

    /**
     * Usedpower分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Usedpower> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Usedpower>(usedpowerMapper.selectAll());
    }

    /**
     * Usedpower条件查询
     * @param usedpower
     * @return
     */
    @Override
    public List<Usedpower> findList(Usedpower usedpower){
        //构建查询条件
        Example example = createExample(usedpower);
        //根据构建的条件查询数据
        return usedpowerMapper.selectByExample(example);
    }


    /**
     * Usedpower构建查询对象
     * @param usedpower
     * @return
     */
    public Example createExample(Usedpower usedpower){
        Example example=new Example(Usedpower.class);
        Example.Criteria criteria = example.createCriteria();
        if(usedpower!=null){
            // 
            if(!StringUtils.isEmpty(usedpower.getId())){
                    criteria.andEqualTo("id",usedpower.getId());
            }
            // 房间号
            if(!StringUtils.isEmpty(usedpower.getRoomID())){
                    criteria.andEqualTo("roomID",usedpower.getRoomID());
            }
            // 更新水电时间（每月一次）
            if(!StringUtils.isEmpty(usedpower.getUpdateTime())){
                    criteria.andEqualTo("updateTime",usedpower.getUpdateTime());
            }
            // 总用水
            if(!StringUtils.isEmpty(usedpower.getWaterTotal())){
                    criteria.andEqualTo("waterTotal",usedpower.getWaterTotal());
            }
            // 总用电数
            if(!StringUtils.isEmpty(usedpower.getElectricityTotal())){
                    criteria.andEqualTo("electricityTotal",usedpower.getElectricityTotal());
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
        usedpowerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Usedpower
     * @param usedpower
     */
    @Override
    public void update(Usedpower usedpower){
        usedpowerMapper.updateByPrimaryKey(usedpower);
    }

    /**
     * 增加Usedpower
     * @param usedpower
     */
    @Override
    public void add(Usedpower usedpower){
        usedpowerMapper.insert(usedpower);
    }

    /**
     * 根据ID查询Usedpower
     * @param id
     * @return
     */
    @Override
    public Usedpower findById(Integer id){
        return  usedpowerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Usedpower全部数据
     * @return
     */
    @Override
    public List<Usedpower> findAll() {
        return usedpowerMapper.selectAll();
    }
}
