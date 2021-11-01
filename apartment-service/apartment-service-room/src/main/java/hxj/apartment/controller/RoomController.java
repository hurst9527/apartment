package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.Room;
import hxj.apartment.service.RoomService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "RoomController")
@RestController
@RequestMapping("/room")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService roomService;

    /***
     * Room分页条件搜索实现
     * @param room
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Room条件分页查询",notes = "分页条件查询Room方法详情",tags = {"RoomController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Room对象",value = "传入JSON数据",required = false) Room room, @PathVariable  int page, @PathVariable  int size){
        //调用RoomService实现分页条件查询Room
        PageInfo<Room> pageInfo = roomService.findPage(room, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Room分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Room分页查询",notes = "分页查询Room方法详情",tags = {"RoomController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RoomService实现分页查询Room
        PageInfo<Room> pageInfo = roomService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param room
     * @return
     */
    @ApiOperation(value = "Room条件查询",notes = "条件查询Room方法详情",tags = {"RoomController"})
    @PostMapping(value = "/search" )
    public Result<List<Room>> findList(@RequestBody(required = false) @ApiParam(name = "Room对象",value = "传入JSON数据",required = false) Room room){
        //调用RoomService实现条件查询Room
        List<Room> list = roomService.findList(room);
        return new Result<List<Room>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Room根据ID删除",notes = "根据ID删除Room方法详情",tags = {"RoomController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用RoomService实现根据主键删除
        roomService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Room数据
     * @param room
     * @param id
     * @return
     */
    @ApiOperation(value = "Room根据ID修改",notes = "根据ID修改Room方法详情",tags = {"RoomController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Room对象",value = "传入JSON数据",required = false) Room room,@PathVariable Integer id){
        //设置主键值
        room.setId(id);
        //调用RoomService实现修改Room
        roomService.update(room);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Room数据
     * @param room
     * @return
     */
    @ApiOperation(value = "Room添加",notes = "添加Room方法详情",tags = {"RoomController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Room对象",value = "传入JSON数据",required = true) Room room){
        //调用RoomService实现添加Room
        roomService.add(room);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Room数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Room根据ID查询",notes = "根据ID查询Room方法详情",tags = {"RoomController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Room> findById(@PathVariable Integer id){
        //调用RoomService实现根据主键查询Room
        Room room = roomService.findById(id);
        return new Result<Room>(true,StatusCode.OK,"查询成功",room);
    }

    /***
     * 查询Room全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Room",notes = "查询所Room有方法详情",tags = {"RoomController"})
    @GetMapping
    public Result<List<Room>> findAll(){
        //调用RoomService实现查询所有Room
        List<Room> list = roomService.findAll();
        return new Result<List<Room>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
