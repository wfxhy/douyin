package com.toker.project.douyin.common.utils.file;

import java.io.*;

/**
 * 文件有关的工具类
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/12 9:10
 * @modified mdmbct
 * @since 1.0
 */
public class FileUtils {


    /**
     * 获取文件扩展名
     *
     * @param fileName  文件名
     * @param withPoint 是否带.
     * @return 文件扩展名
     */
    public static String getFileExtension(String fileName, boolean withPoint) {

        int i = fileName.lastIndexOf('.');
        if (i < 0) {
            return null;
        }
        if (withPoint) {
            return fileName.substring(i);
        }
        return fileName.substring(i + 1);

    }

    public static <T> void writeObject(String path, T t) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t);
    }

    public static <T> T readObjet(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (T) ois.readObject();
    }

}
