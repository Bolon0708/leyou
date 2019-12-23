package com.ley.controller;

import com.leu.item.pojo.Sku;
import com.leu.item.pojo.SpuDetail;
import com.leu.item.pojo.bo.SpuBo;
import com.ley.service.GoodsService;
import com.ly.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: GoodsController
 * @Author: Bolon
 * @Date: 2019/12/21 16:53
 * @Description: GoodsController
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("spu/page")
    /**
     * 功能描述: 分页查询商品列表
     * @param: [key, saleable, page, rows]
     * @return: org.springframework.http.ResponseEntity<com.ly.common.pojo.PageResult<com.leu.item.pojo.bo.SpuBo>>
     * @author: Bolon
     * @date: 2019/12/21 17:00
     */
    public ResponseEntity<PageResult<SpuBo>> queryGoodsByPage(@RequestParam(value = "key", required = false) String key,
                                                              @RequestParam(value = "saleable", required = false) Boolean saleable,
                                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        PageResult<SpuBo> pageResult = goodsService.queryGoodsByPage(key, saleable, page, rows);
        if (CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 功能描述: 新增商品
     * @param: [spuBo]
     * @return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @author: Bolon
     * @date: 2019/12/22 22:52
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 功能描述: 根据spuId查询spuDetail
     * @param: [spuId]
     * @return: org.springframework.http.ResponseEntity<com.leu.item.pojo.SpuDetail>
     * @author: Bolon
     * @date: 2019/12/23 19:12
     */
    //http://api.leyou.com/api/item/spu/detail/184
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if (Objects.isNull(spuDetail)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    /**
     * 功能描述: 根据SpuId查询Sku作为回显数据
     * @param: [id]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leu.item.pojo.Sku>>
     * @author: Bolon
     * @date: 2019/12/23 19:15
     */
    //http://api.leyou.com/api/item/sku/list?id=184
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long spuId) {
        List<Sku> skus = goodsService.querySkuBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }
}
