package hxj.apartment.service.impl;

import hxj.apartment.bean.Spu_Brand_Category;
import hxj.apartment.dao.Spu_Brand_CategoryMapper;
import hxj.apartment.service.Spu_Brand_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-25 20:45
 */
@Service
public class Spu_Brand_CategoryServiceImpl implements Spu_Brand_CategoryService {

    @Autowired
    private Spu_Brand_CategoryMapper spu_brand_categoryMapper;

    @Override
    public List<Spu_Brand_Category> getAllSpu_Brand_Category() {
        return spu_brand_categoryMapper.getAllSpu_Brand_Category();
    }

    @Override
    public Spu_Brand_Category getAllSpu_Brand_CategoryByspuID(String spuID) {
        return spu_brand_categoryMapper.getAllSpu_Brand_CategoryByspuID(spuID);
    }
}
