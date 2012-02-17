package org.nevec.rjm;

import java.math.BigInteger;
import java.util.Vector;

class Factorial
{
  static Vector<BigInteger> a = new Vector();

  public Factorial()
  {
    if (a.size() == 0)
    {
      a.add(BigInteger.ONE);
      a.add(BigInteger.ONE);
    }
  }

  public BigInteger at(int paramInt)
  {
    while (a.size() <= paramInt)
    {
      int i = a.size() - 1;
      BigInteger localBigInteger = new BigInteger("" + (i + 1));
      a.add(((BigInteger)a.elementAt(i)).multiply(localBigInteger));
    }
    return (BigInteger)a.elementAt(paramInt);
  }

  public static void main(String[] paramArrayOfString) throws Exception
  {
    Factorial localFactorial = new Factorial();
    for (int i = 0; i < 70; i++)
      System.out.println(i + " " + localFactorial.at(i).toString());
  }
}