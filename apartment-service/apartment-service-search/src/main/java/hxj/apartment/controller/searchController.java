package hxj.apartment.controller;

import bean.Result;
import bean.StatusCode;
import bean.skuInfo;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping()
    public Result search(@RequestParam(required = false) Map<String, String> searchMap) {
        Map<String, Object> resultMap = searchService.search(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }
}
