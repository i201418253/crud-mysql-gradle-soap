package com.concretepage.SpringBootWSProducer.service;

import java.util.List;

import com.concretepage.SpringBootWSProducer.entity.Article;


public interface AIrticleService {
	 List<Article> getAllArticles();
     Article getArticleById(long articleId);
     boolean addArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(Article article);
}
