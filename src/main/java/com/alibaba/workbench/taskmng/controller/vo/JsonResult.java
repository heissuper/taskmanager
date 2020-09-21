package com.alibaba.workbench.taskmng.controller.vo;

import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class JsonResult<T> {

    private boolean success;

    private String msg;

    private T data;
}
