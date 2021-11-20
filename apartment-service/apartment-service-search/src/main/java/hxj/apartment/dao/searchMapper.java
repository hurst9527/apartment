package hxj.apartment.dao;

import hxj.apartment.bean.skuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HXJ
 * @create 2021-11-13 19:40
 */
@Repository
public interface searchMapper extends ElasticsearchRepository<skuInfo, Long> {
}
