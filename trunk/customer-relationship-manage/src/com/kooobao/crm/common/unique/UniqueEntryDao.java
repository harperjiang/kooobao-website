package com.kooobao.crm.common.unique;

import java.util.Collection;
import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface UniqueEntryDao extends Dao<UniqueEntry> {

	List<UniqueEntry> find(String category, String key, Collection<String> value);

}
