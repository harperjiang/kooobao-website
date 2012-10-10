package com.kooobao.crm.common.unique;

import java.util.Collection;
import java.util.Set;

import com.kooobao.common.domain.dao.Dao;

public interface UniqueEntryDao extends Dao<UniqueItem> {

	Set<String> find(String category, String key, Collection<String> value);

	String store(UniqueEntry ue);

	void discard(String refId);
}
