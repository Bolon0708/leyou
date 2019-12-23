package com.ley.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leu.item.pojo.*;
import com.leu.item.pojo.bo.SpuBo;
import com.ley.mapper.SkuMapper;
import com.ley.mapper.SpuDetailMapper;
import com.ley.mapper.SpuMapper;
import com.ley.mapper.StockMapper;
import com.ly.common.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: GoodsService
 * @Author: Bolon
 * @Date: 2019/12/21 16:52
 * @Description: 商品service
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
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private StockMapper stockMapper;

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

    /**
     * 功能描述: 新增商品
     * @param: [spuBo]
     * @return: void
     * @author: Bolon
     * @date: 2019/12/22 22:52
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(SpuBo spuBo) {
        //先将数据写入spu表中，数据中缺少saleable，valid,createtime,lastupdatetime
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insertSelective(spuBo);
        //将数据写入spu_detail中
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insert(spuDetail);
        //将数据写入sku和stock表中
        spuBo.getSkus().forEach(sku -> {
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(spuBo.getCreateTime());
            sku.setLastUpdateTime(spuBo.getCreateTime());
            skuMapper.insertSelective(sku);
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);
        });
    }

    /**
     * 功能描述: 根据spuId查询spuDetail
     * @param: [spuId]
     * @return: com.leu.item.pojo.SpuDetail
     * @author: Bolon
     * @date: 2019/12/23 19:13
     */
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }

    /**
     * 功能描述: 根据SpuId查询Sku作为回显数据
     * @param: [id]
     * @return: java.util.List<com.leu.item.pojo.Sku>
     * @author: Bolon
     * @date: 2019/12/23 19:16
     */
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(sku);
        skus.forEach(sku1 -> {
            Stock stock = stockMapper.selectByPrimaryKey(sku1.getId());
            if (!Objects.isNull(stock)) {
                sku1.setStock(stock.getStock());
            }
        });
        return skus;
    }
}
