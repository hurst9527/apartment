package hxj.apartment.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import hxj.apartment.bean.Sku;
import hxj.apartment.bean.skuInfo;
import hxj.apartment.feign.skuFeign;
import hxj.apartment.feign.skuSearchFeign;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HXJ
 * @create 2021-11-26 12:57
 * 监听数据库中Sku表
 * 当发生变化时，将更改的数据同步到es的sku_info中
 */
@CanalEventListener
public class SkuListener {

    @Autowired
    private skuSearchFeign skuSearchFeign;

    @Autowired
    private skuFeign skuFeign;


    @ListenPoint(destination = "example",
            schema = "apartment_goods",
            table = {"sku"},
            eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void skuChange(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        /**
         * 如果是从数据库中删除数据，则根据id将es中的数据删除
         */
        if (eventType == CanalEntry.EventType.DELETE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                String fieldName = column.getName();
                if (fieldName.equals("id")) {
                    System.out.println(column.getValue());
                    skuSearchFeign.delete(Long.valueOf(column.getValue()));
                }
            }
        }
        /**
         * 如果是更新数据库中的数据，则将es中的数据根据id删除，然后再将数据从数据库查出重新导入
         */
        else if (eventType == CanalEntry.EventType.UPDATE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                String fieldName = column.getName();
                if (fieldName.equals("id")) {
                    System.out.println(column.getValue());
                    Sku sku = skuFeign.findById(column.getValue()).getResult();//从数据库中查询数据
                    skuInfo skuInfo = JSON.parseObject(JSON.toJSONString(sku), skuInfo.class);//将数据转换为esbean格式
//                    skuSearchFeign.delete(Long.valueOf(column.getValue()));//删除es中的数据
//                    skuSearchFeign.insert(skuInfo);//插入数据到es
                    skuSearchFeign.update(skuInfo);//插入数据到es
                }
            }
        }
        /**
         * 如果是插入数据库中的数据，然后再将数据从数据库查出再导入
         */
        else if (eventType == CanalEntry.EventType.INSERT) {
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                String fieldName = column.getName();
                if (fieldName.equals("id")) {
                    System.out.println(column.getValue());
                    Sku sku = skuFeign.findById(column.getValue()).getResult();//从数据库中查询数据
                    skuInfo skuInfo = JSON.parseObject(JSON.toJSONString(sku), skuInfo.class);//将数据转换为esbean格式
                    skuSearchFeign.insert(skuInfo);//插入数据到es
                }
            }

        }
    }
}
