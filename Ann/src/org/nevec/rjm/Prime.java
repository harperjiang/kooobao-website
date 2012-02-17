package org.nevec.rjm;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Vector;

class Prime
{
  static Vector<BigInteger> a = new Vector();

  static BigInteger nMax = new BigInteger("-1");

  public Prime()
  {
    if (a.size() == 0)
    {
      a.add(new BigInteger("2"));
      a.add(new BigInteger("3"));
      a.add(new BigInteger("5"));
      a.add(new BigInteger("7"));
      a.add(new BigInteger("11"));
      a.add(new BigInteger("13"));
      a.add(new BigInteger("17"));
    }
    nMax = (BigInteger)a.lastElement();
  }

  public boolean contains(BigInteger paramBigInteger)
  {
    switch (millerRabin(paramBigInteger))
    {
    case -1:
      return false;
    case 1:
      return true;
    }
    growto(paramBigInteger);
    return a.contains(paramBigInteger);
  }

  public boolean isSPP(BigInteger paramBigInteger1, BigInteger paramBigInteger2)
  {
    BigInteger localBigInteger1 = new BigInteger("2");

    if (paramBigInteger1.compareTo(localBigInteger1) == -1) {
      return false;
    }

    if (paramBigInteger1.compareTo(localBigInteger1) == 0) {
      return true;
    }

    if (paramBigInteger1.remainder(localBigInteger1).compareTo(BigInteger.ZERO) == 0) {
      return false;
    }

    BigInteger localBigInteger2 = paramBigInteger1.subtract(BigInteger.ONE);
    int i = localBigInteger2.getLowestSetBit();
    BigInteger localBigInteger3 = localBigInteger2.shiftRight(i);

    if (paramBigInteger2.modPow(localBigInteger3, paramBigInteger1).compareTo(BigInteger.ONE) == 0) {
      return true;
    }

    for (int j = 0; j < i; j++)
    {
      if (paramBigInteger2.modPow(localBigInteger3.shiftLeft(j), paramBigInteger1).compareTo(localBigInteger2) == 0)
        return true;
    }
    return false;
  }

  public int millerRabin(BigInteger paramBigInteger)
  {
    String[] arrayOfString = { "2047", "1373653", "25326001", "3215031751", "2152302898747", "3474749660383", "341550071728321" };

    int i = 0;
    while (i < arrayOfString.length)
    {
      int j = paramBigInteger.compareTo(new BigInteger(arrayOfString[i]));
      if (j < 0)
      {
        break;
      }
      if (j == 0)
        return -1;
      i++;
    }

    if (i == arrayOfString.length) {
      return 0;
    }

    for (int j = 0; j <= i; j++)
      if (!isSPP(paramBigInteger, at(j)))
        return -1;
    return 1;
  }

  public BigInteger at(int paramInt)
  {
    while (paramInt >= a.size())
    {
      growto(nMax.add(new BigInteger("5")));
    }
    return (BigInteger)a.elementAt(paramInt);
  }

  public BigInteger pi(BigInteger paramBigInteger)
  {
    growto(paramBigInteger);
    BigInteger localBigInteger = new BigInteger("0");
    for (int i = 0; i < a.size(); i++)
      if (((BigInteger)a.elementAt(i)).compareTo(paramBigInteger) <= 0)
        localBigInteger = localBigInteger.add(BigInteger.ONE);
    return localBigInteger;
  }

  public BigInteger nextprime(BigInteger paramBigInteger)
  {
    if (paramBigInteger.compareTo(BigInteger.ONE) <= 0) {
      return (BigInteger)a.elementAt(0);
    }

    while (((BigInteger)a.lastElement()).compareTo(paramBigInteger) <= 0)
    {
      growto(nMax.add(new BigInteger("5")));
    }
    for (int i = 0; i < a.size(); i++)
      if (((BigInteger)a.elementAt(i)).compareTo(paramBigInteger) == 1)
        return (BigInteger)a.elementAt(i);
    return (BigInteger)a.lastElement();
  }

  public BigInteger prevprime(BigInteger paramBigInteger)
  {
    if (paramBigInteger.compareTo(BigInteger.ONE) <= 0) {
      return BigInteger.ZERO;
    }

    while (((BigInteger)a.lastElement()).compareTo(paramBigInteger) < 0) {
      growto(nMax.add(new BigInteger("5")));
    }
    for (int i = 0; i < a.size(); i++)
      if (((BigInteger)a.elementAt(i)).compareTo(paramBigInteger) >= 0)
        return (BigInteger)a.elementAt(i - 1);
    return (BigInteger)a.lastElement();
  }

  protected void growto(BigInteger paramBigInteger)
  {
    while (nMax.compareTo(paramBigInteger) == -1)
    {
      nMax = nMax.add(BigInteger.ONE);
      int i = 1;
      for (int j = 0; j < a.size(); j++)
      {
        if (((BigInteger)a.get(j)).multiply((BigInteger)a.get(j)).compareTo(nMax) == 1)
        {
          break;
        }

        if (nMax.remainder((BigInteger)a.get(j)).compareTo(BigInteger.ZERO) != 0)
          continue;
        i = 0;
        break;
      }

      if (i != 0)
        a.add(nMax);
    }
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    Prime localPrime = new Prime();
    for (int i = 0; i < 200; i++)
    {
      System.out.println(i + " " + localPrime.at(i).toString());
    }
  }
}