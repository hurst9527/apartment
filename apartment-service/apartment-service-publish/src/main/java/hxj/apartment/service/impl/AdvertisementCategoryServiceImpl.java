package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.AdvertisementCategory;
import hxj.apartment.dao.AdvertisementCategoryMapper;
import hxj.apartment.service.AdvertisementCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:AdvertisementCategory业务层接口实现类
 *****/
@Service
public class AdvertisementCategoryServiceImpl implements AdvertisementCategoryService {

    @Autowired
    private AdvertisementCategoryMapper advertisementCategoryMapper;


    /**
     * AdvertisementCategory条件+分页查询
     *
     * @param advertisementCategory 查询条件
     * @param page                  页码
     * @param size                  页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<AdvertisementCategory> findPage(AdvertisementCategory advertisementCategory, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(advertisementCategory);
        //执行搜索
        return new PageInfo<AdvertisementCategory>(advertisementCategoryMapper.selectByExample(example));
    }

    /**
     * AdvertisementCategory分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<AdvertisementCategory> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<AdvertisementCategory>(advertisementCategoryMapper.selectAll());
    }

    /**
     * AdvertisementCategory条件查询
     *
     * @param advertisementCategory
     * @return
     */
    @Override
    public List<AdvertisementCategory> findList(AdvertisementCategory advertisementCategory) {
        //构建查询条件
        Example example = createExample(advertisementCategory);
        //根据构建的条件查询数据
        return advertisementCategoryMapper.selectByExample(example);
    }


    /**
     * AdvertisementCategory构建查询对象
     *
     * @param advertisementCategory
     * @return
     */
    public Example createExample(AdvertisementCategory advertisementCategory) {
        Example example = new Example(AdvertisementCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if (advertisementCategory != null) {
            // 
            if (!StringUtils.isEmpty(advertisementCategory.getId())) {
                criteria.andEqualTo("id", advertisementCategory.getId());
            }
            // 广告分类
            if (!StringUtils.isEmpty(advertisementCategory.getAdvertisementCategory())) {
                criteria.andEqualTo("advertisementCategory", advertisementCategory.getAdvertisementCategory());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        advertisementCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改AdvertisementCategory
     *
     * @param advertisementCategory
     */
    @Override
    public void update(AdvertisementCategory advertisementCategory) {
        advertisementCategoryMapper.updateByPrimaryKey(advertisementCategory);
    }

    /**
     * 增加AdvertisementCategory
     *
     * @param advertisementCategory
     */
    @Override
    public void add(AdvertisementCategory advertisementCategory) {
        advertisementCategoryMapper.insert(advertisementCategory);
    }

    /**
     * 根据ID查询AdvertisementCategory
     *
     * @param id
     * @return
     */
    @Override
    public AdvertisementCategory findById(Integer id) {
        return advertisementCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询AdvertisementCategory全部数据
     *
     * @return
     */
    @Override
    public List<AdvertisementCategory> findAll() {
        return advertisementCategoryMapper.selectAll();
    }
}
