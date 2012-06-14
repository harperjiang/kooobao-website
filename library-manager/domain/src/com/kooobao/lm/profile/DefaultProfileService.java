package com.kooobao.lm.profile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;

import com.kooobao.authcenter.service.UserService;
import com.kooobao.common.web.email.TemplateMailMessage;
import com.kooobao.lm.finance.dao.FinanceOperationDao;
import com.kooobao.lm.profile.dao.OperatorDao;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.profile.entity.VisitorType;
import com.kooobao.lm.rule.dao.RuleDao;

public class DefaultProfileService implements ProfileService {

	public Visitor getVisitor(String id) {
		return getVisitorDao().find(id);
	}

	public Visitor saveVisitor(Visitor visitor) {
		return getVisitorDao().store(visitor);
	}

	public Visitor activateUser(String activateId) {
		ActivationRecord actr = getVisitorDao().getActivationRecord(activateId);
		if (null == actr)
			return null;
		Visitor v = getVisitorDao().find(actr.getVisitorId());
		if (null == v || !VisitorStatus.INACTIVE.name().equals(v.getStatus()))
			return null;
		if (VisitorType.INSTITUTE == v.getType())
			v.setStatus(VisitorStatus.NOT_VERIFIED);
		else
			v.setStatus(VisitorStatus.ACTIVE.name());

		if (!StringUtils.isEmpty(v.getRecommendBy())) {
			Visitor recommend = getVisitorDao().find(v.getRecommendBy());
			// Recommendation Reward
			if (null != recommend) {
				getFinanceOperationDao().changeVisitorDeposit(recommend,
						getRuleDao().getRewardRule().getRecommendReward(),
						"Recommend Reward:" + v.getId(), null);
			}
		}
		getVisitorDao().store(v);
		getVisitorDao().removeActivationRecord(actr);
		return v;
	}

	protected String genActivationId() {
		return UUID.randomUUID().toString();
	}

	public void register(String email, String password, VisitorType type,
			String recommendBy) {
		if (null != getVisitorDao().find(email)) {
			throw new UserExistedException();
		}

		Visitor visitor = new Visitor();
		visitor.setId(email);
		visitor.setLevel(1);
		visitor.setType(type);
		visitor.setStatus(VisitorStatus.INACTIVE);
		visitor.setRecommendBy(recommendBy);
		getUserService().register("lm", email, password);
		getVisitorDao().store(visitor);
		ActivationRecord actRecord = new ActivationRecord();
		actRecord.setVisitorId(visitor.getId());
		actRecord.setActivationId(genActivationId());
		getVisitorDao().store(actRecord);
		sendRegMail(email, actRecord.getActivationId());

		// Registration Reward
		getFinanceOperationDao().changeVisitorDeposit(visitor,
				getRuleDao().getRewardRule().getRegisterReward(),
				"Registration", null);
	}

	protected void sendRegMail(String email, String activationId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("activation_id", activationId);
		TemplateMailMessage tmm = new TemplateMailMessage(
				"/com/kooobao/lm/profile/mail/reg_mail.vm", model);
		tmm.setFrom("info@kooobao.cn");
		tmm.setFromName("酷宝图书馆");
		tmm.setSubject("感谢注册酷宝图书馆，请激活您的账户");
		tmm.setTo(new String[] { email });
		getMailSender().send(tmm);
	}

	public void redeem(Visitor visitor, BigDecimal amount) {
		Visitor v = getVisitorDao().find(visitor);
		getFinanceOperationDao()
				.changeVisitorDeposit(v, amount, "Redeem", null);
	}

	public Operator getOperator(String id) {
		return getOperatorDao().find(id);
	}

	private VisitorDao visitorDao;

	private UserService userService;

	private JavaMailSender mailSender;

	private RuleDao ruleDao;

	private FinanceOperationDao financeOperationDao;

	public VisitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(VisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	private OperatorDao operatorDao;

	public OperatorDao getOperatorDao() {
		return operatorDao;
	}

	public void setOperatorDao(OperatorDao operatorDao) {
		this.operatorDao = operatorDao;
	}

	public FinanceOperationDao getFinanceOperationDao() {
		return financeOperationDao;
	}

	public void setFinanceOperationDao(FinanceOperationDao financeOperationDao) {
		this.financeOperationDao = financeOperationDao;
	}

}
