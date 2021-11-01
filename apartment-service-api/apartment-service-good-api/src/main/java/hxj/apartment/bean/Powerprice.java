package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Powerprice构建
 *****/
@ApiModel(description = "Powerprice",value = "Powerprice")
@Table(name="powerprice")
public class Powerprice implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "水费",required = false)
    @Column(name = "waterPrice")
	private Double waterPrice;//水费

	@ApiModelProperty(value = "电费",required = false)
    @Column(name = "elkectricityPrce")
	private Double elkectricityPrce;//电费



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public Double getWaterPrice() {
		return waterPrice;
	}

	//set方法
	public void setWaterPrice(Double waterPrice) {
		this.waterPrice = waterPrice;
	}
	//get方法
	public Double getElkectricityPrce() {
		return elkectricityPrce;
	}

	//set方法
	public void setElkectricityPrce(Double elkectricityPrce) {
		this.elkectricityPrce = elkectricityPrce;
	}


}
