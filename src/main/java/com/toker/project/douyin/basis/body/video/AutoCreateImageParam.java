package com.toker.project.douyin.basis.body.video;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自动创建图片参数
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 18:10
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoCreateImageParam implements Serializable {

    private static final long serialVersionUID = -3557735852422848734L;

    @JSONField(name = "micro_app_title")
    private String microAppTitle;

    @JSONField(name = "micro_app_url")
    private String microAppUrl;

    @JSONField(name = "poi_id")
    private String poiId;

    @JSONField(name = "poi_name")
    private String poiName;

    private String text;

    @JSONField(name = "at_users")
    private String[] atUsers;

    @JSONField(name = "micro_app_id")
    private String microAppId;
}
