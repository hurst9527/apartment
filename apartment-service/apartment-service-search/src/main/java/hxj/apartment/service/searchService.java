package hxj.apartment.service;

import bean.skuInfo;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-13 19:41
 */
public interface searchService {
    /**
     * 将good数据库sku表中的数据导入到es
     */
    void importData2Es();

    /**
     * 将good数据库sku表中的数据导入到es
     */
    void reImportData2Es();

    /**
     * 添加sku
     *
     * @param skuInfo
     */
    void add(skuInfo skuInfo);

    void insert(skuInfo skuInfo);

    void delete(skuInfo skuInfo);

    Map<String, Object> search(Map<String, String> searchMap);
}