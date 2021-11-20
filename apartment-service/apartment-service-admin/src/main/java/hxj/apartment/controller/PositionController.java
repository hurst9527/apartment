package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Position;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.PositionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "PositionController")
@RestController
@RequestMapping("/position")
@CrossOrigin
public class PositionController {

    @Autowired
    private PositionService positionService;

    /***
     * Position分页条件搜索实现
     * @param position
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Position条件分页查询",notes = "分页条件查询Position方法详情",tags = {"PositionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Position对象",value = "传入JSON数据",required = false) Position position, @PathVariable  int page, @PathVariable  int size){
        //调用PositionService实现分页条件查询Position
        PageInfo<Position> pageInfo = positionService.findPage(position, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Position分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Position分页查询",notes = "分页查询Position方法详情",tags = {"PositionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PositionService实现分页查询Position
        PageInfo<Position> pageInfo = positionService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param position
     * @return
     */
    @ApiOperation(value = "Position条件查询",notes = "条件查询Position方法详情",tags = {"PositionController"})
    @PostMapping(value = "/search" )
    public Result<List<Position>> findList(@RequestBody(required = false) @ApiParam(name = "Position对象",value = "传入JSON数据",required = false) Position position){
        //调用PositionService实现条件查询Position
        List<Position> list = positionService.findList(position);
        return new Result<List<Position>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Position根据ID删除",notes = "根据ID删除Position方法详情",tags = {"PositionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用PositionService实现根据主键删除
        positionService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Position数据
     * @param position
     * @param id
     * @return
     */
    @ApiOperation(value = "Position根据ID修改",notes = "根据ID修改Position方法详情",tags = {"PositionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Position对象",value = "传入JSON数据",required = false) Position position,@PathVariable Integer id){
        //设置主键值
        position.setId(id);
        //调用PositionService实现修改Position
        positionService.update(position);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Position数据
     * @param position
     * @return
     */
    @ApiOperation(value = "Position添加",notes = "添加Position方法详情",tags = {"PositionController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Position对象",value = "传入JSON数据",required = true) Position position){
        //调用PositionService实现添加Position
        positionService.add(position);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Position数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Position根据ID查询",notes = "根据ID查询Position方法详情",tags = {"PositionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Position> findById(@PathVariable Integer id){
        //调用PositionService实现根据主键查询Position
        Position position = positionService.findById(id);
        return new Result<Position>(true,StatusCode.OK,"查询成功",position);
    }

    /***
     * 查询Position全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Position",notes = "查询所Position有方法详情",tags = {"PositionController"})
    @GetMapping
    public Result<List<Position>> findAll(){
        //调用PositionService实现查询所有Position
        List<Position> list = positionService.findAll();
        return new Result<List<Position>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
