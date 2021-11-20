package hxj.apartment.bean;

import lombok.Data;

import java.io.Serializable;

/****
 * @Author:HXJ
 * @Description:Spu构建
 *****/
@Data
public class Spu_Brand_Category extends Spu implements Serializable {
    private String brandName;
    private String category1Name;
    private String category2Name;
    private String category3Name;
}
