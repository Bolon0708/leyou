package com.leu.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Stock
 * @Author: Bolon
 * @Date: 2019/12/22 22:44
 * @Description: 库存表，代表库存，秒杀库存等信息
 */
@Data
@Table(name = "tb_stock")
public class Stock {
    /**
     * 库存对应的商品sku id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skuId;

    /**
     * 可秒杀库存
     */
    private Integer seckillStock;

    /**
     * 秒杀总数量
     */
    private Integer seckillTotal;

    /**
     * 库存数量
     */
    private Integer stock;
}
