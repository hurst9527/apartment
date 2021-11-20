package hxj.apartment.feign;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Brand;
import hxj.apartment.bean.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-13 19:48
 */
@FeignClient("good")
public interface brandFeign {
    @PostMapping("/brand/findByCateID")
    Result findByCateID(@RequestParam Integer cateID);

    /***
     * Brand分页条件搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Brand条件分页查询", notes = "分页条件查询Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/brand/search/{page}/{size}")
    Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Brand对象", value = "传入JSON数据", required = false) Brand brand, @PathVariable int page, @PathVariable int size);

    /***
     * Brand分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Brand分页查询", notes = "分页查询Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/brand/search/{page}/{size}")
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param brand
     * @return
     */
    @ApiOperation(value = "Brand条件查询", notes = "条件查询Brand方法详情", tags = {"BrandController"})
    @PostMapping(value = "/brand/search")
    Result<List<Brand>> findList(@RequestBody(required = false) @ApiParam(name = "Brand对象", value = "传入JSON数据", required = false) Brand brand);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID删除", notes = "根据ID删除Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/brand/{id}")
    Result delete(@PathVariable Integer id);

    /***
     * 修改Brand数据
     * @param brand
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID修改", notes = "根据ID修改Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/brand/{id}")
    Result update(@RequestBody @ApiParam(name = "Brand对象", value = "传入JSON数据", required = false) Brand brand, @PathVariable Integer id);

    /***
     * 新增Brand数据
     * @param brand
     * @return
     */
    @ApiOperation(value = "Brand添加", notes = "添加Brand方法详情", tags = {"BrandController"})
    @PostMapping
    Result add(@RequestBody Brand brand);

    @ApiOperation(value = "Brand添加", notes = "添加Brand方法详情", tags = {"BrandController"})
    @PostMapping("/brand/appendBrand")
    Result addBrand(@RequestParam Brand brand, @RequestParam("categoryId") Integer categoryId, @RequestParam(value = "file", required = false) MultipartFile multipartFile);

    /***
     * 根据ID查询Brand数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID查询", notes = "根据ID查询Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/brand/{id}")
    Result<Brand> findById(@PathVariable Integer id);

    /***
     * 查询Brand全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Brand", notes = "查询所Brand有方法详情", tags = {"BrandController"})
    @GetMapping("/brand")
    Result<List<Brand>> findAll();

}
