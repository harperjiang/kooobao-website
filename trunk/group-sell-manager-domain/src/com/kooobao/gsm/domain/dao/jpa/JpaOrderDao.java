package com.kooobao.gsm.domain.dao.jpa;

import java.math.BigDecimal;
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
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.order.Order;

public class JpaOrderDao extends AbstractJpaDao<Order> implements OrderDao {

	public List<Order> searchOrders(final SearchBean search) {
		search.validate();

		return getTemplate().execute(new JpaCallback<List<Order>>() {
			public List<Order> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Order> query = cb.createQuery(Order.class);
				Root<Order> root = query.from(Order.class);

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(search.getGroupName())) {
					predicates.add(cb.equal(root.get("group"),
							search.getGroupName()));
				}

				if (!StringUtils.isEmpty(search.getContact())) {
					predicates.add(cb.equal(root.get("contact").get("name"),
							search.getContact()));
				}

				if (!StringUtils.isEmpty(search.getCustomer())) {
					predicates.add(cb.equal(root.get("customer"),
							search.getCustomer()));
				}

				if (search.getStatus() != null && search.getStatus().length > 0) {
					predicates.add(root.get("status").in(
							(Object[]) search.getStatus()));
				}

				if (search.getDeliveryStatus() != null
						&& search.getDeliveryStatus().length > 0) {
					predicates.add(root.get("deliveryStatus").in(
							(Object[]) search.getDeliveryStatus()));
				}
				if (!StringUtils.isEmpty(search.getRefNumber())) {
					if (search.getRefNumber().contains("-")) {
						String[] fromto = search.getRefNumber().split("-");
						predicates.add(cb.between(root
								.<BigDecimal> get("refNumber"), new BigDecimal(
								fromto[0]), new BigDecimal(fromto[1])));
					} else if (search.getRefNumber().startsWith(">")) {

					} else {
						predicates.add(cb.equal(root.get("refNumber"),
								search.getRefNumber()));
					}
				}

				if (search.isFindProblematic()) {
					predicates.add(cb.notEqual(root.get("paidAmount"),
							root.get("totalAmount")));
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
