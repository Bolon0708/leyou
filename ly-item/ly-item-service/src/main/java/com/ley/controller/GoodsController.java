package com.ley.controller;

import com.leu.item.pojo.bo.SpuBo;
import com.ley.service.GoodsService;
import com.ly.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
