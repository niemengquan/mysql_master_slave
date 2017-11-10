package com.nmq.mysql.vo;

/**
 * Created by niemengquan on 2017/11/10.
 */
public class ViewListResult<T> {
    private long total;
    private long pageSize;
    private long pageNum;
    private T result;

    private ViewListResult(long total, long pageSize, long pageNum, T object) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.result = object;
    }

    public static ViewListResult build(long total, long pageSize, long pageNum, Object object){
        return new ViewListResult(total,pageSize,pageNum,object);
    }
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
