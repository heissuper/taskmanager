package com.alibaba.workbench.taskmng.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.workbench.taskmng.dal.CategoryEntity;
import com.alibaba.workbench.taskmng.dal.CategoryMapper;
import com.alibaba.workbench.taskmng.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public List<CategoryEntity> getCategories() {

        Wrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        List<CategoryEntity> categories = categoryMapper.selectList(wrapper);

        return categories
                .stream()
                .sorted(Comparator.comparing(CategoryEntity::getPriority))
                .collect(Collectors.toList());
    }
}
