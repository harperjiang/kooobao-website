package org.nevec.rjm;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Vector;

class Bernoulli
{
  static Vector<Rational> a = new Vector();
  static Factorial factorial = new Factorial();

  public Bernoulli()
  {
    if (a.size() == 0)
    {
      a.add(Rational.ONE);
      a.add(new Rational(1, 6));
    }
  }

  protected void set(int paramInt, Rational paramRational)
  {
    int i = paramInt / 2;
    if (i < a.size()) {
      a.set(i, paramRational);
    }
    else {
      while (a.size() < i)
        a.add(Rational.ZERO);
      a.add(paramRational);
    }
  }

  public Rational at(int paramInt)
  {
    if (paramInt == 1)
      return new Rational(-1, 2);
    if (paramInt % 2 != 0) {
      return Rational.ZERO;
    }

    int i = paramInt / 2;
    if (a.size() <= i)
    {
      for (int j = 2 * a.size(); j <= paramInt; j += 2) {
        set(j, doubleSum(j));
      }
    }
    return (Rational)a.elementAt(i);
  }

  private Rational doubleSum(int paramInt)
  {
    Rational localRational1 = Rational.ZERO;
    for (int i = 0; i <= paramInt; i++)
    {
      Rational localRational2 = Rational.ZERO;
      BigInteger localBigInteger1 = BigInteger.ONE;
      for (int j = 0; j <= i; j++)
      {
        BigInteger localBigInteger2 = new BigInteger("" + j).pow(paramInt);

        if (j % 2 == 0)
          localRational2 = localRational2.add(localBigInteger1.multiply(localBigInteger2));
        else {
          localRational2 = localRational2.subtract(localBigInteger1.multiply(localBigInteger2));
        }

        localBigInteger1 = localBigInteger1.multiply(new BigInteger("" + (i - j))).divide(new BigInteger("" + (j + 1)));
      }

      localRational1 = localRational1.add(localRational2.divide(new BigInteger("" + (i + 1))));
    }
    return localRational1;
  }

  public static void main(String[] paramArrayOfString)
  {
    int i = new Integer(paramArrayOfString[0]).intValue();
    Bernoulli localBernoulli = new Bernoulli();
    System.out.println(localBernoulli.at(i).toString());
  }
}