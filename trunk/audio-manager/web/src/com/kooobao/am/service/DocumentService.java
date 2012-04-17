package com.kooobao.am.service;

import com.kooobao.am.entity.Document;

public interface DocumentService {

	Document saveDocument(Document doc);
	
	String getDocumentPath(int docId);
}
