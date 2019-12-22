package com.leu.item.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bolon
 */
@Data
@Table(name = "tb_sku")
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long spuId;

    private String title;

    private String images;

    private Long price;

    private String indexes;

    private String ownSpec;

    private Boolean enable;

    private Date createTime;

    private Date lastUpdateTime;
    /**
     * 库存数量
     */
    @Transient
    private Integer stock;
}