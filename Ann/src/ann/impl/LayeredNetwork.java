package ann.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;

import ann.DataSet;
import ann.Network;
import ann.StudyCase;
import ann.Unit;

public class LayeredNetwork implements Network {

	private List<List<Unit>> layers;

	private Unit[] inputs;

	private Unit[] outputs;

	public LayeredNetwork() {
		layers = new ArrayList<List<Unit>>();
	}

	@Override
	public Unit[] getInputUnits() {
		if (null != inputs)
			return inputs;
		List<Unit> firstLayer = layers.get(0);
		Unit[] result = new Unit[firstLayer.size()];
		inputs = firstLayer.toArray(result);
		return inputs;
	}

	@Override
	public Unit[] getOutputUnits() {
		if (null != outputs) {
			return outputs;
		}
		List<Unit> firstLayer = layers.get(layers.size() - 1);
		Unit[] result = new Unit[firstLayer.size()];
		outputs = firstLayer.toArray(result);
		return outputs;
	}

	@Override
	public void study(StudyCase studyCase) {
		DataSet output = process(studyCase);

		// Calculate Errand
		BigDecimal[] errand = new BigDecimal[output.getData().length];
		for (int i = 0; i < errand.length; i++) {
			errand[i] = studyCase.getOutputs()[i].subtract(output.getData()[i]);
		}

		// Feedback Process
		List<BigDecimal[]> buffer = new ArrayList<BigDecimal[]>();
		for (int i = 0; i < layers.size(); i++) {
			List<Unit> currentLayer = layers.get(layers.size() - 1 - i);
			for (int u = 0; u < errand.length; u++) {
				Unit unit = currentLayer.get(u);
				buffer.add(unit.feedback(errand[u]));
			}
			BigDecimal[] newval = new BigDecimal[buffer.get(0).length];
			for (int index = 0; index < newval.length; index++)
				newval[index] = BigDecimal.ZERO;
			for (BigDecimal[] errpart : buffer) {
				for (int index = 0; index < newval.length; index++) {
					newval[index] = newval[index].add(errpart[index]);
				}
			}
			errand = newval;
		}
	}

	@Override
	public boolean graduated() {
		return false;
	}

	@Override
	public DataSet process(DataSet event) {
		Validate.isTrue(event.getData().length == getInputUnits().length);

		// First go through the network to get output
		List<BigDecimal[]> currentInputs = new ArrayList<BigDecimal[]>();
		for (BigDecimal input : event.getData())
			currentInputs.add(new BigDecimal[] { input });
		Iterator<BigDecimal[]> inputIterator = currentInputs.iterator();
		BigDecimal[] layerOutput = null;
		for (int i = 0; i < layers.size(); i++) {
			List<Unit> currentLayer = layers.get(i);
			List<BigDecimal> currentOutput = new ArrayList<BigDecimal>();
			// Get Output of current layer
			for (Unit unit : currentLayer) {
				currentOutput.add(unit.process(inputIterator.next()));
			}
			// Calculate Next Layer's input based on connections
			layerOutput = new BigDecimal[currentOutput.size()];
			currentOutput.toArray(layerOutput);
			inputIterator = new RepeatIterator(layerOutput);
		}

		return new BasicDataSet(layerOutput);
	}

	static class RepeatIterator implements Iterator<BigDecimal[]> {

		private BigDecimal[] value;

		public RepeatIterator(BigDecimal[] value) {
			this.value = value;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public BigDecimal[] next() {
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
