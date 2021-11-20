package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.skuInfo;
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
@RequestMapping("/search")
public class searchController {
    @Autowired
    private hxj.apartment.service.searchService searchService;


    @GetMapping("/import")
    public Result importData2Es() {
        searchService.importData2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }

    @GetMapping("/update")
    public Result update(skuInfo skuinfo) {
        searchService.update(skuinfo);
        return new Result(true, StatusCode.OK, "数据更新成功");
    }

    @GetMapping("/insert")
    public Result insert(skuInfo skuInfo) {
        searchService.insert(skuInfo);
        return new Result(true, StatusCode.OK, "数据插入成功");
    }

    @GetMapping("/delete/{skuID}")
    public Result delete(@PathVariable("skuID") Long skuiId) {
        searchService.delete(skuiId);
        return new Result(true, StatusCode.OK, "数据删除成功");
    }

    @GetMapping("/reImport")
    public Result reImportData2Es() {
        searchService.reImportData2Es();
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
                         @PageableDefault(size = 30,sort = "num",direction = Sort.Direction.ASC) Pageable pageable) {
        Map<String, Object> resultMap = searchService.search(searchMap,pageable);
        //当前搜索条件下，各状态的商品总数(删除，下架，上架)
//        searchMap.put("status", "0");
//        Map downResult = searchService.search(searchMap);
//        searchMap.put("status", "1");
//        Map upResult = searchService.search(searchMap);
//        searchMap.put("status", "2");
//        Map delResult = searchService.search(searchMap);
//
//        resultMap.put("offElements", downResult.get("totalElements"));
//        resultMap.put("onElements", downResult.get("totalElements"));
//        resultMap.put("delElements", downResult.get("totalElements"));
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
