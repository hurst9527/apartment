package hxj.apartment.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:HXJ
 * @Description:Disease构建
 *****/
@ApiModel(description = "Disease",value = "Disease")
@Table(name="disease")
public class Disease implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "疾病名称",required = false)
    @Column(name = "disease")
	private String disease;//疾病名称

	@ApiModelProperty(value = "发病描述",required = false)
    @Column(name = "desc")
	private String desc;//发病描述

	@ApiModelProperty(value = "救治药物",required = false)
    @Column(name = "therapeuticDrugs")
	private String therapeuticDrugs;//救治药物

	@ApiModelProperty(value = "禁忌",required = false)
    @Column(name = "taboo")
	private String taboo;//禁忌



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getDisease() {
		return disease;
	}

	//set方法
	public void setDisease(String disease) {
		this.disease = disease;
	}
	//get方法
	public String getDesc() {
		return desc;
	}

	//set方法
	public void setDesc(String desc) {
		this.desc = desc;
	}
	//get方法
	public String getTherapeuticDrugs() {
		return therapeuticDrugs;
	}

	//set方法
	public void setTherapeuticDrugs(String therapeuticDrugs) {
		this.therapeuticDrugs = therapeuticDrugs;
	}
	//get方法
	public String getTaboo() {
		return taboo;
	}

	//set方法
	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}


}
