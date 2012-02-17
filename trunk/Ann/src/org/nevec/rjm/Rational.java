package org.nevec.rjm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

class Rational
  implements Cloneable
{
  BigInteger a;
  BigInteger b;
  static BigInteger MAX_INT = new BigInteger("2147483647");
  static BigInteger MIN_INT = new BigInteger("-2147483648");
  static Rational ONE = new Rational(1, 1);
  static Rational ZERO = new Rational();

  static Rational HALF = new Rational(1, 2);

  public Rational()
  {
    this.a = BigInteger.ZERO;
    this.b = BigInteger.ONE;
  }

  public Rational(BigInteger paramBigInteger1, BigInteger paramBigInteger2)
  {
    this.a = paramBigInteger1;
    this.b = paramBigInteger2;
    normalize();
  }

  public Rational(BigInteger paramBigInteger)
  {
    this.a = paramBigInteger;
    this.b = new BigInteger("1");
  }

  public Rational(int paramInt1, int paramInt2)
  {
    this(new BigInteger("" + paramInt1), new BigInteger("" + paramInt2));
  }

  public Rational(String paramString)
    throws NumberFormatException
  {
    this(paramString, 10);
  }

  public Rational(String paramString, int paramInt)
    throws NumberFormatException
  {
    int i = paramString.indexOf("/");
    if (i == -1)
    {
      this.a = new BigInteger(paramString, paramInt);
      this.b = new BigInteger("1", paramInt);
    }
    else
    {
      this.a = new BigInteger(paramString.substring(0, i), paramInt);
      this.b = new BigInteger(paramString.substring(i + 1), paramInt);
      normalize();
    }
  }

  public Rational clone()
  {
    BigInteger localBigInteger1 = new BigInteger("" + this.a);
    BigInteger localBigInteger2 = new BigInteger("" + this.b);
    return new Rational(localBigInteger1, localBigInteger2);
  }

  public Rational multiply(Rational paramRational)
  {
    BigInteger localBigInteger1 = this.a.multiply(paramRational.a);
    BigInteger localBigInteger2 = this.b.multiply(paramRational.b);

    return new Rational(localBigInteger1, localBigInteger2);
  }

  public Rational multiply(BigInteger paramBigInteger)
  {
    Rational localRational = new Rational(paramBigInteger, BigInteger.ONE);
    return multiply(localRational);
  }

  public Rational multiply(int paramInt)
  {
    BigInteger localBigInteger = new BigInteger("" + paramInt);
    return multiply(localBigInteger);
  }

  public Rational pow(int paramInt)
  {
    if (paramInt == 0) {
      return new Rational(1, 1);
    }
    BigInteger localBigInteger1 = this.a.pow(Math.abs(paramInt));
    BigInteger localBigInteger2 = this.b.pow(Math.abs(paramInt));
    if (paramInt > 0) {
      return new Rational(localBigInteger1, localBigInteger2);
    }
    return new Rational(localBigInteger2, localBigInteger1);
  }

  public Rational pow(BigInteger paramBigInteger)
    throws NumberFormatException
  {
    if (paramBigInteger.compareTo(MAX_INT) == 1)
      throw new NumberFormatException("Exponent " + paramBigInteger.toString() + " too large.");
    if (paramBigInteger.compareTo(MIN_INT) == -1) {
      throw new NumberFormatException("Exponent " + paramBigInteger.toString() + " too small.");
    }

    return pow(paramBigInteger.intValue());
  }

  public Rational root(BigInteger paramBigInteger)
    throws NumberFormatException
  {
    if (paramBigInteger.compareTo(MAX_INT) == 1)
      throw new NumberFormatException("Root " + paramBigInteger.toString() + " too large.");
    if (paramBigInteger.compareTo(MIN_INT) == -1) {
      throw new NumberFormatException("Root " + paramBigInteger.toString() + " too small.");
    }
    int i = paramBigInteger.intValue();

    if ((compareTo(ZERO) == -1) && (i % 2 == 0)) {
      throw new NumberFormatException("Negative basis " + toString() + " with odd root " + paramBigInteger.toString());
    }

    int j = (compareTo(ZERO) == -1) && (i % 2 != 0) ? 1 : 0;

    Ifactor localIfactor1 = new Ifactor(this.a.abs());
    Ifactor localIfactor2 = new Ifactor(this.b);
    Rational localRational = localIfactor1.root(i).divide(localIfactor2.root(i));
    if (j != 0) {
      return localRational.negate();
    }
    return localRational;
  }

  public Rational pow(Rational paramRational)
    throws NumberFormatException
  {
    if (paramRational.a.compareTo(BigInteger.ZERO) == 0) {
      return new Rational(1, 1);
    }

    Rational localRational = pow(paramRational.a);
    return localRational.root(paramRational.b);
  }

  public Rational divide(Rational paramRational)
  {
    BigInteger localBigInteger1 = this.a.multiply(paramRational.b);
    BigInteger localBigInteger2 = this.b.multiply(paramRational.a);

    return new Rational(localBigInteger1, localBigInteger2);
  }

  public Rational divide(BigInteger paramBigInteger)
  {
    Rational localRational = new Rational(paramBigInteger, BigInteger.ONE);
    return divide(localRational);
  }

  public Rational divide(int paramInt)
  {
    Rational localRational = new Rational(paramInt, 1);
    return divide(localRational);
  }

  public Rational add(Rational paramRational)
  {
    BigInteger localBigInteger1 = this.a.multiply(paramRational.b).add(this.b.multiply(paramRational.a));
    BigInteger localBigInteger2 = this.b.multiply(paramRational.b);
    return new Rational(localBigInteger1, localBigInteger2);
  }

  public Rational add(BigInteger paramBigInteger)
  {
    Rational localRational = new Rational(paramBigInteger, BigInteger.ONE);
    return add(localRational);
  }

  public Rational negate()
  {
    return new Rational(this.a.negate(), this.b);
  }

  public Rational subtract(Rational paramRational)
  {
    Rational localRational = paramRational.negate();
    return add(localRational);
  }

  public Rational subtract(BigInteger paramBigInteger)
  {
    Rational localRational = new Rational(paramBigInteger, BigInteger.ONE);
    return subtract(localRational);
  }

  public Rational subtract(int paramInt)
  {
    Rational localRational = new Rational(paramInt, 1);
    return subtract(localRational);
  }

  public static Rational binomial(Rational paramRational, BigInteger paramBigInteger)
  {
    if (paramBigInteger.compareTo(BigInteger.ZERO) == 0)
      return ONE;
    Rational localRational = paramRational;
    for (BigInteger localBigInteger = new BigInteger("2"); localBigInteger.compareTo(paramBigInteger) != 1; localBigInteger = localBigInteger.add(BigInteger.ONE))
    {
      localRational = localRational.multiply(paramRational.subtract(localBigInteger.subtract(BigInteger.ONE))).divide(localBigInteger);
    }
    return localRational;
  }

  public static Rational binomial(Rational paramRational, int paramInt)
  {
    if (paramInt == 0)
      return ONE;
    Rational localRational = paramRational;
    for (int i = 2; i <= paramInt; i++)
    {
      localRational = localRational.multiply(paramRational.subtract(i - 1)).divide(i);
    }
    return localRational;
  }

  public BigInteger numer()
  {
    return this.a;
  }

  public BigInteger denom()
  {
    return this.b;
  }

  public Rational abs()
  {
    return new Rational(this.a.abs(), this.b.abs());
  }

  public BigInteger floor()
  {
    if (this.b.compareTo(BigInteger.ONE) == 0)
      return this.a;
    if (this.a.compareTo(BigInteger.ZERO) > 0) {
      return this.a.divide(this.b);
    }
    return this.a.divide(this.b).subtract(BigInteger.ONE);
  }

  public BigInteger trunc()
  {
    if (this.b.compareTo(BigInteger.ONE) == 0) {
      return this.a;
    }
    return this.a.divide(this.b);
  }

  public int compareTo(Rational paramRational)
  {
    BigInteger localBigInteger1 = this.a.multiply(paramRational.b);
    BigInteger localBigInteger2 = paramRational.a.multiply(this.b);
    return localBigInteger1.compareTo(localBigInteger2);
  }

  public int compareTo(BigInteger paramBigInteger)
  {
    Rational localRational = new Rational(paramBigInteger, BigInteger.ONE);
    return compareTo(localRational);
  }

  public String toString()
  {
    if (this.b.compareTo(BigInteger.ONE) != 0) {
      return this.a.toString() + "/" + this.b.toString();
    }
    return this.a.toString();
  }

  public double doubleValue()
  {
    BigDecimal localBigDecimal = new BigDecimal(this.a).divide(new BigDecimal(this.b), MathContext.DECIMAL128);
    return localBigDecimal.doubleValue();
  }

  public float floatValue()
  {
    BigDecimal localBigDecimal = new BigDecimal(this.a).divide(new BigDecimal(this.b), MathContext.DECIMAL128);
    return localBigDecimal.floatValue();
  }

  public BigDecimal BigDecimalValue(MathContext paramMathContext)
  {
    BigDecimal localBigDecimal1 = new BigDecimal(this.a);
    BigDecimal localBigDecimal2 = new BigDecimal(this.b);
    return localBigDecimal1.divide(localBigDecimal2, paramMathContext);
  }

  public String toFString(int paramInt)
  {
    if (this.b.compareTo(BigInteger.ONE) != 0)
    {
      MathContext localMathContext = new MathContext(paramInt, RoundingMode.DOWN);
      BigDecimal localBigDecimal = new BigDecimal(this.a).divide(new BigDecimal(this.b), localMathContext);
      return localBigDecimal.toString();
    }

    return this.a.toString();
  }

  public Rational max(Rational paramRational)
  {
    if (compareTo(paramRational) > 0) {
      return this;
    }
    return paramRational;
  }

  public Rational min(Rational paramRational)
  {
    if (compareTo(paramRational) < 0) {
      return this;
    }
    return paramRational;
  }

  public Rational Pochhammer(BigInteger paramBigInteger)
  {
    if (paramBigInteger.compareTo(BigInteger.ZERO) < 0)
      return null;
    if (paramBigInteger.compareTo(BigInteger.ZERO) == 0) {
      return ONE;
    }

    Rational localRational = new Rational(this.a, this.b);
    BigInteger localBigInteger = BigInteger.ONE;
    for (; localBigInteger.compareTo(paramBigInteger) < 0; localBigInteger = localBigInteger.add(BigInteger.ONE))
      localRational = localRational.multiply(add(localBigInteger));
    return localRational;
  }

  public Rational Pochhammer(int paramInt)
  {
    return Pochhammer(new BigInteger("" + paramInt));
  }

  public boolean isBigInteger()
  {
    return this.b.abs().compareTo(BigInteger.ONE) == 0;
  }

  public boolean isInteger()
  {
    if (!isBigInteger())
      return false;
    return (this.a.compareTo(MAX_INT) <= 0) && (this.a.compareTo(MIN_INT) >= 0);
  }

  public boolean isIntegerFrac()
  {
    return (this.a.compareTo(MAX_INT) <= 0) && (this.a.compareTo(MIN_INT) >= 0) && (this.b.compareTo(MAX_INT) <= 0) && (this.b.compareTo(MIN_INT) >= 0);
  }

  public int signum()
  {
    return this.b.signum() * this.a.signum();
  }

  protected void normalize()
  {
    BigInteger localBigInteger = this.a.gcd(this.b);
    if (localBigInteger.compareTo(BigInteger.ONE) > 0)
    {
      this.a = this.a.divide(localBigInteger);
      this.b = this.b.divide(localBigInteger);
    }
    if (this.b.compareTo(BigInteger.ZERO) == -1)
    {
      this.a = this.a.negate();
      this.b = this.b.negate();
    }
  }
}