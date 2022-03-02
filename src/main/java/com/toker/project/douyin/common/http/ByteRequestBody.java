package com.toker.project.douyin.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 二进制类型的请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/12 16:24
 * @modified mdmbct
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class ByteRequestBody {

    private final String name;

    private final String fileName;

    private final byte[] bytes;
}
