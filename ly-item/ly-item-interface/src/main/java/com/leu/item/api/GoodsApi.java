package com.leu.item.api;

import com.leu.item.pojo.Sku;
import com.leu.item.pojo.SpuDetail;
import com.leu.item.pojo.bo.SpuBo;
import com.ly.common.pojo.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: GoodsApi
 * @Author: Bolon
 * @Date: 2020/1/8 21:51
 * @Description: 作为服务提供方提供调用接口
 */
public interface GoodsApi {
    /**
     * 功能描述: 分页查询商品列表
     * @param: [key, saleable, page, rows]
     * @return: com.ly.common.pojo.PageResult<com.leu.item.pojo.bo.SpuBo>
     * @author: Bolon
     * @date: 2019/12/21 17:00
     */
    @GetMapping("spu/page")
    PageResult<SpuBo> queryGoodsByPage(@RequestParam(value = "key", required = false) String key,
                                       @RequestParam(value = "saleable", required = false) Boolean saleable,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "rows", defaultValue = "5") Integer rows);
    /**
     * 功能描述: 根据spuId查询spuDetail
     * @param: [spuId]
     * @return: com.leu.item.pojo.SpuDetail
     * @author: Bolon
     * @date: 2019/12/23 19:12
     */
    @GetMapping("spu/detail/{spuId}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 功能描述: 根据SpuId查询Sku作为回显数据
     * @param: [id]
     * @return: java.util.List<com.leu.item.pojo.Sku>
     * @author: Bolon
     * @date: 2019/12/23 19:15
     */
    @GetMapping("sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("id") Long spuId);
}
