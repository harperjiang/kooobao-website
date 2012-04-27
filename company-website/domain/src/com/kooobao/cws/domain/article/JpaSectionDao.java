package com.kooobao.cws.domain.article;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaSectionDao extends AbstractJpaDao<Section> implements
		SectionDao {

	@Override
	public List<Section> getSections(String type) {
		return getEntityManager()
				.createQuery(
						"select s from Section s where s.type = :type order by s.oid",
						Section.class).setParameter("type", type)
				.getResultList();
	}

}
