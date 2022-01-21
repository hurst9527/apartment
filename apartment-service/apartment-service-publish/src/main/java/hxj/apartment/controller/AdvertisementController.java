package hxj.apartment.controller;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Advertisement;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.AdvertisementService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:
 *****/
@Api(value = "AdvertisementController")
@RestController
@RequestMapping("/advertisement")
@CrossOrigin
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    /***
     * Advertisement分页条件搜索实现
     * @param advertisement
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Advertisement条件分页查询", notes = "分页条件查询Advertisement方法详情", tags = {"AdvertisementController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Advertisement对象", value = "传入JSON数据", required = false) Advertisement advertisement, @PathVariable int page, @PathVariable int size) {
        //调用AdvertisementService实现分页条件查询Advertisement
        PageInfo<Advertisement> pageInfo = advertisementService.findPage(advertisement, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Advertisement分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Advertisement分页查询", notes = "分页查询Advertisement方法详情", tags = {"AdvertisementController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用AdvertisementService实现分页查询Advertisement
        PageInfo<Advertisement> pageInfo = advertisementService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param advertisement
     * @return
     */
    @ApiOperation(value = "Advertisement条件查询", notes = "条件查询Advertisement方法详情", tags = {"AdvertisementController"})
    @PostMapping(value = "/search")
    public Result<List<Advertisement>> findList(@RequestBody(required = false) @ApiParam(name = "Advertisement对象", value = "传入JSON数据", required = false) Advertisement advertisement) {
        //调用AdvertisementService实现条件查询Advertisement
        List<Advertisement> list = advertisementService.findList(advertisement);
        return new Result<List<Advertisement>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Advertisement根据ID删除", notes = "根据ID删除Advertisement方法详情", tags = {"AdvertisementController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用AdvertisementService实现根据主键删除
        advertisementService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Advertisement数据
     * @param advertisement
     * @param id
     * @return
     */
    @ApiOperation(value = "Advertisement根据ID修改", notes = "根据ID修改Advertisement方法详情", tags = {"AdvertisementController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Advertisement对象", value = "传入JSON数据", required = false) Advertisement advertisement, @PathVariable Integer id) {
        //设置主键值
        advertisement.setId(id);
        //调用AdvertisementService实现修改Advertisement
        advertisementService.update(advertisement);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Advertisement数据
     * @param advertisement
     * @return
     */
    @ApiOperation(value = "Advertisement添加", notes = "添加Advertisement方法详情", tags = {"AdvertisementController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Advertisement对象", value = "传入JSON数据", required = true) Advertisement advertisement) {
        //调用AdvertisementService实现添加Advertisement
        advertisementService.add(advertisement);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Advertisement数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Advertisement根据ID查询", notes = "根据ID查询Advertisement方法详情", tags = {"AdvertisementController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Advertisement> findById(@PathVariable Integer id) {
        //调用AdvertisementService实现根据主键查询Advertisement
        Advertisement advertisement = advertisementService.findById(id);
        return new Result<Advertisement>(true, StatusCode.OK, "查询成功", advertisement);
    }

    /***
     * 查询Advertisement全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Advertisement", notes = "查询所Advertisement有方法详情", tags = {"AdvertisementController"})
    @GetMapping
    public Result<List<Advertisement>> findAll() {
        //调用AdvertisementService实现查询所有Advertisement
        List<Advertisement> list = advertisementService.findAll();
        return new Result<List<Advertisement>>(true, StatusCode.OK, "查询成功", list);
    }
}
