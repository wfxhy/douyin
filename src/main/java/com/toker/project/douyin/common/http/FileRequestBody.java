package com.toker.project.douyin.common.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

/**
 * 文件类型的请求体
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/11 14:28
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class FileRequestBody {


    private final String name;

    private final String fileName;

    private final String mediaType;

    private final File file;


}
