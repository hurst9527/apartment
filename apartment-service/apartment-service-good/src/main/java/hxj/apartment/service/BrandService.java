package hxj.apartment.service;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Brand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/****
 * @Author:HXJ
 * @Description:Brand业务层接口
 *****/
public interface BrandService {

    /***
     * Brand多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);

    /***
     * Brand分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);

    /***
     * Brand多条件搜索方法
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /***
     * 删除Brand
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Brand数据
     * @param brand
     */
    void update(Brand brand);

    /***
     * 新增Brand
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ID查询Brand
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /***
     * 查询所有Brand
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据分类查找相关品牌
     *
     * @param cateID
     * @return
     */
    List<Brand> findByCateID(Integer cateID);

    /**
     * 新加品牌，并且和分类进行绑定
     *
     * @param brand         品牌信息
     * @param categoryId    分类id
     * @param multipartFile 品牌图片
     */
    void addBrand(Brand brand, Integer categoryId, MultipartFile multipartFile);
}
