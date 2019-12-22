package com.leu.item.pojo.bo;

import com.leu.item.pojo.Sku;
import com.leu.item.pojo.Spu;
import com.leu.item.pojo.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SpuBo
 * @Author: Bolon
 * @Date: 2019/12/21 16:58
 * @Description: SpuBo
 */
@Data
public class SpuBo extends Spu {
    /**
     * 商品分类名称
     */
    private String cname;
    /**
     * 品牌名称
     */
    private String bname;
    /**
     * sku列表
     */
    private List<Sku> skus;

    private SpuDetail spuDetail;
}
