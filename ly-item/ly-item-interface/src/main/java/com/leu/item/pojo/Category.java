package com.leu.item.pojo;

import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName: Category
 * @Author: Bolon
 * @Date: 2019/12/7 21:36
 * @Description: 商品类目表
 */
@Data
@Table(name="tb_category")
public class Category {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
}
