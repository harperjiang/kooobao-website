package com.kooobao.ecom.crm.common.unique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public class DefaultUniquenessService implements UniquenessService {

	@Override
	public UniqueResult store(UniqueEntry uniqueEntry)
			throws UniquenessException {
		String category = uniqueEntry.getCategory();

		List<Set<String>> score = new ArrayList<Set<String>>();

		for (Entry<String, Collection<String>> entry : uniqueEntry
				.getAttributes().entrySet()) {
			Set<String> duplicate = uniqueEntryDao.find(category,
					entry.getKey(), entry.getValue());
			if (!CollectionUtils.isEmpty(duplicate))
				score.add(duplicate);
		}
		// Calculate the score
		// If same element occurred in different categories, increase the score
		int result = 0;
		for (int i = 0; i < score.size(); i++) {
			result += score.get(i).size();
		}
		for (int i = 0; i < score.size(); i++) {
			for (int j = i + 1; j < score.size(); j++)
				result += 100 * CollectionUtils.intersection(score.get(i),
						score.get(j)).size();
		}

		// Threshold for warning
		if (result > 100)
			throw new UniquenessException(UniquenessException.DUPLICATE);
		UniqueResult ur = new UniqueResult();
		String uuid = getUniqueEntryDao().store(uniqueEntry);
		ur.setUuid(uuid);
		ur.setScore(result);
		return ur;
	}

	@Override
	public void discardEntry(String refId) {
		getUniqueEntryDao().discard(refId);
	}

	private UniqueEntryDao uniqueEntryDao;

	public UniqueEntryDao getUniqueEntryDao() {
		return uniqueEntryDao;
	}

	public void setUniqueEntryDao(UniqueEntryDao uniqueEntryDao) {
		this.uniqueEntryDao = uniqueEntryDao;
	}

}
