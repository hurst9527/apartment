package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Good构建
 *****/
@ApiModel(description = "Good",value = "Good")
@Table(name="good")
public class Good implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "商品名称",required = false)
    @Column(name = "name")
	private String name;//商品名称

	@ApiModelProperty(value = "商品价格",required = false)
    @Column(name = "price")
	private String price;//商品价格

	@ApiModelProperty(value = "品牌",required = false)
    @Column(name = "brand")
	private String brand;//品牌

	@ApiModelProperty(value = "分类",required = false)
    @Column(name = "category")
	private String category;//分类

	@ApiModelProperty(value = "库存",required = false)
    @Column(name = "stock")
	private Integer stock;//库存

	@ApiModelProperty(value = "交易单位",required = false)
    @Column(name = "unit")
	private String unit;//交易单位

	@ApiModelProperty(value = "图片连接",required = false)
    @Column(name = "image")
	private String image;//图片连接

	@ApiModelProperty(value = "状态 0下架 1 上架 2 删除",required = false)
    @Column(name = "status")
	private String status;//状态 0下架 1 上架 2 删除



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
	public String getPrice() {
		return price;
	}

	//set方法
	public void setPrice(String price) {
		this.price = price;
	}
	//get方法
	public String getBrand() {
		return brand;
	}

	//set方法
	public void setBrand(String brand) {
		this.brand = brand;
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
	public Integer getStock() {
		return stock;
	}

	//set方法
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	//get方法
	public String getUnit() {
		return unit;
	}

	//set方法
	public void setUnit(String unit) {
		this.unit = unit;
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
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}


}
