package com.whaa.cloudalibab.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.whaa.common.constants.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * created by @author wangzelong 2020/3/24 16:59
 */
@RestController
@Slf4j
public class PaymentController {

    @GetMapping("/payment/payA")
    public ResponseData payA() {
        log.info(Thread.currentThread().getName() + "\t payA");
        return ResponseData.success("/payment/payA");
    }

    @GetMapping("/payment/payB")
    public ResponseData payB() {
        return ResponseData.success("/payment/payB");
    }

    @GetMapping("/payment/payC")
    public ResponseData payC() {
//        try {
////            TimeUnit.SECONDS.sleep(1);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }

        log.info("测试异常比例");
        int age = 10 / 0;
        return ResponseData.success("/payment/payC");
    }

    @GetMapping("/payment/payD")
    public ResponseData payD() {
        log.info("测试异常数");
        int age = 10 / 0;
        return ResponseData.success("/payment/payD");
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "blockHandler")
    public ResponseData payment(String p1, String p2) {
        log.info("p1" + p1);
        log.info("p2" + p2);
        return ResponseData.success();
    }

    public ResponseData blockHandler(String p1, String p2, BlockException blockException) {

        return ResponseData.fail("失败了(灬ꈍ ꈍ灬)");
    }
}
