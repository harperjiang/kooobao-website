package com.kooobao.gsm.domain.dao.memory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public class MemoryDeliveryDao extends AbstractMemoryDao<Delivery> implements
		DeliveryDao {

	@Override
	public List<Delivery> search(String groupName, String customer,
			String contactName) {
		List<Delivery> result = new ArrayList<Delivery>();
		for (Delivery d : getStorage().values()) {
			if ((StringUtils.isEmpty(groupName) || d.getOrder().getGroup()
					.equals(groupName))
					&& (StringUtils.isEmpty(customer) || customer.equals(d
							.getOrder().getCustomer()))
					&& (StringUtils.isEmpty(contactName)
							|| contactName.equals(d.getOrder().getContact()
									.getName()) || contactName.equals(d
							.getContact().getName())))
				result.add(d);
		}
		return result;
	}
}
