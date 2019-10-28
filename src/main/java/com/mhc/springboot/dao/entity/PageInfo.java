package com.mhc.springboot.dao.entity;

import java.util.List;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-28 20:07
 */
public class PageInfo<T> {

    private Long currPage;
    private Long pageSize;
    private Long total;
    private Long tatalPage;
    private List<T> list;

    public Long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTatalPage() {
        return tatalPage;
    }

    public void setTatalPage(Long tatalPage) {
        this.tatalPage = tatalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
