package com.kooobao.ecom.crm.customer.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.Dao;
import com.kooobao.ecom.crm.customer.entity.Hint;

public interface HintDao extends Dao<Hint> {

	List<Hint> getHints(String operatorId);

	List<Hint> getFreeHints(int i);

	Cursor<Hint> getOvertimeHints(int hintRetainTime);

}
