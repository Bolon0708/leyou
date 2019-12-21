package com.ley.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leu.item.pojo.Brand;
import com.leu.item.pojo.Spu;
import com.leu.item.pojo.bo.SpuBo;
import com.ley.mapper.SkuMapper;
import com.ley.mapper.SpuMapper;
import com.ly.common.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: GoodsService
 * @Author: Bolon
 * @Date: 2019/12/21 16:52
 * @Description: TODO
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    /**
     * 功能描述: 分页查询商品列表
     * @param: [key, saleable, page, rows]
     * @return: com.ly.common.pojo.PageResult<com.leu.item.pojo.bo.SpuBo>
     * @author: Bolon
     * @date: 2019/12/21 17:02
     */
    public PageResult<SpuBo> queryGoodsByPage(String key, Boolean saleable, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否有搜索条件
        if (StringUtils.isNotBlank(key)) {
            //有搜索条件时对标题进行模糊查询
            criteria.andLike("title", "%" + key + "%");
        }
        //判断上架下架条件是否存在
        if (saleable != null) {
            //如果存在，加入根据上下架条件查询
            criteria.andEqualTo("saleable", saleable);
        }
        List<Spu> spus = spuMapper.selectByExample(example);
        List<SpuBo> collect = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            //属性拷贝，将spu中封装的属性拷贝到spuBo中
            BeanUtils.copyProperties(spu, spuBo);
            //根据spu中的cid和bid去查询分类名称以及品牌名称，并封装到spuBo中
            List<String> cname = categoryService.queryCategoryNamesByCid(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            //对查询的cnames集合进行处理，转为字符串"手机-充电宝-.."形式返回页面
            spuBo.setCname(StringUtils.join(cname, "-"));
            //查询spu对应的品牌名称
            Brand brand = brandService.queryBrandNameByBid(spu.getBrandId());
            spuBo.setBname(brand.getName());
            return spuBo;
        }).collect(Collectors.toList());
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);
        return new PageResult<>(pageInfo.getTotal(), (long) pageInfo.getPages(), collect);
    }
}
