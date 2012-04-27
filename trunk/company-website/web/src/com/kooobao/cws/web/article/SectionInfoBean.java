package com.kooobao.cws.web.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.domain.article.Resource;
import com.kooobao.cws.domain.article.Section;
import com.kooobao.cws.domain.article.Video;
import com.kooobao.cws.service.article.ArticleService;

public class SectionInfoBean extends AbstractBean implements JSFStartupAware {

	List<SelectItem> newsSectionItem;
	List<SelectItem> videoSectionItem;
	List<SelectItem> resSectionItem;

	@Override
	public void init() {
		List<Section> newsSection = getArticleService().getSections(
				News.getType());
		List<Section> videoSection = getArticleService().getSections(
				Video.getType());
		List<Section> resSection = getArticleService().getSections(
				Resource.getType());
		newsSectionItem = Utilities.wrap(newsSection,"name");
		videoSectionItem = Utilities.wrap(videoSection,"name");
		resSectionItem = Utilities.wrap(resSection,"name");

		sections = new HashMap<String, Map<String, Section>>();

		Map<String, Section> newsMap = new HashMap<String, Section>();
		for (Section newssec : newsSection)
			newsMap.put(newssec.getName(), newssec);
		sections.put(News.getType(), newsMap);

		Map<String, Section> videoMap = new HashMap<String, Section>();
		for (Section videosec : videoSection)
			videoMap.put(videosec.getName(), videosec);
		sections.put(Video.getType(), videoMap);

		Map<String, Section> resMap = new HashMap<String, Section>();
		for (Section ressec : resSection)
			resMap.put(ressec.getName(), ressec);
		sections.put(Resource.getType(), resMap);
	}

	@Override
	public void dispose() {

	}

	private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	private Map<String, Map<String, Section>> sections;

	public Section get(String type, String name) {
		return sections.get(type).get(name);
	}

	public List<SelectItem> getNewsSection() {
		return newsSectionItem;
	}

	public List<SelectItem> getVideoSection() {
		return videoSectionItem;
	}

	public List<SelectItem> getResSection() {
		return resSectionItem;
	}

}
