package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Articles;
import com.company.project.service.ArticlesService;
import com.company.project.service.StorageService;
import com.company.project.util.HTMLSpirit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by tzm on 2018/06/25.
*/
@RestController
@RequestMapping("/articles")
public class ArticlesController {
    @Resource
    private ArticlesService articlesService;
    @Resource
    private StorageService storageService;
    
    
    private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";// 模板位置

	private static final String JAVA_PATH = "/src/main/java"; // java文件路径
	private static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径
	
	private static final String IMAGE_RESOURCES_PATH = "/src/main/resources/images";

    @PostMapping("/add")
    public Result add(Articles articles) {
        articlesService.save(articles);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        articlesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Articles articles) {
        articlesService.update(articles);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Articles articles = articlesService.findById(id);
        return ResultGenerator.genSuccessResult(articles);
    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Articles> list = articlesService.findAll();
        for(Articles articles: list) {
        	Date date = articles.getArticleDate();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	articles.setArticleDateString(sdf.format(date));
        	String overLookContent = HTMLSpirit.delHTMLTag(articles.getContent());
        	if(overLookContent.length() > 20) {
        		overLookContent = overLookContent.substring(0, 20);
        	}
        	articles.setOverLookContent(overLookContent);//去除html标签
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @RequestMapping("/getArticles")
    public Result getArticles(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
    	PageHelper.startPage(page, size);
    	List<Articles> list = articlesService.getArticles();
    	for(Articles articles: list) {
        	Date date = articles.getArticleDate();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	articles.setArticleDateString(sdf.format(date));
        	String overLookContent = HTMLSpirit.delHTMLTag(articles.getContent());
        	if(overLookContent.length() > 20) {
        		overLookContent = overLookContent.substring(0, 20);
        	}
        	articles.setOverLookContent(overLookContent);//去除html标签
        }
    	PageInfo pageInfo = new PageInfo(list);
    	return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody Map<String,Object> reqMap) {
    	Articles articles = new Articles();
    	articles.setAuthor(reqMap.get("author").toString());
    	articles.setTitle(reqMap.get("title").toString());
    	articles.setContent(reqMap.get("content").toString());
    	articles.setImgurl("/images/001.png");
    	articles.setArticleDate(new Date());
    	articles.setSource("个人博客【网站编辑】");
    	articlesService.save(articles);
        return ResultGenerator.genSuccessResult();
    }
    
    @RequestMapping("/uploadImgForArticleTitle")
    public Result uploadImgForArticleTitle(@RequestBody MultipartFile file) {
    	if (file.isEmpty()) {
            return ResultGenerator.genFailResult("the file is empty");
        }
    	
    	 String fileName = file.getOriginalFilename();
         System.out.println("上传的文件名为：" + fileName);
         // 获取文件的后缀名
         String suffixName = fileName.substring(fileName.lastIndexOf("."));
         System.out.println("上传的后缀名为：" + suffixName);
         
         storageService.store(file);

		return ResultGenerator.genSuccessResult();
    	
    }
}
