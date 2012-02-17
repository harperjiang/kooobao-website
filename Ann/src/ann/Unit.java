package ann;

import java.math.BigDecimal;

public interface Unit {

	BigDecimal process(BigDecimal[] value);

	/**
	 * 
	 * @param output
	 * @param actual
	 * @return Errand value for each input
	 */
	BigDecimal[] feedback(BigDecimal errandBase);
}
