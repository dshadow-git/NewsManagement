package com.training.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static boolean saveFile(String filePath, byte[] bytes){
        File file = new File(filePath);  //创建文件对象
        try {
            if (!file.exists()) {				//如果文件不存在则新建文件
                file.mkdirs();
            }
            FileOutputStream output = new FileOutputStream(file);
            output.write(bytes);				//将数组的信息写入文件中
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
