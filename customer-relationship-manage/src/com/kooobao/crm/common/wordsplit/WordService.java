package com.kooobao.crm.common.wordsplit;

public interface WordService {

	/**
	 * Split and Filter words from company name
	 * 
	 * @param input
	 * @return
	 */
	public String[] splitWord(String input);
}
