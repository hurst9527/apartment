package hxj.apartment.controller;
import bean.Result;
import bean.StatusCode;
import hxj.apartment.bean.Disease;
import hxj.apartment.service.DiseaseService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "DiseaseController")
@RestController
@RequestMapping("/disease")
@CrossOrigin
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    /***
     * Disease分页条件搜索实现
     * @param disease
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Disease条件分页查询",notes = "分页条件查询Disease方法详情",tags = {"DiseaseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Disease对象",value = "传入JSON数据",required = false) Disease disease, @PathVariable  int page, @PathVariable  int size){
        //调用DiseaseService实现分页条件查询Disease
        PageInfo<Disease> pageInfo = diseaseService.findPage(disease, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Disease分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Disease分页查询",notes = "分页查询Disease方法详情",tags = {"DiseaseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用DiseaseService实现分页查询Disease
        PageInfo<Disease> pageInfo = diseaseService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param disease
     * @return
     */
    @ApiOperation(value = "Disease条件查询",notes = "条件查询Disease方法详情",tags = {"DiseaseController"})
    @PostMapping(value = "/search" )
    public Result<List<Disease>> findList(@RequestBody(required = false) @ApiParam(name = "Disease对象",value = "传入JSON数据",required = false) Disease disease){
        //调用DiseaseService实现条件查询Disease
        List<Disease> list = diseaseService.findList(disease);
        return new Result<List<Disease>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Disease根据ID删除",notes = "根据ID删除Disease方法详情",tags = {"DiseaseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用DiseaseService实现根据主键删除
        diseaseService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Disease数据
     * @param disease
     * @param id
     * @return
     */
    @ApiOperation(value = "Disease根据ID修改",notes = "根据ID修改Disease方法详情",tags = {"DiseaseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Disease对象",value = "传入JSON数据",required = false) Disease disease,@PathVariable Integer id){
        //设置主键值
        disease.setId(id);
        //调用DiseaseService实现修改Disease
        diseaseService.update(disease);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Disease数据
     * @param disease
     * @return
     */
    @ApiOperation(value = "Disease添加",notes = "添加Disease方法详情",tags = {"DiseaseController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Disease对象",value = "传入JSON数据",required = true) Disease disease){
        //调用DiseaseService实现添加Disease
        diseaseService.add(disease);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Disease数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Disease根据ID查询",notes = "根据ID查询Disease方法详情",tags = {"DiseaseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Disease> findById(@PathVariable Integer id){
        //调用DiseaseService实现根据主键查询Disease
        Disease disease = diseaseService.findById(id);
        return new Result<Disease>(true,StatusCode.OK,"查询成功",disease);
    }

    /***
     * 查询Disease全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Disease",notes = "查询所Disease有方法详情",tags = {"DiseaseController"})
    @GetMapping
    public Result<List<Disease>> findAll(){
        //调用DiseaseService实现查询所有Disease
        List<Disease> list = diseaseService.findAll();
        return new Result<List<Disease>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
