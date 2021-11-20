package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-13 19:48
 */
@FeignClient("good")
public interface skuFeign {
    /***
     * 查询Sku全部数据
     * @return
     */
    @GetMapping("/sku")
    Result<List<Sku>> findAll();

    @GetMapping("/sku/{id}")
    Result<Sku> findById(@PathVariable(name = "id") String id);

}
