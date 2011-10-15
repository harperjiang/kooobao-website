package com.kooobao.gsm.domain.dao.xml;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlException;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.gsm.domain.dao.SupportDao;
import com.kooobao.gsm.domain.entity.rule.DefaultGrossWeightRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRuleType;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;
import com.kooobao.gsm.domain.entity.rule.ExpressRuleType;
import com.kooobao.gsm.domain.entity.rule.FixedRuleType;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.domain.entity.rule.RulesDocument;
import com.kooobao.gsm.domain.entity.rule.da.ExpressRule;
import com.kooobao.gsm.domain.entity.rule.da.FixedAmountRule;

public class XmlSupportDao extends AbstractJpaDao<VersionEntity> implements
		SupportDao {

	private Map<String, List<DeliveryAmountRule>> rules;

	public DeliveryAmountRule getAmountRule(DeliveryTarget target) {
		synchronized (this) {
			if (null == rules || rules.isEmpty()) {
				rules = new HashMap<String, List<DeliveryAmountRule>>();

				List<DeliveryAmountRule> ruleData = buildDeliveryAmountRuleList();
				for (DeliveryAmountRule rule : ruleData) {
					if (!rules.containsKey(rule.getDeliveryMethod())) {
						rules.put(rule.getDeliveryMethod(),
								new ArrayList<DeliveryAmountRule>());
					}
					rules.get(rule.getDeliveryMethod()).add(rule);
				}
			}
		}
		List<DeliveryAmountRule> ruleList = rules.get(target
				.getDeliveryMethod());
		if (null == ruleList)
			return null;
		for (DeliveryAmountRule rule : ruleList) {
			if (rule.match(target)) {
				return rule;
			}
		}
		return null;
	}

	private List<DeliveryAmountRule> buildDeliveryAmountRuleList() {
		List<DeliveryAmountRule> ruleList = new ArrayList<DeliveryAmountRule>();

		try {
			RulesDocument document = RulesDocument.Factory
					.parse(XmlSupportDao.class
							.getResourceAsStream("delivery-amount-rule.xml"));
			for (DeliveryAmountRuleType element : document.getRules()
					.getRuleArray()) {
				DeliveryAmountRule rule = null;
				if (element instanceof ExpressRuleType) {
					ExpressRuleType type = (ExpressRuleType) element;
					ExpressRule erule = new ExpressRule();
					erule.setStart(new BigDecimal(type.getStart()));
					erule.setNext(new BigDecimal(type.getNext()));
					rule = erule;
				}
				if (element instanceof FixedRuleType) {
					FixedRuleType type = (FixedRuleType) element;
					FixedAmountRule frule = new FixedAmountRule();
					frule.setPrice(new BigDecimal(type.getAmount()));
					rule = frule;
				}
				rule.setArea(element.getArea());
				rule.setDeliveryMethod(element.getDeliveryMethod());
				rule.setSumCeiling(new BigDecimal(element.getCeilingAmount()));
				rule.setSumFloor(new BigDecimal(element.getFloorAmount()));
				rule.setWeightCeiling(new BigDecimal(element.getCeilingWeight()));
				rule.setWeightFloor(new BigDecimal(element.getFloorWeight()));
				ruleList.add(rule);
			}
		} catch (IOException e) {
			LogFactory.getLog(getClass()).error("Cannot find rule xmls");
		} catch (XmlException e) {
			LogFactory.getLog(getClass()).error("Cannot read rule xmls");

		}
		return ruleList;
	}

	private GrossWeightRule weightRule;

	public synchronized GrossWeightRule getWeightRule(DeliveryTarget target) {
		if (null == weightRule) {
			weightRule = new DefaultGrossWeightRule(new BigDecimal("1.3"));
		}
		return weightRule;
	}
}
