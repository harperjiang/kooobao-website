package org.nevec.rjm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.security.ProviderException;

public class BigDecimalMath {
	static BigDecimal E = new BigDecimal(
			"2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193200305992181741359662904357290033429526059563073813232862794349076323382988075319525101901157383418793070215408914993488416750924476146066808226480016847741185374234544243710753907774499206955170276183860626133138458300075204493382656029760673711320070932870912744374704723069697720931014169283681902551510865746377211125238978442505695369677078544996996794686445490598793163688923009879312773617821542499922957635148220826989519366803318252886939849646510582093923982948879332036250944311730123819706841614039701983767932068328237646480429531180232878250981945581530175671736133206981125099618188159304169035159888851934580727386673858942287922849989208680582574927961048419844436346324496848756023362482704197862320900216099023530436994184914631409343173814364054625315209618369088870701676839642437814059271456354906130310720851038375051011574770417189861068739696552126715468895703503540212340784981933432106817012100562788023519303322474501585390473041995777709350366041699732972508868769664035557071622684471625607988265178713419512466520103059212366771943252786753985589448969709640975459185695638023637016211204774272283648961342251644507818244235294863637214174023889344124796357437026375529444833799801612549227850925778256209262264832627793338656648162772516401910590049164499828931");

	static BigDecimal PI = new BigDecimal(
			"3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196442881097566593344612847564823378678316527120190914564856692346034861045432664821339360726024914127372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160943305727036575959195309218611738193261179310511854807446237996274956735188575272489122793818301194912983367336244065664308602139494639522473719070217986094370277053921717629317675238467481846766940513200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892354201995611212902196086403441815981362977477130996051870721134999999837297804995105973173281609631859502445945534690830264252230825334468503526193118817101000313783875288658753320838142061717766914730359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019893809525720106548586327886593615338182796823030195203530185296899577362259941389124972177528347913151557485724245415069595082953311686172785588907509838175463746493931925506040092770167113900984882401285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949129331367702898915210475216205696602405803815019351125338243003558764024749647326391419927260426992279678235478163600934172164121992458631503028618297455570674983850549458858692699569092721079750930295532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380008164706001614524919217321721477235014");

	static BigDecimal GAMMA = new BigDecimal(
			"0.57721566490153286060651209008240243104215933593992359880576723488486772677766467093694706329174674951463144724980708248096050401448654283622417399764492353625350033374293733773767394279259525824709491600873520394816567085323315177661152862119950150798479374508570574002992135478614669402960432542151905877553526733139925401296742051375413954911168510280798423487758720503843109399736137255306088933126760017247953783675927135157722610273492913940798430103417771778088154957066107501016191663340152278935867965497252036212879226555953669628176388792726801324310104765059637039473949576389065729679296010090151251959509222435014093498712282479497471956469763185066761290638110518241974448678363808617494551698927923018773910729457815543160050021828440960537724342032854783670151773943987003023703395183286900015581939880427074115422278197165230110735658339673487176504919418123000406546931429992977795693031005030863034185698032310836916400258929708909854868257773642882539549258736295961332985747393023734388470703702844129201664178502487333790805627549984345907616431671031467107223700218107450444186647591348036690255324586254422253451813879124345735013612977822782881489459098638460062931694718871495875254923664935204732436410972682761608775950880951262084045444779922991572482925162512784276596570832146102982146179519579590959227042089896279712553632179488737642106606070659825619901028807561251991375116782176436190570584407835735015800560774579342131449885007864151716151945");

	static BigDecimal LOG2 = new BigDecimal(
			"0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586332699641868754200148102057068573368552023575813055703267075163507596193072757082837143519030703862389167347112335011536449795523912047517268157493206515552473413952588295045300709532636664265410423915781495204374043038550080194417064167151864471283996817178454695702627163106454615025720740248163777338963855069526066834113727387372292895649354702576265209885969320196505855476470330679365443254763274495125040606943814710468994650622016772042452452961268794654619316517468139267250410380254625965686914419287160829380317271436778265487756648508567407764845146443994046142260319309673540257444607030809608504748663852313818167675143866747664789088143714198549423151997354880375165861275352916610007105355824987941472950929311389715599820565439287170007218085761025236889213244971389320378439353088774825970171559107088236836275898425891853530243634214367061189236789192372314672321720534016492568727477823445353476481149418642386776774406069562657379600867076257199184734022651462837904883062033061144630073719489002743643965002580936519443041191150608094879306786515887090060520346842973619384128965255653968602219412292420757432175748909770675268711581705113700915894266547859596489065305846025866838294002283300538207400567705304678700184162404418833232798386349001563121889560650553151272199398332030751408426091479001265168243443893572472788205486271552741877243002489794540196187233980860831664811490930667519339312890431641370681397776498176974868903887789991296503619270710889264105230924783917373501229842420499568935992206602204654941510613");

	private static int TAYLOR_NTERM = 8;

	public static void main(String[] paramArrayOfString) {
		if (paramArrayOfString[0].compareTo("exp") == 0) {
			int i = new Integer(paramArrayOfString[1]).intValue();
			MathContext localMathContext1 = new MathContext(i);
			try {
				System.out.println(exp(localMathContext1).toString());
			} catch (ArithmeticException localArithmeticException21) {
				System.out.println(localArithmeticException21.getMessage());
			}
		} else {
			Object localObject1;
			if (paramArrayOfString[0].compareTo("asin") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(asin((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException1) {
					System.out.println(localArithmeticException1.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("acos") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(acos((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException2) {
					System.out.println(localArithmeticException2.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("atan") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(atan((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException3) {
					System.out.println(localArithmeticException3.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("cos") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(cos((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException4) {
					System.out.println(localArithmeticException4.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("cosh") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(cosh((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException5) {
					System.out.println(localArithmeticException5.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("acosh") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(acosh((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException6) {
					System.out.println(localArithmeticException6.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("Gamma") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				try {
					System.out.println(Gamma((BigDecimal) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException7) {
					System.out.println(localArithmeticException7.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("pochhammer") == 0) {
				localObject1 = new BigDecimal(paramArrayOfString[1]);
				int i2 = new Integer(paramArrayOfString[2]).intValue();
				try {
					System.out
							.println(pochhammer((BigDecimal) localObject1, i2)
									.toString());
				} catch (ArithmeticException localArithmeticException22) {
					System.out.println(localArithmeticException22.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("gamma") == 0) {
				localObject1 = new MathContext(new Integer(
						paramArrayOfString[1]).intValue());
				try {
					System.out.println(gamma((MathContext) localObject1)
							.toString());
				} catch (ArithmeticException localArithmeticException8) {
					System.out.println(localArithmeticException8.getMessage());
				}
			} else if (paramArrayOfString[0].compareTo("psi") == 0) {
				int j = new Integer(paramArrayOfString[1]).intValue();
				double d = new Double(paramArrayOfString[2]).doubleValue();
				try {
					System.out.println(psi(j, d));
				} catch (ArithmeticException localArithmeticException30) {
					System.out.println(localArithmeticException30.getMessage());
				}
			} else {
				Object localObject2;
				Object localObject3;
				if (paramArrayOfString[0].compareTo("hypot") == 0) {
					localObject2 = new BigDecimal(paramArrayOfString[1]);

					localObject3 = new BigDecimal(paramArrayOfString[2]);
					try {
						System.out.println(hypot((BigDecimal) localObject2,
								(BigDecimal) localObject3).toString());
					} catch (ArithmeticException localArithmeticException23) {
						System.out.println(localArithmeticException23
								.getMessage());
					}
				} else {
					BigDecimal localBigDecimal1;
					if (paramArrayOfString[0].compareTo("log") == 0) {
						if (paramArrayOfString.length >= 3) {
							if (paramArrayOfString[1].contains("/")) {
								localObject2 = new Rational(
										paramArrayOfString[1]);
								localObject3 = new MathContext(new Integer(
										paramArrayOfString[2]).intValue());
								try {
									System.out.println(log(
											(Rational) localObject2,
											(MathContext) localObject3)
											.toString());
								} catch (ArithmeticException localArithmeticException24) {
									System.out
											.println(localArithmeticException24
													.getMessage());
								}
							} else {
								int k = new Integer(paramArrayOfString[1])
										.intValue();
								localObject3 = new MathContext(new Integer(
										paramArrayOfString[2]).intValue());
								try {
									System.out.println(log(k,
											(MathContext) localObject3)
											.toString());
								} catch (ArithmeticException localArithmeticException25) {
									System.out
											.println(localArithmeticException25
													.getMessage());
								}
							}
						} else {
							localBigDecimal1 = new BigDecimal(
									paramArrayOfString[1]);
							try {
								System.out.println(log(localBigDecimal1)
										.toString());
							} catch (ArithmeticException localArithmeticException9) {
								System.out.println(localArithmeticException9
										.getMessage());
							}
						}
					} else if (paramArrayOfString[0].compareTo("mod2pi") == 0) {
						localBigDecimal1 = new BigDecimal(paramArrayOfString[1]);
						try {
							System.out.println(mod2pi(localBigDecimal1)
									.toString());
						} catch (ArithmeticException localArithmeticException10) {
							System.out.println(localArithmeticException10
									.getMessage());
						}
					} else if (paramArrayOfString[0].compareTo("modpi") == 0) {
						localBigDecimal1 = new BigDecimal(paramArrayOfString[1]);
						try {
							System.out.println(modpi(localBigDecimal1)
									.toString());
						} catch (ArithmeticException localArithmeticException11) {
							System.out.println(localArithmeticException11
									.getMessage());
						}
					} else {
						Object localObject4;
						if (paramArrayOfString[0].compareTo("pow") == 0) {
							localBigDecimal1 = new BigDecimal(
									paramArrayOfString[1]);
							localObject4 = new Rational(paramArrayOfString[2]);
							try {
								System.out.println(powRound(localBigDecimal1,
										(Rational) localObject4).toString());
							} catch (ArithmeticException localArithmeticException26) {
								System.out.println(localArithmeticException26
										.getMessage());
							}
						} else if (paramArrayOfString[0].compareTo("root") == 0) {
							int m = new Integer(paramArrayOfString[1])
									.intValue();
							localObject4 = new BigDecimal(paramArrayOfString[2]);
							try {
								System.out.println(root(m,
										(BigDecimal) localObject4).toString());
							} catch (ArithmeticException localArithmeticException27) {
								System.out.println(localArithmeticException27
										.getMessage());
							}
						} else {
							BigDecimal localBigDecimal2;
							if (paramArrayOfString[0].compareTo("sin") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(sin(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException12) {
									System.out
											.println(localArithmeticException12
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("sinh") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(sinh(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException13) {
									System.out
											.println(localArithmeticException13
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("asinh") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(asinh(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException14) {
									System.out
											.println(localArithmeticException14
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("sqrt") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(sqrt(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException15) {
									System.out
											.println(localArithmeticException15
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("cbrt") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(cbrt(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException16) {
									System.out
											.println(localArithmeticException16
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("tan") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(tan(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException17) {
									System.out
											.println(localArithmeticException17
													.getMessage());
								}
							} else if (paramArrayOfString[0].compareTo("tanh") == 0) {
								localBigDecimal2 = new BigDecimal(
										paramArrayOfString[1]);
								try {
									System.out.println(tanh(localBigDecimal2)
											.toString());
								} catch (ArithmeticException localArithmeticException18) {
									System.out
											.println(localArithmeticException18
													.getMessage());
								}
							} else {
								int n;
								if (paramArrayOfString[0].compareTo("zeta") == 0) {
									n = new Integer(paramArrayOfString[1])
											.intValue();
									int i3 = new Integer(paramArrayOfString[2])
											.intValue();
									try {
										MathContext localMathContext3 = new MathContext(
												i3);
										System.out.println(zeta(n,
												localMathContext3).toString());
									} catch (ArithmeticException localArithmeticException28) {
										System.out
												.println(localArithmeticException28
														.getMessage());
									}
								} else if (paramArrayOfString[0]
										.compareTo("zeta1") == 0) {
									n = new Integer(paramArrayOfString[1])
											.intValue();
									try {
										System.out.println(zeta1(n));
									} catch (ArithmeticException localArithmeticException19) {
										System.out
												.println(localArithmeticException19
														.getMessage());
									}
								} else if (paramArrayOfString[0]
										.compareTo("scalePrec") == 0) {
									BigDecimal localBigDecimal3 = new BigDecimal(
											paramArrayOfString[1]);
									int i4 = new Integer(paramArrayOfString[2])
											.intValue();
									try {
										System.out.println(scalePrec(
												localBigDecimal3, i4)
												.toString());
									} catch (ArithmeticException localArithmeticException29) {
										System.out
												.println(localArithmeticException29
														.getMessage());
									}
								} else if (paramArrayOfString[0]
										.compareTo("pi") == 0) {
									int i1 = new Integer(paramArrayOfString[1])
											.intValue();
									try {
										MathContext localMathContext2 = new MathContext(
												i1);
										System.out
												.println(pi(localMathContext2)
														.toString());
									} catch (ArithmeticException localArithmeticException20) {
										System.out
												.println(localArithmeticException20
														.getMessage());
									}
								} else {
									System.out.println("usage:");
									System.out
											.println("BigDecimalMath asin <argument in -1..1>");
									System.out
											.println("BigDecimalMath acos <argument in -1..1>");
									System.out
											.println("BigDecimalMath atan <argument>");
									System.out
											.println("BigDecimalMath cos <argument>");
									System.out
											.println("BigDecimalMath cot <argument>");
									System.out
											.println("BigDecimalMath exp <argument>");
									System.out
											.println("BigDecimalMath Gamma <argument>");
									System.out
											.println("BigDecimalMath pochhammer <argument>");
									System.out
											.println("BigDecimalMath hypot <argument> <argument>");
									System.out
											.println("BigDecimalMath log <positive argument>");
									System.out
											.println("BigDecimalMath log <integer argument> <precision>");
									System.out
											.println("BigDecimalMath log <rational> <precision>");
									System.out
											.println("BigDecimalMath pow <positive base argument> <power>");
									System.out
											.println("BigDecimalMath root <positive integer argument> <base argument>");
									System.out
											.println("BigDecimalMath sin <argument>");
									System.out
											.println("BigDecimalMath sqrt <non-negative argument>");
									System.out
											.println("BigDecimalMath cbrt <non-negative argument>");
									System.out
											.println("BigDecimalMath tan <argument>");
									System.out
											.println("BigDecimalMath zeta <positive integer argument> <precision>");
								}
							}
						}
					}
				}
			}
		}
	}

	public static BigDecimal pi(MathContext paramMathContext) {
		if (paramMathContext.getPrecision() < PI.precision()) {
			return PI.round(paramMathContext);
		}

		int[] arrayOfInt = { 1, 0, 0, -1, -1, -1, 0, 0 };
		BigDecimal localBigDecimal = broadhurstBBP(1, 1, arrayOfInt,
				paramMathContext);
		return multiplyRound(localBigDecimal, 8);
	}

	public static BigDecimal gamma(MathContext paramMathContext) {
		if (paramMathContext.getPrecision() < GAMMA.precision()) {
			return GAMMA.round(paramMathContext);
		}

		double d = prec2err(0.577D, paramMathContext.getPrecision());

		MathContext localMathContext = new MathContext(
				2 + paramMathContext.getPrecision());
		BigDecimal localBigDecimal1 = BigDecimal.ONE;
		localBigDecimal1 = localBigDecimal1.add(log(2, localMathContext));
		localBigDecimal1 = localBigDecimal1.subtract(log(3, localMathContext));

		int i = (int) ((Math.log(d / 0.7D) - 2.0D) / 4.0D);
		localMathContext = new MathContext(1 + err2prec(1.2D, d / i));
		for (int j = 1;; j++) {
			BigDecimal localBigDecimal2 = zeta(2 * j + 1, localMathContext)
					.subtract(BigDecimal.ONE);
			BigInteger localBigInteger = new BigInteger("" + (2 * j + 1));
			localBigInteger = localBigInteger.shiftLeft(2 * j);
			localBigDecimal2 = divideRound(localBigDecimal2, localBigInteger);
			localBigDecimal1 = localBigDecimal1.subtract(localBigDecimal2);
			if (localBigDecimal2.doubleValue() < 0.1D * d)
				break;
		}
		return localBigDecimal1.round(paramMathContext);
	}

	public static BigDecimal sqrt(BigDecimal paramBigDecimal,
			MathContext paramMathContext) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			throw new ArithmeticException("negative argument "
					+ paramBigDecimal.toString() + " of square root");
		}
		BigDecimal localBigDecimal1 = new BigDecimal(Math.sqrt(paramBigDecimal
				.doubleValue()), paramMathContext);
		BigDecimal localBigDecimal2 = new BigDecimal("2");

		MathContext localMathContext = new MathContext(
				paramMathContext.getPrecision() + 2,
				paramMathContext.getRoundingMode());

		double d = Math.pow(10.0D, -paramMathContext.getPrecision());

		while (Math.abs(BigDecimal.ONE.subtract(
				paramBigDecimal.divide(
						localBigDecimal1.pow(2, localMathContext),
						localMathContext)).doubleValue()) >= d) {
			localBigDecimal1 = localBigDecimal1.add(
					paramBigDecimal.divide(localBigDecimal1, localMathContext))
					.divide(localBigDecimal2, localMathContext);
		}

		return localBigDecimal1;
	}

	public static BigDecimal sqrt(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			throw new ArithmeticException("negative argument "
					+ paramBigDecimal.toString() + " of square root");
		}
		return root(2, paramBigDecimal);
	}

	public static BigDecimal cbrt(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			return root(3, paramBigDecimal.negate()).negate();
		}
		return root(3, paramBigDecimal);
	}

	public static BigDecimal root(int paramInt, BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			throw new ArithmeticException("negative argument "
					+ paramBigDecimal.toString() + " of root");
		if (paramInt <= 0) {
			throw new ArithmeticException("negative power " + paramInt
					+ " of root");
		}
		if (paramInt == 1) {
			return paramBigDecimal;
		}

		BigDecimal localBigDecimal1 = new BigDecimal(Math.pow(
				paramBigDecimal.doubleValue(), 1.0D / paramInt));

		BigDecimal localBigDecimal2 = new BigDecimal(paramInt);

		BigDecimal localBigDecimal3 = scalePrec(paramBigDecimal, 2);
		MathContext localMathContext1 = new MathContext(
				2 + paramBigDecimal.precision());

		double d = paramBigDecimal.ulp().doubleValue()
				/ (2 * paramInt * paramBigDecimal.doubleValue());
		while (true) {
			BigDecimal localBigDecimal4 = localBigDecimal3.divide(
					localBigDecimal1.pow(paramInt - 1), localMathContext1);
			localBigDecimal4 = localBigDecimal1.subtract(localBigDecimal4);
			MathContext localMathContext2 = new MathContext(
					localBigDecimal4.precision());
			localBigDecimal4 = localBigDecimal4.divide(localBigDecimal2,
					localMathContext2);
			localBigDecimal1 = localBigDecimal1.subtract(localBigDecimal4);
			if (Math.abs(localBigDecimal4.doubleValue()
					/ localBigDecimal1.doubleValue()) < d) {
				break;
			}

		}

		return localBigDecimal1.round(new MathContext(err2prec(d)));
	}

	public static BigDecimal hypot(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		BigDecimal localBigDecimal1 = paramBigDecimal1.pow(2).add(
				paramBigDecimal2.pow(2));

		BigDecimal localBigDecimal2 = paramBigDecimal1.abs()
				.multiply(paramBigDecimal1.ulp())
				.add(paramBigDecimal2.abs().multiply(paramBigDecimal2.ulp()));
		MathContext localMathContext = new MathContext(2 + err2prec(
				localBigDecimal1, localBigDecimal2));

		localBigDecimal1 = sqrt(localBigDecimal1.round(localMathContext));

		localMathContext = new MathContext(err2prec(
				localBigDecimal1.doubleValue(),
				0.5D * localBigDecimal2.doubleValue()
						/ localBigDecimal1.doubleValue()));
		return localBigDecimal1.round(localMathContext);
	}

	public static BigDecimal hypot(int paramInt, BigDecimal paramBigDecimal) {
		BigDecimal localBigDecimal = new BigDecimal(paramInt).pow(2).add(
				paramBigDecimal.pow(2));

		double d = paramBigDecimal.doubleValue()
				* paramBigDecimal.ulp().doubleValue();
		MathContext localMathContext = new MathContext(2 + err2prec(
				localBigDecimal.doubleValue(), d));

		localBigDecimal = sqrt(localBigDecimal.round(localMathContext));

		localMathContext = new MathContext(err2prec(
				localBigDecimal.doubleValue(),
				0.5D * d / localBigDecimal.doubleValue()));
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal exp(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal localBigDecimal1 = exp(paramBigDecimal.negate());

			MathContext localMathContext1 = new MathContext(
					localBigDecimal1.precision());
			return BigDecimal.ONE.divide(localBigDecimal1, localMathContext1);
		}
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return scalePrec(BigDecimal.ONE,
					-(int) Math.log10(paramBigDecimal.ulp().doubleValue()));
		}

		double d1 = paramBigDecimal.doubleValue();
		double d2 = paramBigDecimal.ulp().doubleValue();
		Object localObject2;
		if (Math.pow(d1, TAYLOR_NTERM) < TAYLOR_NTERM * (TAYLOR_NTERM - 1.0D)
				* (TAYLOR_NTERM - 2.0D) * d2) {
			BigDecimal localBigDecimal2 = BigDecimal.ONE;

			BigDecimal localBigDecimal3 = BigDecimal.ONE;

			BigInteger localObject1 = BigInteger.ONE;

			MathContext localMathContext2 = new MathContext(err2prec(1.0D, d2
					/ TAYLOR_NTERM));
			for (int j = 1; j <= TAYLOR_NTERM; j++) {
				localObject1 = ((BigInteger) localObject1)
						.multiply(new BigInteger("" + j));
				localBigDecimal3 = localBigDecimal3.multiply(paramBigDecimal);
				localObject2 = localBigDecimal3.divide(new BigDecimal(
						(BigInteger) localObject1), localMathContext2);
				localBigDecimal2 = localBigDecimal2
						.add((BigDecimal) localObject2);
				if ((Math.abs(localBigDecimal3.doubleValue()) < j)
						&& (Math.abs(((BigDecimal) localObject2).doubleValue()) < 0.5D * d2)) {
					break;
				}
			}

			MathContext localMathContext3 = new MathContext(err2prec(d2 / 2.0D));
			return localBigDecimal2.round(localMathContext3);
		}

		int i = (int) (1.0D - Math.log10(TAYLOR_NTERM * (TAYLOR_NTERM - 1.0D)
				* (TAYLOR_NTERM - 2.0D) * d2 / Math.pow(d1, TAYLOR_NTERM))
				/ (TAYLOR_NTERM - 1.0D));

		BigDecimal localBigDecimal3 = paramBigDecimal.scaleByPowerOfTen(-i);
		Object localObject1 = exp(localBigDecimal3);

		MathContext localMathContext2 = new MathContext(
				((BigDecimal) localObject1).precision() - i);

		while (i > 0) {
			int k = Math.min(8, i);
			i -= k;
			localObject2 = new MathContext(
					((BigDecimal) localObject1).precision() - k + 2);
			int m = 1;
			while (k-- > 0)
				m *= 10;
			localObject1 = ((BigDecimal) localObject1).pow(m,
					(MathContext) localObject2);
		}
		return (BigDecimal) (BigDecimal) ((BigDecimal) localObject1)
				.round(localMathContext2);
	}

	public static BigDecimal exp(MathContext paramMathContext) {
		if (paramMathContext.getPrecision() < E.precision()) {
			return E.round(paramMathContext);
		}

		BigDecimal localBigDecimal = scalePrec(BigDecimal.ONE,
				paramMathContext.getPrecision());
		return exp(localBigDecimal);
	}

	public static BigDecimal log(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			throw new ArithmeticException("Cannot take log of negative "
					+ paramBigDecimal.toString());
		if (paramBigDecimal.compareTo(BigDecimal.ONE) == 0) {
			return scalePrec(BigDecimal.ZERO, paramBigDecimal.precision() - 1);
		}
		if (Math.abs(paramBigDecimal.doubleValue() - 1.0D) <= 0.3D) {
			BigDecimal localBigDecimal1 = scalePrec(
					paramBigDecimal.subtract(BigDecimal.ONE), 2);
			BigDecimal localBigDecimal2 = localBigDecimal1;
			double d2 = 0.5D * paramBigDecimal.ulp().doubleValue()
					/ Math.abs(paramBigDecimal.doubleValue());
			BigDecimal localBigDecimal3 = localBigDecimal1;
			for (int j = 2;; j++) {
				localBigDecimal2 = multiplyRound(localBigDecimal2,
						localBigDecimal1);
				BigDecimal localBigDecimal4 = divideRound(localBigDecimal2, j);
				if (j % 2 == 0)
					localBigDecimal3 = localBigDecimal3
							.subtract(localBigDecimal4);
				else
					localBigDecimal3 = localBigDecimal3.add(localBigDecimal4);
				if (Math.abs(localBigDecimal4.doubleValue()) < d2)
					break;
			}
			MathContext localObject = new MathContext(err2prec(
					localBigDecimal3.doubleValue(), d2));
			return localBigDecimal3.round((MathContext) localObject);
		}

		double d1 = paramBigDecimal.doubleValue();
		double d2 = paramBigDecimal.ulp().doubleValue();

		int i = (int) (Math.log(d1) / 0.2D);

		i = Math.max(2, i);

		Object localObject = scalePrec(paramBigDecimal, 2);
		BigDecimal localBigDecimal4 = root(i, (BigDecimal) localObject);
		localBigDecimal4 = log(localBigDecimal4).multiply(new BigDecimal(i));

		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal4.doubleValue(), d2 / d1));
		return (BigDecimal) localBigDecimal4.round(localMathContext);
	}

	public static BigDecimal log(int paramInt, MathContext paramMathContext) {
		if (paramInt <= 0)
			throw new ArithmeticException("Cannot take log of negative "
					+ paramInt);
		if (paramInt == 1)
			return BigDecimal.ZERO;
		Object localObject1;
		if (paramInt == 2) {
			if (paramMathContext.getPrecision() < LOG2.precision()) {
				return LOG2.round(paramMathContext);
			}

			int[] arrayOfInt = { 2, -5, -2, -7, -2, -5, 2, -3 };
			localObject1 = broadhurstBBP(2, 1, arrayOfInt, new MathContext(
					1 + paramMathContext.getPrecision()));
			localObject1 = ((BigDecimal) localObject1).multiply(new BigDecimal(
					8));
			localObject1 = sqrt(divideRound((BigDecimal) localObject1, 3));
			return ((BigDecimal) localObject1).round(paramMathContext);
		}
		int i;
		BigDecimal localBigDecimal1;
		double d3;
		Rational localRational1;
		int j;
		Rational localRational2;
		BigDecimal localBigDecimal2;
		if (paramInt == 3) {
			i = (int) (paramMathContext.getPrecision() / 1.87D);
			localObject1 = new MathContext(paramMathContext.getPrecision() + 1
					+ (int) Math.log10(i * 0.693D / 1.098D));
			localBigDecimal1 = multiplyRound(
					log(2, (MathContext) localObject1), 19);

			d3 = prec2err(1.098D, paramMathContext.getPrecision()) / i;
			Rational localObject2 = new Rational(7153, 524288);
			localRational1 = new Rational(7153, 524288);
			for (j = 1;; j++) {
				localRational2 = localRational1.divide(j);
				if (localRational2.doubleValue() < d3) {
					break;
				}

				localObject1 = new MathContext(err2prec(
						localRational2.doubleValue(), d3));
				localBigDecimal2 = localRational1.divide(j).BigDecimalValue(
						(MathContext) localObject1);
				if (j % 2 != 0)
					localBigDecimal1 = localBigDecimal1.add(localBigDecimal2);
				else
					localBigDecimal1 = localBigDecimal1
							.subtract(localBigDecimal2);
				localRational1 = localRational1
						.multiply((Rational) localObject2);
			}
			localBigDecimal1 = divideRound(localBigDecimal1, 12);
			return localBigDecimal1.round(paramMathContext);
		}
		if (paramInt == 5) {
			i = (int) (paramMathContext.getPrecision() / 1.33D);
			localObject1 = new MathContext(paramMathContext.getPrecision() + 1
					+ (int) Math.log10(i * 0.693D / 1.609D));
			localBigDecimal1 = multiplyRound(
					log(2, (MathContext) localObject1), 14);

			d3 = prec2err(1.6D, paramMathContext.getPrecision()) / i;
			Rational localObject2 = new Rational(759, 16384);
			localRational1 = new Rational(759, 16384);
			for (j = 1;; j++) {
				localRational2 = localRational1.divide(j);
				if (localRational2.doubleValue() < d3) {
					break;
				}

				localObject1 = new MathContext(err2prec(
						localRational2.doubleValue(), d3));
				localBigDecimal2 = localRational1.divide(j).BigDecimalValue(
						(MathContext) localObject1);
				localBigDecimal1 = localBigDecimal1.subtract(localBigDecimal2);
				localRational1 = localRational1
						.multiply((Rational) localObject2);
			}
			localBigDecimal1 = divideRound(localBigDecimal1, 6);
			return localBigDecimal1.round(paramMathContext);
		}
		if (paramInt == 7) {
			i = (int) (paramMathContext.getPrecision() / 0.903D);
			localObject1 = new MathContext(paramMathContext.getPrecision() + 1
					+ (int) Math.log10(i * 3 * 0.693D / 1.098D));
			localBigDecimal1 = multiplyRound(
					log(2, (MathContext) localObject1), 3);

			d3 = prec2err(1.9D, paramMathContext.getPrecision()) / i;
			Rational localObject2 = new Rational(1, 8);
			localRational1 = new Rational(1, 8);
			for (j = 1;; j++) {
				localRational2 = localRational1.divide(j);
				if (localRational2.doubleValue() < d3) {
					break;
				}

				localObject1 = new MathContext(err2prec(
						localRational2.doubleValue(), d3));
				localBigDecimal2 = localRational1.divide(j).BigDecimalValue(
						(MathContext) localObject1);
				localBigDecimal1 = localBigDecimal1.subtract(localBigDecimal2);
				localRational1 = localRational1
						.multiply((Rational) localObject2);
			}
			return localBigDecimal1.round(paramMathContext);
		}

		double d1 = Math.log(paramInt);
		double d2 = prec2err(d1, paramMathContext.getPrecision());

		d2 *= paramInt;

		MathContext localMathContext = new MathContext(1 + err2prec(paramInt,
				d2));

		Object localObject2 = scalePrec(new BigDecimal(paramInt),
				localMathContext);
		return (BigDecimal) (BigDecimal) log((BigDecimal) localObject2);
	}

	public static BigDecimal log(Rational paramRational,
			MathContext paramMathContext) {
		if (paramRational.compareTo(Rational.ZERO) <= 0)
			throw new ArithmeticException("Cannot take log of negative "
					+ paramRational.toString());
		if (paramRational.compareTo(Rational.ONE) == 0) {
			return BigDecimal.ZERO;
		}

		double d = prec2err(Math.log(paramRational.doubleValue()),
				paramMathContext.getPrecision());

		MathContext localMathContext = new MathContext(1 + err2prec(d));

		BigDecimal localBigDecimal = log(paramRational
				.BigDecimalValue(localMathContext));

		return localBigDecimal.round(paramMathContext);
	}

	public static BigDecimal pow(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		if (paramBigDecimal1.compareTo(BigDecimal.ZERO) < 0)
			throw new ArithmeticException("Cannot power negative "
					+ paramBigDecimal1.toString());
		if (paramBigDecimal1.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal localBigDecimal1 = log(paramBigDecimal1);
		BigDecimal localBigDecimal2 = paramBigDecimal2
				.multiply(localBigDecimal1);
		BigDecimal localBigDecimal3 = exp(localBigDecimal2);

		double d = Math.abs(localBigDecimal1.doubleValue()
				* paramBigDecimal2.ulp().doubleValue() / 2.0D)
				+ Math.abs(paramBigDecimal2.doubleValue()
						* paramBigDecimal1.ulp().doubleValue() / 2.0D
						/ paramBigDecimal1.doubleValue());

		MathContext localMathContext = new MathContext(err2prec(1.0D, d));
		return localBigDecimal3.round(localMathContext);
	}

	public static BigDecimal powRound(BigDecimal paramBigDecimal, int paramInt) {
		if (paramInt == 1)
			return paramBigDecimal;
		if (paramInt == 0) {
			return BigDecimal.ONE;
		}

		MathContext localMathContext = new MathContext(
				paramBigDecimal.precision()
						- (int) Math.log10(Math.abs(paramInt)));
		if (paramInt > 0) {
			return paramBigDecimal.pow(paramInt, localMathContext);
		}
		return BigDecimal.ONE.divide(paramBigDecimal.pow(-paramInt),
				localMathContext);
	}

	public static BigDecimal powRound(BigDecimal paramBigDecimal,
			BigInteger paramBigInteger) {
		if ((paramBigInteger.compareTo(Rational.MAX_INT) > 0)
				|| (paramBigInteger.compareTo(Rational.MIN_INT) < 0)) {
			throw new ProviderException("Unimplemented big power "
					+ paramBigInteger.toString());
		}
		return powRound(paramBigDecimal, paramBigInteger.intValue());
	}

	public static BigDecimal powRound(BigDecimal paramBigDecimal,
			Rational paramRational) {
		if (paramRational.compareTo(BigInteger.ONE) == 0)
			return paramBigDecimal;
		if (paramRational.signum() == 0)
			return BigDecimal.ONE;
		if (paramRational.isInteger()) {
			return powRound(paramBigDecimal, paramRational.a);
		}

		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			throw new ArithmeticException("Cannot power negative "
					+ paramBigDecimal.toString());
		}

		if (paramRational.isIntegerFrac()) {
			double d = Math.pow(paramBigDecimal.doubleValue(),
					paramRational.doubleValue());
			BigDecimal localBigDecimal1 = new BigDecimal(d);

			BigDecimal localBigDecimal2 = new BigDecimal(0.5D
					* paramRational.abs().doubleValue()
					* paramBigDecimal
							.ulp()
							.divide(paramBigDecimal.abs(),
									MathContext.DECIMAL64).doubleValue());

			int j = paramRational.a.intValue();
			int k = paramRational.b.intValue();

			BigDecimal localBigDecimal3 = powRound(paramBigDecimal, j);
			while (true) {
				BigDecimal localBigDecimal4 = localBigDecimal1.pow(k).subtract(
						localBigDecimal3);
				BigDecimal localBigDecimal5 = multiplyRound(
						localBigDecimal1.pow(k - 1), paramRational.b);

				BigDecimal localBigDecimal6 = localBigDecimal4.divide(
						localBigDecimal5, MathContext.DECIMAL64);

				BigDecimal localBigDecimal7 = localBigDecimal1.multiply(
						localBigDecimal2, MathContext.DECIMAL64);
				int m = 2 + err2prec(localBigDecimal6, localBigDecimal7);
				MathContext localMathContext2;
				if (m <= 0) {
					localBigDecimal6 = localBigDecimal4.divide(
							localBigDecimal5, MathContext.DECIMAL32);
				} else {
					localMathContext2 = new MathContext(m);
					localBigDecimal6 = localBigDecimal4.divide(
							localBigDecimal5, localMathContext2);
				}

				localBigDecimal1 = subtractRound(localBigDecimal1,
						localBigDecimal6);

				if (localBigDecimal6
						.divide(localBigDecimal1, MathContext.DECIMAL64).abs()
						.compareTo(localBigDecimal2) < 0) {
					localMathContext2 = new MathContext(
							err2prec(localBigDecimal2.doubleValue()));
					return localBigDecimal1.round(localMathContext2);
				}

			}

		}

		int i = 3 + err2prec(paramBigDecimal.ulp()
				.divide(paramBigDecimal, MathContext.DECIMAL64).doubleValue()
				/ Math.log(paramBigDecimal.doubleValue()));

		MathContext localMathContext1 = new MathContext(i);

		return pow(paramBigDecimal,
				paramRational.BigDecimalValue(localMathContext1));
	}

	public static BigDecimal sin(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return sin(paramBigDecimal.negate()).negate();
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal localBigDecimal1 = mod2pi(paramBigDecimal);
		double d1 = 0.5D * Math.abs(paramBigDecimal.ulp().doubleValue());
		MathContext localMathContext1 = new MathContext(2 + err2prec(3.14159D,
				d1));
		BigDecimal localBigDecimal2 = pi(localMathContext1);
		localMathContext1 = new MathContext(paramBigDecimal.precision());
		if (localBigDecimal1.compareTo(localBigDecimal2) > 0) {
			return sin(subtractRound(localBigDecimal1, localBigDecimal2))
					.negate();
		}
		if (localBigDecimal1.multiply(new BigDecimal("2")).compareTo(
				localBigDecimal2) > 0) {
			return sin(subtractRound(localBigDecimal2, localBigDecimal1));
		}

		if (localBigDecimal1.multiply(new BigDecimal("4")).compareTo(
				localBigDecimal2) > 0) {
			return cos(subtractRound(
					localBigDecimal2.divide(new BigDecimal("2")),
					localBigDecimal1));
		}

		BigDecimal localBigDecimal3 = localBigDecimal1;

		BigDecimal localBigDecimal4 = localBigDecimal1;

		BigInteger localBigInteger = BigInteger.ONE;

		double d2 = localBigDecimal1.ulp().doubleValue();

		int i = (int) (localBigDecimal1.precision() / Math
				.log10(1.0D / localBigDecimal1.doubleValue())) / 2;
		MathContext localMathContext2 = new MathContext(err2prec(
				localBigDecimal1.doubleValue(), d2 / i));
		for (int j = 1;; j++) {
			localBigInteger = localBigInteger.multiply(new BigInteger("" + 2
					* j));
			localBigInteger = localBigInteger.multiply(new BigInteger(""
					+ (2 * j + 1)));
			localBigDecimal4 = localBigDecimal4.multiply(localBigDecimal1)
					.multiply(localBigDecimal1).negate();
			BigDecimal localBigDecimal5 = localBigDecimal4.divide(
					new BigDecimal(localBigInteger), localMathContext2);
			localBigDecimal3 = localBigDecimal3.add(localBigDecimal5);
			if (localBigDecimal5.abs().doubleValue() < 0.5D * d2) {
				break;
			}
		}
		localMathContext1 = new MathContext(localBigDecimal1.precision());
		return localBigDecimal3.round(localMathContext1);
	}

	public static BigDecimal cos(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return cos(paramBigDecimal.negate());
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ONE;
		}

		BigDecimal localBigDecimal1 = mod2pi(paramBigDecimal);
		double d1 = 0.5D * Math.abs(paramBigDecimal.ulp().doubleValue());
		MathContext localMathContext1 = new MathContext(2 + err2prec(3.14159D,
				d1));
		BigDecimal localBigDecimal2 = pi(localMathContext1);
		localMathContext1 = new MathContext(paramBigDecimal.precision());
		if (localBigDecimal1.compareTo(localBigDecimal2) > 0) {
			return cos(subtractRound(localBigDecimal1, localBigDecimal2))
					.negate();
		}
		if (localBigDecimal1.multiply(new BigDecimal("2")).compareTo(
				localBigDecimal2) > 0) {
			return cos(subtractRound(localBigDecimal2, localBigDecimal1))
					.negate();
		}

		if (localBigDecimal1.multiply(new BigDecimal("4")).compareTo(
				localBigDecimal2) > 0) {
			return sin(subtractRound(
					localBigDecimal2.divide(new BigDecimal("2")),
					localBigDecimal1));
		}

		BigDecimal localBigDecimal3 = BigDecimal.ONE;

		BigDecimal localBigDecimal4 = BigDecimal.ONE;

		BigInteger localBigInteger = BigInteger.ONE;

		double d2 = 0.5D * localBigDecimal1.ulp().doubleValue()
				* localBigDecimal1.doubleValue();

		int i = (int) (Math.log(d2) / Math.log(localBigDecimal1.doubleValue())) / 2;
		MathContext localMathContext2 = new MathContext(err2prec(1.0D, d2 / i));
		for (int j = 1;; j++) {
			localBigInteger = localBigInteger.multiply(new BigInteger(""
					+ (2 * j - 1)));
			localBigInteger = localBigInteger.multiply(new BigInteger("" + 2
					* j));
			localBigDecimal4 = localBigDecimal4.multiply(localBigDecimal1)
					.multiply(localBigDecimal1).negate();
			BigDecimal localBigDecimal5 = localBigDecimal4.divide(
					new BigDecimal(localBigInteger), localMathContext2);
			localBigDecimal3 = localBigDecimal3.add(localBigDecimal5);
			if (localBigDecimal5.abs().doubleValue() < 0.5D * d2) {
				break;
			}
		}
		localMathContext1 = new MathContext(err2prec(
				localBigDecimal3.doubleValue(), d2));
		return localBigDecimal3.round(localMathContext1);
	}

	public static BigDecimal tan(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0)
			return BigDecimal.ZERO;
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			return tan(paramBigDecimal.negate()).negate();
		}

		BigDecimal localBigDecimal1 = modpi(paramBigDecimal);

		double d1 = localBigDecimal1.doubleValue();
		double d2 = paramBigDecimal.ulp().doubleValue() / 2.0D;
		double d3 = d2 / 2.0D / Math.pow(Math.cos(d1), 2.0D);

		if (d1 > 0.8D) {
			BigDecimal localBigDecimal2 = cot(paramBigDecimal);
			MathContext localObject = new MathContext(err2prec(
					1.0D / localBigDecimal2.doubleValue(), d3));
			return BigDecimal.ONE.divide(localBigDecimal2,
					(MathContext) localObject);
		}

		BigDecimal localBigDecimal2 = scalePrec(localBigDecimal1, 2);
		Object localObject = multiplyRound(localBigDecimal2, localBigDecimal2);

		BigDecimal localBigDecimal3 = localBigDecimal2.plus();

		BigDecimal localBigDecimal4 = localBigDecimal2;

		Bernoulli localBernoulli = new Bernoulli();

		BigInteger localBigInteger1 = new BigInteger("4");

		BigInteger localBigInteger2 = new BigInteger("2");

		for (int i = 2;; i++) {
			Rational localRational = localBernoulli.at(2 * i).abs();
			localBigInteger1 = localBigInteger1.shiftLeft(2);
			localBigInteger2 = localBigInteger2.multiply(
					new BigInteger("" + 2 * i)).multiply(
					new BigInteger("" + (2 * i - 1)));
			localRational = localRational.multiply(localBigInteger1)
					.multiply(localBigInteger1.subtract(BigInteger.ONE))
					.divide(localBigInteger2);
			localBigDecimal4 = multiplyRound(localBigDecimal4,
					(BigDecimal) localObject);
			BigDecimal localBigDecimal5 = multiplyRound(localBigDecimal4,
					localRational);
			localBigDecimal3 = localBigDecimal3.add(localBigDecimal5);
			if (Math.abs(localBigDecimal5.doubleValue()) < 0.1D * d3)
				break;
		}
		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal3.doubleValue(), d3));
		return (BigDecimal) localBigDecimal3.round(localMathContext);
	}

	public static BigDecimal cot(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			throw new ArithmeticException("Cannot take cot of zero "
					+ paramBigDecimal.toString());
		}
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			return cot(paramBigDecimal.negate()).negate();
		}

		BigDecimal localBigDecimal1 = modpi(paramBigDecimal);

		double d1 = localBigDecimal1.doubleValue();
		double d2 = paramBigDecimal.ulp().doubleValue() / 2.0D;
		double d3 = d2 / 2.0D / Math.pow(Math.sin(d1), 2.0D);

		BigDecimal localBigDecimal2 = scalePrec(localBigDecimal1, 2);
		BigDecimal localBigDecimal3 = multiplyRound(localBigDecimal2,
				localBigDecimal2);

		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), d3));
		BigDecimal localBigDecimal4 = BigDecimal.ONE.divide(localBigDecimal2,
				localMathContext);

		BigDecimal localBigDecimal5 = localBigDecimal2;

		Bernoulli localBernoulli = new Bernoulli();

		BigInteger localBigInteger1 = new BigInteger("4");

		BigInteger localBigInteger2 = BigInteger.ONE;

		for (int i = 1;; i++) {
			Rational localRational = localBernoulli.at(2 * i);
			localBigInteger2 = localBigInteger2.multiply(
					new BigInteger("" + 2 * i)).multiply(
					new BigInteger("" + (2 * i - 1)));
			localRational = localRational.multiply(localBigInteger1).divide(
					localBigInteger2);
			BigDecimal localBigDecimal6 = multiplyRound(localBigDecimal5,
					localRational);
			if (i % 2 == 0)
				localBigDecimal4 = localBigDecimal4.add(localBigDecimal6);
			else
				localBigDecimal4 = localBigDecimal4.subtract(localBigDecimal6);
			if (Math.abs(localBigDecimal6.doubleValue()) < 0.1D * d3) {
				break;
			}
			localBigInteger1 = localBigInteger1.shiftLeft(2);
			localBigDecimal5 = multiplyRound(localBigDecimal5, localBigDecimal3);
		}
		localMathContext = new MathContext(err2prec(
				localBigDecimal4.doubleValue(), d3));
		return localBigDecimal4.round(localMathContext);
	}

	public static BigDecimal asin(BigDecimal paramBigDecimal) {
		if ((paramBigDecimal.compareTo(BigDecimal.ONE) > 0)
				|| (paramBigDecimal.compareTo(BigDecimal.ONE.negate()) < 0)) {
			throw new ArithmeticException("Out of range argument "
					+ paramBigDecimal.toString() + " of asin");
		}
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0)
			return BigDecimal.ZERO;
		if (paramBigDecimal.compareTo(BigDecimal.ONE) == 0) {
			double d1 = Math.sqrt(paramBigDecimal.ulp().doubleValue());
			MathContext localMathContext1 = new MathContext(err2prec(3.14159D,
					d1));
			return pi(localMathContext1).divide(new BigDecimal(2));
		}
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			return asin(paramBigDecimal.negate()).negate();
		}
		Object localObject3;
		if (paramBigDecimal.doubleValue() > 0.7D) {
			BigDecimal localBigDecimal1 = BigDecimal.ONE
					.subtract(paramBigDecimal);
			double d3 = paramBigDecimal.doubleValue();
			double d5 = paramBigDecimal.ulp().doubleValue() / 2.0D;
			double d7 = d5 / 2.0D / Math.sqrt(1.0D - Math.pow(d3, 2.0D));

			BigDecimal localBigDecimal3 = scalePrec(localBigDecimal1, 3);
			BigDecimal localBigDecimal4 = divideRound(localBigDecimal3, 4);

			BigDecimal localObject1 = BigDecimal.ONE;

			BigDecimal localObject2 = BigDecimal.ONE;

			BigInteger localBigInteger1 = BigInteger.ONE;
			BigInteger localBigInteger2 = BigInteger.ONE;

			for (int j = 1;; j++) {
				localBigInteger1 = localBigInteger1.multiply(new BigInteger(""
						+ (2 * j - 1)));
				localBigInteger2 = localBigInteger2.multiply(new BigInteger(""
						+ j));
				if (j == 1)
					localObject2 = localBigDecimal4;
				else
					localObject2 = multiplyRound((BigDecimal) localObject2,
							localBigDecimal4);
				BigDecimal localBigDecimal5 = divideRound(
						multiplyRound((BigDecimal) localObject2,
								localBigInteger1),
						localBigInteger2.multiply(new BigInteger(""
								+ (2 * j + 1))));

				localObject1 = ((BigDecimal) localObject1)
						.add(localBigDecimal5);

				if (Math.abs(localBigDecimal5.doubleValue()) < d5 / 120.0D) {
					break;
				}
			}
			localObject2 = sqrt(localBigDecimal3.multiply(new BigDecimal(2)));
			localObject1 = multiplyRound((BigDecimal) localObject2,
					(BigDecimal) localObject1);

			localObject3 = new MathContext(
					((BigDecimal) localObject1).precision());
			BigDecimal localBigDecimal5 = pi((MathContext) localObject3)
					.divide(new BigDecimal(2));

			localObject3 = new MathContext(err2prec(
					((BigDecimal) localObject1).doubleValue(), d7));
			return localBigDecimal5.subtract((BigDecimal) localObject1,
					(MathContext) localObject3);
		}

		double d2 = paramBigDecimal.doubleValue();
		double d4 = paramBigDecimal.ulp().doubleValue() / 2.0D;
		double d6 = d4 / 2.0D / Math.sqrt(1.0D - Math.pow(d2, 2.0D));

		BigDecimal localBigDecimal2 = scalePrec(paramBigDecimal, 2);
		BigDecimal localBigDecimal3 = multiplyRound(localBigDecimal2,
				localBigDecimal2);

		BigDecimal localBigDecimal4 = localBigDecimal2.plus();

		Object localObject1 = localBigDecimal2;

		Object localObject2 = BigInteger.ONE;
		BigInteger localBigInteger1 = BigInteger.ONE;

		for (int i = 1;; i++) {
			localObject2 = ((BigInteger) localObject2).multiply(new BigInteger(
					"" + (2 * i - 1)));
			localBigInteger1 = localBigInteger1.multiply(new BigInteger("" + 2
					* i));
			localObject1 = multiplyRound((BigDecimal) localObject1,
					localBigDecimal3);
			localObject3 = divideRound(
					multiplyRound((BigDecimal) localObject1,
							(BigInteger) localObject2),
					localBigInteger1.multiply(new BigInteger("" + (2 * i + 1))));

			localBigDecimal4 = localBigDecimal4.add((BigDecimal) localObject3);
			if (Math.abs(((BigDecimal) localObject3).doubleValue()) < 0.1D * d6)
				break;
		}
		MathContext localMathContext2 = new MathContext(err2prec(
				localBigDecimal4.doubleValue(), d6));
		return (BigDecimal) (BigDecimal) (BigDecimal) localBigDecimal4
				.round(localMathContext2);
	}

	public static BigDecimal acos(BigDecimal paramBigDecimal) {
		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);
		BigDecimal localBigDecimal2 = asin(localBigDecimal1);
		double d1 = localBigDecimal2.ulp().doubleValue() / 2.0D;

		MathContext localMathContext = new MathContext(err2prec(3.14159D, d1));
		BigDecimal localBigDecimal3 = pi(localMathContext).divide(
				new BigDecimal(2));
		localBigDecimal2 = localBigDecimal3.subtract(localBigDecimal2);

		double d2 = paramBigDecimal.doubleValue();
		double d3 = paramBigDecimal.ulp().doubleValue() / 2.0D;
		d1 = d3 / 2.0D / Math.sqrt(1.0D - Math.pow(d2, 2.0D));

		localMathContext = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), d1));
		return localBigDecimal2.round(localMathContext);
	}

	public static BigDecimal atan(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			return atan(paramBigDecimal.negate()).negate();
		}
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0)
			return BigDecimal.ZERO;
		BigDecimal localBigDecimal1;
		BigDecimal localBigDecimal2;
		if ((paramBigDecimal.doubleValue() > 0.7D)
				&& (paramBigDecimal.doubleValue() < 3.0D)) {
			localBigDecimal1 = scalePrec(paramBigDecimal, 2);
			localBigDecimal2 = divideRound(
					hypot(1, localBigDecimal1).subtract(BigDecimal.ONE),
					localBigDecimal1);

			BigDecimal localObject1 = multiplyRound(atan(localBigDecimal2), 2);

			double d2 = paramBigDecimal.ulp().doubleValue()
					/ (2.0D * Math.hypot(1.0D, paramBigDecimal.doubleValue()));
			MathContext localObject2 = new MathContext(err2prec(
					((BigDecimal) localObject1).doubleValue(), d2));
			return ((BigDecimal) localObject1)
					.round((MathContext) localObject2);
		}
		if (paramBigDecimal.doubleValue() < 0.71D) {
			localBigDecimal1 = scalePrec(paramBigDecimal, 2);
			localBigDecimal2 = multiplyRound(localBigDecimal1, localBigDecimal1)
					.negate();

			BigDecimal localObject1 = localBigDecimal1.plus();

			BigDecimal localBigDecimal3 = localBigDecimal1;

			double d3 = paramBigDecimal.ulp().doubleValue()
					/ (2.0D * Math.hypot(1.0D, paramBigDecimal.doubleValue()));

			for (int i = 1;; i++) {
				localBigDecimal3 = multiplyRound(localBigDecimal3,
						localBigDecimal2);
				BigDecimal localObject4 = divideRound(localBigDecimal3,
						2 * i + 1);

				localObject1 = ((BigDecimal) localObject1)
						.add((BigDecimal) localObject4);
				if (Math.abs(((BigDecimal) localObject4).doubleValue()) < 0.1D * d3)
					break;
			}
			MathContext localObject3 = new MathContext(err2prec(
					((BigDecimal) localObject1).doubleValue(), d3));
			return ((BigDecimal) localObject1)
					.round((MathContext) localObject3);
		}

		double d1 = paramBigDecimal.ulp().doubleValue()
				/ (2.0D * Math.hypot(1.0D, paramBigDecimal.doubleValue()));

		Object localObject1 = new MathContext(2 + err2prec(3.1416D, d1));
		BigDecimal localBigDecimal3 = pi((MathContext) localObject1);
		BigDecimal localBigDecimal4 = localBigDecimal3
				.divide(new BigDecimal(2));

		Object localObject2 = divideRound(-1, scalePrec(paramBigDecimal, 2));
		Object localObject3 = multiplyRound((BigDecimal) localObject2,
				(BigDecimal) localObject2).negate();

		Object localObject4 = localObject2;

		for (int j = 0;; j++) {
			BigDecimal localBigDecimal5 = divideRound(
					(BigDecimal) localObject4, 2 * j + 1);

			localBigDecimal4 = localBigDecimal4.add(localBigDecimal5);
			if (Math.abs(localBigDecimal5.doubleValue()) < 0.1D * d1)
				break;
			localObject4 = multiplyRound((BigDecimal) localObject4,
					(BigDecimal) localObject3);
		}
		localObject1 = new MathContext(err2prec(localBigDecimal4.doubleValue(),
				d1));
		return (BigDecimal) (BigDecimal) (BigDecimal) (BigDecimal) localBigDecimal4
				.round((MathContext) localObject1);
	}

	public static BigDecimal cosh(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return cos(paramBigDecimal.negate());
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ONE;
		}

		if (paramBigDecimal.doubleValue() > 1.5D) {
			return hypot(1, sinh(paramBigDecimal));
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);

		BigDecimal localBigDecimal2 = BigDecimal.ONE;

		BigDecimal localBigDecimal3 = BigDecimal.ONE;

		BigInteger localBigInteger = BigInteger.ONE;

		double d = 0.5D * paramBigDecimal.ulp().doubleValue()
				* paramBigDecimal.doubleValue();

		int i = (int) (Math.log(d) / Math.log(paramBigDecimal.doubleValue())) / 2;

		MathContext localMathContext1 = new MathContext(err2prec(1.0D, d / i));
		for (int j = 1;; j++) {
			localBigInteger = localBigInteger.multiply(new BigInteger(""
					+ (2 * j - 1)));
			localBigInteger = localBigInteger.multiply(new BigInteger("" + 2
					* j));
			localBigDecimal3 = localBigDecimal3.multiply(localBigDecimal1)
					.multiply(localBigDecimal1);
			BigDecimal localBigDecimal4 = localBigDecimal3.divide(
					new BigDecimal(localBigInteger), localMathContext1);
			localBigDecimal2 = localBigDecimal2.add(localBigDecimal4);
			if (localBigDecimal4.abs().doubleValue() < 0.5D * d) {
				break;
			}
		}
		MathContext localMathContext2 = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), d));
		return localBigDecimal2.round(localMathContext2);
	}

	public static BigDecimal sinh(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return sinh(paramBigDecimal.negate()).negate();
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		if (paramBigDecimal.doubleValue() > 2.4D) {
			BigDecimal localBigDecimal1 = new BigDecimal(2);
			BigDecimal localBigDecimal2 = paramBigDecimal
					.divide(localBigDecimal1);
			BigDecimal localBigDecimal3 = sinh(localBigDecimal2).multiply(
					cosh(localBigDecimal2)).multiply(localBigDecimal1);

			double d1 = Math.tanh(paramBigDecimal.doubleValue());
			MathContext localMathContext1 = new MathContext(err2prec(0.5D
					* paramBigDecimal.ulp().doubleValue() / d1));
			return localBigDecimal3.round(localMathContext1);
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);

		BigDecimal localBigDecimal2 = localBigDecimal1;

		BigDecimal localBigDecimal3 = localBigDecimal1;

		BigInteger localBigInteger = BigInteger.ONE;

		double d2 = paramBigDecimal.ulp().doubleValue();

		int i = (int) (paramBigDecimal.precision() / Math
				.log10(1.0D / localBigDecimal1.doubleValue())) / 2;
		MathContext localMathContext2 = new MathContext(err2prec(
				paramBigDecimal.doubleValue(), d2 / i));
		for (int j = 1;; j++) {
			localBigInteger = localBigInteger.multiply(new BigInteger("" + 2
					* j));
			localBigInteger = localBigInteger.multiply(new BigInteger(""
					+ (2 * j + 1)));
			localBigDecimal3 = localBigDecimal3.multiply(localBigDecimal1)
					.multiply(localBigDecimal1);
			BigDecimal localBigDecimal4 = localBigDecimal3.divide(
					new BigDecimal(localBigInteger), localMathContext2);
			localBigDecimal2 = localBigDecimal2.add(localBigDecimal4);
			if (localBigDecimal4.abs().doubleValue() < 0.5D * d2) {
				break;
			}
		}
		MathContext localMathContext3 = new MathContext(
				paramBigDecimal.precision());
		return localBigDecimal2.round(localMathContext3);
	}

	public static BigDecimal tanh(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return tanh(paramBigDecimal.negate()).negate();
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);

		BigDecimal localBigDecimal2 = exp(localBigDecimal1
				.multiply(new BigDecimal(-2)));

		double d = 0.5D * paramBigDecimal.ulp().doubleValue()
				/ Math.pow(Math.cosh(paramBigDecimal.doubleValue()), 2.0D);
		MathContext localMathContext = new MathContext(err2prec(
				Math.tanh(paramBigDecimal.doubleValue()), d));
		return BigDecimal.ONE.subtract(localBigDecimal2).divide(
				BigDecimal.ONE.add(localBigDecimal2), localMathContext);
	}

	public static BigDecimal asinh(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);

		BigDecimal localBigDecimal2 = log(hypot(1, localBigDecimal1).add(
				localBigDecimal1));

		double d1 = paramBigDecimal.doubleValue();
		double d2 = 0.5D * paramBigDecimal.ulp().doubleValue()
				/ Math.hypot(1.0D, d1);
		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), d2));
		return localBigDecimal2.round(localMathContext);
	}

	public static BigDecimal acosh(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ONE) < 0)
			throw new ArithmeticException("Out of range argument cosh "
					+ paramBigDecimal.toString());
		if (paramBigDecimal.compareTo(BigDecimal.ONE) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);

		BigDecimal localBigDecimal2 = log(sqrt(
				localBigDecimal1.pow(2).subtract(BigDecimal.ONE)).add(
				localBigDecimal1));

		double d1 = paramBigDecimal.doubleValue();
		double d2 = 0.5D * paramBigDecimal.ulp().doubleValue()
				/ Math.sqrt(d1 * d1 - 1.0D);
		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), d2));
		return localBigDecimal2.round(localMathContext);
	}

	public static BigDecimal Gamma(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.compareTo(BigDecimal.ZERO) < 0)
			return divideRound(Gamma(paramBigDecimal.add(BigDecimal.ONE)),
					paramBigDecimal);
		if (paramBigDecimal.doubleValue() > 1.5D) {
			int i = (int) (paramBigDecimal.doubleValue() - 0.5D);
			BigDecimal localObject = paramBigDecimal
					.subtract(new BigDecimal(i));
			return multiplyRound(Gamma((BigDecimal) localObject),
					pochhammer((BigDecimal) localObject, i));
		}

		BigDecimal localBigDecimal1 = paramBigDecimal.subtract(BigDecimal.ONE);

		localBigDecimal1 = scalePrec(localBigDecimal1, 2);
		Object localObject = new MathContext(localBigDecimal1.precision());

		double d1 = paramBigDecimal.ulp().doubleValue()
				/ paramBigDecimal.doubleValue();

		BigDecimal localBigDecimal2 = log(scalePrec(paramBigDecimal, 2))
				.negate();

		if (paramBigDecimal.compareTo(BigDecimal.ONE) != 0) {
			BigDecimal localBigDecimal3 = BigDecimal.ONE
					.subtract(gamma((MathContext) localObject));
			localBigDecimal2 = localBigDecimal2.add(multiplyRound(
					localBigDecimal1, localBigDecimal3));
			for (int j = 2;; j++) {
				BigDecimal localBigDecimal4 = divideRound(
						localBigDecimal1.pow(j, (MathContext) localObject), j);
				MathContext localMathContext = new MathContext(err2prec(j
						* localBigDecimal1.ulp().doubleValue() / 2.0D
						/ localBigDecimal1.doubleValue()));
				localBigDecimal4 = localBigDecimal4.round(localMathContext);

				if (d1 / 100.0D / localBigDecimal4.doubleValue() < 0.01D)
					localMathContext = new MathContext(err2prec(d1 / 100.0D
							/ localBigDecimal4.doubleValue()));
				else {
					localMathContext = new MathContext(2);
				}
				BigDecimal localBigDecimal5 = zeta(j, localMathContext)
						.subtract(BigDecimal.ONE);
				localBigDecimal4 = multiplyRound(localBigDecimal4,
						localBigDecimal5);

				if (j % 2 == 0)
					localBigDecimal2 = localBigDecimal2.add(localBigDecimal4);
				else {
					localBigDecimal2 = localBigDecimal2
							.subtract(localBigDecimal4);
				}

				if (Math.abs(localBigDecimal4.doubleValue()) < d1) {
					break;
				}
			}

		}

		double d2 = localBigDecimal1.doubleValue();
		d1 = psi(d2) * paramBigDecimal.ulp().doubleValue() / 2.0D;
		localObject = new MathContext(err2prec(d1));
		return (BigDecimal) exp(localBigDecimal2).round(
				(MathContext) localObject);
	}

	public static BigDecimal pochhammer(BigDecimal paramBigDecimal, int paramInt) {
		if (paramInt < 0)
			throw new ProviderException(
					"Unimplemented pochhammer with negative index " + paramInt);
		if (paramInt == 0) {
			return BigDecimal.ONE;
		}

		BigDecimal localBigDecimal1 = scalePrec(paramBigDecimal, 2);
		BigDecimal localBigDecimal2 = localBigDecimal1;

		double d1 = paramBigDecimal.ulp().doubleValue();
		double d2 = paramBigDecimal.doubleValue();

		double d3 = 0.5D * d1 / Math.abs(d2);
		for (int i = 1; i < paramInt; i++) {
			d3 += 0.5D * d1 / Math.abs(d2 + i);
			localBigDecimal2 = localBigDecimal2.multiply(localBigDecimal1
					.add(new BigDecimal(i)));
			MathContext localMathContext = new MathContext(4 + err2prec(d3));
			localBigDecimal2 = localBigDecimal2.round(localMathContext);
		}
		return localBigDecimal2.round(new MathContext(err2prec(d3)));
	}

	public static BigDecimal mod2pi(BigDecimal paramBigDecimal) {
		int i = (int) (0.5D * paramBigDecimal.doubleValue() / 3.141592653589793D);
		double d;
		if (i != 0)
			d = 0.25D * Math.abs(paramBigDecimal.ulp().doubleValue() / i);
		else
			d = 0.5D * Math.abs(paramBigDecimal.ulp().doubleValue());
		MathContext localMathContext = new MathContext(2 + err2prec(6.283D, d));
		BigDecimal localBigDecimal1 = pi(localMathContext).multiply(
				new BigDecimal(2));

		BigDecimal localBigDecimal2 = paramBigDecimal
				.remainder(localBigDecimal1);
		if (localBigDecimal2.compareTo(BigDecimal.ZERO) < 0) {
			localBigDecimal2 = localBigDecimal2.add(localBigDecimal1);
		}

		localMathContext = new MathContext(err2prec(
				localBigDecimal2.doubleValue(), paramBigDecimal.ulp()
						.doubleValue() / 2.0D));
		return localBigDecimal2.round(localMathContext);
	}

	public static BigDecimal modpi(BigDecimal paramBigDecimal) {
		int i = (int) (paramBigDecimal.doubleValue() / 3.141592653589793D);
		double d;
		if (i != 0)
			d = 0.5D * Math.abs(paramBigDecimal.ulp().doubleValue() / i);
		else
			d = 0.5D * Math.abs(paramBigDecimal.ulp().doubleValue());
		MathContext localMathContext = new MathContext(2 + err2prec(3.1416D, d));
		BigDecimal localBigDecimal1 = pi(localMathContext);
		BigDecimal localBigDecimal2 = localBigDecimal1
				.divide(new BigDecimal(2));

		BigDecimal localBigDecimal3 = paramBigDecimal
				.remainder(localBigDecimal1);
		if (localBigDecimal3.compareTo(localBigDecimal2) > 0)
			localBigDecimal3 = localBigDecimal3.subtract(localBigDecimal1);
		else if (localBigDecimal3.compareTo(localBigDecimal2.negate()) < 0) {
			localBigDecimal3 = localBigDecimal3.add(localBigDecimal1);
		}

		localMathContext = new MathContext(err2prec(
				localBigDecimal3.doubleValue(), paramBigDecimal.ulp()
						.doubleValue() / 2.0D));
		return localBigDecimal3.round(localMathContext);
	}

	public static BigDecimal zeta(int paramInt, MathContext paramMathContext) {
		if (paramInt <= 0)
			throw new ProviderException(
					"Unimplemented zeta at negative argument " + paramInt);
		if (paramInt == 1) {
			throw new ArithmeticException("Pole at zeta(1) ");
		}
		if (paramInt % 2 == 0) {
			Rational localObject1 = new Bernoulli().at(paramInt).abs();
			localObject1 = ((Rational) localObject1).divide(new Factorial()
					.at(paramInt));
			localObject1 = ((Rational) localObject1).multiply(BigInteger.ONE
					.shiftLeft(paramInt - 1));

			MathContext localObject2 = new MathContext(
					paramMathContext.getPrecision()
							+ (int) Math.log10(10.0D * paramInt));
			BigDecimal localObject3 = pi((MathContext) localObject2).pow(
					paramInt, paramMathContext);
			return multiplyRound((BigDecimal) localObject3,
					(Rational) localObject1);
		}
		BigDecimal localBigDecimal1;
		if (paramInt == 3) {
			int[] localObject1 = new int[] { 1, -7, -1, 10, -1, -7, 1, 0 };
			int[] localObject2 = new int[] { 1, 1, -1, -2, -1, 1, 1, 0 };
			BigDecimal localObject3 = broadhurstBBP(3, 1, localObject1,
					paramMathContext);
			localBigDecimal1 = broadhurstBBP(3, 3, localObject2,
					paramMathContext);
			localObject3 = ((BigDecimal) localObject3).multiply(new BigDecimal(
					48));
			localBigDecimal1 = localBigDecimal1.multiply(new BigDecimal(32));
			return ((BigDecimal) localObject3).add(localBigDecimal1).divide(
					new BigDecimal(7), paramMathContext);
		}
		if (paramInt == 5) {
			int[] localObject1 = new int[] { 31, -1614, -31, -6212, -31, -1614,
					31, 74552 };
			int[] localObject2 = new int[] { 173, 284, -173, -457, -173, 284,
					173, -111 };
			int[] localObject3 = new int[] { 1, 0, -1, -1, -1, 0, 1, 1 };
			localBigDecimal1 = broadhurstBBP(5, 1, localObject1,
					new MathContext(2 + paramMathContext.getPrecision()));
			BigDecimal localObject4 = broadhurstBBP(5, 3, localObject2,
					new MathContext(2 + paramMathContext.getPrecision()));
			BigDecimal localBigDecimal2 = broadhurstBBP(5, 5, localObject3,
					new MathContext(1 + paramMathContext.getPrecision()));
			localBigDecimal1 = localBigDecimal1.multiply(new BigDecimal(18432));
			localObject4 = ((BigDecimal) localObject4).multiply(new BigDecimal(
					14336));
			localBigDecimal2 = localBigDecimal2
					.multiply(new BigDecimal(1511424));
			return localBigDecimal1.add((BigDecimal) localObject4)
					.subtract(localBigDecimal2)
					.divide(new BigDecimal(62651), paramMathContext);
		}

		Object localObject1 = new Rational();
		Object localObject2 = new Bernoulli();
		Object localObject3 = new Factorial();
		for (int i = 0; i <= (paramInt + 1) / 2; i++) {
			Rational localObject4 = ((Bernoulli) localObject2)
					.at(2 * i)
					.multiply(
							((Bernoulli) localObject2).at(paramInt + 1 - 2 * i));
			localObject4 = ((Rational) localObject4).divide(
					((Factorial) localObject3).at(2 * i)).divide(
					((Factorial) localObject3).at(paramInt + 1 - 2 * i));
			localObject4 = ((Rational) localObject4).multiply(1 - 2 * i);
			if (i % 2 == 0)
				localObject1 = ((Rational) localObject1)
						.add((Rational) localObject4);
			else
				localObject1 = ((Rational) localObject1)
						.subtract((Rational) localObject4);
		}
		localObject1 = ((Rational) localObject1).divide(paramInt - 1);

		MathContext localMathContext = new MathContext(2
				+ paramMathContext.getPrecision() + (int) Math.log10(paramInt));
		Object localObject4 = pi(localMathContext).multiply(new BigDecimal(2));
		localObject4 = ((BigDecimal) localObject4).pow(paramInt);
		localObject4 = multiplyRound((BigDecimal) localObject4,
				((Rational) localObject1).BigDecimalValue(localMathContext));
		BigDecimal localBigDecimal2 = new BigDecimal(0);

		double d = Math.pow(10.0D, -paramMathContext.getPrecision());
		int j;
		BigDecimal localBigDecimal3;
		BigDecimal localBigDecimal4;
		if (paramInt % 4 == 3) {
			j = paramMathContext.getPrecision() / 3;
			d /= j;

			localBigDecimal3 = pi(new MathContext(3 + err2prec(3.14D,
					d / 0.0075D)));
			localBigDecimal3 = exp(localBigDecimal3.multiply(new BigDecimal(2)));
			localBigDecimal4 = localBigDecimal3.subtract(BigDecimal.ONE);
			localBigDecimal2 = divideRound(1, localBigDecimal4);
			for (int k = 2; k <= j; k++) {
				localBigDecimal4 = powRound(localBigDecimal3, k).subtract(
						BigDecimal.ONE);
				localBigDecimal4 = multiplyRound(localBigDecimal4,
						new BigInteger("" + k).pow(paramInt));
				localBigDecimal4 = divideRound(1, localBigDecimal4);
				localBigDecimal2 = localBigDecimal2.add(localBigDecimal4);
			}

		} else {
			j = (1 + paramMathContext.getPrecision()) / 3;
			d /= j;

			localBigDecimal3 = pi(new MathContext(3 + err2prec(3.14D,
					d / 0.017D)));
			localBigDecimal3 = localBigDecimal3.multiply(new BigDecimal(2));
			localBigDecimal4 = exp(localBigDecimal3);
			BigDecimal localBigDecimal5 = localBigDecimal4
					.subtract(BigDecimal.ONE);
			localBigDecimal2 = divideRound(1, localBigDecimal5);
			localBigDecimal5 = BigDecimal.ONE.subtract(divideRound(1,
					localBigDecimal4));
			localBigDecimal5 = divideRound(localBigDecimal3, localBigDecimal5)
					.multiply(new BigDecimal(2));
			localBigDecimal5 = divideRound(localBigDecimal5, paramInt - 1).add(
					BigDecimal.ONE);
			localBigDecimal2 = multiplyRound(localBigDecimal2, localBigDecimal5);
			for (int m = 2; m <= j; m++) {
				localBigDecimal5 = powRound(localBigDecimal4, m).subtract(
						BigDecimal.ONE);
				localBigDecimal5 = multiplyRound(localBigDecimal5,
						new BigInteger("" + m).pow(paramInt));

				BigDecimal localBigDecimal6 = divideRound(1,
						localBigDecimal4.pow(m));
				localBigDecimal6 = BigDecimal.ONE.subtract(localBigDecimal6);
				localBigDecimal6 = divideRound(localBigDecimal3,
						localBigDecimal6).multiply(new BigDecimal(2 * m));
				localBigDecimal6 = divideRound(localBigDecimal6, paramInt - 1)
						.add(BigDecimal.ONE);

				localBigDecimal6 = divideRound(localBigDecimal6,
						localBigDecimal5);

				localBigDecimal2 = localBigDecimal2.add(localBigDecimal6);
			}
		}
		localBigDecimal2 = localBigDecimal2.multiply(new BigDecimal(2));
		return (BigDecimal) (BigDecimal) (BigDecimal) (BigDecimal) ((BigDecimal) localObject4)
				.subtract(localBigDecimal2, paramMathContext);
	}

	public static double zeta1(int paramInt) {
		double[] arrayOfDouble = { 0.0D, 0.0D, 0.6449340668482264D,
				0.2020569031595943D, 0.08232323371113819D,
				0.03692775514336993D, 0.0173430619844491D,
				0.008349277381922827D, 0.00407735619794434D,
				0.002008392826082214D, 0.0009945751278180853D,
				0.0004941886041194645D, 0.0002460865533080483D,
				0.0001227133475784892D, 6.124813505870483E-05D,
				3.058823630702049E-05D, 1.528225940865187E-05D,
				7.637197637899763E-06D, 3.81729326499984E-06D,
				1.908212716553939E-06D, 9.539620338727962E-07D,
				4.769329867878065E-07D, 2.38450502727733E-07D,
				1.192199259653111E-07D, 5.960818905125948E-08D,
				2.980350351465228E-08D, 1.490155482836504E-08D,
				7.45071178983543E-09D, 3.725334024788457E-09D,
				1.862659723513049E-09D, 9.313274324196682E-10D,
				4.656629065033784E-10D, 2.328311833676505E-10D,
				1.164155017270052E-10D, 5.820772087902702E-11D,
				2.9103850444971E-11D, 1.455192189104199E-11D,
				7.275959835057482E-12D, 3.637979547378651E-12D,
				1.818989650307066E-12D, 9.094947840263888E-13D,
				4.547473783042154E-13D, 2.273736845824652E-13D,
				1.136868407680228E-13D, 5.684341987627585E-14D,
				2.842170976889302E-14D, 1.421085482803161E-14D,
				7.105427395210853E-15D, 3.552713691337114E-15D,
				1.77635684357912E-15D, 8.881784210930816E-16D,
				4.440892103143813E-16D, 2.220446050798042E-16D,
				1.110223025141066E-16D, 5.551115124845481E-17D,
				2.775557562136124E-17D, 1.387778780972523E-17D,
				6.938893904544153E-18D, 3.469446952165923E-18D,
				1.734723476047577E-18D, 8.673617380119933E-19D,
				4.336808690020651E-19D, 2.16840434499722E-19D,
				1.084202172494241E-19D, 5.421010862456646E-20D,
				2.710505431223469E-20D, 1.355252715610116E-20D,
				6.776263578045189E-21D, 3.388131789020797E-21D,
				1.694065894509799E-21D, 8.470329472546998E-22D,
				4.235164736272833E-22D, 2.117582368136195E-22D,
				1.058791184068023E-22D, 5.293955920339871E-23D,
				2.646977960169853E-23D, 1.323488980084899E-23D,
				6.617444900424404E-24D, 3.308722450212172E-24D,
				1.654361225106076E-24D, 8.271806125530345E-25D,
				4.135903062765161E-25D, 2.067951531382577E-25D,
				1.033975765691287E-25D, 5.169878828456431E-26D,
				2.584939414228214E-26D, 1.292469707114107E-26D,
				6.462348535570532E-27D, 3.231174267785265E-27D,
				1.615587133892633E-27D, 8.077935669463163E-28D,
				4.03896783473158E-28D, 2.01948391736579E-28D,
				1.009741958682895E-28D, 5.048709793414476E-29D,
				2.524354896707238E-29D, 1.262177448353619E-29D,
				6.310887241768094E-30D, 3.155443620884047E-30D,
				1.577721810442024E-30D, 7.888609052210118E-31D };

		if (paramInt <= 0)
			throw new ProviderException(
					"Unimplemented zeta at negative argument " + paramInt);
		if (paramInt == 1) {
			throw new ArithmeticException("Pole at zeta(1) ");
		}
		if (paramInt < arrayOfDouble.length) {
			return arrayOfDouble[paramInt];
		}

		double d = 1.E-18D * Math.pow(2.0D, -paramInt);
		MathContext localMathContext = new MathContext(err2prec(d));
		return zeta(paramInt, localMathContext).subtract(BigDecimal.ONE)
				.doubleValue();
	}

	public static double cot(double paramDouble) {
		return 1.0D / Math.tan(paramDouble);
	}

	public static double psi(double paramDouble) {
		double d2;
		double d4;
		int k;
		if (paramDouble > 2.0D) {
			int i = (int) (paramDouble - 0.5D);
			d2 = paramDouble - i;
			d4 = 0.0D;
			for (k = 1; k <= i; k++)
				d4 += 1.0D / (paramDouble - k);
			return d4 + psi(d2);
		}
		if (Math.abs(paramDouble - 1.461632144968362D) < 0.55D) {
			double[] arrayOfDouble = { 0.967672245447621D,
					-0.4427631689835921D, 0.258499760955651D,
					-0.1639427054424065D, 0.1078240506912624D,
					-0.07219956125645471D, 0.0488042881641431D,
					-0.03316112647484736D, 0.0225976482322181D,
					-0.015424765904949D, 0.01053879161661218D,
					-0.007204534386356869D, 0.004926781395729853D,
					-0.003369801655439328D, 0.00230512632673493D,
					-0.001576936771430197D, 0.001078825201916297D,
					-0.0007380709389960052D, 0.000504953265834602D,
					-0.0003454680251063077D, 0.0002363560156402705D,
					-0.000161706220919748D, 0.000110633727687474D,
					-7.569179582195066E-05D, 5.178575795222081E-05D,
					-3.54300709476596E-05D, 2.424006611860132E-05D,
					-1.658424227185414E-05D, 1.134638458466385E-05D,
					-7.762817668462094E-06D, 5.311060920889864E-06D,
					-3.633650789801046E-06D, 2.486022733129538E-06D,
					-1.700853885433261E-06D, 1.163667536354884E-06D,
					-7.96142543124197E-07D, 5.446941930669446E-07D,
					-3.726616128343823E-07D, 2.549626552021554E-07D,
					-1.744369511772775E-07D, 1.193439482983024E-07D,
					-8.165115189488409E-08D, 5.586299683532171E-08D,
					-3.821960061917494E-08D, 2.614857695196187E-08D,
					-1.788998486491149E-08D, 1.223973140323366E-08D,
					-8.374016297671791E-09D, 5.729222859849993E-09D };

			d2 = paramDouble - 1.461632144968362D;
			d4 = 0.0D;
			for (k = arrayOfDouble.length - 1; k >= 0; k--)
				d4 = d4 * d2 + arrayOfDouble[k];
			return d4 * d2;
		}
		if (paramDouble < 0.0D) {
			double d1 = 1.0D - paramDouble;
			return psi(d1) + 3.141592653589793D
					/ Math.tan(3.141592653589793D * d1);
		}

		double d1 = paramDouble - 1.0D;
		double d3 = 0.0D;
		for (int j = 26; j >= 1; j--) {
			d3 -= zeta1(2 * j + 1);
			d3 *= d1 * d1;
		}

		return d3 + 0.4227843350984671D + 0.5D / d1 - 1.0D / (1.0D - d1 * d1)
				- 3.141592653589793D
				/ (2.0D * Math.tan(3.141592653589793D * d1));
	}

	public static double psi(int paramInt, double paramDouble) {
		if (paramInt == 0) {
			return psi(paramDouble);
		}

		if (paramDouble > 1.5D) {
			int i = (int) (paramDouble - 0.5D);
			double d2 = paramDouble - i;
			double d4 = 0.0D;
			for (int j = 1; j <= i; j++)
				d4 += 1.0D / Math.pow(paramDouble - j, paramInt + 1.0D);
			if (paramInt % 2 != 0)
				d4 *= -1.0D;
			for (int j = 1; j <= paramInt; j++)
				d4 *= j;
			return d4 + psi(paramInt, d2);
		}
		double d1;
		if ((paramDouble < 0.0D) && (paramInt == 1)) {
			d1 = 1.0D - paramDouble;
			return -psi(paramInt, d1)
					+ Math.pow(3.141592653589793D / Math
							.sin(3.141592653589793D * d1), 2.0D);
		}
		if ((paramDouble < 0.0D) && (paramInt == 2)) {
			d1 = 1.0D - paramDouble;
			return psi(paramInt, d1)
					+ 2.0D
					* Math.pow(3.141592653589793D / Math
							.sin(3.141592653589793D * d1), 3.0D)
					* Math.cos(3.141592653589793D * d1);
		}
		double d3;
		if ((paramDouble < 0.0D) && (paramInt == 3)) {
			d1 = 1.0D - paramDouble;
			d3 = Math.sin(3.141592653589793D * d1);
			return -psi(paramInt, d1) + 2.0D
					* Math.pow(3.141592653589793D / d3, 4.0D)
					* (3.0D - 2.0D * d3 * d3);
		}
		if ((paramDouble < 0.0D) && (paramInt == 4)) {
			d1 = 1.0D - paramDouble;
			d3 = Math.sin(3.141592653589793D * d1);
			return psi(paramInt, d1) + 8.0D
					* Math.pow(3.141592653589793D / d3, 5.0D)
					* (3.0D - d3 * d3) * Math.cos(3.141592653589793D * d1);
		}
		if (paramDouble <= -1.0D) {
			throw new ProviderException(
					"Unimplemented polygamma at negative argument "
							+ paramDouble);
		}

		throw new ProviderException("Unimplemented polygamma at argument n= "
				+ paramInt);
	}

	protected static BigDecimal broadhurstBBP(int paramInt1, int paramInt2,
			int[] paramArrayOfInt, MathContext paramMathContext) {
		double d1 = 0.0D;
		for (int i = 1; i < 10; i++) {
			d1 += paramArrayOfInt[((i - 1) % 8)]
					/ Math.pow(2.0D, paramInt2 * (i + 1) / 2)
					/ Math.pow(i, paramInt1);
		}

		double d2 = prec2err(d1, paramMathContext.getPrecision());

		int j = (int) (6.6D * paramMathContext.getPrecision() / paramInt2);

		d2 /= j;
		BigDecimal localBigDecimal = BigDecimal.ZERO;
		for (int k = 0;; k++) {
			Rational localRational1 = new Rational();
			for (int m = 0; m < 8; m++) {
				Rational localRational2 = new Rational(new BigInteger(""
						+ paramArrayOfInt[m]), new BigInteger(""
						+ (1 + 8 * k + m)).pow(paramInt1));

				int n = paramInt2 * (2 + 8 * k + m) / 2;
				localRational2 = localRational2.divide(BigInteger.ONE
						.shiftLeft(n));
				localRational1 = localRational1.add(localRational2);
			}

			if (Math.abs(localRational1.doubleValue()) < d2)
				break;
			MathContext localMathContext = new MathContext(1 + err2prec(
					localRational1.doubleValue(), d2));
			localBigDecimal = localBigDecimal.add(localRational1
					.BigDecimalValue(localMathContext));
		}
		return localBigDecimal.round(paramMathContext);
	}

	public static BigDecimal EllipticK(BigDecimal paramBigDecimal) {
		if (paramBigDecimal.abs().compareTo(BigDecimal.ONE) > 0) {
			throw new ProviderException(
					"Unimplemented Elliptic-K with parameter m "
							+ paramBigDecimal.doubleValue() + " larger than 1.");
		}

		BigDecimal localBigDecimal1 = F21(Rational.HALF, Rational.HALF,
				Rational.ONE, paramBigDecimal);

		BigDecimal localBigDecimal2 = new BigDecimal("0.5");
		localBigDecimal1 = localBigDecimal1.multiply(localBigDecimal2);

		int i = err2prec(localBigDecimal1, localBigDecimal1.ulp());
		MathContext localMathContext = new MathContext(3 + i);
		BigDecimal localBigDecimal3 = pi(localMathContext);
		return multiplyRound(localBigDecimal1, localBigDecimal3);
	}

	public static BigDecimal F21(Rational paramRational1,
			Rational paramRational2, Rational paramRational3,
			BigDecimal paramBigDecimal) {
		if (paramRational1.compareTo(paramRational2) > 0) {
			return F21(paramRational2, paramRational1, paramRational3,
					paramBigDecimal);
		}
		Rational localRational = new Rational("3/2");

		if (paramRational1.compareTo(BigInteger.ZERO) == 0)
			return BigDecimal.ONE;
		if ((paramRational1.compareTo(BigInteger.ZERO) <= 0)
				&& (paramRational1.denom().abs().compareTo(BigInteger.ONE) == 0)) {
			throw new ProviderException(
					"Unimplemented F21 with negative numerator.");
		}
		if ((paramRational1.compareTo(BigInteger.ONE) == 0)
				&& (paramRational2.compareTo(BigInteger.ONE) == 0)
				&& (paramRational3.compareTo(new BigInteger("2")) == 0)) {
			throw new ProviderException("Unimplemented F21(1,1;2;z).");
		}
		if ((paramRational1.compareTo(Rational.HALF) == 0)
				&& (paramRational2.compareTo(BigInteger.ONE) == 0)
				&& (paramRational3.compareTo(localRational) == 0)) {
			throw new ProviderException("Unimplemented F21(1/2,1;3/2;z).");
		}
		if (paramBigDecimal.abs().compareTo(BigDecimal.ONE) > 0) {
			throw new ProviderException("Unimplemented F21 with argument >1.");
		}
		if (paramBigDecimal.abs().compareTo(BigDecimal.ONE) == 0) {
			throw new ProviderException("Unimplemented F21 with argument =1.");
		}
		if (paramBigDecimal.abs().compareTo(new BigDecimal("0.005")) < 0) {
			return F21series(paramRational1, paramRational2, paramRational3,
					paramBigDecimal);
		}
		if (paramRational2.multiply(2).compareTo(paramRational3) == 0) {
			BigDecimal localBigDecimal1 = new BigDecimal("0.99");
			if (paramBigDecimal.compareTo(localBigDecimal1) <= 0) {
				BigDecimal localBigDecimal2 = sqrt(BigDecimal.ONE
						.subtract(paramBigDecimal));

				BigDecimal localBigDecimal3 = BigDecimal.ONE
						.add(localBigDecimal2);

				localBigDecimal2 = divideRound(
						BigDecimal.ONE.subtract(localBigDecimal2),
						localBigDecimal3);
				BigDecimal localBigDecimal4 = F21series(
						paramRational1,
						paramRational1.subtract(paramRational2).add(
								Rational.HALF),
						paramRational2.add(Rational.HALF),
						localBigDecimal2.pow(2));

				localBigDecimal2 = localBigDecimal2.divide(new BigDecimal("2"));

				localBigDecimal2 = powRound(localBigDecimal2,
						paramRational1.multiply(-22));

				return divideRound(localBigDecimal4, localBigDecimal2);
			}

			throw new ProviderException(
					"Unimplemented F21 with argument close to1.");
		}

		return F21series(paramRational1, paramRational2, paramRational3,
				paramBigDecimal);
	}

	private static BigDecimal F21series(Rational paramRational1,
			Rational paramRational2, Rational paramRational3,
			BigDecimal paramBigDecimal) {
		BigDecimal localBigDecimal1 = BigDecimal.ONE;

		BigDecimal localBigDecimal2 = BigDecimal.ONE;
		return BigDecimal.ZERO;
	}

	public static BigDecimal addRound(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		BigDecimal localBigDecimal = paramBigDecimal1.add(paramBigDecimal2);

		double d = Math.abs(paramBigDecimal2.ulp().doubleValue() / 2.0D)
				+ Math.abs(paramBigDecimal1.ulp().doubleValue() / 2.0D);
		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal.doubleValue(), d));
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal subtractRound(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		BigDecimal localBigDecimal = paramBigDecimal1
				.subtract(paramBigDecimal2);

		double d = Math.abs(paramBigDecimal2.ulp().doubleValue() / 2.0D)
				+ Math.abs(paramBigDecimal1.ulp().doubleValue() / 2.0D);
		MathContext localMathContext = new MathContext(err2prec(
				localBigDecimal.doubleValue(), d));
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal multiplyRound(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		BigDecimal localBigDecimal = paramBigDecimal1
				.multiply(paramBigDecimal2);

		MathContext localMathContext = new MathContext(Math.min(
				paramBigDecimal1.precision(), paramBigDecimal2.precision()));
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal multiplyRound(BigDecimal paramBigDecimal,
			Rational paramRational) {
		if (paramRational.compareTo(BigInteger.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		MathContext localMathContext = new MathContext(
				2 + paramBigDecimal.precision());
		BigDecimal localBigDecimal = paramRational
				.BigDecimalValue(localMathContext);

		return multiplyRound(paramBigDecimal, localBigDecimal);
	}

	public static BigDecimal multiplyRound(BigDecimal paramBigDecimal,
			int paramInt) {
		BigDecimal localBigDecimal = paramBigDecimal.multiply(new BigDecimal(
				paramInt));

		MathContext localMathContext = new MathContext(
				paramInt != 0 ? paramBigDecimal.precision() : 0);
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal multiplyRound(BigDecimal paramBigDecimal,
			BigInteger paramBigInteger) {
		BigDecimal localBigDecimal = paramBigDecimal.multiply(new BigDecimal(
				paramBigInteger));

		MathContext localMathContext = new MathContext(
				paramBigInteger.compareTo(BigInteger.ZERO) != 0 ? paramBigDecimal
						.precision() : 0);
		return localBigDecimal.round(localMathContext);
	}

	public static BigDecimal divideRound(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		MathContext localMathContext = new MathContext(Math.min(
				paramBigDecimal1.precision(), paramBigDecimal2.precision()));
		return paramBigDecimal1.divide(paramBigDecimal2, localMathContext);
	}

	public static BigDecimal divideRound(BigDecimal paramBigDecimal,
			int paramInt) {
		MathContext localMathContext = new MathContext(
				paramBigDecimal.precision());
		return paramBigDecimal.divide(new BigDecimal(paramInt),
				localMathContext);
	}

	public static BigDecimal divideRound(BigDecimal paramBigDecimal,
			BigInteger paramBigInteger) {
		MathContext localMathContext = new MathContext(
				paramBigDecimal.precision());
		return paramBigDecimal.divide(new BigDecimal(paramBigInteger),
				localMathContext);
	}

	public static BigDecimal divideRound(BigInteger paramBigInteger,
			BigDecimal paramBigDecimal) {
		MathContext localMathContext = new MathContext(
				paramBigDecimal.precision());
		return new BigDecimal(paramBigInteger).divide(paramBigDecimal,
				localMathContext);
	}

	public static BigDecimal divideRound(int paramInt,
			BigDecimal paramBigDecimal) {
		MathContext localMathContext = new MathContext(
				paramBigDecimal.precision());
		return new BigDecimal(paramInt).divide(paramBigDecimal,
				localMathContext);
	}

	public static BigDecimal scalePrec(BigDecimal paramBigDecimal, int paramInt) {
		return paramBigDecimal.setScale(paramInt + paramBigDecimal.scale());
	}

	public static BigDecimal scalePrec(BigDecimal paramBigDecimal,
			MathContext paramMathContext) {
		int i = paramMathContext.getPrecision() - paramBigDecimal.precision();
		if (i > 0) {
			return scalePrec(paramBigDecimal, i);
		}
		return paramBigDecimal;
	}

	public static int err2prec(BigDecimal paramBigDecimal1,
			BigDecimal paramBigDecimal2) {
		return err2prec(paramBigDecimal2.divide(paramBigDecimal1,
				MathContext.DECIMAL64).doubleValue());
	}

	public static int err2prec(double paramDouble1, double paramDouble2) {
		return 1 + (int) Math.log10(Math
				.abs(0.5D * paramDouble1 / paramDouble2));
	}

	public static int err2prec(double paramDouble) {
		return 1 + (int) Math.log10(Math.abs(0.5D / paramDouble));
	}

	public static double prec2err(double paramDouble, int paramInt) {
		return 5.0D * Math.abs(paramDouble) * Math.pow(10.0D, -paramInt);
	}
}