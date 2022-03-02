package com.toker.project.douyin.common.utils.file;

/**
 * 文件分块处理失败异常
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/25 11:17
 * @modified mdmbct
 * @since 1.0
 */
public class ChunkHandlerFailedException extends Exception {

    private static final long serialVersionUID = -2200606503779344803L;

    public ChunkHandlerFailedException() {
        super();
    }

    public ChunkHandlerFailedException(String message) {
        super(message);
    }
}
