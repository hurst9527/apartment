package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.RoomCategory;
import hxj.apartment.service.RoomCategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "RoomCategoryController")
@RestController
@RequestMapping("/roomCategory")
@CrossOrigin
public class RoomCategoryController {

    @Autowired
    private RoomCategoryService roomCategoryService;

    /***
     * RoomCategory分页条件搜索实现
     * @param roomCategory
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "RoomCategory条件分页查询",notes = "分页条件查询RoomCategory方法详情",tags = {"RoomCategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "RoomCategory对象",value = "传入JSON数据",required = false) RoomCategory roomCategory, @PathVariable  int page, @PathVariable  int size){
        //调用RoomCategoryService实现分页条件查询RoomCategory
        PageInfo<RoomCategory> pageInfo = roomCategoryService.findPage(roomCategory, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * RoomCategory分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "RoomCategory分页查询",notes = "分页查询RoomCategory方法详情",tags = {"RoomCategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RoomCategoryService实现分页查询RoomCategory
        PageInfo<RoomCategory> pageInfo = roomCategoryService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param roomCategory
     * @return
     */
    @ApiOperation(value = "RoomCategory条件查询",notes = "条件查询RoomCategory方法详情",tags = {"RoomCategoryController"})
    @PostMapping(value = "/search" )
    public Result<List<RoomCategory>> findList(@RequestBody(required = false) @ApiParam(name = "RoomCategory对象",value = "传入JSON数据",required = false) RoomCategory roomCategory){
        //调用RoomCategoryService实现条件查询RoomCategory
        List<RoomCategory> list = roomCategoryService.findList(roomCategory);
        return new Result<List<RoomCategory>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "RoomCategory根据ID删除",notes = "根据ID删除RoomCategory方法详情",tags = {"RoomCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用RoomCategoryService实现根据主键删除
        roomCategoryService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改RoomCategory数据
     * @param roomCategory
     * @param id
     * @return
     */
    @ApiOperation(value = "RoomCategory根据ID修改",notes = "根据ID修改RoomCategory方法详情",tags = {"RoomCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "RoomCategory对象",value = "传入JSON数据",required = false) RoomCategory roomCategory,@PathVariable Integer id){
        //设置主键值
        roomCategory.setId(id);
        //调用RoomCategoryService实现修改RoomCategory
        roomCategoryService.update(roomCategory);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增RoomCategory数据
     * @param roomCategory
     * @return
     */
    @ApiOperation(value = "RoomCategory添加",notes = "添加RoomCategory方法详情",tags = {"RoomCategoryController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "RoomCategory对象",value = "传入JSON数据",required = true) RoomCategory roomCategory){
        //调用RoomCategoryService实现添加RoomCategory
        roomCategoryService.add(roomCategory);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询RoomCategory数据
     * @param id
     * @return
     */
    @ApiOperation(value = "RoomCategory根据ID查询",notes = "根据ID查询RoomCategory方法详情",tags = {"RoomCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<RoomCategory> findById(@PathVariable Integer id){
        //调用RoomCategoryService实现根据主键查询RoomCategory
        RoomCategory roomCategory = roomCategoryService.findById(id);
        return new Result<RoomCategory>(true,StatusCode.OK,"查询成功",roomCategory);
    }

    /***
     * 查询RoomCategory全部数据
     * @return
     */
    @ApiOperation(value = "查询所有RoomCategory",notes = "查询所RoomCategory有方法详情",tags = {"RoomCategoryController"})
    @GetMapping
    public Result<List<RoomCategory>> findAll(){
        //调用RoomCategoryService实现查询所有RoomCategory
        List<RoomCategory> list = roomCategoryService.findAll();
        return new Result<List<RoomCategory>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
