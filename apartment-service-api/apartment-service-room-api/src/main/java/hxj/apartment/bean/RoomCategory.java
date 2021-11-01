package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:RoomCategory构建
 *****/
@ApiModel(description = "RoomCategory",value = "RoomCategory")
@Table(name="room_category")
public class RoomCategory implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "房间分类名称",required = false)
    @Column(name = "categoryName")
	private String categoryName;//房间分类名称

	@ApiModelProperty(value = "等级  1：住宅 2：公共场所",required = false)
    @Column(name = "grade")
	private String grade;//等级  1：住宅 2：公共场所



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getCategoryName() {
		return categoryName;
	}

	//set方法
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	//get方法
	public String getGrade() {
		return grade;
	}

	//set方法
	public void setGrade(String grade) {
		this.grade = grade;
	}


}
