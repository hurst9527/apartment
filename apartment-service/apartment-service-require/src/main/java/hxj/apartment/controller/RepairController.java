package hxj.apartment.controller;

import bean.Result;
import bean.StatusCode;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Repair;
import hxj.apartment.feign.FileFeign;
import hxj.apartment.service.RepairService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "RepairController")
@RestController
@RequestMapping("/repair")
@CrossOrigin
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private FileFeign fileFeign;

    /***
     * Repair分页条件搜索实现
     * @param repair
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Repair条件分页查询", notes = "分页条件查询Repair方法详情", tags = {"RepairController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Repair对象",value = "传入JSON数据",required = false) Repair repair, @PathVariable  int page, @PathVariable  int size){
        //调用RepairService实现分页条件查询Repair
        PageInfo<Repair> pageInfo = repairService.findPage(repair, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Repair分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Repair分页查询",notes = "分页查询Repair方法详情",tags = {"RepairController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RepairService实现分页查询Repair
        PageInfo<Repair> pageInfo = repairService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param repair
     * @return
     */
    @ApiOperation(value = "Repair条件查询",notes = "条件查询Repair方法详情",tags = {"RepairController"})
    @PostMapping(value = "/search" )
    public Result<List<Repair>> findList(@RequestBody(required = false) @ApiParam(name = "Repair对象",value = "传入JSON数据",required = false) Repair repair){
        //调用RepairService实现条件查询Repair
        List<Repair> list = repairService.findList(repair);
        return new Result<List<Repair>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Repair根据ID删除",notes = "根据ID删除Repair方法详情",tags = {"RepairController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用RepairService实现根据主键删除
        repairService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Repair数据
     * @param repair
     * @param id
     * @return
     */
    @ApiOperation(value = "Repair根据ID修改",notes = "根据ID修改Repair方法详情",tags = {"RepairController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Repair对象",value = "传入JSON数据",required = false) Repair repair,@PathVariable Integer id){
        //设置主键值
        repair.setId(id);
        //调用RepairService实现修改Repair
        repairService.update(repair);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Repair数据
     * @param repair
     * @return
     */
    @ApiOperation(value = "Repair添加", notes = "添加Repair方法详情", tags = {"RepairController"})
    @PostMapping
    public Result add(Repair repair, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            Result result = fileFeign.upload(file);
            repair.setImage(String.valueOf(result.getResult()));
        }
        //调用RepairService实现添加Repair
        repairService.add(repair);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Repair数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Repair根据ID查询",notes = "根据ID查询Repair方法详情",tags = {"RepairController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Repair> findById(@PathVariable Integer id){
        //调用RepairService实现根据主键查询Repair
        Repair repair = repairService.findById(id);
        return new Result<Repair>(true,StatusCode.OK,"查询成功",repair);
    }

    /***
     * 查询Repair全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Repair",notes = "查询所Repair有方法详情",tags = {"RepairController"})
    @GetMapping
    public Result<List<Repair>> findAll(){
        //调用RepairService实现查询所有Repair
        List<Repair> list = repairService.findAll();
        return new Result<List<Repair>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
