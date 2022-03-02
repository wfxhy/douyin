package com.toker.project.douyin.common.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应中的data字段
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 14:08
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponseData implements Serializable {

    private static final long serialVersionUID = 6014153035833759252L;
    /**
     * 错误码描述
     */
    private String description;

    /**
     * 错误码
     */
    @JSONField(name = "error_code")
    private int errorCode;

}
