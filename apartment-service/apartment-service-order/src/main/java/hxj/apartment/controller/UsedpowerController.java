package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.Usedpower;
import hxj.apartment.service.UsedpowerService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "UsedpowerController")
@RestController
@RequestMapping("/usedpower")
@CrossOrigin
public class UsedpowerController {

    @Autowired
    private UsedpowerService usedpowerService;

    /***
     * Usedpower分页条件搜索实现
     * @param usedpower
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Usedpower条件分页查询",notes = "分页条件查询Usedpower方法详情",tags = {"UsedpowerController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Usedpower对象",value = "传入JSON数据",required = false) Usedpower usedpower, @PathVariable  int page, @PathVariable  int size){
        //调用UsedpowerService实现分页条件查询Usedpower
        PageInfo<Usedpower> pageInfo = usedpowerService.findPage(usedpower, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Usedpower分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Usedpower分页查询",notes = "分页查询Usedpower方法详情",tags = {"UsedpowerController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UsedpowerService实现分页查询Usedpower
        PageInfo<Usedpower> pageInfo = usedpowerService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param usedpower
     * @return
     */
    @ApiOperation(value = "Usedpower条件查询",notes = "条件查询Usedpower方法详情",tags = {"UsedpowerController"})
    @PostMapping(value = "/search" )
    public Result<List<Usedpower>> findList(@RequestBody(required = false) @ApiParam(name = "Usedpower对象",value = "传入JSON数据",required = false) Usedpower usedpower){
        //调用UsedpowerService实现条件查询Usedpower
        List<Usedpower> list = usedpowerService.findList(usedpower);
        return new Result<List<Usedpower>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Usedpower根据ID删除",notes = "根据ID删除Usedpower方法详情",tags = {"UsedpowerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用UsedpowerService实现根据主键删除
        usedpowerService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Usedpower数据
     * @param usedpower
     * @param id
     * @return
     */
    @ApiOperation(value = "Usedpower根据ID修改",notes = "根据ID修改Usedpower方法详情",tags = {"UsedpowerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Usedpower对象",value = "传入JSON数据",required = false) Usedpower usedpower,@PathVariable Integer id){
        //设置主键值
        usedpower.setId(id);
        //调用UsedpowerService实现修改Usedpower
        usedpowerService.update(usedpower);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Usedpower数据
     * @param usedpower
     * @return
     */
    @ApiOperation(value = "Usedpower添加",notes = "添加Usedpower方法详情",tags = {"UsedpowerController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Usedpower对象",value = "传入JSON数据",required = true) Usedpower usedpower){
        //调用UsedpowerService实现添加Usedpower
        usedpowerService.add(usedpower);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Usedpower数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Usedpower根据ID查询",notes = "根据ID查询Usedpower方法详情",tags = {"UsedpowerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Usedpower> findById(@PathVariable Integer id){
        //调用UsedpowerService实现根据主键查询Usedpower
        Usedpower usedpower = usedpowerService.findById(id);
        return new Result<Usedpower>(true,StatusCode.OK,"查询成功",usedpower);
    }

    /***
     * 查询Usedpower全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Usedpower",notes = "查询所Usedpower有方法详情",tags = {"UsedpowerController"})
    @GetMapping
    public Result<List<Usedpower>> findAll(){
        //调用UsedpowerService实现查询所有Usedpower
        List<Usedpower> list = usedpowerService.findAll();
        return new Result<List<Usedpower>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
