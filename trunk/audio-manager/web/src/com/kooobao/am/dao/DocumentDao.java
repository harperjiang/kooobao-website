package com.kooobao.am.dao;

import com.kooobao.am.entity.Document;
import com.kooobao.common.domain.dao.Dao;

public interface DocumentDao extends Dao<Document> {

	Document getDocument(int docId);
	
	Document storeDocument();
}
