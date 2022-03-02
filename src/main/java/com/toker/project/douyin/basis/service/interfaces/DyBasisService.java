package com.toker.project.douyin.basis.service.interfaces;

import com.toker.project.douyin.common.service.DyService;

/**
 * 基础能力权限接口
 * <p>
 * 包括账号授权、用户管理、视频管理、互动管理、搜索管理、数据开放服务
 * <p>
 * 添加新的service应该在该接口中添加相应的get方法<p>
 * 目前有:<p>
 * {@link VideoManagerService} <p>
 * {@link Auth2Service} <p>
 * {@link UserManagerService} <p>
 * {@link ToolAbilityService} <p>
 * {@link InteractionManagerService} <p>
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 10:42
 * @modified mdmbct
 * @since 1.0
 */
public interface DyBasisService extends DyService {



    VideoManagerService getVideoService();

    Auth2Service getAuth2Service();

    UserManagerService getUserManagerService();

    SearchManagerService getSearchManagerService();

    ToolAbilityService getToolAbilityService();
}
