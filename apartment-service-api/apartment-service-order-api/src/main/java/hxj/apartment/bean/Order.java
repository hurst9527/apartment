package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:Order构建
 *****/
@ApiModel(description = "Order", value = "Order")
@Table(name = "orders")
public class Order implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "userID", required = false)
    @Column(name = "userID")
    private Long userID;//userID
    @ApiModelProperty(value = "skuID", required = false)
    @Column(name = "skuID")

    private String skuID;//商品id
    @ApiModelProperty(value = "spuID", required = false)
    @Column(name = "spuID")
    private String spuID;//商品id
    @ApiModelProperty(value = "", required = false)
    @Column(name = "price")
    private Float price;//
    @ApiModelProperty(value = "下单时间", required = false)
    @Column(name = "orderTime")
    private Date orderTime;//下单时间

    @ApiModelProperty(value = "商品数量", required = false)
    @Column(name = "number")
    private Integer number;//商品数量
    @ApiModelProperty(value = "完成订单时间", required = false)
    @Column(name = "finishTime")
    private Date finishTime;//下单时间

    @ApiModelProperty(value = "0：不送   1：送上楼", required = false)
    @Column(name = "ifUpstairs")
    private String ifUpstairs;//0：不送   1：送上楼
    @ApiModelProperty(value = "0待处理   1：处理完成 ", required = false)
    @Column(name = "status")
    private String status;//0待处理   1：处理完成

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkuID() {
        return skuID;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public String getSpuID() {
        return spuID;
    }

    public void setSpuID(String spuID) {
        this.spuID = spuID;
    }

    //get方法
    public Integer getNumber() {
        return number;
    }

    //set方法
    public void setNumber(Integer number) {
        this.number = number;
    }

    //get方法
    public Float getPrice() {
        return price;
    }

    //set方法
    public void setPrice(Float price) {
        this.price = price;
    }

    //get方法
    public String getIfUpstairs() {
        return ifUpstairs;
    }

    //set方法
    public void setIfUpstairs(String ifUpstairs) {
        this.ifUpstairs = ifUpstairs;
    }

    //get方法
    public Date getOrderTime() {
        return orderTime;
    }

    //set方法
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


}
