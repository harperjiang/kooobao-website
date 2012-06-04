package com.kooobao.lm.rule;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.lm.profile.entity.Visitor;

public interface RuleService {

	Date getExpirePeriod(Visitor visitor);

	BigDecimal getPenalty(Visitor visitor);
}
