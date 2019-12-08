package com.ley.mapper;

import com.leu.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: BrandMapper
 * @Author: Bolon
 * @Date: 2019/12/8 13:38
 * @Description: 品牌Mapper
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 功能描述: 品牌新增
     * @param: [cid, id]
     * @return: void
     * @author: Bolon
     * @date: 2019/12/8 20:54
     */
    @Insert("insert into tb_category_brand(category_id,brand_id) values(#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long id);
}
