package com.kooobao.lm.book;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kooobao.common.domain.entity.SimpleEntity;

public class Book extends SimpleEntity {

	private String isbn;

	private List<Category> tags;

	private String pictureUrl;

	private String name;

	private int rating;

	private BigDecimal listPrice;

	private Map<String, String> attributes = new HashMap<String, String>();

	private Map<String, String> content = new HashMap<String, String>();

	public List<Category> getTags() {
		return tags;
	}

	public void setTags(List<Category> tags) {
		this.tags = tags;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	private String displayName;

	static final int NAME_SIZE = 10;

	public String getDisplayName() {
		if (null != displayName)
			return displayName;
		if (name.length() > NAME_SIZE) {
			displayName = name.substring(0, NAME_SIZE) + "...";
		} else {
			displayName = name;
		}
		return displayName;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected void setContent(BookContent contentType, String value) {
		content.put(contentType.name(), value);
	}

	protected String getContent(BookContent contentType) {
		return content.get(contentType.name());
	}

	public String getBrief() {
		return getContent(BookContent.BRIEF);
	}

	public void setBrief(String brief) {
		setContent(BookContent.BRIEF, brief);
	}

	public String getAbstract() {
		return getContent(BookContent.ABSTRACT);
	}

	public void setAbstract(String abs) {
		setContent(BookContent.ABSTRACT, abs);
	}

	public String getEditorComment() {
		return getContent(BookContent.EDITOR_COMMENT);
	}

	public void setEditorComment(String editorComment) {
		setContent(BookContent.EDITOR_COMMENT, editorComment);
	}

	public String getTextContent() {
		return getContent(BookContent.TEXT_CONTENT);
	}

	public void setTextContent(String textContent) {
		setContent(BookContent.TEXT_CONTENT, textContent);
	}

	public String getOther() {
		return getContent(BookContent.OTHER);
	}

	public void setOther(String other) {
		setContent(BookContent.OTHER, other);
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	protected void setAttribute(String key, String value) {
		this.attributes.put(key, value);
	}

	protected String getAttribute(String key) {
		return this.attributes.get(key);
	}

	public String getAuthor() {
		return getAttribute(BookAttribute.AUTHOR.name());
	}

	public void setAuthor(String author) {
		setAttribute(BookAttribute.AUTHOR.name(), author);
	}

	public String getPublisher() {
		return getAttribute(BookAttribute.PUBLISHER.name());
	}

	public void setPublisher(String publisher) {
		setAttribute(BookAttribute.PUBLISHER.name(), publisher);
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
