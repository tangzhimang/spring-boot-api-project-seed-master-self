package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.company.project.core.Mapper;
import com.company.project.model.Articles;

import tk.mybatis.mapper.provider.base.BaseSelectProvider;

public interface ArticlesMapper extends Mapper<Articles> {
	
    List<Articles> getArticles();
}