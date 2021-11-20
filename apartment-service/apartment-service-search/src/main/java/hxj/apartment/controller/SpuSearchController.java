package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.spuInfo;
import hxj.apartment.service.spuSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-13 19:41
 */
@RestController
@RequestMapping("/spuSearch")
public class SpuSearchController {
    @Autowired
    private spuSearchService spuSearchService;


    @GetMapping("/import")
    public Result importData2Es() {
        spuSearchService.importData2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody spuInfo spuInfo) {
        spuSearchService.update(spuInfo);
        return new Result(true, StatusCode.OK, "数据更新成功");
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody spuInfo spuInfo) {
        spuSearchService.insert(spuInfo);
        return new Result(true, StatusCode.OK, "数据插入成功");
    }

    @GetMapping("/delete/{skuID}")
    public Result delete(@PathVariable("skuID") Long skuiId) {
        spuSearchService.delete(skuiId);
        return new Result(true, StatusCode.OK, "数据删除成功");
    }

    @GetMapping("/reImport")
    public Result reImportData2Es() {
        spuSearchService.reImportData2Es();
        return new Result(true, StatusCode.OK, "数据重新导入成功");
    }

    /***
     * searchMap :
     *      category
     *      brand
     *      keyword
     *      price
     *      page
     *      sortField
     *      sortRule
     *
     * @return resultMap:
     *      categoryList
     *      brandList
     *      specList
     *      totalPage
     *      totalElements
     *      skuList
     */
    @GetMapping
    public Result search(@RequestParam(required = false) Map<String, String> searchMap,
                         @PageableDefault(size = 28, sort = "saleNum", direction = Sort.Direction.ASC) Pageable pageable) {
        Map<String, Object> resultMap = spuSearchService.search(searchMap, pageable);
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
