package com.ley.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leu.item.pojo.Brand;
import com.ley.mapper.BrandMapper;
import com.ly.common.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: BrandService
 * @Author: Bolon
 * @Date: 2019/12/8 13:39
 * @Description: 品牌Service
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开启分页助手,传入当前页及每页行数
        PageHelper.startPage(page, rows);
        // 创建模板对象，建立查询模板,传入要查询的对象
        Example example = new Example(Brand.class);
        // 进行key和sortBy的值判断
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " desc" : " asc"));
        }
        // 通过设置好的模板进行查询
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<>(brands.getTotal(), (long) brands.getPages(), brands);
    }
}
