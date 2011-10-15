package com.kooobao.gsm.domain.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.jpa.JpaCallback;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public class JpaDeliveryDao extends AbstractJpaDao<Delivery> implements
		DeliveryDao {

	public List<Delivery> search(final SearchBean search) {
		search.validate();
		return getTemplate().execute(new JpaCallback<List<Delivery>>() {
			public List<Delivery> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Delivery> query = cb.createQuery(Delivery.class);
				Root<Delivery> root = query.from(Delivery.class);

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(search.getGroupName())) {
					predicates.add(cb.equal(root.get("order").get("group"),
							search.getGroupName()));
				}

				if (!StringUtils.isEmpty(search.getStatus())) {
					predicates.add(cb.equal(root.get("status"),
							search.getStatus()));
				}

				if (!StringUtils.isEmpty(search.getCustomer())) {
					predicates.add(cb.equal(root.get("order").get("customer"),
							search.getCustomer()));
				}

				if (!StringUtils.isEmpty(search.getContactName())) {
					predicates.add(cb.or(
							cb.equal(root.get("contact").get("name"),
									search.getContactName()),
							cb.equal(
									root.get("order").get("contact")
											.get("name"),
									search.getContactName())));
				}

				if (!CollectionUtils.isEmpty(predicates)) {
					Predicate p = predicates.get(0);
					for (int index = 1; index < predicates.size(); index++)
						p = cb.and(p, predicates.get(index));
					query.where(p);
				}

				return em.createQuery(query).getResultList();
			}
		});
	}
}
