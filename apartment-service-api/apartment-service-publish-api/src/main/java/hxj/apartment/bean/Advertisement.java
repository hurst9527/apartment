package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:Advertisement构建
 *****/
@ApiModel(description = "Advertisement", value = "Advertisement")
@Table(name = "advertisement")
public class Advertisement implements Serializable {

	@ApiModelProperty(value = "", required = false)
	@Id
	@Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "广告名", required = false)
	@Column(name = "name")
	private String name;//广告名

	@ApiModelProperty(value = "广告内容", required = false)
	@Column(name = "content")
	private String content;//广告内容

	@ApiModelProperty(value = "地址", required = false)
	@Column(name = "url")
	private String url;//地址

	@ApiModelProperty(value = "图片地址", required = false)
	@Column(name = "pic")
	private String pic;//图片地址

	@ApiModelProperty(value = "状态 0：下架 1：上架", required = false)
	@Column(name = "status")
	private String status;//状态 0：下架 1：上架

	@ApiModelProperty(value = "广告分类", required = false)
	@Column(name = "category_id")
	private Integer categoryId;//广告分类

	@ApiModelProperty(value = "开始时间", required = false)
	@Column(name = "startTime")
	private Date startTime;//开始时间

	@ApiModelProperty(value = "失效时间", required = false)
	@Column(name = "endTime")
	private Date endTime;//失效时间


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
	public String getContent() {
		return content;
	}

	//set方法
	public void setContent(String content) {
		this.content = content;
	}

	//get方法
	public String getUrl() {
		return url;
	}

	//set方法
	public void setUrl(String url) {
		this.url = url;
	}

	//get方法
	public String getPic() {
		return pic;
	}

	//set方法
	public void setPic(String pic) {
		this.pic = pic;
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
	public Integer getCategoryId() {
		return categoryId;
	}

	//set方法
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	//get方法
	public Date getStartTime() {
		return startTime;
	}

	//set方法
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	//get方法
	public Date getEndTime() {
		return endTime;
	}

	//set方法
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


}
