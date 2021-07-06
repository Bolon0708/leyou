package com.leu.item.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: CategoryApi
 * @Author: Bolon
 * @Date: 2020/1/8 21:18
 * @Description: 作为服务提供方提供调用接口
 */
@RequestMapping("category")
public interface CategoryApi {

    /**
     * 功能描述: 根据id列表查询
     * @param: [ids]
     * @return: org.springframework.http.ResponseEntity
     * @author: Bolon
     * @date: 2020/1/8 21:20
     */
    @GetMapping("names")
    List<String> queryNameByIds(@RequestParam("ids") List<Long> ids);
}
