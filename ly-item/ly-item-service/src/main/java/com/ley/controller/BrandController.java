package com.ley.controller;

import com.leu.item.pojo.Brand;
import com.ley.service.BrandService;
import com.ly.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BrandController
 * @Author: Bolon
 * @Date: 2019/12/8 13:40
 * @Description: TODO
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 功能描述: 对品牌进行分页查询
     * @param: [key, page, rows, sortBy, desc]
     * @return: org.springframework.http.ResponseEntity<com.ly.common.pojo.PageResult < com.leu.item.pojo.Brand>>
     * @author: Bolon
     * @date: 2019/12/8 13:43
     */
    //http://api.leyou.com/api/item/brand/page?key=&page=1&rows=5&sortBy=id&desc=false
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@RequestParam(value = "key", required = false) String key, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "5") Integer rows, @RequestParam(value = "sortBy", required = false) String sortBy,
                                                              @RequestParam(value = "desc", defaultValue = "false") Boolean desc) {
        PageResult<Brand> pageResult = brandService.queryBrandByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }
}
