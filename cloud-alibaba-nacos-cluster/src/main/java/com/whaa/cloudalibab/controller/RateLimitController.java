package com.whaa.cloudalibab.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.whaa.cloudalibab.CustomBlockHandler;
import com.whaa.common.constants.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by @author wangzelong 2020/3/25 15:36
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "byResourceHandler")
    public ResponseData byResource() {

        return ResponseData.success("成功");
    }

    public ResponseData byResourceHandler(BlockException blockException) {
        return ResponseData.fail(blockException.getClass().getCanonicalName() + "资源名称失败");
    }

    @GetMapping("/byResource1")
    @SentinelResource(value = "byResource1", blockHandler = "handler1", blockHandlerClass = {CustomBlockHandler.class})
    public ResponseData byResource1() {
        return ResponseData.success("byResource1成功");
    }
}
