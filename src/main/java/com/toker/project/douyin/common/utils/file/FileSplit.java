package com.toker.project.douyin.common.utils.file;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件分块
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/15 15:58
 * @modified mdmbct
 * @since 1.0
 */
@Getter
public class FileSplit {

    private final BufferedInputStream bis;

    private final FileInputStream fis;
    /**
     * 分块大小
     */
    @Getter
    private final int chunkSize;
    /**
     * 分块数量
     */
    @Getter
    private final int chunkCount;

    /**
     * 一组分块中分块的数量（每次返回的分块数）<p>
     * 该组分块整体提交给{@link FileChunkGroupHandler}处理
     * <p>
     * 全部返回会占用等于文件大小的内存
     */
    @Getter
    private final int groupChunkCount;

    /**
     * 当前输出到list中分块的下标
     */
    private int curChunkIndex = 0;

    /**
     *
     * @param file 要被分块的文件
     * @param defaultChunkSize 默认分块大小
     * @param minChunkSize 最小的块大小 如果最后一块大小小于该值 将重新分块 每块的大小为默认块大小-最后一块大小的1/2
     * @param groupChunkCount 每次返回的分块数 全部返回会占用等于文件大小的内存
     * @throws FileNotFoundException 要被分块的文件不存在抛出
     */
    public FileSplit(File file, int defaultChunkSize, int minChunkSize, int groupChunkCount) throws FileNotFoundException {

        long size = file.length();

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);

        int chunkSize = defaultChunkSize;
        // 分快数 直接扔掉小数部分
        int chunkCount = (int) (size / chunkSize);
        // 检查最后一块大小是否比最小分块小
        int lastChunkSize = (int) (size - chunkSize * chunkCount);
        while (lastChunkSize < minChunkSize && chunkSize > minChunkSize) {
            // 最后一个分块大小小于最小分块 ====> 将分块的大小减小最后一个分块的1/2
            chunkSize -= lastChunkSize / 2;
            chunkCount = (int) (size / chunkSize);
            lastChunkSize = (int) (size - chunkSize * chunkCount);
        }
        // 加上最后一个较小的分块得到总分快数
        chunkCount++;

        this.chunkSize = chunkSize;
        this.chunkCount = chunkCount;
        this.groupChunkCount = Math.min(groupChunkCount, chunkCount);

    }

    /**
     * 文件分块
     * @param fileChunkGroupHandler 每次分块中的字节处理函数
     * @return 大小为everyReturnChunkCount的保存字节处理函数返回结果的list
     * @exception IOException 读取异常时返回一个空的list
     */
    public <R> List<R> split(FileChunkGroupHandler<R> fileChunkGroupHandler) {

        List<R> rs = new ArrayList<>();
        // 存储分片数据的list
        List<FileChunk> chunks = new ArrayList<>(groupChunkCount);
        try {
            for (int i = 0; i < chunkCount; i++) {
                byte[] buffer = new byte[chunkSize];
                int r = bis.read(buffer, 0, chunkSize);
                curChunkIndex++;
                chunks.add(new FileChunk(curChunkIndex, buffer));
                if (r == -1 || chunks.size() == groupChunkCount) {
                    List<R> rs1 = fileChunkGroupHandler.handleChunks(chunks);
                    rs.addAll(rs1);
                    chunks.clear();
                }
            }
            return rs;
        } catch (IOException e) {
            e.printStackTrace();
            finishedSplit();
        } catch (ChunkHandlerFailedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 完成分片后一定需要调用
     */
    public void finishedSplit() {
        try {
            fis.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
