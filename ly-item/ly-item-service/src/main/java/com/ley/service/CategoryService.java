package com.ley.service;

import com.leu.item.pojo.Category;
import com.ley.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoryService
 * @Author: Bolon
 * @Date: 2019/12/7 21:47
 * @Description: 商品类目Service
 */
@Service
public class CategoryService {
    @Autowired
    public CategoryMapper categoryMapper;

    /**
     * 功能描述: 通过父分类id查询子分类
     * @param: [pid]
     * @return: java.util.List<com.leu.item.pojo.Category>
     * @author: Bolon
     * @date: 2019/12/7 22:02
     */
    public List<Category> queryByParentId(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    /**
     * 功能描述: 根据分类id查询分类名称
     * @param: [cids]
     * @return: java.util.List<java.lang.String>
     * @author: Bolon
     * @date: 2019/12/21 17:20
     */
    public List<String> queryCategoryNamesByCid(List<Long> cids) {
        List<Category> categories = categoryMapper.selectByIdList(cids);
        return categories.stream().map(Category::getName).collect(Collectors.toList());
    }
}
