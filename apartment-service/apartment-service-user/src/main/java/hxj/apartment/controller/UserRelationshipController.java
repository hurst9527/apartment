package hxj.apartment.controller;

import bean.Result;
import bean.StatusCode;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.UserRelationship;
import hxj.apartment.service.UserRelationshipService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "UserRelationshipController")
@RestController
@RequestMapping("/userRelationship")
@CrossOrigin
public class UserRelationshipController {

    @Autowired
    private UserRelationshipService userRelationshipService;

    /***
     * UserRelationship分页条件搜索实现
     * @param userRelationship
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "UserRelationship条件分页查询", notes = "分页条件查询UserRelationship方法详情", tags = {"UserRelationshipController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "UserRelationship对象", value = "传入JSON数据", required = false) UserRelationship userRelationship, @PathVariable int page, @PathVariable int size) {
        //调用UserRelationshipService实现分页条件查询UserRelationship
        PageInfo<UserRelationship> pageInfo = userRelationshipService.findPage(userRelationship, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * UserRelationship分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "UserRelationship分页查询", notes = "分页查询UserRelationship方法详情", tags = {"UserRelationshipController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserRelationshipService实现分页查询UserRelationship
        PageInfo<UserRelationship> pageInfo = userRelationshipService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param userRelationship
     * @return
     */
    @ApiOperation(value = "UserRelationship条件查询", notes = "条件查询UserRelationship方法详情", tags = {"UserRelationshipController"})
    @PostMapping(value = "/search")
    public Result<List<UserRelationship>> findList(@RequestBody(required = false) @ApiParam(name = "UserRelationship对象", value = "传入JSON数据", required = false) UserRelationship userRelationship) {
        //调用UserRelationshipService实现条件查询UserRelationship
        List<UserRelationship> list = userRelationshipService.findList(userRelationship);
        return new Result<List<UserRelationship>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UserRelationship根据ID删除", notes = "根据ID删除UserRelationship方法详情", tags = {"UserRelationshipController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用UserRelationshipService实现根据主键删除
        userRelationshipService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改UserRelationship数据
     * @param userRelationship
     * @param id
     * @return
     */
    @ApiOperation(value = "UserRelationship根据ID修改", notes = "根据ID修改UserRelationship方法详情", tags = {"UserRelationshipController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "UserRelationship对象", value = "传入JSON数据", required = false) UserRelationship userRelationship, @PathVariable Integer id) {
        //设置主键值
        userRelationship.setId(id);
        //调用UserRelationshipService实现修改UserRelationship
        userRelationshipService.update(userRelationship);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增UserRelationship数据
     * @param userRelationship
     * @return
     */
    @ApiOperation(value = "UserRelationship添加", notes = "添加UserRelationship方法详情", tags = {"UserRelationshipController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "UserRelationship对象", value = "传入JSON数据", required = true) UserRelationship userRelationship) {
        //调用UserRelationshipService实现添加UserRelationship
        userRelationshipService.add(userRelationship);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询UserRelationship数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UserRelationship根据ID查询", notes = "根据ID查询UserRelationship方法详情", tags = {"UserRelationshipController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<UserRelationship> findById(@PathVariable Integer id) {
        //调用UserRelationshipService实现根据主键查询UserRelationship
        UserRelationship userRelationship = userRelationshipService.findById(id);
        return new Result<UserRelationship>(true, StatusCode.OK, "查询成功", userRelationship);
    }

    /***
     * 查询UserRelationship全部数据
     * @return
     */
    @ApiOperation(value = "查询所有UserRelationship", notes = "查询所UserRelationship有方法详情", tags = {"UserRelationshipController"})
    @GetMapping
    public Result<List<UserRelationship>> findAll() {
        //调用UserRelationshipService实现查询所有UserRelationship
        List<UserRelationship> list = userRelationshipService.findAll();
        return new Result<List<UserRelationship>>(true, StatusCode.OK, "查询成功", list);
    }
}
