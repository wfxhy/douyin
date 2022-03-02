package com.toker.project.douyin.config;

import com.toker.framework.redis.RedisCache;
import com.toker.project.douyin.basis.service.DyBasisServiceImpl;
import com.toker.project.douyin.basis.service.interfaces.DyBasisService;
import com.toker.project.douyin.common.config.AppConfig;
import com.toker.project.douyin.common.config.DyOpenApiConfig;
import com.toker.project.douyin.common.config.HttpConfig;
import com.toker.project.douyin.common.enums.HttpClientType;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.http.okhttp.OkHttpExecutorFactory;
import com.toker.project.douyin.common.storage.DyStorageManager;
import com.toker.project.douyin.common.storage.RedisStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 抖音open api手动配置
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/25 8:56
 * @modified mdmbct
 * @since 1.0
 */
@Configuration
public class DyConfig {
    @Value("${douyin.appId}")
    private String appId;
    @Value("${douyin.appSecret}")
    private String appSecret;
    @Autowired
    private RedisCache redisCache;

    /**
     * RedisStorageManager
     */
//    @Bean
//    public DyStorageManager dyStorageManager(StringRedisTemplate redisTemplate) {
//        DyRedisOps dyRedisOps = new RedisTemplateOps(redisTemplate);
//        return new RedisStorageManager(dyRedisOps, getDyOpenApiConfig());
//    }

    /**
     * JedisStorageManager
     */
   @Bean
   public DyStorageManager dyStorageManager() {
        return new RedisStorageManager(redisCache, getDyOpenApiConfig());
   }

    @Bean
    public DyBasisService dyBasisService(DyStorageManager storageManager) {

        OkHttpExecutorFactory okHttpExecutorFactory = new OkHttpExecutorFactory();
        HttpExecutor httpExecutor = okHttpExecutorFactory.createHttpExecutor(storageManager.getOpenApiConfig().getHttp());

        return new DyBasisServiceImpl(storageManager, httpExecutor);
    }

    private DyOpenApiConfig getDyOpenApiConfig() {
        DyOpenApiConfig apiConfig = new DyOpenApiConfig();
        apiConfig.setApp(
                AppConfig.builder()
                        .key(appId)
                        .secret(appSecret)
                        .build()
        );
        apiConfig.setHttp(
                HttpConfig.builder()
                        .type(HttpClientType.OK_HTTP)
                        .build()
        );
        return apiConfig;
    }

}
