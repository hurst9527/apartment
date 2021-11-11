package hxj.apartment.dao;

import hxj.apartment.bean.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Brand的Dao
 *****/
public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT `id`,`name`,`image`,`letter`,`seq` FROM `brand` WHERE `id` in (SELECT brand_id FROM category_brand WHERE category_id=#{cateID});")
    List<Brand> findByCateID(Integer cateID);
}
