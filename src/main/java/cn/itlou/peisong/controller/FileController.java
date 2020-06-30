package cn.itlou.peisong.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载接口
 *
 * @author yuanyl
 * @date 2020/6/29 21:31
 **/
@RestController
public class FileController {

    @PostMapping("upload")
    public void upload(MultipartFile file){
        System.out.println(file);
    }

}
