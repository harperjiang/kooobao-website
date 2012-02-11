package com.kooobao.fr.web.common;

import javax.faces.model.SelectItem;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.RecordStatus;

public class SupportInfoBean extends AbstractBean {

	private SelectItem[] status;

	public SelectItem[] getStatus() {
		if (null != status)
			return status;
		status = new SelectItem[RecordStatus.values().length];
		for (int i = 0; i < status.length; i++) {
			status[i] = new SelectItem(RecordStatus.values()[i].name(),
					StatusUtils.text(RecordStatus.values()[i]));
		}
		return status;
	}
}
