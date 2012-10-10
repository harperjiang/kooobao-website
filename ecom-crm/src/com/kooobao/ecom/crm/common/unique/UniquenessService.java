package com.kooobao.ecom.crm.common.unique;

public interface UniquenessService {

	/**
	 * Check and store a unique entry
	 * 
	 * @param uniqueEntry
	 * @throws UniquenessException
	 *             if the entry is not unique in the given category
	 */
	UniqueResult store(UniqueEntry uniqueEntry) throws UniquenessException;

	/**
	 * Discard the entry by given id
	 * 
	 * @param refId
	 */
	void discardEntry(String refId);

}
