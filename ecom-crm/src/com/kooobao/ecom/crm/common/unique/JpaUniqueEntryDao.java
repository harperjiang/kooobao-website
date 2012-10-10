package com.kooobao.ecom.crm.common.unique;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaUniqueEntryDao extends AbstractJpaDao<UniqueItem> implements
		UniqueEntryDao {

	@Override
	public Set<String> find(String category, String key,
			Collection<String> value) {
		Set<String> entries = new HashSet<String>();
		for (String val : value) {
			List<UniqueItem> uis = find(category, key, val);
			for (UniqueItem ui : uis)
				entries.add(ui.getUuid());
		}
		return entries;
	}

	public List<UniqueItem> find(String category, String key, String val) {
		return getEntityManager()
				.createQuery(
						"select ui from UniqueItem ui where ui.category = :c and ui.key = :key and ui.value = :val",
						UniqueItem.class).setParameter("c", category)
				.setParameter("key", key).setParameter("val", val)
				.getResultList();
	}

	@Override
	public String store(UniqueEntry ue) {
		String uuid = UUID.randomUUID().toString();
		for (Entry<String, Collection<String>> entry : ue.getAttributes()
				.entrySet()) {
			for (String val : entry.getValue()) {
				UniqueItem ui = new UniqueItem();
				ui.setCategory(ue.getCategory());
				ui.setUuid(uuid);
				ui.setKey(entry.getKey());
				ui.setValue(val);
				store(ui);
			}
		}
		return uuid;
	}

	@Override
	public void discard(String refId) {
		List<UniqueItem> uis = getEntityManager()
				.createQuery(
						"select ui from UniqueItem ui where ui.uuid = :uuid",
						UniqueItem.class).setParameter("uuid", refId)
				.getResultList();
		for (UniqueItem ui : uis)
			getEntityManager().remove(ui);
	}
}
