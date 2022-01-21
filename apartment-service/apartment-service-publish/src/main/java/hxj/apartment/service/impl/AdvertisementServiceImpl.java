package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Advertisement;
import hxj.apartment.dao.AdvertisementMapper;
import hxj.apartment.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Advertisement业务层接口实现类
 *****/
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;


    /**
     * Advertisement条件+分页查询
     *
     * @param advertisement 查询条件
     * @param page          页码
     * @param size          页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Advertisement> findPage(Advertisement advertisement, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(advertisement);
        //执行搜索
        return new PageInfo<Advertisement>(advertisementMapper.selectByExample(example));
    }

    /**
     * Advertisement分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Advertisement> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Advertisement>(advertisementMapper.selectAll());
    }

    /**
     * Advertisement条件查询
     *
     * @param advertisement
     * @return
     */
    @Override
    public List<Advertisement> findList(Advertisement advertisement) {
        //构建查询条件
        Example example = createExample(advertisement);
        //根据构建的条件查询数据
        return advertisementMapper.selectByExample(example);
    }


    /**
     * Advertisement构建查询对象
     *
     * @param advertisement
     * @return
     */
    public Example createExample(Advertisement advertisement) {
        Example example = new Example(Advertisement.class);
        Example.Criteria criteria = example.createCriteria();
        if (advertisement != null) {
            // 
            if (!StringUtils.isEmpty(advertisement.getId())) {
                criteria.andEqualTo("id", advertisement.getId());
            }
            // 广告名
            if (!StringUtils.isEmpty(advertisement.getName())) {
                criteria.andLike("name", "%" + advertisement.getName() + "%");
            }
            // 广告内容
            if (!StringUtils.isEmpty(advertisement.getContent())) {
                criteria.andEqualTo("content", advertisement.getContent());
            }
            // 地址
            if (!StringUtils.isEmpty(advertisement.getUrl())) {
                criteria.andEqualTo("url", advertisement.getUrl());
            }
            // 图片地址
            if (!StringUtils.isEmpty(advertisement.getPic())) {
                criteria.andEqualTo("pic", advertisement.getPic());
            }
            // 状态 0：下架 1：上架
            if (!StringUtils.isEmpty(advertisement.getStatus())) {
                criteria.andEqualTo("status", advertisement.getStatus());
            }
            // 广告分类
            if (!StringUtils.isEmpty(advertisement.getCategoryId())) {
                criteria.andEqualTo("categoryId", advertisement.getCategoryId());
            }
            // 开始时间
            if (!StringUtils.isEmpty(advertisement.getStartTime())) {
                criteria.andEqualTo("startTime", advertisement.getStartTime());
            }
            // 失效时间
            if (!StringUtils.isEmpty(advertisement.getEndTime())) {
                criteria.andEqualTo("endTime", advertisement.getEndTime());
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
        advertisementMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Advertisement
     *
     * @param advertisement
     */
    @Override
    public void update(Advertisement advertisement) {
        advertisementMapper.updateByPrimaryKey(advertisement);
    }

    /**
     * 增加Advertisement
     *
     * @param advertisement
     */
    @Override
    public void add(Advertisement advertisement) {
        advertisementMapper.insert(advertisement);
    }

    /**
     * 根据ID查询Advertisement
     *
     * @param id
     * @return
     */
    @Override
    public Advertisement findById(Integer id) {
        return advertisementMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Advertisement全部数据
     *
     * @return
     */
    @Override
    public List<Advertisement> findAll() {
        return advertisementMapper.selectAll();
    }
}
