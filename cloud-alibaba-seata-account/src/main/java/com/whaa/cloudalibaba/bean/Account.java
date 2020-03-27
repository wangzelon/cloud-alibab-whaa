package com.whaa.cloudalibaba.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * created by @author wangzelong 2020/3/25 16:48
 */
@Data
@TableName("account")
public class Account {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private BigDecimal money;

    private BigDecimal laveMoney;

    private BigDecimal useMoney;

}
