package hxj.apartment.service;


import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Advertisement;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Advertisement业务层接口
 *****/
public interface AdvertisementService {

    /***
     * Advertisement多条件分页查询
     * @param advertisement
     * @param page
     * @param size
     * @return
     */
    PageInfo<Advertisement> findPage(Advertisement advertisement, int page, int size);

    /***
     * Advertisement分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Advertisement> findPage(int page, int size);

    /***
     * Advertisement多条件搜索方法
     * @param advertisement
     * @return
     */
    List<Advertisement> findList(Advertisement advertisement);

    /***
     * 删除Advertisement
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Advertisement数据
     * @param advertisement
     */
    void update(Advertisement advertisement);

    /***
     * 新增Advertisement
     * @param advertisement
     */
    void add(Advertisement advertisement);

    /**
     * 根据ID查询Advertisement
     *
     * @param id
     * @return
     */
    Advertisement findById(Integer id);

    /***
     * 查询所有Advertisement
     * @return
     */
    List<Advertisement> findAll();
}
