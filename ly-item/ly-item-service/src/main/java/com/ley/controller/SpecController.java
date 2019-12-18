package com.ley.controller;

import com.leu.item.pojo.SpecGroup;
import com.leu.item.pojo.SpecParam;
import com.ley.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SpecController
 * @Author: Bolon
 * @Date: 2019/12/18 21:33
 * @Description: 规格参数controller
 */
@RestController
@RequestMapping("spec")
public class SpecController {
    @Autowired
    SpecService specService;

    /**
     * 功能描述: 根据cid查询对应的规格参数组
     * @param: [cid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leu.item.pojo.SpecGroup>>
     * @author: Bolon
     * @date: 2019/12/18 21:35
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> specGroups = specService.querySpecByCid(cid);
        if (CollectionUtils.isEmpty(specGroups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specGroups);
    }

    /**
     * 功能描述: 根据条件查询规格参数
     * @param: [gid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leu.item.pojo.SpecParam>>
     * @author: Bolon
     * @date: 2019/12/18 21:57
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParam(@RequestParam("gid") Long gid) {
        List<SpecParam> specParams = specService.querySpecParams(gid);
        if (CollectionUtils.isEmpty(specParams)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specParams);
    }
}
