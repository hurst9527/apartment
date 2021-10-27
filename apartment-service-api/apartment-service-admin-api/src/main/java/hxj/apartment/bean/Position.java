package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Position构建
 *****/
@ApiModel(description = "Position",value = "Position")
@Table(name="position")
public class Position implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "职位",required = false)
    @Column(name = "position")
	private String position;//职位



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getPosition() {
		return position;
	}

	//set方法
	public void setPosition(String position) {
		this.position = position;
	}


}
