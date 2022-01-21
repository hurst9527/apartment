package hxj.apartment.feign;

import hxj.apartment.bean.Admin;
import hxj.apartment.bean.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-10-27 10:27
 */
@FeignClient("admin")
public interface adminFeign {

    /**
     * 根据在职状态查找员工
     *
     * @param status
     * @return
     */
    @GetMapping("/admin/query")
    Result queryAdmin(@RequestParam("status") String status);

    @GetMapping("/admin/{id}")
    Result<Admin> findById(@PathVariable Integer id);

    @PostMapping(value = "/admin/search")
    Result<List<Admin>> findList(@RequestBody(required = false) @ApiParam(name = "Admin对象", value = "传入JSON数据", required = false) Admin admin);
}
