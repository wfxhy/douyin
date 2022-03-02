package com.toker.project.douyin.common.utils.file;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程文件块处理
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/22 13:13
 * @modified mdmbct
 * @since 1.0
 */
public class MultiThreadFileChunkGroupHandler<R> implements FileChunkGroupHandler<R> {

//    private final int corePoolSize;

    private final FileChunkHandler<R> chunkHandler;

    private final ThreadPoolExecutor executor;

    private final List<Future<R>> futures;

    /**
     * @param coreChunkHandlerThreadCount 处理文件分块的基本线程数
     * @param maxChunkHandlerThreadCount  处理文件分块的最大线程数
     * @param chunkHandler                单个文件分块处理
     */
    public MultiThreadFileChunkGroupHandler(int coreChunkHandlerThreadCount, int maxChunkHandlerThreadCount, FileChunkHandler<R> chunkHandler) {
//        this.corePoolSize = coreChunkHandlerThreadCount;
        this.chunkHandler = chunkHandler;
        this.futures = new ArrayList<>(coreChunkHandlerThreadCount);
        this.executor = new ThreadPoolExecutor(coreChunkHandlerThreadCount, maxChunkHandlerThreadCount, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new UploadThreadFactory());
    }

    @Override
    public List<R> handleChunks(List<FileChunk> chunkGroup) throws ChunkHandlerFailedException {

        List<R> rs = new ArrayList<>(chunkGroup.size());

        for (FileChunk fileChunk : chunkGroup) {
            futures.add(
                    executor.submit(() -> chunkHandler.handleChunks(fileChunk))
            );
        }

        boolean undone = true;
        while (undone) {
            for (Future<R> future : futures) {
                undone &= !future.isDone();
            }
        }

        for (Future<R> future : futures) {

            try {
                rs.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                executor.shutdown();
                try {
                    executor.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                executor.shutdownNow();
                throw new ChunkHandlerFailedException(e.getMessage());
            }
        }

        futures.clear();
        return rs;
    }

    /**
     * 结束上传时调用
     */
    public void finishUpload() {
        executor.shutdown();
    }

    @NoArgsConstructor
    private static class UploadThreadFactory implements ThreadFactory {

        private String threadNamePrefix = "UploadThread-";

        private final AtomicInteger threadCount = new AtomicInteger();

        public UploadThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, getNextThreadName());
        }

        private String getNextThreadName() {
            return threadNamePrefix + threadCount.incrementAndGet();
        }
    }



}
