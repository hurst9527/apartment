package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Powerprice;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.PowerpriceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "PowerpriceController")
@RestController
@RequestMapping("/powerprice")
@CrossOrigin
public class PowerpriceController {

    @Autowired
    private PowerpriceService powerpriceService;

    /***
     * Powerprice分页条件搜索实现
     * @param powerprice
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Powerprice条件分页查询",notes = "分页条件查询Powerprice方法详情",tags = {"PowerpriceController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Powerprice对象",value = "传入JSON数据",required = false) Powerprice powerprice, @PathVariable  int page, @PathVariable  int size){
        //调用PowerpriceService实现分页条件查询Powerprice
        PageInfo<Powerprice> pageInfo = powerpriceService.findPage(powerprice, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Powerprice分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Powerprice分页查询",notes = "分页查询Powerprice方法详情",tags = {"PowerpriceController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PowerpriceService实现分页查询Powerprice
        PageInfo<Powerprice> pageInfo = powerpriceService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param powerprice
     * @return
     */
    @ApiOperation(value = "Powerprice条件查询",notes = "条件查询Powerprice方法详情",tags = {"PowerpriceController"})
    @PostMapping(value = "/search" )
    public Result<List<Powerprice>> findList(@RequestBody(required = false) @ApiParam(name = "Powerprice对象",value = "传入JSON数据",required = false) Powerprice powerprice){
        //调用PowerpriceService实现条件查询Powerprice
        List<Powerprice> list = powerpriceService.findList(powerprice);
        return new Result<List<Powerprice>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Powerprice根据ID删除",notes = "根据ID删除Powerprice方法详情",tags = {"PowerpriceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用PowerpriceService实现根据主键删除
        powerpriceService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Powerprice数据
     * @param powerprice
     * @param id
     * @return
     */
    @ApiOperation(value = "Powerprice根据ID修改",notes = "根据ID修改Powerprice方法详情",tags = {"PowerpriceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Powerprice对象",value = "传入JSON数据",required = false) Powerprice powerprice,@PathVariable Integer id){
        //设置主键值
        powerprice.setId(id);
        //调用PowerpriceService实现修改Powerprice
        powerpriceService.update(powerprice);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Powerprice数据
     * @param powerprice
     * @return
     */
    @ApiOperation(value = "Powerprice添加",notes = "添加Powerprice方法详情",tags = {"PowerpriceController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Powerprice对象",value = "传入JSON数据",required = true) Powerprice powerprice){
        //调用PowerpriceService实现添加Powerprice
        powerpriceService.add(powerprice);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Powerprice数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Powerprice根据ID查询",notes = "根据ID查询Powerprice方法详情",tags = {"PowerpriceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Powerprice> findById(@PathVariable Integer id){
        //调用PowerpriceService实现根据主键查询Powerprice
        Powerprice powerprice = powerpriceService.findById(id);
        return new Result<Powerprice>(true,StatusCode.OK,"查询成功",powerprice);
    }

    /***
     * 查询Powerprice全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Powerprice",notes = "查询所Powerprice有方法详情",tags = {"PowerpriceController"})
    @GetMapping
    public Result<List<Powerprice>> findAll(){
        //调用PowerpriceService实现查询所有Powerprice
        List<Powerprice> list = powerpriceService.findAll();
        return new Result<List<Powerprice>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
