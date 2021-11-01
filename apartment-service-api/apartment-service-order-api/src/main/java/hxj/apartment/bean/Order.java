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
@Table(name = "order")
public class Order implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "商品id", required = false)
    @Column(name = "goodID")
    private Integer goodID;//商品id

    @ApiModelProperty(value = "商品数量", required = false)
    @Column(name = "number")
    private Integer number;//商品数量

    @ApiModelProperty(value = "", required = false)
    @Column(name = "price")
    private String price;//

    @ApiModelProperty(value = "0：不送   1：送上楼", required = false)
    @Column(name = "ifUpstairs")
    private String ifUpstairs;//0：不送   1：送上楼

    @ApiModelProperty(value = "下单时间", required = false)
    @Column(name = "time")
    private Date time;//下单时间

	@ApiModelProperty(value = "0待处理   1：处理完成 ", required = false)
	@Column(name = "status")
	private String status;//0待处理   1：处理完成

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public Integer getGoodID() {
        return goodID;
    }

    //set方法
    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
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
    public String getPrice() {
        return price;
    }

    //set方法
    public void setPrice(String price) {
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
    public Date getTime() {
        return time;
    }

    //set方法
    public void setTime(Date time) {
        this.time = time;
    }


}
