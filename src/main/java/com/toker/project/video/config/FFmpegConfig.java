package com.toker.project.video.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取ffmpeg项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ffmpeg")
public class FFmpegConfig {
    /**
     * ffmpeg程序路径
     */
    private static String exeAbsolutePath;

    /**
     * 生成临时文件的目录
     */
    private static String tempAbsolutePath;


    public static String getExeAbsolutePath() {
        return exeAbsolutePath;
    }

    public void setExeAbsolutePath(String exeAbsolutePath) {
        this.exeAbsolutePath = exeAbsolutePath;
    }

    public static String getTempAbsolutePath() {
        return tempAbsolutePath;
    }

    public void setTempAbsolutePath(String tempAbsolutePath) {
        this.tempAbsolutePath = tempAbsolutePath;
    }
}
