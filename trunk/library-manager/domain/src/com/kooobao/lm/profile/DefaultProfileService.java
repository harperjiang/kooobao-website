package com.kooobao.lm.profile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;

import com.kooobao.authcenter.service.UserService;
import com.kooobao.common.web.email.TemplateMailMessage;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.rule.dao.RuleDao;

public class DefaultProfileService implements ProfileService {

	public Visitor getVisitor(String id) {
		return getVisitorDao().find(id);
	}

	public Visitor saveVisitor(Visitor visitor) {
		return getVisitorDao().store(visitor);
	}

	public boolean activateUser(String activateId) {
		ActivationRecord actr = getVisitorDao().getActivationRecord(activateId);
		if (null == actr)
			return false;
		Visitor v = getVisitorDao().find(actr.getVisitorId());
		if (null == v || !VisitorStatus.INACTIVE.name().equals(v.getStatus()))
			return false;
		v.setStatus(VisitorStatus.ACTIVE.name());
		getVisitorDao().store(v);
		getVisitorDao().removeActivationRecord(actr);
		return true;
	}
	
	protected String genActivationId() {
		return UUID.randomUUID().toString();
	}

	public void register(String email, String password) {
		Visitor visitor = new Visitor();
		visitor.setId(email);
		visitor.setLevel(1);
		visitor.setStatus(VisitorStatus.INACTIVE);
		getUserService().register("lm", email, password);
		getVisitorDao().store(visitor);
		ActivationRecord actRecord = new ActivationRecord();
		actRecord.setVisitorId(visitor.getId());
		actRecord.setActivationId(genActivationId());
		getVisitorDao().store(actRecord);
		sendRegMail(email, actRecord.getActivationId());
	}

	protected void sendRegMail(String email, String activationId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("activation_id", activationId);
		TemplateMailMessage tmm = new TemplateMailMessage("reg_mail.vm", model);
		tmm.setFrom("info@kooobao.cn");
		tmm.setSubject("感谢注册酷宝图书馆，请激活您的账户");
		tmm.setTo(new String[] { email });
		getMailSender().send(tmm);
	}

	public void redeem(Visitor visitor, BigDecimal amount) {
		Visitor v = getVisitorDao().find(visitor);
		BigDecimal resultAmount = v.getDeposit().add(amount);
		int newLevel = getRuleDao().getVisitorLevelRule().getLevel(resultAmount);
		v.setLevel(newLevel);
		v.setDeposit(resultAmount);
	}

	private VisitorDao visitorDao;

	private UserService userService;

	private JavaMailSender mailSender;

	private RuleDao ruleDao;

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

}
