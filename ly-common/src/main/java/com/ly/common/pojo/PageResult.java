package com.ly.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Author: Bolon
 * @Date: 2019/12/8 13:09
 * @Description: 结果分页
 */
@Data
public class PageResult<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 当前页数据
     */
    private List<T> items;
}
