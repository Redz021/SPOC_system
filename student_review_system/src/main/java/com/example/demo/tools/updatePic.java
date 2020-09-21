package com.example.demo.tools;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 图片接收并存储（工具类）
 */
@Component
@Data
public class updatePic {
    //将图片写入到文件
    public boolean writeIn(String targetFilePath,byte []bytes)
    {
        //优先判空
        if(bytes==null||bytes.length==0)
        {
            return false;
        }
        Path path = Paths.get(targetFilePath);
        if(!Files.exists(path))
        {
            //文件若不存在，则创建文件
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(path,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
