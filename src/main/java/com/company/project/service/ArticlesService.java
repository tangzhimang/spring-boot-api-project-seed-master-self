package com.company.project.service;
import com.company.project.model.Articles;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by tzm on 2018/06/25.
 */
public interface ArticlesService extends Service<Articles> {
	
    List<Articles> getArticles();
}
