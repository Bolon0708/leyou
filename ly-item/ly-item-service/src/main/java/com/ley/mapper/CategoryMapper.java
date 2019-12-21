package com.ley.mapper;

import com.leu.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: CategoryMapper
 * @Author: Bolon
 * @Date: 2019/12/7 21:44
 * @Description: 商品类目Mapper
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
}
