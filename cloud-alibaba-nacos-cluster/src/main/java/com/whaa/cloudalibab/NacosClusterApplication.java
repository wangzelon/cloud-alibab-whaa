package com.whaa.cloudalibab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * created by @author wangzelong 2020/3/24 16:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosClusterApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClusterApplication.class, args);
    }
}
