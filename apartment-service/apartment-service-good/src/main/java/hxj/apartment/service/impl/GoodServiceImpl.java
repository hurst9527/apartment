package hxj.apartment.service.impl;
import hxj.apartment.dao.GoodMapper;
import hxj.apartment.bean.Good;
import hxj.apartment.service.GoodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Good业务层接口实现类
 *****/
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;


    /**
     * Good条件+分页查询
     * @param good 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Good> findPage(Good good, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(good);
        //执行搜索
        return new PageInfo<Good>(goodMapper.selectByExample(example));
    }

    /**
     * Good分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Good> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Good>(goodMapper.selectAll());
    }

    /**
     * Good条件查询
     * @param good
     * @return
     */
    @Override
    public List<Good> findList(Good good){
        //构建查询条件
        Example example = createExample(good);
        //根据构建的条件查询数据
        return goodMapper.selectByExample(example);
    }


    /**
     * Good构建查询对象
     * @param good
     * @return
     */
    public Example createExample(Good good){
        Example example=new Example(Good.class);
        Example.Criteria criteria = example.createCriteria();
        if(good!=null){
            // 
            if(!StringUtils.isEmpty(good.getId())){
                    criteria.andEqualTo("id",good.getId());
            }
            // 商品名称
            if(!StringUtils.isEmpty(good.getName())){
                    criteria.andLike("name","%"+good.getName()+"%");
            }
            // 商品价格
            if(!StringUtils.isEmpty(good.getPrice())){
                    criteria.andEqualTo("price",good.getPrice());
            }
            // 品牌
            if(!StringUtils.isEmpty(good.getBrand())){
                    criteria.andEqualTo("brand",good.getBrand());
            }
            // 分类
            if(!StringUtils.isEmpty(good.getCategory())){
                    criteria.andEqualTo("category",good.getCategory());
            }
            // 库存
            if(!StringUtils.isEmpty(good.getStock())){
                    criteria.andEqualTo("stock",good.getStock());
            }
            // 交易单位
            if(!StringUtils.isEmpty(good.getUnit())){
                    criteria.andEqualTo("unit",good.getUnit());
            }
            // 图片连接
            if(!StringUtils.isEmpty(good.getImage())){
                    criteria.andEqualTo("image",good.getImage());
            }
            // 状态 0下架 1 上架 2 删除
            if(!StringUtils.isEmpty(good.getStatus())){
                    criteria.andEqualTo("status",good.getStatus());
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
        goodMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Good
     * @param good
     */
    @Override
    public void update(Good good){
        goodMapper.updateByPrimaryKey(good);
    }

    /**
     * 增加Good
     * @param good
     */
    @Override
    public void add(Good good){
        goodMapper.insert(good);
    }

    /**
     * 根据ID查询Good
     * @param id
     * @return
     */
    @Override
    public Good findById(Integer id){
        return  goodMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Good全部数据
     * @return
     */
    @Override
    public List<Good> findAll() {
        return goodMapper.selectAll();
    }
}
