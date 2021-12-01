package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.spuInfo;
import hxj.apartment.service.spuSearchService;
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
@Api
@RestController
@RequestMapping("/spuSearch")
public class SpuSearchController {
    @Autowired
    private spuSearchService spuSearchService;

    @ApiOperation(value = "将spu数据导入到es中")
    @GetMapping("/import")
    public Result importData2Es() {
        spuSearchService.importData2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }

    @ApiOperation(value = "更新spu数据")
    @PutMapping("/update")
    public Result update(@RequestBody spuInfo spuInfo) {
        spuSearchService.update(spuInfo);
        return new Result(true, StatusCode.OK, "数据更新成功");
    }

    @ApiOperation(value = "插入一条spu数据")
    @PostMapping("/insert")
    public Result insert(@RequestBody spuInfo spuInfo) {
        spuSearchService.insert(spuInfo);
        return new Result(true, StatusCode.OK, "数据插入成功");
    }

    @ApiOperation(value = "根据主键从es中删除一条数据")
    @GetMapping("/delete/{skuID}")
    public Result delete(@PathVariable("skuID") Long skuiId) {
        spuSearchService.delete(skuiId);
        return new Result(true, StatusCode.OK, "数据删除成功");
    }

    @ApiOperation(value = "重新导入spu数据到es中")
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
    @ApiOperation(value = "从es中查询spu数据")
    @GetMapping
    public Result search(@RequestParam(required = false) Map<String, String> searchMap,
                         @PageableDefault(size = 28, sort = "saleNum", direction = Sort.Direction.ASC) Pageable pageable) {
        Map<String, Object> resultMap = spuSearchService.search(searchMap, pageable);
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
