package com.wzh.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    //定义一个目标路径，就是我们要把图片上传到的位置
    private static final String BASE_PATH="D:\\code\\310\\code\\Idea\\gllyjd\\src\\main\\resources\\public\\";
    public static String upload(MultipartFile file) {
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("_","");
        String newFilename = uuid+"_"+filename;
        File image = null;
        if (containsChineseCharacters(filename)){
            int lastDotIndex = filename.lastIndexOf(".");
            String result = filename.substring(lastDotIndex, filename.length());
            System.err.println("res"+result);
            image = new File(BASE_PATH,uuid+"_"+result);
            try {
                file.transferTo(image);
            } catch (IOException e) {
                return null;
            }
            return uuid+"_"+result;

        }
        else {
            image = new File(BASE_PATH, newFilename);
            try {
                file.transferTo(image);
            } catch (IOException e) {
                return null;
            }
            return newFilename;

        }

    }
    public static boolean containsChineseCharacters(String str) {
        if (str == null) {
            return false;
        }
        return str.chars().anyMatch(c -> isChineseCharacter((char) c));
    }
    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

}
