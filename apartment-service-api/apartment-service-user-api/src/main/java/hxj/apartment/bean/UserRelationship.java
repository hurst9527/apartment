package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:UserRelationships构建
 *****/
@ApiModel(description = "UserRelationships",value = "UserRelationships")
@Table(name="user_relationships")
public class UserRelationship implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "关系名",required = false)
    @Column(name = "relationshipName")
	private String relationshipName;//关系名

	@ApiModelProperty(value = "关系等级 1：直系亲属 2：旁系  3：无血缘关系",required = false)
    @Column(name = "grade")
	private String grade;//关系等级 1：直系亲属 2：旁系  3：无血缘关系



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getRelationshipName() {
		return relationshipName;
	}

	//set方法
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
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
