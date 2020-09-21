package com.alibaba.workbench.taskmng.controller.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class TaskVO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date gmtCreate;

    private String title;

    private String content;

    private String status;

    private Long categoryId;
}
