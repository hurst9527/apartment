package hxj.apartment.feign;

import hxj.apartment.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author HXJ
 * @create 2021-10-20 23:31
 */

@FeignClient("file")
public interface FileFeign {

    @PostMapping(value = "/file/upload", consumes = "multipart/form-data")
    Result upload(MultipartFile multipartFile);

    @DeleteMapping("/file/delete")
    Result delete(@RequestParam String groupName, @RequestParam String fileName);

    @GetMapping("/file/download")
    InputStream download(@RequestParam String groupName, @RequestParam String fileName);

}
