package com.toker.project.douyin.basis.response.user;

import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.PageResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取关注列表返回结果
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 12:10
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowingListRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -1684294367540209092L;

    private GetFollowingListResData data;

    @Override
    public ResponseExtra getExtra() {
        return data.getExtra();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class GetFollowingListResData extends PageResponse {

        private static final long serialVersionUID = 3390807945752024623L;

        private ResponseExtra extra;

        private List<FansListRes.UserInfo> list;

    }
}
