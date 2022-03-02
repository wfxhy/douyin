package com.toker.project.douyin.basis.response.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.PageResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询授权账号视频数据返回数据
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 13:39
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoListRes implements DyOpenApiResponse {

    private ResponseExtra extra;

    private VideoListResData data;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class VideoListResData extends PageResponse {

        /**
         * 由于置顶的原因, list长度可能比count指定的数量多一些或少一些。
         */
        private List<VideoDetail> list;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoDetail {

        /**
         * 视频标题
         */
        private String title;

        /**
         * 表示视频状态。1:已发布;2:不适宜公开;4:审核中
         */
        @JSONField(name = "video_status")
        private int videoStatus;

        /**
         * 视频封面
         */
        private String cover;

        /**
         * 视频创建时间戳
         */
        @JSONField(name = "create_time")
        private long createTime;

        /**
         * 是否置顶
         */
        @JSONField(name = "is_top")
        private boolean isTop;

        /**
         * 视频id
         */
        @JSONField(name = "item_id")
        private String itemId;

        /**
         * 表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。
         */
        @JSONField(name = "is_reviewed")
        private boolean isReviewed;

        /**
         * 视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。
         */
        @JSONField(name = "share_url")
        private String shareUrl;

        /**
         * 统计数据
         */
        private VideoStatistics statistics;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoStatistics {

        /**
         * 点赞数
         */
        @JSONField(name = "digg_count")
        private int diggCount;

        /**
         * 下载数
         */
        @JSONField(name = "download_count")
        private int downloadCount;

        /**
         * 转发数
         */
        @JSONField(name = "forward_count")
        private int forwardCount;

        /**
         * 播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0。
         */
        @JSONField(name = "play_count")
        private int playCount;

        /**
         * 分享数
         */
        @JSONField(name = "share_count")
        private int shareCount;

        /**
         * 评论数
         */
        @JSONField(name = "commentCount")
        private int commentCount;

    }
}
