package com.kooobao.am.facade;

import com.kooobao.am.entity.Document;

public interface DocumentFacade {

	Document getDocument(int docId);
	
	Document storeDocument();
}
