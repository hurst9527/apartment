package hxj.apartment.dao;

import hxj.apartment.bean.Spu_Brand_Category;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Category的Dao
 *****/
public interface Spu_Brand_CategoryMapper extends Mapper<Spu_Brand_Category> {
    //    @Select("SELECT s.id,s.sn,s.name,s.caption,s.category1_id,s.category2_id,s.category3_id,s.image,s.sale_service,s.introduction,s.spec_items,s.sale_num,s.comment_num,s.is_marketable,s.is_enable_spec,s.is_delete,s.`status`,c1.name as category1Name,c2.name as category2Name,c3.name as category3Name FROM `spu` as s \n" +
//            "\n" +
//            "LEFT JOIN brand as b \n" +
//            "ON s.brand_id = b.id \n" +
//            "\n" +
//            "LEFT JOIN category as c1 \n" +
//            "on s.category1_id = c1.id \n" +
//            "\n" +
//            "LEFT JOIN category as c2 \n" +
//            "on s.category2_id = c2.id \n" +
//            "\n" +
//            "LEFT JOIN category as c3 \n" +
//            "on s.category3_id = c3.id \n" +
//            ";")
    List<Spu_Brand_Category> getAllSpu_Brand_Category();


    Spu_Brand_Category getAllSpu_Brand_CategoryByspuID(String spuID);
}
