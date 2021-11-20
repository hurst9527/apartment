package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author HXJ
 * @create 2021-10-27 10:27
 */
@FeignClient("admin")
public interface adminFeign {

    /**
     * 根据在职状态查找员工
     * @param status
     * @return
     */
    @GetMapping("/admin/query")
    Result queryAdmin(@RequestParam("status")String status);


}
