package com.toker.project.api.controller;


import com.toker.framework.web.domain.AjaxResult;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchant")
public class ApiMerchantsController {

    @Autowired
    private IMerchantsService merchantsService;

    /**
     * 查询商户信息
     */
    @GetMapping("/getMerchant")
    public AjaxResult getMerchant(Long id) {
        Merchants merchants = merchantsService.selectMerchantsById(id);
        return AjaxResult.success("查询商户信息成功", merchants);
    }

    /**
     * 查询商户抖音信息
     *
     * @return
     */
    @GetMapping("/getMerchantDouyinInfo")
    public AjaxResult getMerchantDouyinInfo(Long merchantId) {
        return AjaxResult.success("查询商户抖音信息成功", merchantsService.selectMerchantsById(merchantId));
    }
}
