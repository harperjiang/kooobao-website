package com.kooobao.gsm.domain.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public interface DeliveryDao extends Dao<Delivery> {

	public List<Delivery> search(String groupName, String status,
			String customer, String contactName);

}
