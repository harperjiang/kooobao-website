package com.kooobao.ecom.crm.customer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.service.Context;
import com.kooobao.ecom.crm.ErrorCode;
import com.kooobao.ecom.crm.common.ChangeHelper;
import com.kooobao.ecom.crm.common.unique.UniqueEntry;
import com.kooobao.ecom.crm.common.unique.UniqueResult;
import com.kooobao.ecom.crm.common.unique.UniquenessException;
import com.kooobao.ecom.crm.common.unique.UniquenessService;
import com.kooobao.ecom.crm.common.wordsplit.WordService;
import com.kooobao.ecom.crm.customer.dao.CustomerDao;
import com.kooobao.ecom.crm.customer.dao.HintDao;
import com.kooobao.ecom.crm.customer.entity.Hint;
import com.kooobao.ecom.crm.customer.entity.HintFollowup;
import com.kooobao.ecom.crm.customer.entity.HintStatus;
import com.kooobao.ecom.crm.setting.CustomerSetting;
import com.kooobao.ecom.setting.dao.SettingDao;

public class DefaultHintService implements HintService {

	@Override
	public void freeHints() {
		Context context = new Context();
		context.setOperatorId("SYSTEM");
		Cursor<Hint> hints = getHintDao().getOvertimeHints(
				getSettingDao().getSetting(CustomerSetting.class)
						.getHintRetainTime());
		while (hints.hasNext()) {
			Hint hint = hints.next();
			updateStatus(context, hint, HintStatus.FREE, "Over protection");
		}
	}

	@Override
	public Hint addHint(Context context, Hint hint) {
		checkDuplicate(hint);
		if (HintStatus.SUSPEND == hint.getStatus()) {
			// Need human revise, do nothing now
		}
		hint.setRegisterBy(context.getOperatorId());
		return getHintDao().store(hint);
	}

	protected void checkDuplicate(Hint hint) {
		// Construct Uniqueness Entry
		UniqueEntry ue = new UniqueEntry();
		ue.setCategory("HINT");
		// Split and filter words
		Collection<String> nameKeys = getWordService()
				.splitWord(hint.getName());

		ue.getAttributes().put("NAME", nameKeys);

		List<String> phones = new ArrayList<String>();
		phones.add(hint.getContact().getPhone());
		List<String> qqs = new ArrayList<String>();
		qqs.add(hint.getContact().getIm());

		ue.getAttributes().put("CONTACT-PHONE", phones);
		ue.getAttributes().put("CONTACT-QQ", qqs);

		// Store to uniqueness store
		try {
			UniqueResult result = getUniquenessService().store(ue);
			if (result.getScore() != 0)
				hint.setStatus(HintStatus.SUSPEND);
			else
				hint.setStatus(HintStatus.FOLLOWUP);
			hint.setRefId(result.getUuid());
		} catch (UniquenessException e) {
			throw new DuplicateHintException(ErrorCode.HINT_DUPLICATE.name());
		}
	}

	@Override
	public void followup(Context context, HintFollowup followup) {
		Validate.isTrue(
				followup.getHint().getOwnBy().equals(context.getOperatorId()),
				ErrorCode.HINT_UPDATE_NOT_OWN.name());
		Validate.isTrue(
				!followup.getHint().getStatus().equals(HintStatus.CUSTOMER),
				ErrorCode.HINT_UPDATE_ALREADY_CUSTOMER.name());

		Hint copy = getHintDao().find(followup.getHint().getOid());
		followup.setOwnBy(context.getOperatorId());
		copy.addFollowup(followup);
	}

	@Override
	public List<Hint> getAvailableHints(Context context) {
		return getHintDao().getHints(context.getOperatorId());
	}

	@Override
	public int request(Context context) {
		CustomerSetting cs = getSettingDao().getSetting(CustomerSetting.class);
		int current = getAvailableHints(context).size();
		if (cs.getHintLimit() <= current)
			return 0;
		List<Hint> hints = getHintDao().getFreeHints(
				cs.getHintLimit() - current);
		updateStatus(context, hints, HintStatus.FOLLOWUP, "Request");
		return hints.size();
	}

	protected void updateStatus(Context context, List<Hint> hints,
			HintStatus newstatus, String comment) {
		for (Hint hint : hints) {
			updateStatus(context, hint, newstatus, comment);
		}
	}

	protected void updateStatus(Context context, Hint hint,
			HintStatus newstatus, String comment) {
		hint.setOwnBy(context.getOperatorId());
		hint.setUpdateTime(new Date());
		HintStatus old = hint.getStatus();
		hint.setStatus(newstatus);

		HintFollowup hfu = new HintFollowup();
		hfu.setOwnBy(context.getOperatorId());
		hfu.setCreateTime(new Date());
		hfu.setComment(comment);
		hfu.setReference(MessageFormat.format("Status: {0} -> {1}", old.name(),
				newstatus.name()));

	}

	@Override
	public int exchange(Context context, List<Hint> hints) {
		// TODO Limit exchange size
		for (Hint hint : hints) {
			updateStatus(context, hint, HintStatus.FREE, "Exchange");
		}
		List<Hint> newHints = getHintDao().getFreeHints(hints.size());
		updateStatus(context, newHints, HintStatus.FOLLOWUP, "Exchange");
		return newHints.size();
	}

	@Override
	public void discard(Context context, Hint hint, String comment) {
		updateStatus(context, hint, HintStatus.DISCARDED, comment);
		getUniquenessService().discardEntry(hint.getRefId());
	}

	@Override
	public void revise(Context context, Hint hint, boolean pass) {
		if (pass) {
			updateStatus(context, hint, HintStatus.FREE, "Revise Pass");
		} else {
			discard(context, hint, "Revise Reject");
		}
	}

	@Override
	public Hint update(Context context, Hint hint) {
		// Record follow up
		Validate.isTrue(context.getOperatorId().equals(hint.getOwnBy()),
				ErrorCode.HINT_UPDATE_NOT_OWN.name());
		Hint old = getHintDao().find(hint.getOid());
		HintFollowup hfu = generateChange(old, hint);
		if (null != hfu) {
			hint.addFollowup(hfu);
		}
		return getHintDao().store(hint);
	}

	private HintFollowup generateChange(Hint old, Hint newhint) {
		HintFollowup hfu = new HintFollowup();
		hfu.setCreateTime(new Date());

		ChangeHelper.checkInvalidChange(old, newhint, new String[] { "id",
				ErrorCode.HINT_UPDATE_FORBID.name(), "registerBy",
				ErrorCode.HINT_UPDATE_FORBID.name() });

		String change = ChangeHelper.generateChange(old, newhint, new String[] {
				"name", "Name: {0} -> {1}", "contact.name",
				"Contact Name: {0} -> {1}", "contact.email",
				"Contact Email: {0} -> {1}", "contact.phone",
				"Contact Phone: {0} -> {1}", "contact.address",
				"Contact Address: {0} -> {1}", "contact.im",
				"Contact Im: {0} -> {1}" });
		if (!StringUtils.isEmpty(change)) {
			hfu.setReference(change);
			return hfu;
		}
		return null;
	}

	private HintDao hintDao;

	private CustomerDao customerDao;

	private SettingDao settingDao;

	private UniquenessService uniquenessService;

	private WordService wordService;

	public HintDao getHintDao() {
		return hintDao;
	}

	public void setHintDao(HintDao hintDao) {
		this.hintDao = hintDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	public UniquenessService getUniquenessService() {
		return uniquenessService;
	}

	public void setUniquenessService(UniquenessService uniquenessService) {
		this.uniquenessService = uniquenessService;
	}

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

}
