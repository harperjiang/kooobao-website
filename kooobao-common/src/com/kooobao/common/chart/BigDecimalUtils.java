package com.kooobao.common.chart;

import java.math.BigDecimal;

public class BigDecimalUtils {

	public static BigDecimal max(BigDecimal[] array) {
		BigDecimal max = null;
		for (BigDecimal b : array) {
			if (b != null && (max == null || max.compareTo(b) < 0))
				max = b;
		}
		return max;
	}

	public static BigDecimal min(BigDecimal[] array) {
		BigDecimal min = null;
		for (BigDecimal b : array) {
			if (b != null && (min == null || min.compareTo(b) > 0))
				min = b;
		}
		return min;
	}

	public static BigDecimal[] subarray(BigDecimal[] content, int start,
			int stop) {
		BigDecimal[] result = new BigDecimal[stop - start];
		System.arraycopy(content, start, result, 0, result.length);
		return result;
	}
}
