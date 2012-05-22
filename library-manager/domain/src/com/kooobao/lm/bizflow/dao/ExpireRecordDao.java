package com.kooobao.lm.bizflow.dao;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.SearchBean;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.profile.entity.Visitor;

public interface ExpireRecordDao extends Dao<ExpireRecord> {

	PageSearchResult<ExpireRecord> search(Visitor visitor, SearchBean searchBean);

	ExpireRecord findByTransaction(Transaction transaction);

	Cursor<ExpireRecord> findActivateRecords();

}
