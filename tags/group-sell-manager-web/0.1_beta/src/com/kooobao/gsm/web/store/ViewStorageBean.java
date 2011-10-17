package com.kooobao.gsm.web.store;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.gsm.domain.entity.store.Storage;

@ManagedBean
@SessionScoped
public class ViewStorageBean {

	private List<Storage> storage;

	public List<Storage> getStorage() {
		return storage;
	}

	public void setStorage(List<Storage> storage) {
		this.storage = storage;
	}

}
