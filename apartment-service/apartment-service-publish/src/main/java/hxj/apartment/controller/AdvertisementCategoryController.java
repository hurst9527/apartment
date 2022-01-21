package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.AdvertisementCategory;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.AdvertisementCategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "AdvertisementCategoryController")
@RestController
@RequestMapping("/advertisementCategory")
@CrossOrigin
public class AdvertisementCategoryController {

    @Autowired
    private AdvertisementCategoryService advertisementCategoryService;

    /***
     * AdvertisementCategory分页条件搜索实现
     * @param advertisementCategory
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory条件分页查询", notes = "分页条件查询AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "AdvertisementCategory对象", value = "传入JSON数据", required = false) AdvertisementCategory advertisementCategory, @PathVariable int page, @PathVariable int size) {
        //调用AdvertisementCategoryService实现分页条件查询AdvertisementCategory
        PageInfo<AdvertisementCategory> pageInfo = advertisementCategoryService.findPage(advertisementCategory, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * AdvertisementCategory分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory分页查询", notes = "分页查询AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用AdvertisementCategoryService实现分页查询AdvertisementCategory
        PageInfo<AdvertisementCategory> pageInfo = advertisementCategoryService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param advertisementCategory
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory条件查询", notes = "条件查询AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @PostMapping(value = "/search")
    public Result<List<AdvertisementCategory>> findList(@RequestBody(required = false) @ApiParam(name = "AdvertisementCategory对象", value = "传入JSON数据", required = false) AdvertisementCategory advertisementCategory) {
        //调用AdvertisementCategoryService实现条件查询AdvertisementCategory
        List<AdvertisementCategory> list = advertisementCategoryService.findList(advertisementCategory);
        return new Result<List<AdvertisementCategory>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory根据ID删除", notes = "根据ID删除AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用AdvertisementCategoryService实现根据主键删除
        advertisementCategoryService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改AdvertisementCategory数据
     * @param advertisementCategory
     * @param id
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory根据ID修改", notes = "根据ID修改AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "AdvertisementCategory对象", value = "传入JSON数据", required = false) AdvertisementCategory advertisementCategory, @PathVariable Integer id) {
        //设置主键值
        advertisementCategory.setId(id);
        //调用AdvertisementCategoryService实现修改AdvertisementCategory
        advertisementCategoryService.update(advertisementCategory);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增AdvertisementCategory数据
     * @param advertisementCategory
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory添加", notes = "添加AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "AdvertisementCategory对象", value = "传入JSON数据", required = true) AdvertisementCategory advertisementCategory) {
        //调用AdvertisementCategoryService实现添加AdvertisementCategory
        advertisementCategoryService.add(advertisementCategory);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询AdvertisementCategory数据
     * @param id
     * @return
     */
    @ApiOperation(value = "AdvertisementCategory根据ID查询", notes = "根据ID查询AdvertisementCategory方法详情", tags = {"AdvertisementCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<AdvertisementCategory> findById(@PathVariable Integer id) {
        //调用AdvertisementCategoryService实现根据主键查询AdvertisementCategory
        AdvertisementCategory advertisementCategory = advertisementCategoryService.findById(id);
        return new Result<AdvertisementCategory>(true, StatusCode.OK, "查询成功", advertisementCategory);
    }

    /***
     * 查询AdvertisementCategory全部数据
     * @return
     */
    @ApiOperation(value = "查询所有AdvertisementCategory", notes = "查询所AdvertisementCategory有方法详情", tags = {"AdvertisementCategoryController"})
    @GetMapping
    public Result<List<AdvertisementCategory>> findAll() {
        //调用AdvertisementCategoryService实现查询所有AdvertisementCategory
        List<AdvertisementCategory> list = advertisementCategoryService.findAll();
        return new Result<List<AdvertisementCategory>>(true, StatusCode.OK, "查询成功", list);
    }
}
