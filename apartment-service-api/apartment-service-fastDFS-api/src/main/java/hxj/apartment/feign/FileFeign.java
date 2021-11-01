package hxj.apartment.feign;

import bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author HXJ
 * @create 2021-10-20 23:31
 */

@FeignClient("file")
public interface FileFeign {

    @PostMapping("/file/upload")
    Result upload(MultipartFile multipartFile);

    @DeleteMapping("/file/delete")
    Result delete(@RequestParam String groupName,  @RequestParam String fileName);

    @GetMapping("/file/download")
    InputStream download(@RequestParam String groupName,@RequestParam String fileName);

}
