package hxj.apartment.controller;

import hxj.apartment.bean.OrderInfo;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.service.impl.OrderInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 18:12
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoServiceImpl orderInfoService;

    /***
     * 通过userid查找所有状态为0（未完成）的所有订单
     * @param userID
     * @return
     */
    @GetMapping("/waitingOrder/{userID}")
    public Result<List<OrderInfo>> getWaitingOrderInfoByUserID(@PathVariable("userID") Integer userID) {
        List<OrderInfo> waitingOrderInfos = orderInfoService.getWaitingOrderInfoByUserID(userID);
        return new Result<>(true, StatusCode.OK, "查询成功", waitingOrderInfos);
    }

    /***
     * 通过userid查找所有状态为1（已完成）的所有订单
     * @param userID
     * @return
     */
    @GetMapping("/finishedOrder/{userID}")
    public Result<List<OrderInfo>> getFinishedOrderInfoByUserID(@PathVariable("userID") Integer userID) {
        List<OrderInfo> finishedOrderInfos = orderInfoService.getFinishedOrderInfoByUserID(userID);
        return new Result<>(true, StatusCode.OK, "查询成功", finishedOrderInfos);
    }

    /***
     * 查找所有状态为0（未完成）的所有订单
     * @param
     * @return
     */
    @GetMapping("/waitingOrder")
    public Result<List<OrderInfo>> getAllWaitingOrderInfo() {
        List<OrderInfo> waitingOrderInfos = orderInfoService.getAllWaitingOrderInfo();
        return new Result<>(true, StatusCode.OK, "查询成功", waitingOrderInfos);
    }

    /***
     * 查找所有状态为1（已完成）的所有订单
     * @param
     * @return
     */
    @GetMapping("/finishedOrder")
    public Result<List<OrderInfo>> getAllFinishedOrderInfo() {
        List<OrderInfo> finishedOrderInfos = orderInfoService.getAllFinishedOrderInfo();
        return new Result<>(true, StatusCode.OK, "查询成功", finishedOrderInfos);
    }
}

