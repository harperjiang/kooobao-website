package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.lm.bizflow.FavouriteService;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;

@ManagedBean(name="myFavBean")
@SessionScoped
public class MyFavBean extends PageSearchBean {

	@Override
	public void onPageLoad() {
		search();
	}

	@Override
	public String search() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		result = getFavouriteService().searchFavoriteRecords(
				myIndexBean.getVisitor());
		return "success";
	}

	public String delete() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		long bookOid = 0;
		try {
			bookOid = Long.parseLong(getParameter("book_id"));
			getFavouriteService().deleteFavorite(myIndexBean.getVisitor(),
					bookOid);
		} catch (Exception e) {
			return "failed";
		}
		// Remove from list,avoiding a research
		for (int i = 0; i < result.size(); i++) {
			if (bookOid == result.get(i).getFavorite().getOid()) {
				result.remove(i);
				break;
			}
		}
		return "success";
	}

	@ManagedProperty("#{favouriteService}")
	private FavouriteService favouriteService;

	public FavouriteService getFavouriteService() {
		return favouriteService;
	}

	public void setFavouriteService(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	private List<FavoriteRecord> result;

	public List<FavoriteRecord> getResult() {
		return result;
	}

	public void setResult(List<FavoriteRecord> result) {
		this.result = result;
	}

}
