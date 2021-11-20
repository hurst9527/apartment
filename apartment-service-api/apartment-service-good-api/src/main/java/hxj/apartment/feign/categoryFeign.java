package hxj.apartment.feign;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Category;
import hxj.apartment.bean.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-13 19:48
 */
@FeignClient("good")
public interface categoryFeign {
    /***
     * Category分页条件搜索实现
     * @param category
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Category条件分页查询", notes = "分页条件查询Category方法详情", tags = {"CategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/category/search/{page}/{size}")
    Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Category对象", value = "传入JSON数据", required = false) Category category, @PathVariable int page, @PathVariable int size);

    /***
     * Category分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Category分页查询", notes = "分页查询Category方法详情", tags = {"CategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/category/search/{page}/{size}")
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param category
     * @return
     */
    @ApiOperation(value = "Category条件查询", notes = "条件查询Category方法详情", tags = {"CategoryController"})
    @PostMapping(value = "/category/search")
    Result<List<Category>> findList(@RequestBody(required = false) @ApiParam(name = "Category对象", value = "传入JSON数据", required = false) Category category);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Category根据ID删除", notes = "根据ID删除Category方法详情", tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/category/{id}")
    Result delete(@PathVariable Integer id);

    /***
     * 修改Category数据
     * @param category
     * @param id
     * @return
     */
    @ApiOperation(value = "Category根据ID修改", notes = "根据ID修改Category方法详情", tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/category/{id}")
    Result update(@RequestBody @ApiParam(name = "Category对象", value = "传入JSON数据", required = false) Category category, @PathVariable Integer id);

    /***
     * 新增Category数据
     * @param category
     * @return
     */
    @ApiOperation(value = "Category添加", notes = "添加Category方法详情", tags = {"CategoryController"})
    @PostMapping
    Result add(@RequestBody @ApiParam(name = "Category对象", value = "传入JSON数据", required = true) Category category);

    /***
     * 根据ID查询Category数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Category根据ID查询", notes = "根据ID查询Category方法详情", tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/category/{id}")
    Result<Category> findById(@PathVariable Integer id);

    /***
     * 查询Category全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Category", notes = "查询所Category有方法详情", tags = {"CategoryController"})
    @GetMapping
    Result<List<Category>> findAll();

}
