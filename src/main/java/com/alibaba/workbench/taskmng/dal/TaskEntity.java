package com.alibaba.workbench.taskmng.dal;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
@TableName("tasks")
public class TaskEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String content;

    private String status;

    private Long categoryId;

    public static final String FIELD_ID = "id";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_MODIFIED = "gmt_modified";
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_TITLE = "title";
}
