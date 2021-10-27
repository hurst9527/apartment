package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Category构建
 *****/
@ApiModel(description = "Category",value = "Category")
@Table(name="category")
public class Category implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
    @Column(name = "category")
	private String category;//



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getCategory() {
		return category;
	}

	//set方法
	public void setCategory(String category) {
		this.category = category;
	}


}
