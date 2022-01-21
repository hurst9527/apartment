package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Admin;
import hxj.apartment.bean.Message;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.feign.adminFeign;
import hxj.apartment.service.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "MessageController")
@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private adminFeign adminFeign;

    /***
     * Message分页条件搜索实现
     * @param message
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Message条件分页查询", notes = "分页条件查询Message方法详情", tags = {"MessageController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Message对象", value = "传入JSON数据", required = false) Message message, @PathVariable int page, @PathVariable int size) {
        //调用MessageService实现分页条件查询Message
        PageInfo<Message> pageInfo = messageService.findPage(message, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Message分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Message分页查询", notes = "分页查询Message方法详情", tags = {"MessageController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用MessageService实现分页查询Message
        PageInfo<Message> pageInfo = messageService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param message
     * @return
     */
    @ApiOperation(value = "Message条件查询", notes = "条件查询Message方法详情", tags = {"MessageController"})
    @PostMapping(value = "/search")
    public Result<List<Message>> findList(@RequestBody(required = false) @ApiParam(name = "Message对象", value = "传入JSON数据", required = false) Message message) {
        //调用MessageService实现条件查询Message
        List<Message> list = messageService.findList(message);
        return new Result<List<Message>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Message根据ID删除", notes = "根据ID删除Message方法详情", tags = {"MessageController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用MessageService实现根据主键删除
        messageService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Message数据
     * @param message
     * @param id
     * @return
     */
    @ApiOperation(value = "Message根据ID修改", notes = "根据ID修改Message方法详情", tags = {"MessageController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Message对象", value = "传入JSON数据", required = false) Message message, @PathVariable Integer id) {
        //设置主键值
        message.setId(id);
        //调用MessageService实现修改Message
        messageService.update(message);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Message数据
     * @param message
     * @return
     */
    @ApiOperation(value = "Message添加", notes = "添加Message方法详情", tags = {"MessageController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Message对象", value = "传入JSON数据", required = true) Message message) {
        //调用MessageService实现添加Message
        if (message != null && message.getMessageFor() != null) {
            //查询管理员是否存在
            Admin admin = new Admin();
            admin.setId(Integer.valueOf(message.getMessageFrom()));
            admin.setStatus("1");
            admin.setGrade("1");
            Result<List<Admin>> adminResult = adminFeign.findList(admin);
            if (adminResult.getResult().size() > 0) {
                message.setMessageFrom(adminResult.getResult().get(0).getName());
                messageService.add(message);
                return new Result(true, StatusCode.OK, "添加成功");
            }
            return new Result(true, StatusCode.IDENTERROR, "用户身份存在问题，请联系管理员");
        } else return new Result(true, StatusCode.ERROR, "消息内容为空，插入失败");
    }

    /***
     * 根据ID查询Message数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Message根据ID查询", notes = "根据ID查询Message方法详情", tags = {"MessageController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Message> findById(@PathVariable Integer id) {
        //调用MessageService实现根据主键查询Message
        Message message = messageService.findById(id);
        return new Result<Message>(true, StatusCode.OK, "查询成功", message);
    }

    /***
     * 查询Message全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Message", notes = "查询所Message有方法详情", tags = {"MessageController"})
    @GetMapping
    public Result<List<Message>> findAll() {
        //调用MessageService实现查询所有Message
        List<Message> list = messageService.findAll();
        return new Result<List<Message>>(true, StatusCode.OK, "查询成功", list);
    }
}
