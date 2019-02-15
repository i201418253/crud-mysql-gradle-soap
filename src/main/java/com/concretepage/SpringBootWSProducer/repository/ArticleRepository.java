package com.concretepage.SpringBootWSProducer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concretepage.SpringBootWSProducer.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	Article findByArticleId(long articleId);

	List<Article> findByTitleAndCategory(String title, String category);
}
