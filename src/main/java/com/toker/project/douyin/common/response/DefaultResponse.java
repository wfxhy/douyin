package com.toker.project.douyin.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 默认响应结果
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 14:53
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse implements DyOpenApiResponse {

    private static final long serialVersionUID = 1380888951392784185L;

    private ResponseExtra extra;

    private DefaultResponseData data;
}
