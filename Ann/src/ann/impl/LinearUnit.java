package ann.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;

public class LinearUnit extends BasicUnit {

	@Override
	public BigDecimal process(BigDecimal[] value) {
		setLastInputs(value);
		Validate.isTrue(value.length == getWeights().length - 1);
		BigDecimal sum = getWeights()[0];
		for (int i = 0; i < value.length; i++) {
			sum = sum.add(getWeights()[i + 1].multiply(value[i]));
		}
		setLastOutput(sum);
		return sum;
	}

	protected BigDecimal getErrand(BigDecimal base) {
		return base;
	}

	public BigDecimal[] feedback(BigDecimal errandBase) {
		BigDecimal errandSum = BigDecimal.ZERO;
		BigDecimal[] errands = new BigDecimal[getWeights().length - 1];
		BigDecimal[] deltaW = new BigDecimal[getWeights().length - 1];

		BigDecimal weightSum = BigDecimal.ZERO;
		BigDecimal errand = getErrand(errandBase);
		for (int i = 0; i < getLastInputs().length; i++) {
			BigDecimal pwfEnet = errandBase.multiply(getLastInputs()[i]);
			deltaW[i] = step.multiply(pwfEnet);
			errandSum = errandSum.add(pwfEnet);
			weightSum = weightSum.add(getWeights()[i + 1]);
		}
		getWeights()[0] = step.multiply(errand);
		for (int i = 0; i < getLastInputs().length; i++) {
			errands[i] = getWeights()[i + 1].multiply(errandSum).divide(
					weightSum);
			getWeights()[i + 1] = getWeights()[i + 1].add(deltaW[i]);
		}
		return errands;
	}
}
