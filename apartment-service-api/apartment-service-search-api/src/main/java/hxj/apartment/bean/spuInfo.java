package hxj.apartment.bean;

/**
 * @author HXJ
 * @create 2021-11-13 17:00
 */

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Map;

/**
 * SKU名称
 * type = FieldType.Text 类型：Text 支持分词
 * index = true ;添加数据的时候，是否分词
 * analyzer = "ik_smart" 创建索引的分词器
 * store = false;是否存储
 * searchAnalyzer = "ik_smart" 搜索时使用的分词器
 */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
/**
 * indexName = skumap  索引库的名称  type 可写可不写
 */
@Document(indexName = "spu_info", type = "docs")
public class spuInfo implements Serializable {

    @Id
    private Long id;//商品id，同时也是商品编号

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String name;

    private Integer sn;//货号

    private String caption;//副标题

    private Integer brandID;//品牌Name
    @Field(type = FieldType.Keyword)
    private String brandName;//品牌Name

    private Integer category1ID;//一级分类Name
    @Field(type = FieldType.Keyword)
    private String category1Name;//一级分类Name

    private Integer category2ID;//二级分类ID
    @Field(type = FieldType.Keyword)
    private String category2Name;//二级分类Name

    private Integer category3ID;//三级分类ID
    @Field(type = FieldType.Keyword)
    private String category3Name;//三级分类Name

    private String image;//商品首图

    private String saleService;//售后服务

    private String introduction;//介绍

    private String specItems;//参数列表

    private Integer saleNum;//销量

    private Integer commentNum;//评论数

    private String isMarketable;//是否上架

    private String isDelete;//是否删除

    private String status;//审核状态

    private Map<String, Object> specMap;//动态域

}