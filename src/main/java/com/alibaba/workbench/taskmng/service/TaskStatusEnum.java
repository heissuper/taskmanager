package com.alibaba.workbench.taskmng.service;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public enum TaskStatusEnum {
    /**
     * 新任务，待完成
     */
    NEW("new"),
    /**
     * 已完成
     */
    COMPLETED("completed");

    private final String value;

    TaskStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaskStatusEnum ofValue(String value) {
        for (TaskStatusEnum taskStatusEnum : values()) {
            if (taskStatusEnum.value.equals(value)) {
                return taskStatusEnum;
            }
        }
        return null;
    }
}
