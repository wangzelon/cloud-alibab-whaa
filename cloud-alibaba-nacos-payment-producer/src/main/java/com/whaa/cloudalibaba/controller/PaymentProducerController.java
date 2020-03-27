package com.whaa.cloudalibaba.controller;

import com.whaa.common.constants.ResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by @author wangzelong 2020/3/23 15:25
 */
@RestController
public class PaymentProducerController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/echo/{string}")
    public ResponseData echo(@PathVariable("string") String string) {
        return ResponseData.success(string + "port:" + serverPort);
    }

}
