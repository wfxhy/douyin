package com.toker.project.douyin.common.utils.file;

import java.util.List;

/**
 * 处理一组分块(多个分块集合)的函数式接口<p>
 * <code>R</code> 处理一个分块后的返回值
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/15 17:29
 * @modified mdmbct
 * @since 1.0
 */
@FunctionalInterface
public interface FileChunkGroupHandler<R> {

    /**
     * 处理文件分块
     * @param chunkGroup 一组分块即 保存字节信息的几个分块
     * @return 理分块后的返回值
     * @throws ChunkHandlerFailedException 文件分块处理失败抛出
     */
    List<R> handleChunks(List<FileChunk> chunkGroup) throws ChunkHandlerFailedException;

}
