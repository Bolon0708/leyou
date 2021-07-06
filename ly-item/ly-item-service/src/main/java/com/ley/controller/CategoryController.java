package com.ley.controller;

import com.leu.item.pojo.Category;
import com.ley.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Author: Bolon
 * @Date: 2019/12/7 21:49
 * @Description: TODO
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;

    /**
     * 功能描述: 根据父分类id查询子分类
     * @param: [pid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leu.item.pojo.Category>>
     * @author: Bolon
     * @date: 2019/12/7 21:59
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryByParentId(pid);
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    /**
     * 功能描述: 根据id列表查询
     * @param: [ids]
     * @return: org.springframework.http.ResponseEntity<java.util.List<java.lang.String>>
     * @author: Bolon
     * @date: 2020/1/8 21:22
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids) {
        List<String> names = categoryService.queryCategoryNamesByCid(ids);
        if (CollectionUtils.isEmpty(names)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);
    }
}
