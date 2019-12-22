package com.leu.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpuDetail
 * @Author: Bolon
 * @Date: 2019/12/22 22:49
 * @Description: TODO
 */
@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spuId;

    private String genericSpec;

    private String specialSpec;

    private String packingList;

    private String afterService;

    private String description;
}
