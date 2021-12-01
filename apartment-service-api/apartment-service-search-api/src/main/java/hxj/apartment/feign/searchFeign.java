package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.skuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-21 9:01
 */
@FeignClient("search")
public interface searchFeign {

        @GetMapping("/search/import")
        Result importData2Es() ;

        @GetMapping("/search/update")
        Result update(skuInfo skuinfo);

        @GetMapping("/search/insert")
        Result insert(skuInfo skuInfo) ;

        @GetMapping("/search/delete/{skuID}")
        Result delete(@PathVariable("skuID") Long skuiId);

        @GetMapping("/search/reImport")
        Result reImportData2Es();

        @GetMapping("/search")
        Result search(@RequestParam(required = false) Map<String, String> searchMap, Pageable pageable);

}
