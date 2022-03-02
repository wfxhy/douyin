package com.toker.project.douyin.common.utils.file;

/**
 * 单个文件分块处理<p>
 * R 处理文件分块后的返回结果类型
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/22 13:25
 * @modified mdmbct
 * @since 1.0
 */
@FunctionalInterface
public interface FileChunkHandler<R> {

    /**
     * 处理单个文件分块
     * @param chunk 文件分块
     * @return 具体处理文件分块后的返回结果
     * @throws ChunkHandlerFailedException 文件分块处理失败抛出
     */
    R handleChunks(FileChunk chunk) throws ChunkHandlerFailedException;
}
