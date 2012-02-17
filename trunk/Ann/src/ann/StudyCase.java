package ann;

import java.math.BigDecimal;

public interface StudyCase extends DataSet {

	BigDecimal[] getInputs();

	BigDecimal[] getOutputs();
}
