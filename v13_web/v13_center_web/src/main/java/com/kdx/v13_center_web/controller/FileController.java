package com.kdx.v13_center_web.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.kdx.common.pojo.ResultBean;
import com.kdx.v13_center_web.pojo.EditorResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/13
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FastFileStorageClient client;

    @Value("${image.url}")
    private String url;

    @PostMapping("upload")
    @ResponseBody
    public ResultBean fileupload(MultipartFile file){


        System.out.println(url);
        //得到文件名
        String originalFilename = file.getOriginalFilename();
        //得到文件后缀名
        String str = originalFilename.substring(originalFilename.indexOf(".") + 1);
        try {
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), str, null);
            String fullPath = storePath.getFullPath();

            String path = new StringBuilder(url).append(fullPath).toString();
            System.out.println(fullPath);
            System.out.println(path);

            return new ResultBean("200",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultBean("404","上传失败，请重试。");
    }

    @RequestMapping("many")
    @ResponseBody
    public EditorResultBean manyupload(MultipartFile[] many){

        String[] data = new String[many.length];

            try {
                for (int i = 0; i < many.length; i++) {
                    MultipartFile file = many[i];
                    String name = file.getOriginalFilename();
                    String lastName = name.substring(name.indexOf(".") + 1);
                    StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), lastName, null);
                    String fullPath = storePath.getFullPath();
                    String path = new StringBuilder(url).append(fullPath).toString();
                    data[i] = path;
                    System.out.println(data[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();

                return new EditorResultBean("1",null);
            }

        return new EditorResultBean("0",data);
    }



}
