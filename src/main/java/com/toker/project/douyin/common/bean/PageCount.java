package com.toker.project.douyin.common.bean;

import com.toker.project.douyin.common.exception.InvalidRequestParamException;

/**
 * 分页时 每页显示数量 抖音api最多20
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 10:52
 * @modified mdmbct
 * @since 1.0
 */
public class PageCount {

    private final int value;

    private static final int MAX_COUNT = 20;

    public PageCount(int value) {
        if (value <= 0 || value > MAX_COUNT) {
            throw new InvalidRequestParamException("分页的count参数在1~20之间(包含边界)");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
