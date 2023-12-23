package com.example.backend.pojo.Vo;

import java.io.File;
import java.io.IOException;

public class a {
    public static void main(String[] args) {
        File rootFolder = new File("root");
        rootFolder.mkdir();

        // 循环创建编号为1-150的文件夹
        for (int i = 1; i <= 150; i++) {
            // 创建编号为i的文件夹
            File folder = new File(rootFolder, String.valueOf(i));
            folder.mkdir();

            // 在编号为i的文件夹中创建0、1、2文件夹
            for (int j = 0; j <= 2; j++) {
                File subFolder = new File(folder, String.valueOf(j));
                subFolder.mkdir();

                // 在0文件夹中创建0.txt文件
                    File file = new File(subFolder, "0.txt");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                
            }
        }
    }
}
