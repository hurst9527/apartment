package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.Spu_Brand_Category;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.Spu_Brand_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-25 21:05
 */
@RestController
@RequestMapping("/Spu_Brand_Category")
public class Spu_Brand_CategoryController {

    @Autowired
    private Spu_Brand_CategoryService spu_brand_categoryService;

    @GetMapping
    public Result<List<Spu_Brand_Category>> getAllSpu_Brand_Category() {
        List<Spu_Brand_Category> allSpu_brand_category = spu_brand_categoryService.getAllSpu_Brand_Category();
        return new Result<>(true, StatusCode.OK, "查询成功", allSpu_brand_category);
    }

    @GetMapping("/{spuID}")
    public Result<Spu_Brand_Category> getAllSpu_Brand_Category(@PathVariable("spuID") String spuID) {
        Spu_Brand_Category Spu_brand_category = spu_brand_categoryService.getAllSpu_Brand_CategoryByspuID(spuID);
        return new Result<>(true, StatusCode.OK, "查询成功", Spu_brand_category);
    }

}
