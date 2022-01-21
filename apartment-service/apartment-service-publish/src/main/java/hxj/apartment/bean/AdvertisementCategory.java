package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:HXJ
 * @Description:AdvertisementCategory构建
 *****/
@ApiModel(description = "AdvertisementCategory", value = "AdvertisementCategory")
@Table(name = "advertisement_category")
public class AdvertisementCategory implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "广告分类", required = false)
    @Column(name = "advertisement_category")
    private String advertisementCategory;//广告分类


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public String getAdvertisementCategory() {
        return advertisementCategory;
    }

    //set方法
    public void setAdvertisementCategory(String advertisementCategory) {
        this.advertisementCategory = advertisementCategory;
    }


}
