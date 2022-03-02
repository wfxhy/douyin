package com.toker.project.douyin.basis.body.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建图片请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 14:26
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageCreateBody implements DyOpenApiRequestBody {

    private static final long serialVersionUID = -2909423516165696164L;

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

    @JSONField(name = "image_id")
    private String imageId;

    @JSONField(name = "micro_app_id")
    private String microAppId;

    public static ImageCreateBody build(AutoCreateImageParam param) {
        return ImageCreateBody.builder()
                .microAppId(param.getMicroAppId())
                .poiId(param.getPoiId())
                .poiName(param.getPoiName())
                .atUsers(param.getAtUsers())
                .text(param.getText())
                .microAppTitle(param.getMicroAppTitle())
                .microAppUrl(param.getMicroAppUrl())
                .build();
    }

}
