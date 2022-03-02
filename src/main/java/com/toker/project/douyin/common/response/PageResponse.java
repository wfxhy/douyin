package com.toker.project.douyin.common.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分请求返回
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 15:50
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageResponse extends DefaultResponseData {

    private static final long serialVersionUID = -7446522767453029121L;
    /**
     * 用于下一页请求的cursor
     */
    private long cursor;

    @JSONField(name = "has_more")
    private boolean hasMore;
}
