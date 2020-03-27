package com.whaa.cloudalibaba.controller;

import com.whaa.cloudalibaba.bean.Stock;
import com.whaa.cloudalibaba.mapper.StockMapper;
import com.whaa.common.constants.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * created by @author wangzelong 2020/3/25 16:57
 */
@RestController
public class StockController {
    @Autowired
    private StockMapper stockMapper;

    /**
     * 扣减库存
     *
     * @param productId
     * @param num
     * @return
     */
    @PostMapping("/subStock")
    public ResponseData subStock(Integer productId, Integer num) {
        Stock stock = stockMapper.selectById(productId);
        //模拟延时
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        stock.setUseNumber(num);
        stock.setLaveNumber(stock.getLaveNumber() - num);
        stockMapper.updateById(stock);
        return ResponseData.success();
    }
}
