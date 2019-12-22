package com.ley.controller;

import com.leu.item.pojo.Brand;
import com.ley.service.BrandService;
import com.ly.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BrandController
 * @Author: Bolon
 * @Date: 2019/12/8 13:40
 * @Description: 品牌controller
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 功能描述: 对品牌进行分页查询
     *
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

    /**
     * 功能描述: 品牌新增
     * @param: [brand, cids]
     * @return: org.springframework.http.ResponseEntity
     * @author: Bolon
     * @date: 2019/12/8 20:46
     */
    @PostMapping
    public ResponseEntity saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 功能描述: 根据分类cid查询品牌列表
     * @param: [cid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leu.item.pojo.Brand>>
     * @author: Bolon
     * @date: 2019/12/22 22:02
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid) {
        List<Brand> brands = brandService.queryBrandByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }
}
