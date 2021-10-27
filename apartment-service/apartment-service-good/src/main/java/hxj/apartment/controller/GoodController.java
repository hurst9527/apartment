package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.Good;
import hxj.apartment.service.GoodService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "GoodController")
@RestController
@RequestMapping("/good")
@CrossOrigin
public class GoodController {

    @Autowired
    private GoodService goodService;

    /***
     * Good分页条件搜索实现
     * @param good
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Good条件分页查询",notes = "分页条件查询Good方法详情",tags = {"GoodController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Good对象",value = "传入JSON数据",required = false) Good good, @PathVariable  int page, @PathVariable  int size){
        //调用GoodService实现分页条件查询Good
        PageInfo<Good> pageInfo = goodService.findPage(good, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Good分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Good分页查询",notes = "分页查询Good方法详情",tags = {"GoodController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用GoodService实现分页查询Good
        PageInfo<Good> pageInfo = goodService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param good
     * @return
     */
    @ApiOperation(value = "Good条件查询",notes = "条件查询Good方法详情",tags = {"GoodController"})
    @PostMapping(value = "/search" )
    public Result<List<Good>> findList(@RequestBody(required = false) @ApiParam(name = "Good对象",value = "传入JSON数据",required = false) Good good){
        //调用GoodService实现条件查询Good
        List<Good> list = goodService.findList(good);
        return new Result<List<Good>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Good根据ID删除",notes = "根据ID删除Good方法详情",tags = {"GoodController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用GoodService实现根据主键删除
        goodService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Good数据
     * @param good
     * @param id
     * @return
     */
    @ApiOperation(value = "Good根据ID修改",notes = "根据ID修改Good方法详情",tags = {"GoodController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Good对象",value = "传入JSON数据",required = false) Good good,@PathVariable Integer id){
        //设置主键值
        good.setId(id);
        //调用GoodService实现修改Good
        goodService.update(good);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Good数据
     * @param good
     * @return
     */
    @ApiOperation(value = "Good添加",notes = "添加Good方法详情",tags = {"GoodController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Good对象",value = "传入JSON数据",required = true) Good good){
        //调用GoodService实现添加Good
        goodService.add(good);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Good数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Good根据ID查询",notes = "根据ID查询Good方法详情",tags = {"GoodController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Good> findById(@PathVariable Integer id){
        //调用GoodService实现根据主键查询Good
        Good good = goodService.findById(id);
        return new Result<Good>(true,StatusCode.OK,"查询成功",good);
    }

    /***
     * 查询Good全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Good",notes = "查询所Good有方法详情",tags = {"GoodController"})
    @GetMapping
    public Result<List<Good>> findAll(){
        //调用GoodService实现查询所有Good
        List<Good> list = goodService.findAll();
        return new Result<List<Good>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
