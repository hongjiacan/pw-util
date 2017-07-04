package com.pw.util.query;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class Page {

    private String name;
    private int start;
    private int limit;
    private int total;

    public Page() {}

    public Page(String name, int start, int limit) {
        this.name = name;
        this.start = start;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return this.getStart() / this.getLimit() + 1;
    }

    public int getTotalPage() {
        return (this.getTotal() + this.getLimit() - 1)/this.getLimit();
    }

    public int getPageSize() {
        return this.getLimit();
    }
}
