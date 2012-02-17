package ann.impl;

import java.math.BigDecimal;

import org.nevec.rjm.BigDecimalMath;

public class SigmondUnit extends LinearUnit {

	@Override
	public BigDecimal process(BigDecimal[] value) {
		BigDecimal linearResult = super.process(value);
		return BigDecimal.ONE.divide(BigDecimal.ONE.add(BigDecimalMath
				.exp(linearResult.negate())));
	}

	@Override
	protected BigDecimal getErrand(BigDecimal errandBase) {
		return errandBase.multiply(getLastOutput()).multiply(
				BigDecimal.ONE.divide(getLastOutput()));
	}
}
