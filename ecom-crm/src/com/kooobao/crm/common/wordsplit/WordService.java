package com.kooobao.crm.common.wordsplit;

import java.util.Collection;

public interface WordService {

	/**
	 * Split and Filter words from company name
	 * 
	 * @param input
	 * @return
	 */
	public Collection<String> splitWord(String input);
}
