package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.skuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-21 9:01
 */
@FeignClient("search")
public interface skuSearchFeign {

        @GetMapping("/skuSearch/import")
        Result importData2Es();

        @PutMapping("/skuSearch/update")
        Result update(@RequestBody skuInfo skuinfo);

        @PostMapping("/skuSearch/insert")
        Result insert(@RequestBody skuInfo skuInfo);

        @GetMapping("/skuSearch/delete/{skuID}")
        Result delete(@PathVariable("skuID") Long skuiId);

        @GetMapping("/skuSearch/reImport")
        Result reImportData2Es();

        @GetMapping("/skuSearch")
        Result search(@RequestParam(required = false) Map<String, String> searchMap, Pageable pageable);

}
