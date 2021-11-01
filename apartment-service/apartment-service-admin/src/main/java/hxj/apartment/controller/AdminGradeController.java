package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.AdminGrade;
import hxj.apartment.service.AdminGradeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "AdminGradeController")
@RestController
@RequestMapping("/adminGrade")
@CrossOrigin
public class AdminGradeController {

    @Autowired
    private AdminGradeService adminGradeService;

    /***
     * AdminGrade分页条件搜索实现
     * @param adminGrade
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "AdminGrade条件分页查询",notes = "分页条件查询AdminGrade方法详情",tags = {"AdminGradeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "AdminGrade对象",value = "传入JSON数据",required = false) AdminGrade adminGrade, @PathVariable  int page, @PathVariable  int size){
        //调用AdminGradeService实现分页条件查询AdminGrade
        PageInfo<AdminGrade> pageInfo = adminGradeService.findPage(adminGrade, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * AdminGrade分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "AdminGrade分页查询",notes = "分页查询AdminGrade方法详情",tags = {"AdminGradeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AdminGradeService实现分页查询AdminGrade
        PageInfo<AdminGrade> pageInfo = adminGradeService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param adminGrade
     * @return
     */
    @ApiOperation(value = "AdminGrade条件查询",notes = "条件查询AdminGrade方法详情",tags = {"AdminGradeController"})
    @PostMapping(value = "/search" )
    public Result<List<AdminGrade>> findList(@RequestBody(required = false) @ApiParam(name = "AdminGrade对象",value = "传入JSON数据",required = false) AdminGrade adminGrade){
        //调用AdminGradeService实现条件查询AdminGrade
        List<AdminGrade> list = adminGradeService.findList(adminGrade);
        return new Result<List<AdminGrade>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "AdminGrade根据ID删除",notes = "根据ID删除AdminGrade方法详情",tags = {"AdminGradeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AdminGradeService实现根据主键删除
        adminGradeService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改AdminGrade数据
     * @param adminGrade
     * @param id
     * @return
     */
    @ApiOperation(value = "AdminGrade根据ID修改",notes = "根据ID修改AdminGrade方法详情",tags = {"AdminGradeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "AdminGrade对象",value = "传入JSON数据",required = false) AdminGrade adminGrade,@PathVariable Integer id){
        //设置主键值
        adminGrade.setId(id);
        //调用AdminGradeService实现修改AdminGrade
        adminGradeService.update(adminGrade);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增AdminGrade数据
     * @param adminGrade
     * @return
     */
    @ApiOperation(value = "AdminGrade添加",notes = "添加AdminGrade方法详情",tags = {"AdminGradeController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "AdminGrade对象",value = "传入JSON数据",required = true) AdminGrade adminGrade){
        //调用AdminGradeService实现添加AdminGrade
        adminGradeService.add(adminGrade);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询AdminGrade数据
     * @param id
     * @return
     */
    @ApiOperation(value = "AdminGrade根据ID查询",notes = "根据ID查询AdminGrade方法详情",tags = {"AdminGradeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<AdminGrade> findById(@PathVariable Integer id){
        //调用AdminGradeService实现根据主键查询AdminGrade
        AdminGrade adminGrade = adminGradeService.findById(id);
        return new Result<AdminGrade>(true,StatusCode.OK,"查询成功",adminGrade);
    }

    /***
     * 查询AdminGrade全部数据
     * @return
     */
    @ApiOperation(value = "查询所有AdminGrade",notes = "查询所AdminGrade有方法详情",tags = {"AdminGradeController"})
    @GetMapping
    public Result<List<AdminGrade>> findAll(){
        //调用AdminGradeService实现查询所有AdminGrade
        List<AdminGrade> list = adminGradeService.findAll();
        return new Result<List<AdminGrade>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
