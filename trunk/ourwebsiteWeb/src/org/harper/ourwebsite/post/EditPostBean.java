package org.harper.ourwebsite.post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.harper.ourwebsite.domain.entity.webcnt.Post;
import org.harper.ourwebsite.service.post.PostService;

@ManagedBean
public class EditPostBean {

	private Post content;

	public EditPostBean() {
		content = new Post();
	}

	public Post getContent() {
		return content;
	}

	public String save() {
		getPostService().savePost(getContent());
		return "success";
	}

	@ManagedProperty(value = "#{postService}")
	private PostService postService;

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

}
