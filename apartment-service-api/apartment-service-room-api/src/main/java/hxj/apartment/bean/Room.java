package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Room构建
 *****/
@ApiModel(description = "Room",value = "Room")
@Table(name="room")
public class Room implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "房间类型",required = false)
    @Column(name = "category")
	private String category;//房间类型

	@ApiModelProperty(value = "配置参数",required = false)
    @Column(name = "spec")
	private String spec;//配置参数

	@ApiModelProperty(value = "价格（/月）",required = false)
    @Column(name = "price")
	private String price;//价格（/月）



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
	//get方法
	public String getSpec() {
		return spec;
	}

	//set方法
	public void setSpec(String spec) {
		this.spec = spec;
	}
	//get方法
	public String getPrice() {
		return price;
	}

	//set方法
	public void setPrice(String price) {
		this.price = price;
	}


}
