package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.skuInfo;
import hxj.apartment.service.skuSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api
@RequestMapping("/skuSearch")
public class SkuSearchController {
    @Autowired
    private skuSearchService skuSearchService;

    @ApiOperation(value = "将sku数据库中的数据导入到es中")
    @GetMapping("/import")
    public Result importData2Es() {
        skuSearchService.importData2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }

    @ApiOperation(value = "更新es中sku数据")
    @PutMapping("/update")
    public Result update(@RequestBody skuInfo skuinfo) {
        skuSearchService.update(skuinfo);
        return new Result(true, StatusCode.OK, "数据更新成功");
    }

    @ApiOperation(value = "往es中插入一条sku数据")
    @PostMapping("/insert")
    public Result insert(@RequestBody skuInfo skuInfo) {
        skuSearchService.insert(skuInfo);
        return new Result(true, StatusCode.OK, "数据插入成功");
    }

    @ApiOperation(value = "根据主键从es中删除sku数据")
    @GetMapping("/delete/{skuID}")
    public Result delete(@PathVariable("skuID") Long skuiId) {
        skuSearchService.delete(skuiId);
        return new Result(true, StatusCode.OK, "数据删除成功");
    }

    @ApiOperation(value = "重新导入sku数据到es中")
    @GetMapping("/reImport")
    public Result reImportData2Es() {
        skuSearchService.reImportData2Es();
        return new Result(true, StatusCode.OK, "数据重新导入成功");
    }

    /***
     * searchMap :
     *      category    分类
     *      brand       品牌
     *      keyword     关键词
     *      price       价格0-500   500-1000 ... 3000元以上
     *      page        分页(整数)
     *      sortField   排序字段
     *      sortRule    排序规则 （ASC,DESC）
     *
     * @return resultMap:
     *      categoryList
     *      brandList
     *      specList
     *      totalPage
     *      totalElements
     *      skuList
     */
    @ApiOperation(value = "从es中查询sku数据")
    @GetMapping()
    public Result search(@RequestParam(required = false) Map<String, String> searchMap,
                         @PageableDefault(size = 30, sort = "num", direction = Sort.Direction.ASC) Pageable pageable) {
        Map<String, Object> resultMap = skuSearchService.search(searchMap, pageable);
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
