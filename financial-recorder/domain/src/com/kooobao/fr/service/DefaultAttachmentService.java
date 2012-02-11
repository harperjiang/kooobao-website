package com.kooobao.fr.service;

import javax.persistence.NoResultException;

import com.kooobao.fr.domain.dao.AttachmentDao;
import com.kooobao.fr.domain.entity.Attachment;

public class DefaultAttachmentService implements AttachmentService {

	public Attachment getAttachment(String oid) {
		try {
			return getAttachmentDao().find(Integer.valueOf(oid));
		} catch (NumberFormatException e) {
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	private AttachmentDao attachmentDao;

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

}
