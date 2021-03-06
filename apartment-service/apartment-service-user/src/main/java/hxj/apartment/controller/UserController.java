package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.bean.User;
import hxj.apartment.feign.FileFeign;
import hxj.apartment.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private FileFeign fileFeign;

    @Autowired
    private UserService userService;

    @Autowired
    private hxj.apartment.feign.adminFeign adminFeign;


    /***
     * 获取所有系统中有注册日期，且注销日期为空，状态为1（审核通过）的所有住户。
     * @return
     */
    @GetMapping("/usersInSystem")
    public Result<List<User>> getAllUsersInSystem() {
        List<User> allUsersInSystem = userService.getAllUsersInSystem();
        return new Result<>(true, StatusCode.OK, "查询成功", allUsersInSystem);
    }


    /**
     * 查找电话号码，判断是否可用
     *
     * @param phoneNo
     * @return
     */
    @GetMapping("/phoneQuery")
    public Result phoneQuery(Long phoneNo) {
        User user = new User();
        user.setPhoneNo(phoneNo);
        user.setStatus("1");      //验证通过
        List<User> list = userService.findList(user);
        if (list.size() > 0) {
            return new Result(true, StatusCode.PHONENOERROR, "号码不可用");
        }
        return new Result(true, StatusCode.OK, "号码可用");
    }


    @PostMapping("/login")
    public Result login(User user, @RequestParam("identity") String identity,
                        @RequestParam(value = "isMember", required = false) boolean isMember) {
//        登录身份判断
        if (identity.equals("family")) {
            user.setEmergencyContactPhoneNo(user.getPhoneNo());
            user.setPhoneNo(null);
        }
        User searchUser = userService.login(user);
        if (searchUser == null) {
            return new Result(true, StatusCode.IDENTERROR, "账号错误");
        }
        if (searchUser.getPassword().equals(user.getPassword())) {
            return new Result(true, StatusCode.OK, "密码正确，登录成功", searchUser);
        }
        return new Result(true, StatusCode.PWDERROR, "密码错误");
    }

    /**
     * 将用户状态设置为0 不通过
     *
     * @param userId
     * @return
     */
    @PostMapping("/checkOut/{userId}")
    public Result checkout(@PathVariable("userId") Integer userId) {
        userService.userCheckout(userId);
        return new Result(true, StatusCode.OK, "用户成功注销");
    }

    /**
     * 将用户状态设置为1  通过
     *
     * @param userId
     * @return
     */
    @PostMapping("/passVerifi/{userId}")
    public Result PassVerification(@PathVariable("userId") Integer userId) {
        userService.passVerifi(userId);
        return new Result(true, StatusCode.OK, "用户已通过验证");
    }


    /**
     * 将用户状态设置为3  注销
     *
     * @param userId
     * @return
     */
    @PostMapping("/unPassVerifi/{userId}")
    public Result unPassVerification(@PathVariable("userId") Integer userId) {
        userService.unPassVerifi(userId);
        return new Result(true, StatusCode.OK, "用户未通过验证，请及时联系用户");
    }


    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "User条件分页查询", notes = "分页条件查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable int page, @PathVariable int size) {
//    public Result<PageInfo> findPage(User user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        PageInfo<User> pageInfo = userService.findPage(user, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "User分页查询", notes = "分页查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        PageInfo<User> pageInfo = userService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User条件查询", notes = "条件查询User方法详情", tags = {"UserController"})
    @PostMapping(value = "/search")
    public Result<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user) {
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID删除", notes = "根据ID删除User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID修改", notes = "根据ID修改User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PostMapping(value = "/{id}")
    public Result update(User user, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                         @PathVariable("id") Integer id, @RequestParam(name = "file", value = "file", required = false) MultipartFile file) {
        //设置主键值
        user.setId(id);
        user.setBirthday(birthDate);
//        将上传的图像进行保存
        if (file != null) {
            Result uploadRes = fileFeign.upload(file);
            user.setImage(String.valueOf(uploadRes.getResult()));
        }
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User添加", notes = "添加User方法详情", tags = {"UserController"})
    @PostMapping
    public Result add(User user) {
        //调用UserService实现添加User
        user.setStatus("3");
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询", notes = "根据ID查询User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Integer id) {
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @ApiOperation(value = "查询所有User", notes = "查询所User有方法详情", tags = {"UserController"})
    @GetMapping
    public Result<List<User>> findAll() {
        //调用UserService实现查询所有User
        List<User> list = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }
}
