package hxj.apartment.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import hxj.apartment.bean.Spu_Brand_Category;
import hxj.apartment.bean.spuInfo;
import hxj.apartment.feign.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HXJ
 * @create 2021-11-26 12:57
 * 监听数据库中Spu、Brand、Category三张表
 * 当发生变化时，将更改的数据同步到es的spu_info中
 */
@CanalEventListener
public class SpuBrandCategoryListener {

    @Autowired
    private brandFeign brandFeign;

    @Autowired
    private categoryFeign categoryFeign;

    @Autowired
    private spuFeign spuFeign;

    @Autowired
    private spuBrandCategoryFeign spuBrandCategoryFeign;

    @Autowired
    private spuSearchFeign spuSearchFeign;

    @ListenPoint(destination = "example",
            schema = "apartment_goods",
            table = {"spu"},
            eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void spuChange(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        /**
         * 如果是从数据库中删除数据，则根据id将es中的数据删除
         */
        if (eventType == CanalEntry.EventType.DELETE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                if (column.getName().equals("id")) {
                    spuSearchFeign.delete(Long.valueOf(column.getValue()));
                }
            }
        }
        /**
         * 如果是更新数据库中的数据，则将es中的数据根据id删除，然后再将数据从数据库查出重新导入
         */
        else if (eventType == CanalEntry.EventType.UPDATE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                if (column.getName().equals("id")) {
                    Spu_Brand_Category spu_brand_category = spuBrandCategoryFeign.getAllSpu_Brand_CategoryByspuID(column.getValue()).getResult();
                    spuInfo spuInfo = JSON.parseObject(JSON.toJSONString(spu_brand_category), spuInfo.class);
                    spuSearchFeign.update(spuInfo);//                    spuSearchFeign.insert(spuInfo);
                }
            }
        }
        /**
         * 如果是插入数据库中的数据，然后再将数据从数据库查出再导入
         */
        else if (eventType == CanalEntry.EventType.INSERT) {
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                if (column.getName().equals("id")) {
                    Spu_Brand_Category spu_brand_category = spuBrandCategoryFeign.getAllSpu_Brand_CategoryByspuID(column.getValue()).getResult();
                    spuInfo spuInfo = JSON.parseObject(JSON.toJSONString(spu_brand_category), spuInfo.class);
                    spuSearchFeign.insert(spuInfo);
                }
            }

        }
    }

//    @ListenPoint(destination = "example",
//            schema = "apartment_goods",
//            table = {"brand"},
//            eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
//    public void brandChange(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        /**
//         * 如果是从数据库中删除品牌，获取到品牌id后，将es中所有品牌id设置为该品牌id的数据更新
//         */
//        if (eventType == CanalEntry.EventType.DELETE) {
//            String id = null;
//            String brandName = null;
//            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
//                if (column.getName().equals("id")) {
//                    id = column.getValue();
//                }
//                if (column.getName().equals("name")) {
//                    brandName = column.getValue();
//                }
//            }
//            if (id != null && brandName != null) {
//                HashMap<Object, Object> searchMap = new HashMap<>();
////                spuSearchFeign.search(searchMap);
//            }
//        }
//        /**
//         * 如果是更新数据库中的数据，则将es中的数据根据id删除，然后再将数据从数据库查出重新导入
//         */
//        else if (eventType == CanalEntry.EventType.UPDATE) {
//            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
//                if (column.getName().equals("id")) {
//                    Spu_Brand_Category spu_brand_category = spuBrandCategoryFeign.getAllSpu_Brand_CategoryByspuID(column.getValue()).getResult();
//                    spuInfo spuInfo = JSON.parseObject(JSON.toJSONString(spu_brand_category), spuInfo.class);
//                    spuSearchFeign.update(spuInfo);
//                }
//            }
//        }
//        /**
//         * 如果是插入数据库中的数据，然后再将数据从数据库查出再导入
//         */
//        else if (eventType == CanalEntry.EventType.INSERT) {
//            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
//                if (column.getName().equals("id")) {
//                    Spu_Brand_Category spu_brand_category = spuBrandCategoryFeign.getAllSpu_Brand_CategoryByspuID(column.getValue()).getResult();
//                    spuInfo spuInfo = JSON.parseObject(JSON.toJSONString(spu_brand_category), spuInfo.class);
//                    spuSearchFeign.insert(spuInfo);
//                }
//            }
//
//        }
//    }

}
