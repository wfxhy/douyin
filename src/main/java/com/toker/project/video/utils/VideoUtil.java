package com.toker.project.video.utils;

import com.toker.project.video.ffmpeg.Encoder;
import com.toker.project.video.ffmpeg.MultimediaInfo;
import com.toker.project.video.ffmpeg.Encoder;
import com.toker.project.video.ffmpeg.MultimediaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: LiYaQ
 * @description:
 * @date: Created in 2019/3/10 11:45
 * @modified By:
 */
public class VideoUtil {

    private static final Logger log = LoggerFactory.getLogger(VideoUtil.class);
    //过渡效果：38种
    private static String[] xfades = {"fade", "slideleft", "slideright", "slideup", "slidedown",
            "circlecrop", "rectcrop", "distance", "fadeblack", "fadewhite", "radial",
            "smoothleft", "smoothright", "smoothup", "smoothdown", "circleopen"
            , "circleclose", "vertopen", "vertclose", "horzopen", "horzclose", "dissolve"
            , "pixelize", "diagtl", "diagtr", "diagbl", "diagbr", "hlslice", "hrslice"
            , "vuslice", "vdslice", "hblur", "fadegrays", "wipetl", "wipetr", "wipebl", "wipebr", "squeezeh"};


    public static void main(String[] args) {
        //System.out.println(compressVideo("E:\\data\\2.mp4","E:\\data\\1.mp4"));
        //System.out.println(videoScreenshot("http://c7c6ea5c95c6a.cdn.sohucs.com/shortvideo/20d8bbda-0dff-4475-8217-e60d55e04650.mp4", "2.00"));
    /*    System.out.println("开始=====");
        long start = System.currentTimeMillis();
        MultimediaInfo info = getVideoInfoByUrl("http://c7c6ea5c95c6a.cdn.sohucs.com/shortvideo/20d8bbda-0dff-4475-8217-e60d55e04650.mp4", null, 3,false);
        long end = System.currentTimeMillis();
        System.out.println("耗时=" + (end - start));
        System.out.println(info.getDuration());
        System.out.println(info.getVideo().getSize().getWidth());
        System.out.println(info.getVideo().getSize().getHeight());*/

        String output = "D:\\test\\1.mp4";
//String input1 = "D:\\ruoyi\\uploadPath\\upload\\2021\\03\\28\\8a261ccc-45e0-4c3a-82ad-67fa9d1c1e5d.mp4";
//String input2 = "E:\\抖音\\d13f389c3d313da95920e7d5bfe4b93b.mp4";
        String input3 = "E:\\抖音\\src=http___awb.img.xmtbang.com_img_uploadnew_201412_17_c5619402240e4c569a6b14a12d3dc486.jpg&refer=http___awb.img.xmtbang.jpg";
        List list = new ArrayList();
//       list.add(input1);
//        list.add(input2);
        list.add(input3);
        mergeVideo(output, list);

    }

    /**
     * 通过视频文件获取视频信息
     *
     * @param videoPath
     * @return
     */
    public static MultimediaInfo getVideoInfoByFile(String videoPath) {
        try {
            File file = new File(videoPath);
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfoByFile(file);
            if (null != m) {
                m.setVideoSize(file.length());
            }
            return m;
        } catch (Exception e) {
            log.error("获取播放播放时长异常 videoPath=" + videoPath, e);
        }
        return null;
    }

    /**
     * 通过视频地址获取视频信息
     *
     * @param videoUrl
     * @return
     */
    public static MultimediaInfo getVideoInfoByUrl(String videoUrl, String ua, int timeout, boolean ifProxy) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfoByUrl(videoUrl, ua, timeout, ifProxy);
            long end = System.currentTimeMillis();
            log.info("获取视频宽高时长,duration={}; 耗时={}", m.getDuration(), (end - start));
            return m;
        } catch (Exception e) {
            log.error("获取视频信息异常 videoUrl=" + videoUrl, e);
        }
        return null;
    }

    /**
     * 下载m3u8视频
     *
     * @param url    m3u8播放地址
     * @param output 视频输出路径
     * @return
     */
    public static boolean downloadAndMergeM3U8Video(String url, String output) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.mergeM3U8Video(url, output);
            long end = System.currentTimeMillis();
            log.info("url={} output={} m3u8视频耗时={}", url, output, (end - start));
            return b;
        } catch (Exception e) {
            log.error("合并视频异常 url={} output={}", url, output, e);
        }
        return false;
    }

    /**
     * 合并视频
     *
     * @param output 视频的输出位置
     * @param input  分段视频
     * @return
     */
    public static boolean mergeVideo(String output, List<String> input) {
        try {
            if (null == output || null == input) {
                return false;
            }
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.mergeVideo(output, input.toArray(new String[input.size()]));
            long end = System.currentTimeMillis();
            log.info("input={} output={} 合并视频耗时={}", input, output, (end - start));
            return b;
        } catch (Exception e) {
            log.error("合并视频异常 input=" + input + " output" + output, e);
        }
        return false;
    }

    /**
     * 合并视频
     *
     * @param output 视频的输出位置
     * @param input  分段视频
     * @return
     */
    public static boolean mergeVideo(String output, List<String> input, boolean xfade, String audioPath) {
        try {
            if (null == output || null == input) {
                return false;
            }
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();

            boolean b = false;
            if (xfade) {
                b = encoder.mergeVideo(output, input, xfades, audioPath);
            } else {
                b = mergeVideo(output, input);
            }
            long end = System.currentTimeMillis();
            log.info("input={} output={} 合并视频耗时={}", input, output, (end - start));
            return b;
        } catch (Exception e) {
            log.error("合并视频异常 input=" + input + " output" + output, e);
        }
        return false;
    }

    /**
     * 截封面图
     *
     * @param input     视频文件或地址
     * @param time      截图的固定时间点
     * @param imgOutPut 图片的输出路径
     * @return 是否成功
     */
    public static boolean videoScreenshot(String input, String time, String imgOutPut) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            String imgPath = null;
            if (null == imgOutPut) {
                File temp = new File(System.getProperty("user.dir"), "work");
                if (!temp.exists()) {
                    temp.mkdirs();
                }
                imgPath = temp.getAbsolutePath() + File.separator + UUID.randomUUID().toString() + ".png";
            } else {
                imgPath = imgOutPut;
            }
            boolean b = encoder.videoScreenshot(input, time, imgPath);
            long end = System.currentTimeMillis();
            log.info("input={} imgPath={} 截图耗时={}", input, imgOutPut, (end - start));
            return b;
        } catch (Exception e) {
            log.error("视频截图异常 time=" + time + " output" + input, e);
        }
        return false;
    }

    /**
     * 压缩视频
     *
     * @param output
     * @param input
     * @return
     */
    public static boolean compressVideo(String output, String input) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.compressVideo(output, input);
            long end = System.currentTimeMillis();
            log.info("input=" + input + " output=" + output + "压缩视频耗时=" + (end - start));
            return b;
        } catch (Exception e) {
            log.error("压缩视频异常 input=" + input + " output" + output, e);
        }
        return false;
    }

    /**
     * 获取当前时间，用于作为文件名
     */
    public static String nowTime() {
        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS");
        LocalDate nowdata = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        return nowdata.atTime(nowTime).format(f3);
    }

}

