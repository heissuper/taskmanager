package com.alibaba.workbench.taskmng.controller.vo;

import java.util.List;

import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class PageJsonResult<T> extends JsonResult<List<T>> {

    private Long totalRecords;

    public PageJsonResult(List<T> data, Long totalRecords) {
        setTotalRecords(totalRecords);
        setData(data);
    }
}
