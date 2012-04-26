package com.kooobao.cws.dummy;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.domain.article.Resource;
import com.kooobao.cws.domain.article.Video;
import com.kooobao.cws.service.article.ArticleService;

public class DummyArticleService implements ArticleService {
	
	@Override
	public <T extends Article> List<T> getLatestArticle(
			int limit,Class<T> articleClass) {
		if (articleClass == Resource.class) {
			List<Resource> videos = new ArrayList<Resource>();
			
			Video video = new Video();
			video.setOid(1);
			video.setContent("XMTY3NTcwNjE2");
			video.setTitle("2010最新原版小学教材-Spotlight on English介绍");
			videos.add(video);
			
			video = new Video();
			video.setOid(2);
			video.setContent("XMzgyNTA5MjQw");
			video.setTitle("MM Publication在KOTESOL会议上的讲话");
			videos.add(video);
			
			Resource res = new Resource();
			res.setOid(3);
			res.setTitle("第三届上海xx会议招生情况");
			res.setContent("Good adsfasfadsf");
			videos.add(res);
			
			video = new Video();
			video.setOid(4);
			video.setContent("XMzgyNTA5MjQw");
			video.setTitle("eno白板使用视频");
			videos.add(video);

			return (List<T>) videos;
		}
		if (articleClass == News.class) {
			List<News> videos = new ArrayList<News>();
			
			News res = new News();
			res.setOid(10);
			res.setTitle("第三届上海xx会议招生情况31");
			res.setContent("Good adsfasfadsf");
			videos.add(res);
			
			res = new News();
			res.setOid(11);
			res.setTitle("第三届上海xx会议招生情况asdf");
			res.setContent("Good adsfasfadsf");
			videos.add(res);
			res = new News();
			res.setOid(12);
			res.setTitle("第三届上海xx会议招43rfqewf生情况");
			res.setContent("Good adsfasfadsf");
			videos.add(res);
			res = new News();
			res.setOid(13);
			res.setTitle("第三届上海xx会议asdfas招生情况");
			res.setContent("Good adsfasfadsf");
			videos.add(res);

			return (List<T>) videos;
		}
		return null;
	}

	@Override
	public Article getArticle(long articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article saveArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findArticles(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
