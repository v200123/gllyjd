package com.wzh.controller;

import com.wzh.until.DataJson;
import com.wzh.until.JsonObject;
import com.wzh.until.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class CommonController {
//    @Value("${server.port}")
//    private String dkh;
    //    web上传图片
    @RequestMapping("/image")
    @ResponseBody
    public DataJson uploadImage(@RequestParam(value = "file") MultipartFile file) {
        String imagePath = UploadUtils.upload(file);//获得图片路径
        DataJson dataJson = new DataJson();
        if (imagePath != null) {
            dataJson.setCode(1);
            dataJson.setMsg("上传成功");
            HashMap<String, String> map = new HashMap<>();
            map.put("url", imagePath);
            dataJson.setData(map);
        } else {
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }
    @RequestMapping("/imageqds")
    @ResponseBody
    public JsonObject uploadImageqds(@RequestParam(value = "file") MultipartFile files[]) {
        String zong = "";
        String imagePath = null;
        for (MultipartFile file : files) {
            imagePath = UploadUtils.upload(file);//获得图片路径
            if (files.length>1) {
                zong = zong +"#"+ imagePath;
            }
            else {
                zong = imagePath;
            }
        }
        String Json = zong.substring(1);
        if (files.length > 1) {
            System.err.println("1");
            return new JsonObject("200", "", Json);
        } else if (files.length == 1) {
            System.err.println("0");
            return new JsonObject("200", "", imagePath);
        } else {
            return new JsonObject("500", "失败", "");
        }
    }

    @RequestMapping("/file")
    @ResponseBody
    public DataJson uploadFile(@RequestParam(value = "file") MultipartFile file) {
        String filePath = UploadUtils.upload(file);//获得图片路径
        DataJson dataJson = new DataJson();
        if (filePath != null) {
            dataJson.setCode(1);
            dataJson.setMsg(filePath);
            HashMap<String, String> map = new HashMap<>();
            map.put("url", filePath);
            map.put("code", UUID.randomUUID().toString().replace("-", ""));
            dataJson.setData(map);
        } else {
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }



    //上传文件
    @RequestMapping("/images")
    @ResponseBody
    public DataJson uploadImages(@RequestParam(value = "file") MultipartFile file) {
        String imagePath = UploadUtils.upload(file);//获得图片路径
        DataJson dataJson = new DataJson();
        if (imagePath != null) {
            dataJson.setCode(1);
            dataJson.setMsg("上传成功");
            HashMap<String, String> map = new HashMap<>();
            map.put("url", imagePath);
            System.out.println("文件" + imagePath);
            dataJson.setData(map);
        } else {
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }

    @RequestMapping("/imageqd")
    @ResponseBody
    public JsonObject uploadImageqd(@RequestParam(value = "file") MultipartFile file) {
        String imagePath = UploadUtils.upload(file);//获得图片路径
        if(imagePath!=null) {
            System.err.println("图片名"+imagePath);
            return new JsonObject("200","",imagePath);
        }else {
            return new JsonObject("500","","");


        }
    }


}
