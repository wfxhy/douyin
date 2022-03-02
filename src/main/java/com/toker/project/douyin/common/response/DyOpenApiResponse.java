package com.toker.project.douyin.common.response;

import java.io.Serializable;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 11:10
 * @modified mdmbct
 * @since 1.0
 */
public interface DyOpenApiResponse extends Serializable {

    /**
     * 获取抖音api返回的data字段对应的数据
     * @return 抖音api返回的data字段对应的数据
     */
    DefaultResponseData getData();

    /**
     * 获取抖音api返回的extra字段对应的数据
     * @return 抖音api返回的extra字段对应的数据
     */
    ResponseExtra getExtra();

    /**
     * 获取返回api返回失败或者错误时的信息
     *
     * @return api返回失败或者错误时的信息
     */
    default String getErrorMsg() {
        String dataErrorMsg = "";
        String extraErrorMsg = "";
        String subExtraErrorMsg = "";
        if (getData() != null) {
            dataErrorMsg = getData().getDescription();
        }
        if (getExtra() != null) {
            extraErrorMsg = getExtra().getDescription();
            subExtraErrorMsg = getExtra().getSubDescription();
        }

        if (dataErrorMsg.equals(extraErrorMsg)) {
            return dataErrorMsg;
        }

        String errorMsg = dataErrorMsg + " " + extraErrorMsg + " " + subExtraErrorMsg;
        return errorMsg.trim();
    }

}
