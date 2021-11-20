package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.spuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-21 9:01
 */
@FeignClient("search")
public interface spuSearchFeign {

    @GetMapping("/spuSearch/import")
    Result importData2Es();

    @PutMapping("/spuSearch/update")
    Result update(@RequestBody spuInfo spuInfo);

    @PostMapping("/spuSearch/insert")
    Result insert(@RequestBody spuInfo spuInfo);

    @GetMapping("/spuSearch/delete/{skuID}")
    Result delete(@PathVariable("skuID") Long skuiId);

    @GetMapping("/spuSearch/reImport")
    Result reImportData2Es();

    @GetMapping("/spuSearch")
    Result search(@RequestParam(required = false) Map<String, String> searchMap,
                  @PageableDefault(size = 28, sort = "saleNum", direction = Sort.Direction.ASC) Pageable pageable);

}
