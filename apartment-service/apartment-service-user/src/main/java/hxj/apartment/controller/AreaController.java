package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Area;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.AreaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "AreaController")
@RestController
@RequestMapping("/area")
@CrossOrigin
public class AreaController {

    @Autowired
    private AreaService areaService;


    @GetMapping("/search/{pid}")
    public Result<List<Area>> searchByPid(@PathVariable("pid") Integer pid) {
        Area area = new Area();
        area.setPid(pid);
        List<Area> list = areaService.findList(area);
        return new Result<List<Area>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * Area分页条件搜索实现
     * @param area
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Area条件分页查询", notes = "分页条件查询Area方法详情", tags = {"AreaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Area对象", value = "传入JSON数据") Area area, @PathVariable int page, @PathVariable int size) {
        //调用AreaService实现分页条件查询Area
        PageInfo<Area> pageInfo = areaService.findPage(area, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Area分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Area分页查询",notes = "分页查询Area方法详情",tags = {"AreaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AreaService实现分页查询Area
        PageInfo<Area> pageInfo = areaService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param area
     * @return
     */
    @ApiOperation(value = "Area条件查询", notes = "条件查询Area方法详情", tags = {"AreaController"})
    @PostMapping(value = "/search")
    public Result<List<Area>> findList(@RequestBody(required = false) @ApiParam(name = "Area对象", value = "传入JSON数据", required = false) Area area) {
//    public Result<List<Area>> findList(Area area) {
        //调用AreaService实现条件查询Area
        List<Area> list = areaService.findList(area);
        return new Result<List<Area>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Area根据ID删除",notes = "根据ID删除Area方法详情",tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AreaService实现根据主键删除
        areaService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Area数据
     * @param area
     * @param id
     * @return
     */
    @ApiOperation(value = "Area根据ID修改",notes = "根据ID修改Area方法详情",tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Area对象",value = "传入JSON数据",required = false) Area area,@PathVariable Integer id){
        //设置主键值
        area.setId(id);
        //调用AreaService实现修改Area
        areaService.update(area);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Area数据
     * @param area
     * @return
     */
    @ApiOperation(value = "Area添加",notes = "添加Area方法详情",tags = {"AreaController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Area对象",value = "传入JSON数据",required = true) Area area){
        //调用AreaService实现添加Area
        areaService.add(area);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Area数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Area根据ID查询",notes = "根据ID查询Area方法详情",tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Area> findById(@PathVariable Integer id){
        //调用AreaService实现根据主键查询Area
        Area area = areaService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", area);
    }

    /***
     * 查询Area全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Area",notes = "查询所Area有方法详情",tags = {"AreaController"})
    @GetMapping
    public Result<List<Area>> findAll(){
        //调用AreaService实现查询所有Area
        List<Area> list = areaService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }
}
