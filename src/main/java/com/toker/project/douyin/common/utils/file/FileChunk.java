package com.toker.project.douyin.common.utils.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * File Chunk Info
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/22 13:28
 * @modified mdmbct
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class FileChunk {

    /**
     * 分块序号
     */
    private final int chunkNum;

    /**
     * 该分块的字节数组
     */
    private final byte[] bytes;
}
