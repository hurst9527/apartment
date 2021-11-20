package hxj.apartment.service;

import hxj.apartment.bean.spuInfo;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-13 19:41
 */
public interface spuSearchService {
    /**
     * 将good数据库spu表中的数据导入到es
     */
    void importData2Es();

    /**
     * 将good数据库spu表中的数据导入到es
     */
    void reImportData2Es();

    /**
     * 添加spu
     *
     * @param spuInfo
     */
    void update(spuInfo spuInfo);

    void insert(spuInfo spuInfo);

    void delete(Long spuInfoID);

    Map<String, Object> search(Map<String, String> searchMap, Pageable pageable);

}