package com.toker.project.douyin.common.config;

import lombok.Data;

import java.io.Serializable;

/**
 * 抖音open api配置 用于保存在内存或者Redis中
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 15:35
 * @modified mdmbct
 * @since 1.0
 */
@Data
public class DyOpenApiConfig implements Serializable {

    /**
     * redis存储open api的key 默认"dy.api"
     */
    private String configCacheKey = "dy.open";

    /**
     * 是否多线程上传 默认false
     */
    private boolean multiUpload = false;

    /**
     * 多线程上传时 线程数  默认3 1 ~ 8 超过8会被设置成8 小于1会被设置成1
     */
    private int uploadThreadNum = 3;

    private AppConfig app;

    private HttpConfig http = HttpConfig.builder().build();


    public void setUploadThreadNum(int uploadThreadNum) {
        if (uploadThreadNum < 1) {
            this.uploadThreadNum = 1;
        } else {
            this.uploadThreadNum = Math.min(uploadThreadNum, 8);
        }
    }
}
