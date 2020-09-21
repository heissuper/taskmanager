package com.alibaba.workbench.taskmng.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public class Pager<T> implements Serializable {

    private List<T> records;

    private Long totalRecords;

    public Pager() {

    }

    public Pager(List<T> records, Long totalRecords) {
        this.records = records;
        this.totalRecords = totalRecords;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
