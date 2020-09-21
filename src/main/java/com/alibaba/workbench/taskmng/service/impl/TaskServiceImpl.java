package com.alibaba.workbench.taskmng.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.workbench.taskmng.dal.TaskEntity;
import com.alibaba.workbench.taskmng.dal.TaskMapper;
import com.alibaba.workbench.taskmng.service.Pager;
import com.alibaba.workbench.taskmng.service.TaskDTO;
import com.alibaba.workbench.taskmng.service.TaskService;
import com.alibaba.workbench.taskmng.service.TaskStatusEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public Pager<TaskDTO> query(String status, Integer page) {

        page = page > 1 ? page : 1;

        if (ALL_STATUS.equals(status)) {
            status = null;
        }

        IPage<TaskEntity> pager = new Page<>(page, DEFAULT_PAGE_SIZE);
        QueryWrapper<TaskEntity> wrapper = new QueryWrapper<>();
        if (status != null) {
            wrapper.eq(TaskEntity.FIELD_STATUS, status);
        }
        wrapper.orderByAsc(TaskEntity.FIELD_STATUS).orderByDesc(TaskEntity.FIELD_ID);

        IPage<TaskEntity> pageRes = taskMapper.selectPage(pager, wrapper);

        List<TaskEntity> records = pageRes.getRecords();

        List<TaskDTO> data = records.stream().map(this::convert2DTO).collect(Collectors.toList());

        return new Pager<>(data, pageRes.getTotal());
    }

    @Override
    public void complete(Long id) {
        Wrapper<TaskEntity> wrapper = new QueryWrapper<TaskEntity>()
                .eq(TaskEntity.FIELD_ID, id);
        TaskEntity obj = taskMapper.selectOne(wrapper);
        if (obj == null) {
            return;
        }
        Wrapper<TaskEntity> update = new UpdateWrapper<TaskEntity>()
                .set(TaskEntity.FIELD_STATUS, TaskStatusEnum.COMPLETED.getValue())
                .eq(TaskEntity.FIELD_ID, id);
        taskMapper.update(null, update);
    }

    @Override
    public void unComplete(Long id) {
        Wrapper<TaskEntity> wrapper = new QueryWrapper<TaskEntity>()
                .eq(TaskEntity.FIELD_ID, id);
        TaskEntity obj = taskMapper.selectOne(wrapper);
        if (obj == null) {
            return;
        }
        Wrapper<TaskEntity> update = new UpdateWrapper<TaskEntity>()
                .set(TaskEntity.FIELD_STATUS, TaskStatusEnum.NEW.getValue())
                .eq(TaskEntity.FIELD_ID, id);
        taskMapper.update(null, update);
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        if (taskDTO.getId() == null || taskDTO.getId() <= 0L) {
            TaskEntity taskEntity = new TaskEntity();
            BeanUtils.copyProperties(taskDTO, taskEntity);

            taskEntity.setId(null);
            taskEntity.setGmtCreate(new Date());
            taskEntity.setGmtModified(new Date());
            // insert;
            taskMapper.insert(taskEntity);
        } else {
            // update
            Wrapper<TaskEntity> wrapper = new UpdateWrapper<TaskEntity>()
                    .set(TaskEntity.FIELD_MODIFIED, new Date())
                    .set(TaskEntity.FIELD_TITLE, taskDTO.getTitle())
                    .set(TaskEntity.FIELD_CONTENT, taskDTO.getContent())
                    .eq(TaskEntity.FIELD_ID, taskDTO.getId());

            taskMapper.update(null, wrapper);
        }
        return taskDTO;
    }

    @Override
    public void del(Long id) {
        taskMapper.deleteById(id);
    }

    private TaskDTO convert2DTO(TaskEntity obj) {
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(obj, taskDTO);
        return taskDTO;
    }
}
