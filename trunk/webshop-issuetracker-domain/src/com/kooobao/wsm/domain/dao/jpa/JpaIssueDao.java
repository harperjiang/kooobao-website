package com.kooobao.wsm.domain.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.orm.jpa.JpaCallback;

import com.kooobao.wsm.domain.dao.IssueDao;
import com.kooobao.wsm.domain.entity.issue.Issue;

public class JpaIssueDao extends AbstractJpaDao<Issue> implements IssueDao {

	public List<? extends Issue> findIssues(final FindIssueBean bean) {
		return getTemplate().execute(new JpaCallback<List<? extends Issue>>() {
			public List<? extends Issue> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<? extends Issue> query = cb.createQuery(bean
						.getCategory());
				Root<? extends Issue> root = query.from(bean.getCategory());

				Validate.notNull(bean.getCategory());

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(bean.getCreator())) {
					predicates.add(cb.equal(root.<String> get("creatorId"),
							bean.getCreator()));
				}
				if (!StringUtils.isEmpty(bean.getFollower())) {
					predicates.add(cb.equal(root.<String> get("followerId"),
							bean.getFollower()));
				}
				if (null != bean.getStatus()) {
					predicates.add(root.<String[]> get("status").in(
							(Object[]) bean.getStatus()));
				}
				if (-1 != bean.getLastingDays()) {
					predicates.add(cb.greaterThan(
							cb.function("daydiff", Integer.class,
									cb.function("now", Date.class),
									root.<Date> get("createTime")),
							bean.getLastingDays()));
				}
				if (null != bean.getFromDate()) {
					predicates.add(cb.greaterThan(
							root.<Date> get("createTime"), bean.getFromDate()));
				}
				if (null != bean.getToDate()) {
					predicates.add(cb.lessThan(root.<Date> get("createTime"),
							bean.getToDate()));
				}
				if (!CollectionUtils.isEmpty(predicates)) {
					Predicate p = predicates.get(0);
					for (int index = 1; index < predicates.size(); index++)
						p = cb.and(p, predicates.get(index));
					query.where(p);
				}
				TypedQuery<? extends Issue> q = em.createQuery(query);
				return q.getResultList();
			}
		});
	}
}
