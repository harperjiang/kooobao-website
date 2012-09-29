package com.kooobao.crm.common.unique;


public interface UniquenessDao {

	/**
	 * Check and store a unique entry
	 * 
	 * @param uniqueEntry
	 * @throws UniquenessException
	 *             if the entry is not unique in the given category
	 */
	void store(UniqueEntry uniqueEntry) throws UniquenessException;

}
