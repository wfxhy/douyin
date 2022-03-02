package com.toker.project.douyin.common.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应返回中的extra字段
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 14:04
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExtra implements Serializable {

    private static final long serialVersionUID = 1136163261023684972L;
    /**
     * 子错误码描述
     */
    @JSONField(name = "sub_description")
    private String subDescription;

    /**
     * 子错误码
     */
    @JSONField(name = "sub_error_code")
    private long subErrorCode;

    /**
     * 错误码描述
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 错误码
     */
    @JSONField(name = "error_code")
    private long errorCode;

    /**
     * 标识请求的唯一id
     */
    @JSONField(name = "logid")
    private String logId;

    /**
     * 毫秒级时间戳
     */
    private long now;


}
