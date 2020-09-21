package com.alibaba.workbench.taskmng.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.workbench.taskmng.controller.vo.PageJsonResult;
import com.alibaba.workbench.taskmng.controller.vo.TaskVO;
import com.alibaba.workbench.taskmng.service.Pager;
import com.alibaba.workbench.taskmng.service.TaskDTO;
import com.alibaba.workbench.taskmng.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "status", defaultValue = TaskService.ALL_STATUS) String status) {
        Pager<TaskDTO> tasks = taskService.query(status, page);
        List<TaskVO> list = tasks.getRecords().stream().map(this::convert).collect(Collectors.toList());
        return new PageJsonResult(list, tasks.getTotalRecords());
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestParam(name = "id") Long id,
                       @RequestParam(name = "title") String title,
                       @RequestParam(name = "content") String content,
                       @RequestParam(name = "category") Long category) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setContent(content);
        taskDTO.setCategoryId(category);
        taskDTO = taskService.save(taskDTO);
        return taskDTO;
    }

    @RequestMapping("/complete")
    @ResponseBody
    public Object complete(@RequestParam(name = "id") Long id) {
        taskService.complete(id);
        return "success";
    }

    @RequestMapping("/unComplete")
    @ResponseBody
    public Object unComplete(@RequestParam(name = "id") Long id) {
        taskService.unComplete(id);
        return "success";
    }

    @RequestMapping("/del")
    @ResponseBody
    public Object del(@RequestParam(name = "id") Long id) {
        taskService.del(id);
        return "success";
    }

    private TaskVO convert(TaskDTO taskDTO) {
        TaskVO vo = new TaskVO();
        BeanUtils.copyProperties(taskDTO, vo);
        return vo;
    }
}
