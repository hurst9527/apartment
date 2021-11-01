package hxj.apartment.service.impl;
import hxj.apartment.dao.PowerpriceMapper;
import hxj.apartment.bean.Powerprice;
import hxj.apartment.service.PowerpriceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Powerprice业务层接口实现类
 *****/
@Service
public class PowerpriceServiceImpl implements PowerpriceService {

    @Autowired
    private PowerpriceMapper powerpriceMapper;


    /**
     * Powerprice条件+分页查询
     * @param powerprice 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Powerprice> findPage(Powerprice powerprice, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(powerprice);
        //执行搜索
        return new PageInfo<Powerprice>(powerpriceMapper.selectByExample(example));
    }

    /**
     * Powerprice分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Powerprice> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Powerprice>(powerpriceMapper.selectAll());
    }

    /**
     * Powerprice条件查询
     * @param powerprice
     * @return
     */
    @Override
    public List<Powerprice> findList(Powerprice powerprice){
        //构建查询条件
        Example example = createExample(powerprice);
        //根据构建的条件查询数据
        return powerpriceMapper.selectByExample(example);
    }


    /**
     * Powerprice构建查询对象
     * @param powerprice
     * @return
     */
    public Example createExample(Powerprice powerprice){
        Example example=new Example(Powerprice.class);
        Example.Criteria criteria = example.createCriteria();
        if(powerprice!=null){
            // 
            if(!StringUtils.isEmpty(powerprice.getId())){
                    criteria.andEqualTo("id",powerprice.getId());
            }
            // 水费
            if(!StringUtils.isEmpty(powerprice.getWaterPrice())){
                    criteria.andEqualTo("waterPrice",powerprice.getWaterPrice());
            }
            // 电费
            if(!StringUtils.isEmpty(powerprice.getElkectricityPrce())){
                    criteria.andEqualTo("elkectricityPrce",powerprice.getElkectricityPrce());
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
        powerpriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Powerprice
     * @param powerprice
     */
    @Override
    public void update(Powerprice powerprice){
        powerpriceMapper.updateByPrimaryKey(powerprice);
    }

    /**
     * 增加Powerprice
     * @param powerprice
     */
    @Override
    public void add(Powerprice powerprice){
        powerpriceMapper.insert(powerprice);
    }

    /**
     * 根据ID查询Powerprice
     * @param id
     * @return
     */
    @Override
    public Powerprice findById(Integer id){
        return  powerpriceMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Powerprice全部数据
     * @return
     */
    @Override
    public List<Powerprice> findAll() {
        return powerpriceMapper.selectAll();
    }
}
