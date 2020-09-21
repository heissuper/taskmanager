package com.alibaba.workbench.taskmng.dal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
@TableName("categories")
public class CategoryEntity {

    private Long id;

    private String name;

    private Long priority;
}
