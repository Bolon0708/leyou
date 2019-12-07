package com.ley.service;

import com.leu.item.pojo.Category;
import com.ley.mapper.CategoryMapper;
import com.sun.prism.impl.Disposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.cmm.CMSManager;

import java.util.List;

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
}
