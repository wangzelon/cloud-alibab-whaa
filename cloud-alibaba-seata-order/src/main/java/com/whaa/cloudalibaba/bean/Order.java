package com.whaa.cloudalibaba.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * created by @author wangzelong 2020/3/25 16:53
 */
@Data
@TableName("`order`")
public class Order {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer productId;

    private Integer accountId;

    private Integer buyNumber;

    private BigDecimal totalPrice;

    private Integer orderStatus;

}
