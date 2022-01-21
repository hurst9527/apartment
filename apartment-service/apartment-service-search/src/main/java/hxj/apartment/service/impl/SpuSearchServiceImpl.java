package hxj.apartment.service.impl;

import com.alibaba.fastjson.JSON;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.Spu_Brand_Category;
import hxj.apartment.bean.spuInfo;
import hxj.apartment.dao.SpuSearchMapper;
import hxj.apartment.feign.spuBrandCategoryFeign;
import hxj.apartment.service.spuSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author HXJ
 * @create 2021-11-13 19:41
 */
@Service
public class SpuSearchServiceImpl implements spuSearchService {

    @Autowired
    private spuBrandCategoryFeign spuBrandCateFeign;

    @Autowired
    private SpuSearchMapper spuSearchMapper;

    @Autowired//elasticsearchTemplate封装了更好用的搜索方法
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String, Object> search(Map<String, String> searchMap, Pageable pageable) {
        //封装搜索结果进行返回
        Map<String, Object> resultmap = new LinkedHashMap<>();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = buildBaseQueryBuilder(searchMap, pageable);
        //      nativeSearchQueryBuilder.build()根据构建器构建搜索条件,spuInfo.class将结果封装为spuInfo
        AggregatedPage<spuInfo> spuInfos = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), spuInfo.class);

        int totalPages = spuInfos.getTotalPages();//总页数
        List<spuInfo> spuInfoList = spuInfos.getContent();//参数结果
        long totalElements = spuInfos.getTotalElements();//商品总数

        //用户点击了分类，即传入了分类数据，就不需要再对分类进行分组查询
        String firCategory = searchMap.get("FirCategory");
        String seCategory = searchMap.get("SeCategory");
        String thCategory = searchMap.get("ThCategory");
        if (StringUtils.isEmpty(thCategory)) {
            ArrayList<String> categoryList = getCategoryList(nativeSearchQueryBuilder, "category3Name", "ThCategoryList");
            resultmap.put("ThCategoryList", categoryList);
        }
        if ((StringUtils.isEmpty(seCategory)) && (StringUtils.isEmpty(thCategory))) {
            ArrayList<String> categoryList = getCategoryList(nativeSearchQueryBuilder, "category2Name", "SeCategoryList");
            resultmap.put("SeCategoryList", categoryList);
        }
        if ((StringUtils.isEmpty(firCategory)) && (StringUtils.isEmpty(seCategory)) && (StringUtils.isEmpty(thCategory))) {
            ArrayList<String> categoryList = getCategoryList(nativeSearchQueryBuilder, "category1Name", "FirCategoryList");
            resultmap.put("FirCategoryList", categoryList);
        }
        //用户点击了品牌，即传入了品牌数据，就不需要再对品牌进行分组查询
        if (StringUtils.isEmpty(searchMap.get("brand"))) {
            ArrayList<String> brandList = getBrandList(nativeSearchQueryBuilder);
            resultmap.put("brandList", brandList);
        }
        HashMap<String, Set<String>> specList = getSpecList(nativeSearchQueryBuilder);
        resultmap.put("specList", specList);
        resultmap.put("spuList", spuInfoList);
        LinkedHashMap<String, Object> pageInfo = new LinkedHashMap<>();
        pageInfo.put("page", pageable.getPageNumber());
        pageInfo.put("size", pageable.getPageSize());
        pageInfo.put("totalPage", totalPages);
        pageInfo.put("totalElements", totalElements);
        resultmap.put("pageInfo", pageInfo);
        return resultmap;
    }


    /**
     * 将搜索结果对分类进行分组查询
     * 以上都是进行关键词查询,也需要查询出相关关键词中的所有分类
     * 搜索条件的基础上进行分组查询,获取到所有去重的分类,类似于MySQL中的group by
     */
    private ArrayList<String> getCategoryList(NativeSearchQueryBuilder nativeSearchQueryBuilder, String FieldName, String aggregationName) {
        /**
         * .addAggregation()在原来搜索条件上添加聚合查询   .terms("cateList")查询字段设置别名  .field("categoryName")分组字段
         * 类似于select categoryName as terms from table where name = keyword group by categoryName;
         * 这里需要指定size，否则搜索出来的的结果默认只有十条，获取到的规格参数数据可能不完整
         */
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(aggregationName).field(FieldName).size(10000));
        AggregatedPage<spuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), spuInfo.class);
        StringTerms cateList = aggregatedPage.getAggregations().get(aggregationName);//这里获取数据字段名是取别名之后的字段名
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
        AggregatedPage<spuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), spuInfo.class);
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
     * @return HashMap
     */
    private HashMap<String, Set<String>> getSpecList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        HashMap<String, Set<String>> specList = new HashMap<>();
        //这里需要指定size，否则搜索出来的的结果默认只有十条，获取到的规格参数数据可能不完整
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("specList").field("specItems.keyword").size(10000));
        AggregatedPage<spuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), spuInfo.class);
        StringTerms cateList = aggregatedPage.getAggregations().get("specList");
        ArrayList<String> specItems = new ArrayList<>();
        for (StringTerms.Bucket bucket : cateList.getBuckets()) {
            specItems.add(bucket.getKeyAsString());
        }
        for (String specItem : specItems) {
            try {
                //每一项的格式为{'颜色': '灰色', '尺码': '200度'}
                Map<String, String> itemSpecMap = JSON.parseObject(specItem, Map.class);
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


    /***
     * 抽取方法：根据搜索的条件（searchMap）构建基础的搜索条件
     * @param searchMap   搜索条件
     * @return NativeSearchQueryBuilder  条件构造器
     */
    private NativeSearchQueryBuilder buildBaseQueryBuilder(Map<String, String> searchMap, Pageable pageable) {
        //构建搜索条件构建器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //构建boolQueryBuilder的目的：当有多个条件进行查找时，他们是并列关系，需要使用boolQueryBuilder
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        /**
         * 分页搜索(page=1),无论是否有搜索词，都需要分页
         */
        nativeSearchQueryBuilder.withPageable(pageable);

        //当搜索条件不为空
        if (searchMap != null && searchMap.size() > 0) {

            /**
             * 判断是否需要进行关键词搜索
             */
            String keyword = searchMap.get("name");
            if (keyword != null) {
                //.field("keyword")指定搜索域,.queryStringQuery(keyword)搜索值 类似于sql中的select * from table where 搜索域=搜索值
                //nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword).field("name"));//这个适用于但条件查询
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keyword).field("name"));
            }
            /**
             * 分类搜索（category=红色）
             */
            String FirCategory = searchMap.get("FirCategory");
            String SeCategory = searchMap.get("SeCategory");
            String ThCategory = searchMap.get("ThCategory");
            if (!StringUtils.isEmpty(FirCategory)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("category1Name", FirCategory));//.termQuery第一个是域名称第二个是值
            }
            if (!StringUtils.isEmpty(SeCategory)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("category2Name", SeCategory));
            }
            if (!StringUtils.isEmpty(ThCategory)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("category3Name", ThCategory));
            }
//            if (!StringUtils.isEmpty(ThCategory)){
//                boolQueryBuilder.must(QueryBuilders.termQuery("category3Name", ThCategory));
//            }else {
//                if (!StringUtils.isEmpty(SeCategory)){
//                    boolQueryBuilder.must(QueryBuilders.termQuery("category2Name", SeCategory));
//                }else {
//                    if (!StringUtils.isEmpty(FirCategory)){
//                        boolQueryBuilder.must(QueryBuilders.termQuery("category1Name", FirCategory));
//                    }
//                }
//            }

            /**
             * 规格参数搜索（spec_网络=4G&spec_颜色=红色）
             */
            String brand = searchMap.get("brand");
            if (!StringUtils.isEmpty(brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand));
            }
            String status = searchMap.get("status");
            if (!StringUtils.isEmpty(status)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("status", status));
            }
            // 遍历searchMap，找出所有以spec_开头的参数，作为规格域的搜索
            for (Map.Entry<String, String> searchEntry : searchMap.entrySet()) {
                String searchKey = searchEntry.getKey();
                String searchVal = searchEntry.getValue();
                if (searchKey.startsWith("spec_")) {
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + searchKey.substring(5) + ".keyword", searchVal));
                }
            }
            /**
             * 根据价格区间搜索
             */
            String price = searchMap.get("price");
            if (!StringUtils.isEmpty(price)) {
                //0-500   500-1000 .。。。3000元以上
                price = price.replace("元", "").replace("以上", "");//去除所有汉字
                String[] prices = price.split("-");
                if (prices.length == 2) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])).lt(Integer.parseInt(prices[1])));
                } else {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                }
            }

            /**
             * 排序功能(sortField=销量&sortRule=DESC)
             */
            String sortField = searchMap.get("sortField");
            String sortRule = searchMap.get("sortRule");
            if (!StringUtils.isEmpty(sortField) && !StringUtils.isEmpty(sortRule)) {
                sortRule = sortRule.toUpperCase();
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(SortOrder.valueOf(sortRule)));
            }
        }
        //如果没有搜索条件或者搜索条件中不指定商品状态的话，默认搜索上架商品
        if (searchMap == null || searchMap.get("status") == null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("status", "1"));
        }
        //将boolQueryBuilder填充给nativeSearchQueryBuilder
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return nativeSearchQueryBuilder;
    }


    /**
     * 将Spu、Brand、Category数据库中的数据导入到es中
     */
    @Override
    public void importData2Es() {
        Result<List<Spu_Brand_Category>> allSpu_Brand_Category = spuBrandCateFeign.getAllSpu_Brand_Category();
        List<spuInfo> spuInfoList = JSON.parseArray(JSON.toJSONString(allSpu_Brand_Category.getResult()), spuInfo.class);
        for (spuInfo spuInfo : spuInfoList) {
            try {
                String specItems = spuInfo.getSpecItems();
                Map specMap = JSON.parseObject(specItems, Map.class);
                spuInfo.setSpecMap(specMap);
            } catch (Exception e) {
            }
        }
        spuSearchMapper.saveAll(spuInfoList);
    }

    @Override
    public void reImportData2Es() {
        boolean spu_info = elasticsearchTemplate.deleteIndex("spu_info");
        if (spu_info) {
            importData2Es();
        }
    }

    @Override
    public void update(spuInfo spuInfo) {
        String specItems = spuInfo.getSpecItems();
        spuInfo.setSpecMap(JSON.parseObject(specItems, Map.class));
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(spuInfo)
                .build();
        String index = elasticsearchTemplate.index(indexQuery);
        // 返回的 index 是数据 id,如果指定了,返回指定的 id 值,未指定,返回一个 es 自动生成的
        System.out.println(index);
    }

    @Override
    public void insert(spuInfo spuInfo) {
        String specItems = spuInfo.getSpecItems();
        spuInfo.setSpecMap(JSON.parseObject(specItems, Map.class));
        spuSearchMapper.save(spuInfo);
    }

    @Override
    public void delete(Long spuInfoID) {
        spuSearchMapper.deleteById(spuInfoID);
    }

}
