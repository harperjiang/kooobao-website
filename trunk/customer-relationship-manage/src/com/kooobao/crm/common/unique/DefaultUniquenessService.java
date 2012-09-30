package com.kooobao.crm.common.unique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

public class DefaultUniquenessService implements UniquenessService {

	@Override
	public int store(UniqueEntry uniqueEntry) throws UniquenessException {
		String category = uniqueEntry.getCategory();

		List<List<UniqueEntry>> score = new ArrayList<List<UniqueEntry>>();

		for (Entry<String, Collection<String>> entry : uniqueEntry
				.getAttributes().entrySet()) {
			List<UniqueEntry> duplicate = uniqueEntryDao.find(category,
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
		getUniqueEntryDao().store(uniqueEntry);
		return result;
	}

	private UniqueEntryDao uniqueEntryDao;

	public UniqueEntryDao getUniqueEntryDao() {
		return uniqueEntryDao;
	}

	public void setUniqueEntryDao(UniqueEntryDao uniqueEntryDao) {
		this.uniqueEntryDao = uniqueEntryDao;
	}

}
