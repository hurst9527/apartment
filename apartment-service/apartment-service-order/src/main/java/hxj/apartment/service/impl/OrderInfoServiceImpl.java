package hxj.apartment.service.impl;

import hxj.apartment.bean.OrderInfo;
import hxj.apartment.dao.OrderInfoMapper;
import hxj.apartment.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 18:00
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /***
     * 通过userid查找所有状态为0（未完成）的所有订单
     * @param userID
     * @return
     */
    @Override
    public List<OrderInfo> getWaitingOrderInfoByUserID(Integer userID) {
        return orderInfoMapper.getWaitingOrderInfoByUserID(userID);
    }

    /***
     * 通过userid查找所有状态为1（已完成）的所有订单
     * @param userID
     * @return
     */
    @Override
    public List<OrderInfo> getFinishedOrderInfoByUserID(Integer userID) {
        return orderInfoMapper.getFinishedOrderInfoByUserID(userID);
    }

    /***
     * 查找所有状态为0（未完成）的所有订单
     * @param
     * @return
     */
    @Override
    public List<OrderInfo> getAllWaitingOrderInfo() {
        return orderInfoMapper.getAllWaitingOrderInfo();
    }

    /***
     * 查找所有状态为1（已完成）的所有订单
     * @param
     * @return
     */
    @Override
    public List<OrderInfo> getAllFinishedOrderInfo() {
        return orderInfoMapper.getAllFinishedOrderInfo();
    }
}
