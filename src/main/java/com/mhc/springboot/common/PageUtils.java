package com.mhc.springboot.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mhc.springboot.dao.entity.PageInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-28 20:04
 */
public class PageUtils {

    private PageUtils() {
    }


    /**
     * 获取默认分页
     */
    public static Page getDefaultPage() {
        return new Page(1, 10);
    }

    /**
     * 根据参数获取对应的 Page 对象
     *
     * @param currPage 当前页数
     * @param pageSize 页面显示数量
     * @return
     */
    public static Page getPage(Long currPage, Long pageSize) {
        return new Page(Optional.of(currPage).orElse(1L), Optional.of(pageSize).orElse(10L));
    }


    /**
     * 构造返回数据的 PageInfo 信息
     * @param list 数据list
     * @param page page对象
     * @return
     */
    public static PageInfo buildPageInfo(List list, Page page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrPage(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setTatalPage(page.getTotal() / page.getSize() + 1);
        pageInfo.setList(list);
        return pageInfo;
    }

}
