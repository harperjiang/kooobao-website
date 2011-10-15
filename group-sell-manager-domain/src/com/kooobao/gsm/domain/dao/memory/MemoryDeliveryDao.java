package com.kooobao.gsm.domain.dao.memory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public class MemoryDeliveryDao extends AbstractMemoryDao<Delivery> implements
		DeliveryDao {

	public List<Delivery> search(SearchBean search) {
		List<Delivery> result = new ArrayList<Delivery>();
		for (Delivery d : getStorage().values()) {
			if ((StringUtils.isEmpty(search.getGroupName()) || d.getOrder().getGroup()
					.equals(search.getGroupName()))
					&& (StringUtils.isEmpty(search.getCustomer()) || search.getCustomer().equals(d
							.getOrder().getCustomer())
					&& (StringUtils.isEmpty(search.getContactName())
							|| search.getContactName().equals(d.getOrder().getContact()
									.getName()) || search.getContactName().equals(d
							.getContact().getName()))
					&& (StringUtils.isEmpty(search.getStatus()) || search.getStatus().equals(d
							.getStatus()))))
				result.add(d);
		}
		return result;
	}
}
