package com.toker.project.api.controller;


import com.toker.common.utils.SecurityUtils;
import com.toker.framework.security.LoginBody;
import com.toker.framework.security.LoginUser;
import com.toker.framework.security.service.SysLoginService;
import com.toker.framework.security.service.TokenService;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.project.system.domain.SysUser;
import com.toker.project.system.service.ISysUserService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getWechatOpenid")
    public AjaxResult getWechatOpenid(@RequestParam String code) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openid = wxMpOAuth2AccessToken.getOpenId();
            return AjaxResult.success(openid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    @ResponseBody
    @PostMapping("/wxBindLogin")
    public AjaxResult wxBindLogin(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = null;
        try{
            // 生成令牌
            token = loginService.login(loginBody.getUsername(),loginBody.getPassword(),"1","1");
            ajax.put("data", token);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return ajax;
    }

    @GetMapping("/wxLogin")
    public AjaxResult wxLogin(@RequestParam String code,ModelMap map) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openid = wxMpOAuth2AccessToken.getOpenId();
            System.out.println(openid);
            SysUser user = userService.selectUserByOpenid(openid);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(user);

            return AjaxResult.success(tokenService.createToken(loginUser));

        }catch (Exception e){
            e.printStackTrace();
        }

        return AjaxResult.error();
    }

    @ResponseBody
    @GetMapping("/jsTicket")
    public AjaxResult jsTicket(@RequestParam String url) {
        try { // 直接调用wxMpServer 接口
            System.out.println(url);
            WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
            return AjaxResult.success(wxJsapiSignature);
        } catch (WxErrorException e) {
            return null;
        }
    }

}
