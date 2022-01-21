package hxj.apartment.service;


import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.AdvertisementCategory;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:AdvertisementCategory业务层接口
 *****/
public interface AdvertisementCategoryService {

    /***
     * AdvertisementCategory多条件分页查询
     * @param advertisementCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<AdvertisementCategory> findPage(AdvertisementCategory advertisementCategory, int page, int size);

    /***
     * AdvertisementCategory分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<AdvertisementCategory> findPage(int page, int size);

    /***
     * AdvertisementCategory多条件搜索方法
     * @param advertisementCategory
     * @return
     */
    List<AdvertisementCategory> findList(AdvertisementCategory advertisementCategory);

    /***
     * 删除AdvertisementCategory
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改AdvertisementCategory数据
     * @param advertisementCategory
     */
    void update(AdvertisementCategory advertisementCategory);

    /***
     * 新增AdvertisementCategory
     * @param advertisementCategory
     */
    void add(AdvertisementCategory advertisementCategory);

    /**
     * 根据ID查询AdvertisementCategory
     *
     * @param id
     * @return
     */
    AdvertisementCategory findById(Integer id);

    /***
     * 查询所有AdvertisementCategory
     * @return
     */
    List<AdvertisementCategory> findAll();
}
