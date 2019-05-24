package com.chq.project.management.common.entity;

import com.github.pagehelper.PageInfo;

/**
 * 分页结果
 *
 * @author CHQ
 * @Description
 * @date 2019/5/15
 */
public class PageResponse<T> extends Response {

    /**
     * 数据总数
     */
    private long total;

    /**
     * 请求成功
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> PageResponse<T> ok(PageInfo pageInfo) {
        PageResponse<T> resp = new PageResponse<>();
        resp.setResult(pageInfo.getList());
        resp.setCode(200);
        resp.setTotal(pageInfo.getTotal());
        return resp;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
