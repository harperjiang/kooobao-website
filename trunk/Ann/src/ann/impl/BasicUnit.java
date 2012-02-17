package ann.impl;

import java.math.BigDecimal;

import ann.Unit;

public abstract class BasicUnit implements Unit {

	private BigDecimal[] weights;
	
	private transient BigDecimal[] lastInputs;
	
	private transient BigDecimal lastOutput;

	static final BigDecimal step = new BigDecimal("0.05");

	public BigDecimal[] getWeights() {
		return weights;
	}

	public BasicUnit() {
		this(1);
	}

	public BasicUnit(int inputSize) {
		this(init(inputSize));
	}

	public BasicUnit(BigDecimal[] weights) {
		super();
		this.weights = weights;
	}

	static BigDecimal[] init(int inputSize) {
		BigDecimal[] value = new BigDecimal[inputSize];
		for (int i = 0; i < inputSize; i++)
			value[i] = new BigDecimal("0.05");
		return value;
	}

	public BigDecimal[] getLastInputs() {
		return lastInputs;
	}

	protected void setLastInputs(BigDecimal[] lastInputs) {
		this.lastInputs = lastInputs;
	}

	public BigDecimal getLastOutput() {
		return lastOutput;
	}

	protected void setLastOutput(BigDecimal lastOutput) {
		this.lastOutput = lastOutput;
	}
	
	
}
