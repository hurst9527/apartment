package hxj.apartment.controller;

import bean.Result;
import bean.StatusCode;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Admin;
import hxj.apartment.bean.AdminInfo;
import hxj.apartment.bean.User;
import hxj.apartment.feign.UserFeign;
import hxj.apartment.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "AdminController")
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private UserFeign userFeign;


    /**
     * 根据状态查找用户
     *
     * @param status
     * @return
     */
    @GetMapping("/userQuery")
    public Result userQuery(String status) {
        User searchTemp = new User();
        searchTemp.setStatus("1");
        return userFeign.findList(searchTemp);
    }


    @PostMapping("/login")
    public Result login(Admin admin) {
//        构造搜索条件
        Admin searchTemp = new Admin();
        searchTemp.setPhoneNo(admin.getPhoneNo());
        searchTemp.setStatus("1");//在职
        List<Admin> list = adminService.findList(searchTemp);
        if (list.size() == 0) {
            return new Result(true, StatusCode.PHONENOERROR, "该账号不存在");
        }
        if (list.get(0).getPassword().equals(admin.getPassword())) {
            return new Result(true, StatusCode.OK, "登录成功");
        } else {
            return new Result(true, StatusCode.PWDERROR, "密码错误");
        }
    }


    /**
     * 根据在职状态查找员工
     *
     * @param status
     * @return
     */
    @GetMapping("/query")
    public Result queryAdmin(@RequestParam("status") String status) {
        List<AdminInfo> nowAmin = adminService.queryAdmin(status);
        return new Result(true,StatusCode.OK,"查询成功",nowAmin);
    }


    /***
     * Admin分页条件搜索实现
     * @param admin
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Admin条件分页查询",notes = "分页条件查询Admin方法详情",tags = {"AdminController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Admin对象",value = "传入JSON数据",required = false) Admin admin, @PathVariable  int page, @PathVariable  int size){
        //调用AdminService实现分页条件查询Admin
        PageInfo<Admin> pageInfo = adminService.findPage(admin, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Admin分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Admin分页查询",notes = "分页查询Admin方法详情",tags = {"AdminController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AdminService实现分页查询Admin
        PageInfo<Admin> pageInfo = adminService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param admin
     * @return
     */
    @ApiOperation(value = "Admin条件查询",notes = "条件查询Admin方法详情",tags = {"AdminController"})
    @PostMapping(value = "/search" )
    public Result<List<Admin>> findList(@RequestBody(required = false) @ApiParam(name = "Admin对象",value = "传入JSON数据",required = false) Admin admin){
        //调用AdminService实现条件查询Admin
        List<Admin> list = adminService.findList(admin);
        return new Result<List<Admin>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Admin根据ID删除",notes = "根据ID删除Admin方法详情",tags = {"AdminController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AdminService实现根据主键删除
        adminService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Admin数据
     * @param admin
     * @param id
     * @return
     */
    @ApiOperation(value = "Admin根据ID修改",notes = "根据ID修改Admin方法详情",tags = {"AdminController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Admin对象",value = "传入JSON数据",required = false) Admin admin,@PathVariable Integer id){
        //设置主键值
        admin.setId(id);
        //调用AdminService实现修改Admin
        adminService.update(admin);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Admin数据
     * @param admin
     * @return
     */
    @ApiOperation(value = "Admin添加",notes = "添加Admin方法详情",tags = {"AdminController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Admin对象",value = "传入JSON数据",required = true) Admin admin){
        //调用AdminService实现添加Admin
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Admin数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Admin根据ID查询",notes = "根据ID查询Admin方法详情",tags = {"AdminController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Admin> findById(@PathVariable Integer id){
        //调用AdminService实现根据主键查询Admin
        Admin admin = adminService.findById(id);
        return new Result<Admin>(true,StatusCode.OK,"查询成功",admin);
    }

    /***
     * 查询Admin全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Admin",notes = "查询所Admin有方法详情",tags = {"AdminController"})
    @GetMapping
    public Result<List<Admin>> findAll(){
        //调用AdminService实现查询所有Admin
        List<Admin> list = adminService.findAll();
        return new Result<List<Admin>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
