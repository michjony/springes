package com.mark.es.es;

import java.util.Date;
import java.util.Iterator;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mark.es.model.Article;
import com.mark.es.model.Author;
import com.mark.es.model.Tutorial;
import com.mark.es.service.ArticleSearchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

	@Autowired
	private ArticleSearchRepository articleSearchRepository;
	@Test
	public void testSaveArticleIndex(){
		Author author=new Author();
		author.setId(1L);
		author.setName("mark zhao");
		author.setRemark("java developer");
		
		Tutorial tutorial=new Tutorial();
		tutorial.setId(1L);
		tutorial.setName("elastic search");
		
		Article article =new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setTutorial(tutorial);
		article.setAuthor(author);
		article.setContent("elasticsearch based on lucene,"
				+ "spring-data-elastichsearch based on elaticsearch"
				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setPostTime(new Date());
		article.setClickCount(1L);
		
		articleSearchRepository.save(article);
	}

	
	@Test
	public void testSearch(){
		String queryString="springboot";//搜索关键字
		QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = articleSearchRepository.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		while(iterator.hasNext()){
			System.out.println("===================");
			System.out.println(iterator.next());
			System.out.println("===================");
		}
	}
	
}
