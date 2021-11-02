package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:Repair构建
 *****/
@ApiModel(description = "Repair", value = "Repair")
@Table(name = "repair")
public class Repair implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "房间号", required = false)
    @Column(name = "roomID")
    private String roomID;//房间号


    @ApiModelProperty(value = "电话号码", required = false)
    @Column(name = "phoneNo")
    private Long phoneNo;//电话号码
    @ApiModelProperty(value = "提交时间", required = false)
    @Column(name = "submitTime")
    private Date submitTime;//提交时间

    @ApiModelProperty(value = "维修时间", required = false)
    @Column(name = "finishTime")
    private Date finishTime;//维修时间

    @ApiModelProperty(value = "需要报修描述", required = false)
    @Column(name = "otherDesc")
    private String desc;//需要报修描述


    @Column(name = "image")
    private String image;//头像

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    //get方法
    public String getRoomID() {
        return roomID;
    }

    //set方法
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    //get方法
    public Date getSubmitTime() {
        return submitTime;
    }

    //set方法
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    //get方法
    public Date getFinishTime() {
        return finishTime;
    }

    //set方法
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    //get方法
    public String getDesc() {
        return desc;
    }

    //set方法
    public void setDesc(String desc) {
        this.desc = desc;
    }


}
