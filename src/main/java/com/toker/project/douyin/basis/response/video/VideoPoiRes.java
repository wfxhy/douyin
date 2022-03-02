package com.toker.project.douyin.basis.response.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.PageResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询视频携带的地点信息返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 14:51
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPoiRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -4366605206998989663L;

    private ResponseExtra extra;

    private VideoPoiResData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoPoiResData extends PageResponse {

        private static final long serialVersionUID = -1030093864511079966L;

        private List<Poi> pois;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Poi implements Serializable {

        private static final long serialVersionUID = 5411187832495117542L;

        @JSONField(name = "country_code")
        private String countryCode;

        @JSONField(name = "poi_id")
        private String poiId;

        private String district;

        private String location;

        @JSONField(name = "poi_name")
        private String poiName;

        private String province;

        private String address;

        private String city;

        @JSONField(name = "city_code")
        private String cityCode;

        private String country;
    }
}
