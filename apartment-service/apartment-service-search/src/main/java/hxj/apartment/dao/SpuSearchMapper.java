package hxj.apartment.dao;

import hxj.apartment.bean.spuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HXJ
 * @create 2021-11-13 19:40
 */
@Repository
public interface SpuSearchMapper extends ElasticsearchRepository<spuInfo, Long> {
}
