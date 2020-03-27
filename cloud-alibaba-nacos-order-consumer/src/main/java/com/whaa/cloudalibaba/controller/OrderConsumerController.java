package com.whaa.cloudalibaba.controller;

import com.whaa.common.constants.ResponseData;
import com.whaa.common.service.PaymentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * created by @author wangzelong 2020/3/23 15:42
 */
@RestController
@RefreshScope
public class OrderConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;
    @Reference
    private PaymentService paymentService;

    private static final String URL = "http://nacos-payment-producer";
//    @Value("${config.info}")
//    private String configInfo;

//    @GetMapping("/consumer/echo/{name}")
//    public ResponseData echoApplicationName(@PathVariable("name") String name) {
//        return restTemplate.getForObject(URL + "/echo/" + name, ResponseData.class);
//    }

    @GetMapping("/consumer/config")
    public ResponseData getConfigInfo() {
        String lijie = paymentService.findByString("lijie");
        return ResponseData.fail(lijie);
    }
}
