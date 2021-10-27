package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:AdminGrade构建
 *****/
@ApiModel(description = "AdminGrade",value = "AdminGrade")
@Table(name="admin_grade")
public class AdminGrade implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "身份",required = false)
    @Column(name = "identity")
	private String identity;//身份

	@ApiModelProperty(value = "等级",required = false)
    @Column(name = "grade")
	private String grade;//等级



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
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
	public String getGrade() {
		return grade;
	}

	//set方法
	public void setGrade(String grade) {
		this.grade = grade;
	}


}
