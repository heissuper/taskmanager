package com.alibaba.workbench.taskmng.service;



public interface TaskService {

    String ALL_STATUS = "all";

    /**
     * query task by status
     * @param status    the task status, "all" for all status
     * @param pageIndex page index , start with 1
     */
    Pager<TaskDTO> query(String status, Integer pageIndex);

    /**
     * complete a task
     */
    void complete(Long id);

    /**
     * unComplete a task
     */
    void unComplete(Long id);

    /**
     * insert or update a task
     */
    TaskDTO save(TaskDTO taskDTO);


    void del(Long id);
}
