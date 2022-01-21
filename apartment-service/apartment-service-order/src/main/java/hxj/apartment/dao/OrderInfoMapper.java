package hxj.apartment.dao;

import hxj.apartment.bean.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 17:47
 */
public interface OrderInfoMapper extends Mapper<OrderInfo> {
    /***
     * 通过userid查找所有状态为0（未完成）的所有订单
     * @param userID
     * @return
     */
    @Select("SELECT orders.id,orders.userID,orders.skuID skuID,sku.`name` skuName,sku.price skuPrice,spu.id spuID,spu.`name` spuName,orders.number,orders.price,orders.ifUpstairs,orders.orderTime,orders.finishTime,orders.`status` \n" +
            "\tFROM `orders` \n" +
            "LEFT JOIN sku \n" +
            "\tON orders.skuID = sku.id\n" +
            "LEFT JOIN spu \n" +
            "\tON orders.spuID = spu.id\n" +
            "WHERE orders.`finishTime` IS NULL AND orders.`status`='0' AND sku.`status`='1' AND spu.`status`='1' AND orders.userID=#{userID};")
    List<OrderInfo> getWaitingOrderInfoByUserID(@Param("userID") Integer userID);

    /***
     * 通过userid查找所有状态为1（已完成）的所有订单
     * @param userID
     * @return
     */
    @Select("SELECT orders.id,orders.userID,orders.skuID skuID,sku.`name` skuName,sku.price skuPrice,spu.id spuID,spu.`name` spuName,orders.number,orders.price,orders.ifUpstairs,orders.orderTime,orders.finishTime,orders.`status` \n" +
            "\tFROM `orders` \n" +
            "LEFT JOIN sku \n" +
            "\tON orders.skuID = sku.id\n" +
            "LEFT JOIN spu \n" +
            "\tON orders.spuID = spu.id\n" +
            "WHERE orders.`finishTime` IS NOT NULL AND orders.`status`='1'  AND sku.`status`='1' AND spu.`status`='1' AND orders.userID=#{userID};")
    List<OrderInfo> getFinishedOrderInfoByUserID(@Param("userID") Integer userID);

    /***
     * 查找所有状态为0（未完成）的所有订单
     * @param
     * @return
     */
    @Select("SELECT orders.id,orders.userID,orders.skuID skuID,sku.`name` skuName,sku.price skuPrice,spu.id spuID,spu.`name` spuName,orders.number,orders.price,orders.ifUpstairs,orders.orderTime,orders.finishTime,orders.`status` \n" +
            "\tFROM `orders` \n" +
            "LEFT JOIN sku \n" +
            "\tON orders.skuID = sku.id\n" +
            "LEFT JOIN spu \n" +
            "\tON orders.spuID = spu.id\n" +
            "WHERE orders.`finishTime` IS NULL AND orders.`status`='0' AND sku.`status`='1' AND spu.`status`='1';")
    List<OrderInfo> getAllWaitingOrderInfo();

    /***
     * 查找所有状态为1（已完成）的所有订单
     * @param
     * @return
     */
    @Select("SELECT orders.id,orders.userID,orders.skuID skuID,sku.`name` skuName,sku.price skuPrice,spu.id spuID,spu.`name` spuName,orders.number,orders.price,orders.ifUpstairs,orders.orderTime,orders.finishTime,orders.`status` \n" +
            "\tFROM `orders` \n" +
            "LEFT JOIN sku \n" +
            "\tON orders.skuID = sku.id\n" +
            "LEFT JOIN spu \n" +
            "\tON orders.spuID = spu.id\n" +
            "WHERE orders.`finishTime` IS NOT NULL AND orders.`status`='1' AND sku.`status`='1' AND spu.`status`='1';")
    List<OrderInfo> getAllFinishedOrderInfo();
}
