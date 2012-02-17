package org.nevec.rjm;

import java.math.BigInteger;
import java.util.Vector;

class Ifactor implements Cloneable {
	public BigInteger n;
	public Vector<Integer> primeexp;

	public Ifactor(int paramInt) {
		this.n = new BigInteger("" + paramInt);
		this.primeexp = new Vector();
		if (paramInt > 1) {
			int i = 0;
			Prime localPrime = new Prime();

			while (paramInt > 1) {
				int j = 0;
				int k = localPrime.at(i).intValue();
				while (paramInt % k == 0) {
					j++;
					paramInt /= k;
					if (paramInt == 1)
						break;
				}
				this.primeexp.add(new Integer(j));
				i++;
			}
		} else if (paramInt == 1) {
			this.primeexp.add(new Integer(0));
		}
	}

	public Ifactor(BigInteger paramBigInteger) {
		this.n = paramBigInteger;
		this.primeexp = new Vector();
		if (paramBigInteger.compareTo(BigInteger.ONE) == 0) {
			this.primeexp.add(new Integer(0));
		} else {
			int i = 0;
			Prime localPrime = new Prime();

			while (paramBigInteger.compareTo(BigInteger.ONE) == 1) {
				int j = 0;
				BigInteger localBigInteger = localPrime.at(i);
				while (paramBigInteger.remainder(localBigInteger).compareTo(
						BigInteger.ZERO) == 0) {
					j++;
					paramBigInteger = paramBigInteger.divide(localBigInteger);
					if (paramBigInteger.compareTo(BigInteger.ONE) == 0)
						break;
				}
				this.primeexp.add(Integer.valueOf(j));
				i++;
			}
		}
	}

	public Ifactor(Vector<Integer> paramVector) {
		this.primeexp = paramVector;
		if (paramVector.size() > 0) {
			this.n = BigInteger.ONE;
			int i = 0;
			Prime localPrime = new Prime();

			for (int j = 0; j < this.primeexp.size(); j++) {
				int k = 0;
				if (((Integer) this.primeexp.elementAt(j)).intValue() != 0) {
					BigInteger localBigInteger = localPrime.at(i);
					this.n = this.n.multiply(localBigInteger
							.pow(((Integer) this.primeexp.elementAt(j))
									.intValue()));
				}
				i++;
			}
		} else {
			this.n = BigInteger.ZERO;
		}
	}

	public Ifactor(Ifactor paramIfactor) {
		this.n = paramIfactor.n;
		this.primeexp = paramIfactor.primeexp;
	}

	public Ifactor clone() {
		Vector localVector = (Vector) this.primeexp.clone();
		return new Ifactor(localVector);
	}

	public boolean equals(Ifactor paramIfactor) {
		return this.n.compareTo(paramIfactor.n) == 0;
	}

	public Ifactor multiply(Ifactor paramIfactor) {
		Vector localVector = new Vector();
		if ((this.primeexp.size() != 0) && (paramIfactor.primeexp.size() != 0)) {
			int i = 0;
			for (; i < paramIfactor.primeexp.size(); i++) {
				if (i < this.primeexp.size()) {
					int j = Integer.valueOf(
							((Integer) this.primeexp.elementAt(i)).intValue())
							.intValue();
					int k = Integer.valueOf(
							((Integer) paramIfactor.primeexp.elementAt(i))
									.intValue()).intValue();
					localVector.add(new Integer(j + k));
				} else {
					localVector.add(paramIfactor.primeexp.elementAt(i));
				}
			}
			for (; i < this.primeexp.size(); i++) {
				localVector.add(this.primeexp.elementAt(i));
			}
		}
		return new Ifactor(localVector);
	}

	public Ifactor multiply(BigInteger paramBigInteger) {
		return multiply(new Ifactor(paramBigInteger));
	}

	public Ifactor divide(Ifactor paramIfactor) {
		return new Ifactor(this.n.divide(paramIfactor.n));
	}

	public Ifactor add(BigInteger paramBigInteger) {
		return new Ifactor(this.n.add(paramBigInteger));
	}

	public Ifactor pow(int paramInt) {
		Vector localVector = new Vector();
		if (this.primeexp.size() != 0) {
			for (int i = 0; i < this.primeexp.size(); i++)
				localVector.add(new Integer(paramInt
						* ((Integer) this.primeexp.elementAt(i)).intValue()));
		}
		return new Ifactor(localVector);
	}

	public Rational root(int paramInt) throws ArithmeticException {
		if (paramInt == 0)
			throw new ArithmeticException("Cannot pull zeroth root of "
					+ toString());
		if (paramInt < 0) {
			Rational localObject = root(-paramInt);
			return Rational.ONE.divide((Rational) localObject);
		}

		Object localObject = new Vector();
		if (this.primeexp.size() != 0) {
			for (int i = 0; i < this.primeexp.size(); i++) {
				if (((Integer) this.primeexp.elementAt(i)).intValue()
						% paramInt != 0) {
					throw new ArithmeticException("Cannot pull " + paramInt
							+ "th root of " + toString());
				}
				((Vector) localObject).add(new Integer(((Integer) this.primeexp
						.elementAt(i)).intValue() / paramInt));
			}
		}

		Ifactor localIfactor = new Ifactor((Vector) localObject);
		return (Rational) new Rational(localIfactor.n);
	}

	public Ifactor lcm(Ifactor paramIfactor) {
		Vector localVector = new Vector();
		if ((this.primeexp.size() != 0) && (paramIfactor.primeexp.size() != 0)) {
			int i = 0;
			for (; i < paramIfactor.primeexp.size(); i++) {
				if (i < this.primeexp.size()) {
					int j = Integer.valueOf(
							((Integer) this.primeexp.elementAt(i)).intValue())
							.intValue();
					int k = Integer.valueOf(
							((Integer) paramIfactor.primeexp.elementAt(i))
									.intValue()).intValue();
					int m = j > k ? j : k;
					localVector.add(new Integer(m));
				} else {
					localVector.add(paramIfactor.primeexp.elementAt(i));
				}
			}
			for (; i < this.primeexp.size(); i++) {
				localVector.add(this.primeexp.elementAt(i));
			}
		}
		return new Ifactor(localVector);
	}

	private Ifactor sigmaIncompl() {
		Vector localVector = new Vector();
		if (this.n.compareTo(BigInteger.ONE) <= 0) {
			return new Ifactor(localVector);
		}

		Ifactor localIfactor1 = dropPrime();

		int i = Integer.valueOf(
				((Integer) this.primeexp.lastElement()).intValue()).intValue();

		Ifactor localIfactor2 = localIfactor1.sigmaIncompl();

		int j = i + 1;

		Prime localPrime = new Prime();
		BigInteger localBigInteger1 = localPrime.at(this.primeexp.size() - 1);

		BigInteger localBigInteger2 = localBigInteger1.pow(j)
				.subtract(BigInteger.ONE)
				.divide(localBigInteger1.subtract(BigInteger.ONE));

		localIfactor2 = localIfactor2.multiply(localBigInteger2);

		localBigInteger2 = localBigInteger2.subtract(BigInteger.ONE);
		return localIfactor2.add(localBigInteger2);
	}

	public Ifactor sigma() {
		if (this.n.compareTo(BigInteger.ZERO) != 0) {
			return sigmaIncompl().add(BigInteger.ONE);
		}
		return this;
	}

	public Ifactor dropPrime() {
		Vector localVector = new Vector();

		if (this.n.compareTo(BigInteger.ONE) == 0) {
			localVector.add(new Integer(0));
		} else if (this.n.compareTo(BigInteger.ONE) == 1) {
			int i = this.primeexp.size() - 2;
			for (; i >= 0; i--) {
				if (((Integer) this.primeexp.elementAt(i)).intValue() > 0)
					break;
			}
			if (i >= 0)
				for (int j = 0; j <= i; j++)
					localVector.add(this.primeexp.elementAt(j));
			else
				localVector.add(new Integer(0));
		}
		return new Ifactor(localVector);
	}

	public Ifactor gcd(Ifactor paramIfactor) {
		return new Ifactor(this.n.gcd(paramIfactor.n));
	}

	public boolean issquare() {
		int i = 1;
		for (int j = 0; j < this.primeexp.size(); j++) {
			if (((Integer) this.primeexp.elementAt(j)).intValue() % 2 != 0)
				return false;
		}
		return true;
	}

	public int bigomega() {
		int i = 0;
		for (int j = 0; j < this.primeexp.size(); j++)
			i += ((Integer) this.primeexp.elementAt(j)).intValue();
		return i;
	}

	public int omega() {
		int i = 0;
		for (int j = 0; j < this.primeexp.size(); j++)
			if (((Integer) this.primeexp.elementAt(j)).intValue() > 0)
				i++;
		return i;
	}

	public BigInteger core() {
		BigInteger localBigInteger = new BigInteger("1");
		Prime localPrime = new Prime();
		for (int i = 0; i < this.primeexp.size(); i++)
			if (((Integer) this.primeexp.elementAt(i)).intValue() % 2 != 0)
				localBigInteger = localBigInteger.multiply(localPrime.at(i));
		return localBigInteger;
	}

	public int moebius() {
		if (this.n.compareTo(BigInteger.ONE) <= 0) {
			return 1;
		}
		int i = 1;
		for (int j = 0; j < this.primeexp.size(); j++) {
			int k = ((Integer) this.primeexp.elementAt(j)).intValue();
			if (k > 1)
				return 0;
			if (k == 1) {
				i *= -1;
			}
		}

		return i;
	}

	public Ifactor max(Ifactor paramIfactor) {
		if (this.n.compareTo(paramIfactor.n) >= 0) {
			return this;
		}
		return paramIfactor;
	}

	public Ifactor min(Ifactor paramIfactor) {
		if (this.n.compareTo(paramIfactor.n) <= 0) {
			return this;
		}
		return paramIfactor;
	}

	public static Ifactor max(Vector<Ifactor> paramVector) {
		Ifactor localIfactor = (Ifactor) paramVector.elementAt(0);
		for (int i = 1; i < paramVector.size(); i++)
			localIfactor = localIfactor.max((Ifactor) paramVector.elementAt(i));
		return localIfactor;
	}

	public static Ifactor min(Vector<Ifactor> paramVector) {
		Ifactor localIfactor = (Ifactor) paramVector.elementAt(0);
		for (int i = 1; i < paramVector.size(); i++)
			localIfactor = localIfactor.min((Ifactor) paramVector.elementAt(i));
		return localIfactor;
	}

	public String toString() {
		String str = new String(this.n.toString() + ":");
		Prime localPrime = new Prime();
		int i = 1;
		for (int j = 0; j < this.primeexp.size(); j++) {
			if (((Integer) this.primeexp.elementAt(j)).intValue() == 0)
				continue;
			if (i == 0)
				str = str + "*";
			if (((Integer) this.primeexp.elementAt(j)).intValue() > 1)
				str = str + localPrime.at(j).toString() + "^"
						+ ((Integer) this.primeexp.elementAt(j)).toString();
			else
				str = str + localPrime.at(j).toString();
			i = 0;
		}

		return str;
	}

	public static void main(String[] paramArrayOfString) throws Exception {
		for (int i = 0; i < 20; i++) {
			Ifactor localIfactor1 = new Ifactor(i);
			System.out.println(localIfactor1.toString());
		}
		int i;
		int j;
		Ifactor localIfactor3;
		Ifactor localIfactor4;
		Ifactor localIfactor5;
		for (i = 0; i < 20; i++) {
			for (j = 0; j < 20; j++) {
				localIfactor3 = new Ifactor(i);
				localIfactor4 = new Ifactor(j);
				localIfactor5 = localIfactor3.lcm(localIfactor4);
				System.out.println(localIfactor3.toString() + " "
						+ localIfactor4.toString() + " lcm "
						+ localIfactor5.toString());
			}

		}

		for (i = 0; i < 20; i++) {
			for (j = 0; j < 20; j++) {
				localIfactor3 = new Ifactor(i);
				localIfactor4 = new Ifactor(j);
				localIfactor5 = localIfactor3.gcd(localIfactor4);
				System.out.println(localIfactor3.toString() + " "
						+ localIfactor4.toString() + " gcd "
						+ localIfactor5.toString());
			}
		}
		Ifactor localIfactor2;
		for (i = 0; i < 20; i++) {
			localIfactor2 = new Ifactor(i);
			System.out.println(localIfactor2.toString() + " "
					+ localIfactor2.issquare());
		}

		for (i = 0; i < 30; i++) {
			localIfactor2 = new Ifactor(i);
			System.out.println(localIfactor2.toString() + " "
					+ localIfactor2.dropPrime());
		}
		int k;
		for (i = 0; i < 5; i++) {
			localIfactor2 = new Ifactor(i);
			for (k = 0; k < 3; k++) {
				System.out.println(localIfactor2.toString() + " " + k + " "
						+ localIfactor2.pow(k));
			}

		}

		for (i = 0; i < 5; i++) {
			localIfactor2 = new Ifactor(i);
			for (k = 0; k < 7; k++) {
				localIfactor4 = new Ifactor(k);
				System.out.println(i + " * " + k + " "
						+ localIfactor2.multiply(localIfactor4));
			}

		}

		for (i = 0; i < 30; i++) {
			localIfactor2 = new Ifactor(i);
			System.out.println(localIfactor2.toString() + " "
					+ localIfactor2.sigmaIncompl());
		}

		for (i = 0; i < 30; i++) {
			localIfactor2 = new Ifactor(i);
			System.out.println(localIfactor2.toString() + " "
					+ localIfactor2.sigma());
		}
	}
}