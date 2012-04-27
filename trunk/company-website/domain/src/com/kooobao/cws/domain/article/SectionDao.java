package com.kooobao.cws.domain.article;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface SectionDao extends Dao<Section> {

	List<Section> getSections(String type);
}
