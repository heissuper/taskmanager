package com.alibaba.workbench.taskmng.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.workbench.taskmng.controller.vo.CategoryVO;
import com.alibaba.workbench.taskmng.dal.CategoryEntity;
import com.alibaba.workbench.taskmng.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@RestController
@RequestMapping("task")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public List<CategoryVO> getCategories() {
    	List<CategoryEntity> list = categoryService.getCategories();
    	
    	 List<CategoryVO>  rtnVal = list.stream().map( item->{ CategoryVO vo = new CategoryVO(); vo.setId( item.getId());vo.setName( item.getName()); return vo; } ).collect( Collectors.toList()  );
    	 return rtnVal;
    }
}
