package com.whaa.cloudalibaba.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * created by @author wangzelong 2020/3/25 16:53
 */
@Data
@TableName
public class Stock {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer allNumber;

    private Integer useNumber;

    private Integer laveNumber;

}
