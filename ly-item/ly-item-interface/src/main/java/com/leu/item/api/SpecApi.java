package com.leu.item.api;

import com.leu.item.pojo.SpecGroup;
import com.leu.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: SpecificationApi
 * @Author: Bolon
 * @Date: 2020/1/8 21:23
 * @Description: 作为服务提供方提供调用接口
 */
@RequestMapping("spec")
public interface SpecApi {
    /**
     * 功能描述: 根据条件查询规格参数
     *
     * @param: [gid]
     * @return: java.util.List < com.leu.item.pojo.SpecParam>
     * @author: Bolon
     * @date: 2019/12/18 21:57
     */
    @GetMapping("params")
    List<SpecParam> querySpecParam(@RequestParam(value = "gid", required = false) Long gid,
                                   @RequestParam(value = "cid", required = false) Long cid,
                                   @RequestParam(value = "searching", required = false) Boolean searching,
                                   @RequestParam(value = "generic", required = false) Boolean generic);

    /**
     * 功能描述: 根据cid查询对应的规格参数组
     *
     * @param: [cid]
     * @return: java.util.List<com.leu.item.pojo.SpecGroup>
     * @author: Bolon
     * @date: 2019/12/18 21:35
     */
    @GetMapping("groups/{cid}")
    List<SpecGroup> querySpecGroupByCid(@PathVariable("cid") Long cid);
}
