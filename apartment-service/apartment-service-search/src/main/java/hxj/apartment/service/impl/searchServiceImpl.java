package hxj.apartment.service.impl;

import bean.Result;
import bean.skuInfo;
import com.alibaba.fastjson.JSON;
import hxj.apartment.bean.Sku;
import hxj.apartment.dao.searchMapper;
import hxj.apartment.feign.skuFeign;
import hxj.apartment.service.searchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author HXJ
 * @create 2021-11-13 19:41
 */
@Service
public class searchServiceImpl implements searchService {

    @Autowired
    private skuFeign skuFeign;

    @Autowired
    private searchMapper searchMapper;

    @Autowired//elasticsearchTemplate封装了更好用的搜索方法
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        //封装搜索结果进行返回
        Map<String, Object> resultmap = new HashMap<>();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = buildBaseQueryBuilder(searchMap);
        //      nativeSearchQueryBuilder.build()根据构建器构建搜索条件,skuInfo.class将结果封装为skuInfo
        AggregatedPage<skuInfo> skuInfos = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), skuInfo.class);

        int totalPages = skuInfos.getTotalPages();//总页数
        List<skuInfo> skuInfoList = skuInfos.getContent();//参数结果
        long totalElements = skuInfos.getTotalElements();//商品总数

        //用户点击了分类，即传入了分类数据，就不需要再对分类进行分组查询
        if (StringUtils.isEmpty(searchMap.get("category"))) {
            /**
             * 抽取的方法：获取去重之后的分类
             * 以上都是进行关键词查询,也需要查询出相关关键词中的所有分类
             * 可以在上面的搜索条件的基础上进行分组查询,获取到所有去重的分类,类似于MySQL中的group by
             */
            ArrayList<String> categoryList = getCategoryList(nativeSearchQueryBuilder);
            resultmap.put("categoryList", categoryList);
        }
        //用户点击了分类，即传入了分类数据，就不需要再对分类进行分组查询
        if (StringUtils.isEmpty(searchMap.get("brand"))) {
            ArrayList<String> brandList = getBrandList(nativeSearchQueryBuilder);
            resultmap.put("brandList", brandList);
        }
        HashMap<String, Set<String>> specList = getSpecList(nativeSearchQueryBuilder);
        resultmap.put("specList", specList);
        resultmap.put("totalPage", totalPages);
        resultmap.put("totalElements", totalElements);
        resultmap.put("skuList", skuInfoList);
        return resultmap;
    }


    /**
     * 将搜索结果对分类进行分组查询
     * 以上都是进行关键词查询,也需要查询出相关关键词中的所有分类
     * 搜索条件的基础上进行分组查询,获取到所有去重的分类,类似于MySQL中的group by
     */
    private ArrayList<String> getCategoryList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //.addAggregation()在原来搜索条件上添加聚合查询   .terms("cateList")查询字段设置别名  .field("categoryName")分组字段
        //类似于select categoryName as terms from table where name = keyword group by categoryName;
        //这里需要指定size，否则搜索出来的的结果默认只有十条，获取到的规格参数数据可能不完整
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cateList").field("categoryName").size(10000));
        AggregatedPage<skuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), skuInfo.class);
        StringTerms cateList = aggregatedPage.getAggregations().get("cateList");//这里获取数据字段名是取别名之后的字段名
        ArrayList<String> categoryList = new ArrayList<>();
        for (StringTerms.Bucket bucket : cateList.getBuckets()) {
            categoryList.add(bucket.getKeyAsString());//获取其中一个分类名字
        }
        return categoryList;
    }

    /**
     * 将搜索结果对品牌进行分组查询
     */
    private ArrayList<String> getBrandList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //这里需要指定size，否则搜索出来的的结果默认只有十条，获取到的规格参数数据可能不完整
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("brandList").field("brandName").size(10000));
        AggregatedPage<skuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), skuInfo.class);
        StringTerms cateList = aggregatedPage.getAggregations().get("brandList");
        ArrayList<String> brandList = new ArrayList<>();
        for (StringTerms.Bucket bucket : cateList.getBuckets()) {
            brandList.add(bucket.getKeyAsString());
        }
        return brandList;
    }

    /**
     * 将规格参数进行分组
     *
     * @param nativeSearchQueryBuilder
     * @return
     */
    private HashMap<String, Set<String>> getSpecList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        HashMap<String, Set<String>> specList = new HashMap<>();
        //这里需要指定size，否则搜索出来的的结果默认只有十条，获取到的规格参数数据可能不完整
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("specList").field("spec.keyword").size(10000));
        AggregatedPage<skuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), skuInfo.class);
        StringTerms cateList = aggregatedPage.getAggregations().get("specList");
        ArrayList<String> specItems = new ArrayList<>();
        for (StringTerms.Bucket bucket : cateList.getBuckets()) {
            specItems.add(bucket.getKeyAsString());
        }
        for (String spec : specItems) {
            try {
                //每一项的格式为{'颜色': '灰色', '尺码': '200度'}
                Map<String, String> itemSpecMap = JSON.parseObject(spec, Map.class);
                for (Map.Entry<String, String> entry : itemSpecMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    //已经存在相关的set直接插入
                    Set<String> values = specList.get(key);
                    if (values == null) {
                        values = new HashSet<>();
                    }
                    values.add(value);
                    specList.put(key, values);
                }
            } catch (Exception e) {
                continue;
            }
        }
        return specList;
    }


    /**
     * 抽取方法：根据搜索的条件（searchMap）构建基础的搜索条件
     * 返回条件构造器
     */
    private NativeSearchQueryBuilder buildBaseQueryBuilder(Map<String, String> searchMap) {
        //构建搜索条件构建器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //构建boolQueryBuilder的目的：当有多个条件进行查找时，他们是并列关系，需要使用boolQueryBuilder
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //当搜索条件不为空
        if (searchMap != null && searchMap.size() > 0) {
            //判断是否需要进行关键词搜索
            String keyword = searchMap.get("keyword");
            if (keyword != null) {
                //.field("keyword")指定搜索域,.queryStringQuery(keyword)搜索值 类似于sql中的select * from table where 搜索域=搜索值
                //nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword).field("name"));//这个适用于但条件查询
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keyword).field("name"));
            }
            String category = searchMap.get("category");
            if (!StringUtils.isEmpty(category)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", category));//.termQuery第一个是域名称第二个是值
            }
            String brand = searchMap.get("brand");
            if (!StringUtils.isEmpty(brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand));
            }
            // 遍历searchMap，找出所有以spec_开头的参数，作为规格域的搜索
            for (Map.Entry<String, String> searchEntry : searchMap.entrySet()) {
                String searchKey = searchEntry.getKey();
                String searchVal = searchEntry.getValue();
                if (searchKey.startsWith("spec_")) {
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + searchKey.substring(5) + ".keyword", searchVal));
                }
            }
        }
        //将boolQueryBuilder填充给nativeSearchQueryBuilder
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return nativeSearchQueryBuilder;
    }


    /**
     * 将Sku数据库中的数据导入到es中
     */
    @Override
    public void importData2Es() {
        Result<List<Sku>> allSku = skuFeign.findAll();

        //因为saveall需要一个可迭代对象，所以需要将list转换为数组
        //先转换为json字符串再转换成skuinfo类型数组
        List<skuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(allSku.getResult()), skuInfo.class);
        //这里需要生成一个动态域
        for (skuInfo skuInfo : skuInfoList) {
            try {
                //{'颜色': '紫色', '尺码': '250度'}
                String spec = skuInfo.getSpec();
                Map specMap = JSON.parseObject(spec, Map.class);
                skuInfo.setSpecMap(specMap);
            } catch (Exception e) {
                continue;
            }
        }
        searchMapper.saveAll(skuInfoList);
    }

    @Override
    public void reImportData2Es() {
        boolean sku_info = elasticsearchTemplate.deleteIndex("sku_info");
        if (sku_info) {
            importData2Es();
        }
    }

    @Override
    public void add(skuInfo skuInfo) {

    }

    @Override
    public void insert(skuInfo skuInfo) {
        searchMapper.save(skuInfo);
    }

    @Override
    public void delete(skuInfo skuInfo) {
        searchMapper.delete(skuInfo);
    }
}
