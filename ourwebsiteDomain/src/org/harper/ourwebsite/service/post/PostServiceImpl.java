package org.harper.ourwebsite.service.post;

import org.harper.frm.dao.CommonDao;
import org.harper.ourwebsite.domain.entity.webcnt.Post;

public class PostServiceImpl implements PostService {

	public void savePost(Post post) {
		// TODO Auto-generated method stub

	}

	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
