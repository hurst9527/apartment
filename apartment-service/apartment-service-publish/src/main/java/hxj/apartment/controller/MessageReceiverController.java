package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.MessageReceiver;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.MessageReceiverService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "MessageReceiverController")
@RestController
@RequestMapping("/messageReceiver")
@CrossOrigin
public class MessageReceiverController {

    @Autowired
    private MessageReceiverService messageReceiverService;

    /***
     * MessageReceiver分页条件搜索实现
     * @param messageReceiver
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "MessageReceiver条件分页查询", notes = "分页条件查询MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "MessageReceiver对象", value = "传入JSON数据", required = false) MessageReceiver messageReceiver, @PathVariable int page, @PathVariable int size) {
        //调用MessageReceiverService实现分页条件查询MessageReceiver
        PageInfo<MessageReceiver> pageInfo = messageReceiverService.findPage(messageReceiver, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * MessageReceiver分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "MessageReceiver分页查询", notes = "分页查询MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用MessageReceiverService实现分页查询MessageReceiver
        PageInfo<MessageReceiver> pageInfo = messageReceiverService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param messageReceiver
     * @return
     */
    @ApiOperation(value = "MessageReceiver条件查询", notes = "条件查询MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @PostMapping(value = "/search")
    public Result<List<MessageReceiver>> findList(@RequestBody(required = false) @ApiParam(name = "MessageReceiver对象", value = "传入JSON数据", required = false) MessageReceiver messageReceiver) {
        //调用MessageReceiverService实现条件查询MessageReceiver
        List<MessageReceiver> list = messageReceiverService.findList(messageReceiver);
        return new Result<List<MessageReceiver>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "MessageReceiver根据ID删除", notes = "根据ID删除MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用MessageReceiverService实现根据主键删除
        messageReceiverService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改MessageReceiver数据
     * @param messageReceiver
     * @param id
     * @return
     */
    @ApiOperation(value = "MessageReceiver根据ID修改", notes = "根据ID修改MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "MessageReceiver对象", value = "传入JSON数据", required = false) MessageReceiver messageReceiver, @PathVariable Integer id) {
        //设置主键值
        messageReceiver.setId(id);
        //调用MessageReceiverService实现修改MessageReceiver
        messageReceiverService.update(messageReceiver);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增MessageReceiver数据
     * @param messageReceiver
     * @return
     */
    @ApiOperation(value = "MessageReceiver添加", notes = "添加MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "MessageReceiver对象", value = "传入JSON数据", required = true) MessageReceiver messageReceiver) {
        //调用MessageReceiverService实现添加MessageReceiver
        messageReceiverService.add(messageReceiver);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询MessageReceiver数据
     * @param id
     * @return
     */
    @ApiOperation(value = "MessageReceiver根据ID查询", notes = "根据ID查询MessageReceiver方法详情", tags = {"MessageReceiverController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<MessageReceiver> findById(@PathVariable Integer id) {
        //调用MessageReceiverService实现根据主键查询MessageReceiver
        MessageReceiver messageReceiver = messageReceiverService.findById(id);
        return new Result<MessageReceiver>(true, StatusCode.OK, "查询成功", messageReceiver);
    }

    /***
     * 查询MessageReceiver全部数据
     * @return
     */
    @ApiOperation(value = "查询所有MessageReceiver", notes = "查询所MessageReceiver有方法详情", tags = {"MessageReceiverController"})
    @GetMapping
    public Result<List<MessageReceiver>> findAll() {
        //调用MessageReceiverService实现查询所有MessageReceiver
        List<MessageReceiver> list = messageReceiverService.findAll();
        return new Result<List<MessageReceiver>>(true, StatusCode.OK, "查询成功", list);
    }
}
