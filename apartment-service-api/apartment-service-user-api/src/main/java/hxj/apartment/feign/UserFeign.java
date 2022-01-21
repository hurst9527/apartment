package hxj.apartment.feign;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.User;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-10-19 11:36
 */
@FeignClient("user")
public interface UserFeign {

    /***
     * 获取所有系统中有注册日期，且注销日期为空，状态为1（审核通过）的所有住户。
     * @return
     */
    @GetMapping("/user/usersInSystem")
    Result<List<User>> getAllUsersInSystem();

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/user/search/{page}/{size}")
    Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable int page, @PathVariable int size);

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/user/search/{page}/{size}")
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) ;

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @PostMapping(value = "/user/search")
    Result<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user);
    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/user/{id}")
    Result delete(@PathVariable Integer id);

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @PutMapping(value = "/user/{id}")
    Result update(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable Integer id) ;

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping
    Result add(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = true) User user);

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    Result<User> findById(@PathVariable Integer id);

    /***
     * 查询User全部数据
     * @return
     */
    @GetMapping
    Result<List<User>> findAll() ;

}
