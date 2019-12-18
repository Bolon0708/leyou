package com.leu.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bolon
 * 规格参数的分组表，每个商品分类下有多个规格参数组
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品分类id，一个分类下有多个规格组
     */
    private Long cid;

    /**
     * 规格组的名称
     */
    private String name;
}