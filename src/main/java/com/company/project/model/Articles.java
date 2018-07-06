package com.company.project.model;


import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import javax.persistence.*;

public class Articles {
    @Id
    @Column(name = "article_id")
    private String articleId;

    private String title;

    private String imgurl;

    private String author;

    @Column(name = "article_date")
    private Date articleDate;

    private String source;

    private String content;
    
    @Transient
    private String articleDateString;
    
    @Transient
    private String overLookContent;
    
    
    public String getOverLookContent() {
		return overLookContent;
	}

	public void setOverLookContent(String overLookContent) {
		this.overLookContent = overLookContent;
	}

	public String getArticleDateString() {
		return articleDateString;
	}

	public void setArticleDateString(String articleDateString) {
		this.articleDateString = articleDateString;
	}

	/**
     * @return article_id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return imgurl
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * @param imgurl
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return article_date
     */
    public Date getArticleDate() { 
    	 return articleDate; 
    }

    /**
     * @param articleDate
     */
    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}