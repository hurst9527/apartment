package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
/****
 * @Author:HXJ
 * @Description:Admin构建
 *****/
@ApiModel(description = "Admin",value = "Admin")
@Table(name="admin")
public class Admin implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "用户名",required = false)
    @Column(name = "name")
	private String name;//用户名

	@ApiModelProperty(value = "密码",required = false)
    @Column(name = "password")
	private String password;//密码

	@ApiModelProperty(value = "管理员等级   关联admin_grade中的grade字段",required = false)
    @Column(name = "identity")
	private String identity;//管理员等级   关联admin_grade中的grade字段

	@ApiModelProperty(value = "职位",required = false)
    @Column(name = "position")
	private String position;//职位

	@ApiModelProperty(value = "头像",required = false)
    @Column(name = "image")
	private String image;//头像

	@ApiModelProperty(value = "个人简介",required = false)
    @Column(name = "introduce")
	private String introduce;//个人简介

	@ApiModelProperty(value = "0:：已删除   1：审核中    2：审核通过",required = false)
    @Column(name = "status")
	private String status;//0:：已删除   1：审核中    2：审核通过

	@ApiModelProperty(value = "电话",required = false)
    @Column(name = "phoneNo")
	private Long phoneNo;//电话

	@ApiModelProperty(value = "邮箱",required = false)
    @Column(name = "email")
	private String email;//邮箱


	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
	public String getIdentity() {
		return identity;
	}

	//set方法
	public void setIdentity(String identity) {
		this.identity = identity;
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
	public String getIntroduce() {
		return introduce;
	}

	//set方法
	public void setIntroduce(String desc) {
		this.introduce = desc;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
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
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}


}
