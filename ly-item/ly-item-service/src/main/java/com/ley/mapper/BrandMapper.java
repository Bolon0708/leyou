package com.ley.mapper;

import com.leu.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: BrandMapper
 * @Author: Bolon
 * @Date: 2019/12/8 13:38
 * @Description: 品牌Mapper
 */
public interface BrandMapper extends Mapper<Brand>, SelectByIdListMapper<Brand, Long> {
    /**
     * 功能描述: 品牌新增
     * @param: [cid, id]
     * @return: void
     * @author: Bolon
     * @date: 2019/12/8 20:54
     */
    @Insert("insert into tb_category_brand(category_id,brand_id) values(#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long id);

    /**
     * 功能描述: 根据cid查询bid
     * @param: cid
     * @return: java.util.List<java.lang.Long>
     * @author: Bolon
     * @date: 2019/12/22 22:07
     */
    @Select("select brand_id from tb_category_brand where category_id = #{cid}")
    List<Long> selectBidByCid(@Param("cid") Long cid);
}
