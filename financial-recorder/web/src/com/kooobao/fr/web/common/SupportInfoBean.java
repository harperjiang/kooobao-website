package com.kooobao.fr.web.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.RecordStatus;

public class SupportInfoBean extends AbstractBean {

	private List<SelectItem> status;

	public List<SelectItem> getStatus() {
		if (null != status)
			return status;
		status = new ArrayList<SelectItem>();
		for (int i = 0; i < RecordStatus.values().length; i++) {
			status.add(new SelectItem(RecordStatus.values()[i].name(),
					StatusUtils.text(RecordStatus.values()[i])));
		}
		return status;
	}
}
