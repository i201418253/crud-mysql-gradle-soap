package com.concretepage.SpringBootWSProducer.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.concretepage.SpringBootWSProducer.articles.AddArticleRequest;
import com.concretepage.SpringBootWSProducer.articles.AddArticleResponse;
import com.concretepage.SpringBootWSProducer.articles.ArticleInfo;
import com.concretepage.SpringBootWSProducer.articles.DeleteArticleRequest;
import com.concretepage.SpringBootWSProducer.articles.DeleteArticleResponse;
import com.concretepage.SpringBootWSProducer.articles.GetAllArticlesResponse;
import com.concretepage.SpringBootWSProducer.articles.GetArticleByIdRequest;
import com.concretepage.SpringBootWSProducer.articles.GetArticleByIdResponse;
import com.concretepage.SpringBootWSProducer.articles.ServiceStatus;
import com.concretepage.SpringBootWSProducer.articles.UpdateArticleRequest;
import com.concretepage.SpringBootWSProducer.articles.UpdateArticleResponse;
import com.concretepage.SpringBootWSProducer.entity.Article;
import com.concretepage.SpringBootWSProducer.service.AIrticleService;

@Endpoint
public class ArticleEndpoint {
	private static final String NAMESPACE_URI = "http://www.concretepage.com/article-ws";
	@Autowired
	private AIrticleService articleService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
	@ResponsePayload
	public GetArticleByIdResponse getArticle(@RequestPayload GetArticleByIdRequest request) {
		GetArticleByIdResponse response = new GetArticleByIdResponse();
		ArticleInfo articleInfo = new ArticleInfo();
		BeanUtils.copyProperties(articleService.getArticleById(request.getArticleId()), articleInfo);
		response.setArticleInfo(articleInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllArticlesRequest")
	@ResponsePayload
	public GetAllArticlesResponse getAllArticles() {
		GetAllArticlesResponse response = new GetAllArticlesResponse();
		List<ArticleInfo> articleInfoList = new ArrayList<>();
		List<Article> articleList = articleService.getAllArticles();
		for (int i = 0; i < articleList.size(); i++) {
			ArticleInfo ob = new ArticleInfo();
			BeanUtils.copyProperties(articleList.get(i), ob);
			articleInfoList.add(ob);
		}
		response.getArticleInfo().addAll(articleInfoList);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addArticleRequest")
	@ResponsePayload
	public AddArticleResponse addArticle(@RequestPayload AddArticleRequest request) {
		AddArticleResponse response = new AddArticleResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Article article = new Article();
		article.setTitle(request.getTitle());
		article.setCategory(request.getCategory());
		boolean flag = articleService.addArticle(article);
		if (flag == false) {
			serviceStatus.setStatusCode("CONFLICT");
			serviceStatus.setMessage("Contenido ya disponible");
			response.setServiceStatus(serviceStatus);
		} else {
			ArticleInfo articleInfo = new ArticleInfo();
			BeanUtils.copyProperties(article, articleInfo);
			response.setArticleInfo(articleInfo);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Contenido añadido con éxito");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArticleRequest")
	@ResponsePayload
	public UpdateArticleResponse updateArticle(@RequestPayload UpdateArticleRequest request) {
		Article article = new Article();
		BeanUtils.copyProperties(request.getArticleInfo(), article);
		articleService.updateArticle(article);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatusCode("SUCCESS");
		serviceStatus.setMessage("Contenido actualizado con éxito");
		UpdateArticleResponse response = new UpdateArticleResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteArticleRequest")
	@ResponsePayload
	public DeleteArticleResponse deleteArticle(@RequestPayload DeleteArticleRequest request) {
		Article article = articleService.getArticleById(request.getArticleId());
		ServiceStatus serviceStatus = new ServiceStatus();
		if (article == null) {
			serviceStatus.setStatusCode("FAIL");
			serviceStatus.setMessage("Contenido no disponible");
		} else {
			articleService.deleteArticle(article);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Contenido eliminado con éxito");
		}
		DeleteArticleResponse response = new DeleteArticleResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}

}
