package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Brand;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.feign.FileFeign;
import hxj.apartment.service.BrandService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "BrandController")
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private FileFeign fileFeign;

    @PostMapping("/findByCateID")
    public Result findByCateID(@RequestParam Integer cateID) {
        List<Brand> brandList = brandService.findByCateID(cateID);
        return new Result(true, StatusCode.OK, "查询成功", brandList);
    }

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
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand, @PathVariable  int page, @PathVariable  int size){
        //调用BrandService实现分页条件查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Brand分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Brand分页查询",notes = "分页查询Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用BrandService实现分页查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param brand
     * @return
     */
    @ApiOperation(value = "Brand条件查询",notes = "条件查询Brand方法详情",tags = {"BrandController"})
    @PostMapping(value = "/search" )
    public Result<List<Brand>> findList(@RequestBody(required = false) @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand){
        //调用BrandService实现条件查询Brand
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID删除",notes = "根据ID删除Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用BrandService实现根据主键删除
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Brand数据
     * @param brand
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID修改",notes = "根据ID修改Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand,@PathVariable Integer id){
        //设置主键值
        brand.setId(id);
        //调用BrandService实现修改Brand
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Brand数据
     * @param brand
     * @return
     */
    @ApiOperation(value = "Brand添加", notes = "添加Brand方法详情", tags = {"BrandController"})
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        //调用BrandService实现添加Brand
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    @ApiOperation(value = "Brand添加", notes = "添加Brand方法详情", tags = {"BrandController"})
    @PostMapping("/appendBrand")
    public Result addBrand(Brand brand, Integer categoryId, @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        brandService.addBrand(brand, categoryId, multipartFile);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 根据ID查询Brand数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Brand根据ID查询", notes = "根据ID查询Brand方法详情", tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        //调用BrandService实现根据主键查询Brand
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查询成功",brand);
    }

    /***
     * 查询Brand全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Brand",notes = "查询所Brand有方法详情",tags = {"BrandController"})
    @GetMapping
    public Result<List<Brand>> findAll(){
        //调用BrandService实现查询所有Brand
        List<Brand> list = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
