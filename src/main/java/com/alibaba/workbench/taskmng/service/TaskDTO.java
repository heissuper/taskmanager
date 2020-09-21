package com.alibaba.workbench.taskmng.service;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class TaskDTO implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String status;

    private String title;

    private String content;

    private Long categoryId;

}
