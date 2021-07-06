package com.leu.item.api;

import com.leu.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: BrandApi
 * @Author: Bolon
 * @Date: 2020/1/8 21:08
 * @Description: 作为服务提供方提供调用接口
 */
@RequestMapping("brand")
public interface BrandApi {
    /**
     * 功能描述: 根据id列表查询品牌
     * @param: ids
     * @return: java.util.List < com.leu.item.pojo.Brand>
     * @author: Bolon
     * @date: 2020/1/8 21:11
     */
    @GetMapping("list")
    List<Brand> queryBrandByIds(@RequestParam("ids") List<Long> ids);
}
