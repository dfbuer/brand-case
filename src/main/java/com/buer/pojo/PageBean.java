package com.buer.pojo;

import java.util.List;

/**
 * 分页查询的javaBean
 */
public class PageBean<T> {
    //总记录数
    private int totalCount;

    //当前页数据,自定义泛型
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
