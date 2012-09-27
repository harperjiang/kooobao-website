package com.kooobao.dm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;

@SessionScoped
@ManagedBean(name = "listFileBean")
public class ListFileBean extends AbstractBean {

	private FileItem root;

	private List<FileItem> children;

	@Override
	public void onPageLoad() {
		children = getDownloadService().listFiles(root);
	}

	public FileItem getRoot() {
		return root;
	}

	public void setRoot(FileItem root) {
		this.root = root;
	}

	public List<FileItem> getChildren() {
		return children;
	}

	public void setChildren(List<FileItem> children) {
		this.children = children;
	}

	@ManagedProperty("#{downloadService}")
	private DownloadService downloadService;

	public DownloadService getDownloadService() {
		return downloadService;
	}

	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	public String enter() {
		Long fileId = Long.parseLong(getParameter("file_id"));

		root = getDownloadService().getFile(fileId);

		return "success";
	}

	public String upLevel() {
		if (null != root)
			root = root.getParent();
		return "success";
	}

	public String refresh() {
		if (null == root)
			getDownloadService().indexFiles();
		else {
			getDownloadService().indexFile(root);
		}
		return "success";
	}
}
