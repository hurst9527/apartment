package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.Spu_Brand_Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-13 19:48
 */
@FeignClient("good")
public interface spuBrandCategoryFeign {

    @GetMapping("/Spu_Brand_Category/Spu_Brand_Category")
    Result<List<Spu_Brand_Category>> getAllSpu_Brand_Category();

    @GetMapping("/Spu_Brand_Category/{spuID}")
    Result<Spu_Brand_Category> getAllSpu_Brand_CategoryByspuID(@PathVariable("spuID") String spuID);
}

