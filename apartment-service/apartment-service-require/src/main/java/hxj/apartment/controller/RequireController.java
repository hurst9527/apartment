package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Require;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.feign.FileFeign;
import hxj.apartment.service.RequireService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "RequireController")
@RestController
@RequestMapping("/require")
@CrossOrigin
public class RequireController {

    @Autowired
    private RequireService requireService;

    @Autowired
    private FileFeign fileFeign;

    /***
     * 根据用户id以及房间id获取对应的需求数据（无完成时间---finishTime is null）
     * @param require
     * @return
     */
    @PostMapping("/waitingRequires")
    public Result<List<Require>> getWaitRequire(@RequestBody Require require) {
        List<Require> requires = requireService.getWaitRequires(require.getUserID());
        return new Result<>(true, StatusCode.OK, "查询未完成需求订单成功", requires);
    }

    /***
     * 根据用户id以及房间id获取对应的需求数据（有完成时间---finishTime is not null）
     * @param require
     * @return
     */
    @PostMapping("/finishedRequires")
    public Result<List<Require>> getFinishRequire(@RequestBody Require require) {
        List<Require> requires = requireService.getFinishRequires(require.getUserID());
        return new Result<>(true, StatusCode.OK, "查询已完成需求订单成功", requires);
    }


    /***
     * 已完成的需求重新提交需求，将完成时间删除即可
     * @param requireID
     * @return
     */
    @GetMapping("/recommitRequire/{requireID}")
    public Result recommitRequire(@PathVariable("requireID") Integer requireID) {
        Require require = requireService.findById(requireID);
        require.setFinishTime(null);
        requireService.update(require);
        return new Result(true, StatusCode.OK, "取消需求订单成功");
    }


    /***
     * Require分页条件搜索实现
     * @param require
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Require条件分页查询", notes = "分页条件查询Require方法详情", tags = {"RequireController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Require对象", value = "传入JSON数据", required = false) Require require, @PathVariable int page, @PathVariable int size) {
        //调用RequireService实现分页条件查询Require
        PageInfo<Require> pageInfo = requireService.findPage(require, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Require分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Require分页查询", notes = "分页查询Require方法详情", tags = {"RequireController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用RequireService实现分页查询Require
        PageInfo<Require> pageInfo = requireService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索需求数据
     * @param require
     * @return
     */
    @ApiOperation(value = "Require条件查询", notes = "条件查询Require方法详情", tags = {"RequireController"})
    @PostMapping(value = "/search")
    public Result<List<Require>> findList(@RequestBody(required = false) @ApiParam(name = "Require对象", value = "传入JSON数据", required = false) Require require) {
        //调用RequireService实现条件查询Require
        List<Require> list = requireService.findList(require);
        return new Result<List<Require>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除需求数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Require根据ID删除", notes = "根据ID删除Require方法详情", tags = {"RequireController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用RequireService实现根据主键删除
        requireService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Require数据
     * @param require
     * @param id
     * @return
     */
    @ApiOperation(value = "Require根据ID修改", notes = "根据ID修改Require方法详情", tags = {"RequireController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Require对象", value = "传入JSON数据", required = false) Require require, @PathVariable Integer id) {
        //设置主键值
        require.setId(id);
        //调用RequireService实现修改Require
        requireService.update(require);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Require数据
     * @param require
     * @return
     */
    @ApiOperation(value = "Require添加", notes = "添加Require方法详情", tags = {"RequireController"})
    @PostMapping
    public Result add(Require require, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            Result result = fileFeign.upload(file);
            require.setImage(String.valueOf(result.getResult()));
        }
        //调用RequireService实现添加Require
        requireService.add(require);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Require数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Require根据ID查询", notes = "根据ID查询Require方法详情", tags = {"RequireController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Require> findById(@PathVariable Integer id) {
        //调用RequireService实现根据主键查询Require
        Require require = requireService.findById(id);
        return new Result<Require>(true, StatusCode.OK, "查询成功", require);
    }

    /***
     * 查询Require全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Require", notes = "查询所Require有方法详情", tags = {"RequireController"})
    @GetMapping
    public Result<List<Require>> findAll() {
        //调用RequireService实现查询所有Require
        List<Require> list = requireService.findAll();
        return new Result<List<Require>>(true, StatusCode.OK, "查询成功", list);
    }
}
