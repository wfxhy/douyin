package com.toker.project.video.m3u8;

import com.toker.project.video.utils.VideoUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiYaQ
 * @description:
 * @date: Created in 2019/5/31 11:19
 * @modified By:
 */
public class M3u8VideoDownload {

    private static final Logger log = LoggerFactory.getLogger(M3u8VideoDownload.class);


    public static boolean singleThread(String urlStr, String pathname) {
        try {
            if (null == urlStr || "".equals(urlStr)) {
                return false;
            }
            M3U8 m3u8ByURL = getM3U8ByURL(urlStr);
            String basePath = m3u8ByURL.getBasepath();
            List<M3U8.Ts> list = m3u8ByURL.getTsList();
            List<String> files = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                try {
                    M3U8.Ts m3U8Ts = list.get(i);
                    String pathname_temp = pathname.replace(".mp4", "_" + i + ".mp4");
                    System.out.println("down==" + basePath + m3U8Ts.getFile());
                    long len = singleThread3(basePath + m3U8Ts.getFile(), pathname_temp);
                    if (len > 0) {
                        files.add(pathname_temp);
                    } else {
                        System.out.println("下载失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            boolean b = VideoUtil.mergeVideo(pathname, files);
            for (String s : files) {
                deleteFile(s);
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static M3U8 getM3U8ByURL(String m3u8URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
            if (conn.getResponseCode() == 200) {
                String realUrl = conn.getURL().toString();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
                System.out.println("basepath==" + basepath);
                M3U8 ret = new M3U8();
                ret.setBasepath(basepath);

                String line;
                float seconds = 0;
                int mIndex;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#EXTINF:")) {
                            line = line.substring(8);
                            if ((mIndex = line.indexOf(",")) != -1) {
                                line = line.substring(0, mIndex + 1);
                            }
                            try {
                                seconds = Float.parseFloat(line);
                            } catch (Exception e) {
                                seconds = 0;
                            }
                        }
                        continue;
                    }
                    if (line.endsWith("m3u8")) {
                        return getM3U8ByURL(basepath + line);
                    }
                    System.out.println("line=" + line);
                    ret.addTs(new M3U8.Ts(line, seconds));
                    seconds = 0;
                }
                reader.close();

                return ret;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        try {
            if (null == fileName) {
                log.info("删除的单个文件不存在== " + fileName);
                return false;
            }
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("删除单个文件" + fileName + "成功！");
                    return true;
                } else {
                    log.info("删除单个文件" + fileName + "失败！");
                    return false;
                }
            } else {
                log.info("删除单个文件失败：" + fileName + "不存在！");
                return false;
            }
        } catch (Exception e) {
            log.error("删除单个文件失败== " + fileName, e);
        }
        return false;
    }

    private static long singleThread3(String urlStr, String pathname) throws Exception {
        if (null == urlStr || !urlStr.startsWith("http") || null == pathname) {
            log.info("下载视频的参数有问题 urlStr=" + urlStr + " , pathname=" + pathname);
            return 0;
        }
        File file = new File(pathname);
        // 创建Httpclient对象
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(20 * 60 * 1000) //数据传输的最长时间
                .setConnectTimeout(60000) // 设置连接超时时间
                //.setConnectionRequestTimeout(5000) // 设置连接请求最长时间
                .setStaleConnectionCheckEnabled(false) // 提交请求前测试连接是否可用
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(urlStr);
        httpGet.setHeader("Accept", "*/*");
        //httpGet.setHeader("Referer", "https://www.bilibili.com/video/av38477555");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
        RequestConfig.Builder builder = RequestConfig.copy(defaultRequestConfig);
//        if (ifProxy) {
//            builder.setProxy(new HttpHost(Constant.PROXY_IP, Constant.PROXY_PORT));
//        }
        RequestConfig requestConfig = builder.build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity en = response.getEntity();
                InputStream inputStream = en.getContent();
                long length = en.getContentLength();//这个就是下载的文件（不单指文件）大小
                //ConsoleProgressBar cpb = new ConsoleProgressBar(0, length, 20, '=');
                FileOutputStream fos = new FileOutputStream(file);
                byte[] temp = new byte[2048];
                int len;
                //int count = 0;
                while ((len = inputStream.read(temp)) != -1) {
                    fos.write(temp, 0, len);
                    //count = count + len;
                    //cpb.show(count);
                }
                fos.flush();
                fos.close();
                inputStream.close();
                log.info("文件" + pathname + " file is exists " + file.exists());
                if (file.exists()) {
                    return length;
                }
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
        return 0;
    }

}
