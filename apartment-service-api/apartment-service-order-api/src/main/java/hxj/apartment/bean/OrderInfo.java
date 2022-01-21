package hxj.apartment.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HXJ
 * @create 2022-01-02 17:43
 */
@Data
public class OrderInfo implements Serializable {
    private Integer id;//
    private Integer userID;//用户id
    private String skuID;//商品id
    private String skuName;
    private Integer skuPrice;
    private String spuID;//商品id
    private String spuName;
    private Integer number;//商品数量
    private String price;//
    private String ifUpstairs;//0：不送   1：送上楼
    private Date orderTime;//下单时间
    private Date finishTime;//下单时间
    private String status;//0待处理   1：处理完成
}
