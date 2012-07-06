package com.kooobao.common.util;

import java.math.BigDecimal;

public class NumberFormatter {

	public static BigDecimal format(BigDecimal number) {
		if (null == number)
			return null;
		return number.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
