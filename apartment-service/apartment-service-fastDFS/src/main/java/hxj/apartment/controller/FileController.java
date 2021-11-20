package hxj.apartment.controller;

import hxj.apartment.bean.FastDFSFile;
import hxj.apartment.bean.Result;
import hxj.apartment.bean.StatusCode;
import hxj.apartment.util.fastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author HXJ
 * @create 2021-10-19 21:35
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            FastDFSFile file = new FastDFSFile(multipartFile.getName(),multipartFile.getBytes(), StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()));
            String[] infos = fastDFSUtil.upload(file);
            return new Result(true, StatusCode.OK,"文件上传成功！！", fastDFSUtil.getTrackerUrl()+infos[0]+"/"+infos[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"文件上传失败！！");
        }
    }


    @DeleteMapping("/delete")
    public Result delete(String groupName,String fileName){
        try {
            fastDFSUtil.delFile(groupName,fileName);
            return new Result(true, StatusCode.OK,"文件删除成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, StatusCode.ERROR,"文件删除失败！！");
        }
    }

    @GetMapping("/download")
    public InputStream download(String groupName,String fileName){
        try {
            InputStream inputStream = fastDFSUtil.downFile(groupName, fileName);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
