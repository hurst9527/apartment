package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:User构建
 *****/
@ApiModel(description = "User", value = "User")
@Table(name = "user")
public class User implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//主键

    @ApiModelProperty(value = "用户名", required = false)
    @Column(name = "name")
    private String name;//用户名

    @ApiModelProperty(value = "密码", required = false)
    @Column(name = "password")
    private String password;//密码

    @ApiModelProperty(value = "电话号码（用于登录）", required = false)
    @Column(name = "phoneNo")
    private Long phoneNo;//电话号码（用于登录）

    @ApiModelProperty(value = "图片", required = false)
    @Column(name = "image")
    private String image;//图片

    @ApiModelProperty(value = "出生日期", required = false)
    @Column(name = "birthday")
    private Date birthday;//出生日期

    @ApiModelProperty(value = "籍贯", required = false)
    @Column(name = "nativePlace")
    private String nativePlace;//籍贯

    @ApiModelProperty(value = "房间号", required = false)
    @Column(name = "roomID")
    private String roomID;//房间号

    @ApiModelProperty(value = "紧急联系人（人名）", required = false)
    @Column(name = "emergencyContactName")
    private String emergencyContactName;//紧急联系人（人名）

    @ApiModelProperty(value = "紧急联系人（电话号码）用于登录", required = false)
    @Column(name = "emergencyContactPhoneNo")
    private Long emergencyContactPhoneNo;//紧急联系人（电话号码）用于登录

    @ApiModelProperty(value = "紧急联系人（与本人关系）", required = false)
    @Column(name = "emergencyContactRelationship")
    private String emergencyContactRelationship;//紧急联系人（与本人关系）

    @ApiModelProperty(value = "紧急联系人（地址）", required = false)
    @Column(name = "emergencyContactAddress")
    private String emergencyContactAddress;//紧急联系人（地址）

    @ApiModelProperty(value = "注册日期", required = false)
    @Column(name = "registDate")
    private Date registDate;//注册日期

    @ApiModelProperty(value = "疾病", required = false)
    @Column(name = "diseases")
    private String diseases;//疾病

    @ApiModelProperty(value = "备注", required = false)
    @Column(name = "otherDesc")
    private String otherDesc;//备注

    @ApiModelProperty(value = "退房日期", required = false)
    @Column(name = "checkOutDate")
    private Date checkOutDate;//退房日期

    @ApiModelProperty(value = "0已注销   1：验证通过  2：验证不通过   3：等待验证", required = false)
    @Column(name = "status")
    private String status;//0已注销   1：验证通过  2：验证不通过   3：等待验证


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public String getName() {
        return name;
    }

    //set方法
    public void setName(String name) {
        this.name = name;
    }

    //get方法
    public String getPassword() {
        return password;
    }

    //set方法
    public void setPassword(String password) {
        this.password = password;
    }

    //get方法
    public Long getPhoneNo() {
        return phoneNo;
    }

    //set方法
    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    //get方法
    public String getImage() {
        return image;
    }

    //set方法
    public void setImage(String image) {
        this.image = image;
    }

    //get方法
    public Date getBirthday() {
        return birthday;
    }

    //set方法
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //get方法
    public String getNativePlace() {
        return nativePlace;
    }

    //set方法
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
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
    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    //set方法
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    //get方法
    public Long getEmergencyContactPhoneNo() {
        return emergencyContactPhoneNo;
    }

    //set方法
    public void setEmergencyContactPhoneNo(Long emergencyContactPhoneNo) {
        this.emergencyContactPhoneNo = emergencyContactPhoneNo;
    }

    //get方法
    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    //set方法
    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    //get方法
    public String getEmergencyContactAddress() {
        return emergencyContactAddress;
    }

    //set方法
    public void setEmergencyContactAddress(String emergencyContactAddress) {
        this.emergencyContactAddress = emergencyContactAddress;
    }

    //get方法
    public Date getRegistDate() {
        return registDate;
    }

    //set方法
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    //get方法
    public String getDiseases() {
        return diseases;
    }

    //set方法
    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    //get方法
    public String getOtherDesc() {
        return otherDesc;
    }

    //set方法
    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    //get方法
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    //set方法
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    //get方法
    public String getStatus() {
        return status;
    }

    //set方法
    public void setStatus(String status) {
        this.status = status;
    }

}
