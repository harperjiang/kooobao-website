package com.kooobao.lm.book.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_book")
public class Book extends SimpleEntity {

	@Column(name = "isbn")
	private String isbn;

	@OneToOne
	@JoinColumn(name = "category")
	private Category category;

	@ElementCollection
	@CollectionTable(name = "lm_book_tag", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "obj_oid"))
	@Column(name = "tag", columnDefinition = "varchar(25)")
	private List<String> tags;

	@Column(name = "picture_url")
	private String pictureUrl;

	@Column(name = "name")
	private String name;

	@Column(name = "rating")
	private int rating;

	@Column(name = "list_price", columnDefinition = "decimal(10,2)")
	private BigDecimal listPrice;

	@ElementCollection
	@CollectionTable(name = "lm_book_attr", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "obj_oid"))
	@Column(name = "attr", columnDefinition = "varchar(25)")
	@MapKeyColumn(name = "attr")
	private Map<String, String> attributes = new HashMap<String, String>();

	@ElementCollection
	@CollectionTable(name = "lm_book_content", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "obj_oid"))
	@Column(name = "content", columnDefinition = "text")
	@MapKeyColumn(name = "content")
	private Map<String, String> content = new HashMap<String, String>();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
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

	@Transient
	private transient String displayName;

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
