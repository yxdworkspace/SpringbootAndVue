package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Book;
import com.example.demo.entity.News;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 杨旭东
 * @create 2022/11/14 9:09
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsMapper newsMapper;


    @PostMapping
    public Result<?> save(@RequestBody News news){
        newsMapper.insert(news);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody News news){
        newsMapper.updateById(news);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        newsMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(News::getTitle,search);
        }
        Page<News> BookPage = newsMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(BookPage);
    }
}
