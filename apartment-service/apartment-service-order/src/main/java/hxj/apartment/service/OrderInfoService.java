package hxj.apartment.service;

import hxj.apartment.bean.OrderInfo;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 18:00
 */
public interface OrderInfoService {
    /***
     * 通过userid查找所有状态为0（未完成）的所有订单
     * @param userID
     * @return
     */
    List<OrderInfo> getWaitingOrderInfoByUserID(Integer userID);

    /***
     * 通过userid查找所有状态为1（已完成）的所有订单
     * @param userID
     * @return
     */
    List<OrderInfo> getFinishedOrderInfoByUserID(Integer userID);

    /***
     * 查找所有状态为0（未完成）的所有订单
     * @param
     * @return
     */
    List<OrderInfo> getAllWaitingOrderInfo();

    /***
     * 查找所有状态为1（已完成）的所有订单
     * @param
     * @return
     */
    List<OrderInfo> getAllFinishedOrderInfo();
}
