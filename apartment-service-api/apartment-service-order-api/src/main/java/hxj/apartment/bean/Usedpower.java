package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Usedpower构建
 *****/
@ApiModel(description = "Usedpower",value = "Usedpower")
@Table(name="usedpower")
public class Usedpower implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "房间号",required = false)
    @Column(name = "roomID")
	private String roomID;//房间号

	@ApiModelProperty(value = "更新水电时间（每月一次）",required = false)
    @Column(name = "updateTime")
	private Date updateTime;//更新水电时间（每月一次）

	@ApiModelProperty(value = "总用水",required = false)
    @Column(name = "waterTotal")
	private Integer waterTotal;//总用水

	@ApiModelProperty(value = "总用电数",required = false)
    @Column(name = "electricityTotal")
	private Integer electricityTotal;//总用电数



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
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
	public Date getUpdateTime() {
		return updateTime;
	}

	//set方法
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	//get方法
	public Integer getWaterTotal() {
		return waterTotal;
	}

	//set方法
	public void setWaterTotal(Integer waterTotal) {
		this.waterTotal = waterTotal;
	}
	//get方法
	public Integer getElectricityTotal() {
		return electricityTotal;
	}

	//set方法
	public void setElectricityTotal(Integer electricityTotal) {
		this.electricityTotal = electricityTotal;
	}


}
