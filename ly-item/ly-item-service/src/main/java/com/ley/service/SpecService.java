package com.ley.service;

import com.leu.item.pojo.SpecGroup;
import com.leu.item.pojo.SpecParam;
import com.ley.mapper.SpecGroupMapper;
import com.ley.mapper.SpecParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SpecService
 * @Author: Bolon
 * @Date: 2019/12/18 21:32
 * @Description: 规格参数service
 */
@Service
public class SpecService {
    @Autowired
    private SpecGroupMapper groupMapper;
    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 功能描述: 根据cid查询对应的规格参数组
     *
     * @param: [cid]
     * @return: java.util.List<com.leu.item.pojo.SpecGroup>
     * @author: Bolon
     * @date: 2019/12/18 21:37
     */
    public List<SpecGroup> querySpecByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return groupMapper.select(specGroup);
    }

    /**
     * 功能描述: 根据条件查询规格参数
     * @param: [gid, cid, searching, generic]
     * @return: java.util.List<com.leu.item.pojo.SpecParam>
     * @author: Bolon
     * @date: 2019/12/22 22:14
     */
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        //select方法具有通用性，只有当属性有值时才会根据该属性去查询
        return paramMapper.select(specParam);
    }
}
