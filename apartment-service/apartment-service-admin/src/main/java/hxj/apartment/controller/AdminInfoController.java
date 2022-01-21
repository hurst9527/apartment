package hxj.apartment.controller;

import hxj.apartment.bean.AdminInfo;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.impl.AdminInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 11:19
 */
@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {

    @Autowired
    private AdminInfoServiceImpl adminInfoService;

    @GetMapping
    public Result getNomalAdminInfos() {
        List<AdminInfo> adminInfos = adminInfoService.getNomalAdminInfo();
        return new Result(true, StatusCode.OK, "查询管理员信息成功", adminInfos);
    }

}
