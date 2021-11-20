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
import java.util.Date;
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
@Document(indexName = "sku_info", type = "docs")
public class skuInfo implements Serializable {

    @Id
    private Long id;//商品id，同时也是商品编号

    /**
     * SKU名称
     * type = FieldType.Text        类型：Text 支持分词
     * index = true                 添加数据的时候，是否分词
     * analyzer = "ik_smart"        创建索引的分词器
     * store = false                是否存储
     * searchAnalyzer = "ik_smart"  搜索时使用的分词器
     * 在es5.0之后，但设置字段type为string类型时，会创建两个字段，一个是text类型，一个是.keyword类型
     * Text：
     * 会分词，然后进行索引
     * 支持模糊、精确查询
     * 不支持聚合
     * keyword：
     * 不进行分词，直接索引
     * 支持模糊、精确查询
     * 支持聚合
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Double)
    private Long price;//商品价格，单位为：元

    private Integer num;//库存数量

    private String image;//商品图片

    private String status;//商品状态，0-下架，1-正常，2-删除

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String isDefault; //是否默认

    private Long spuId;//SPU_ID

    private Long categoryId;//类目ID

    private Integer saleNum;//销量

    @Field(type = FieldType.Keyword)
    private String categoryName;//类目名称,不分词

    @Field(type = FieldType.Keyword)
    private String brandName;//品牌名称，不分词

    private String spec;//规格，（string）{'颜色': '红色', '版本': '8GB+128GB'}

    private Map<String, Object> specMap;//规格参数map，用于生成动态域
}