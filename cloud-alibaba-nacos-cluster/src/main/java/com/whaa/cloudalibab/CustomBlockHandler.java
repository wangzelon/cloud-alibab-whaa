package com.whaa.cloudalibab;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.whaa.common.constants.ResponseData;

/**
 * 全局限流处理类
 * created by @author wangzelong 2020/3/25 15:53
 */
public class CustomBlockHandler {

    public static ResponseData handler1(BlockException blockException) {

        return ResponseData.fail("handler1-->");
    }

    public static ResponseData handler2(BlockException blockException) {
        return ResponseData.fail("handler2-->");
    }

}
