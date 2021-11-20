package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.skuInfo;
import hxj.apartment.service.skuSearchService;
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
@RequestMapping("/skuSearch")
public class SkuSearchController {
    @Autowired
    private skuSearchService skuSearchService;


    @GetMapping("/import")
    public Result importData2Es() {
        skuSearchService.importData2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }

    @PutMapping("/update")
    public Result update(@RequestBody skuInfo skuinfo) {
        skuSearchService.update(skuinfo);
        return new Result(true, StatusCode.OK, "数据更新成功");
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody skuInfo skuInfo) {
        skuSearchService.insert(skuInfo);
        return new Result(true, StatusCode.OK, "数据插入成功");
    }

    @GetMapping("/delete/{skuID}")
    public Result delete(@PathVariable("skuID") Long skuiId) {
        skuSearchService.delete(skuiId);
        return new Result(true, StatusCode.OK, "数据删除成功");
    }

    @GetMapping("/reImport")
    public Result reImportData2Es() {
        skuSearchService.reImportData2Es();
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
    @GetMapping()
    public Result search(@RequestParam(required = false) Map<String, String> searchMap,
                         @PageableDefault(size = 30, sort = "num", direction = Sort.Direction.ASC) Pageable pageable) {
        Map<String, Object> resultMap = skuSearchService.search(searchMap, pageable);
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
