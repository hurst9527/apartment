package hxj.apartment.controller;

import hxj.apartment.bean.Result;
import hxj.apartment.feign.spuSearchFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author HXJ
 * @create 2021-11-22 18:32
 */
@Controller
public class FrontSearchController {
    @Autowired
    private spuSearchFeign spuSearchFeign;

    /***
     * searchMap :
     *      category
     *      brand
     *      keyword
     *      price
     *      page
     *      sortField
     *      sortRule
     * @return resultMap:
     *      categoryList
     *      brandList
     *      specList
     *      totalPage
     *      totalElements
     *      skuList
     */
    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map<String, String> searchMap, Model model, @PageableDefault(size = 28, sort = "brandName", direction = Sort.Direction.ASC) Pageable pageable) {
        Result result = spuSearchFeign.search(searchMap, pageable);
        model.addAttribute("result", result.getResult());//搜索数据
        model.addAttribute("searchMap", searchMap);//搜索数据
        model.addAttribute("url", getUrl(searchMap));//搜索数据
        return "shopping";
    }


    /***
     * 根据搜索条件拼接url并返回
     *
     * @param searchMap
     * @return
     */
    public String getUrl(Map<String, String> searchMap) {
        String defaultUrl = "/list";
        String spiltUrl = defaultUrl;
        if (searchMap.size() > 0) {
            spiltUrl += "?";
            for (Map.Entry<String, String> searchEntry : searchMap.entrySet()) {
                String key = searchEntry.getKey();
                String val = searchEntry.getValue();
                if (!val.equals("")) {
                    spiltUrl += key + "=" + val + "&";
                }
            }
            spiltUrl = spiltUrl.substring(0, spiltUrl.length() - 1);
        }
        return spiltUrl;
    }
}
