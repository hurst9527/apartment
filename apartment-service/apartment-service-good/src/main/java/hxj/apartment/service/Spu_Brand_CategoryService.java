package hxj.apartment.service;

import hxj.apartment.bean.Spu_Brand_Category;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-25 20:44
 */
public interface Spu_Brand_CategoryService {

    List<Spu_Brand_Category> getAllSpu_Brand_Category();

    Spu_Brand_Category getAllSpu_Brand_CategoryByspuID(String spuID);
}
