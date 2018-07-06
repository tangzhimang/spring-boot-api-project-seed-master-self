package com.company.project.service.impl;

import com.company.project.dao.ArticlesMapper;
import com.company.project.model.Articles;
import com.company.project.service.ArticlesService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by tzm on 2018/06/25.
 */
@Service
@Transactional
public class ArticlesServiceImpl extends AbstractService<Articles> implements ArticlesService {
    @Resource
    private ArticlesMapper articlesMapper;

	@Override
	public List<Articles> getArticles() {
		// TODO Auto-generated method stub
		return articlesMapper.getArticles();
	}

}
