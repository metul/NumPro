package numpro1;



import java.util.Arrays;

public class Test_Gleitpunktzahl {
/**
 * Testklasse, ob alles funktioniert!
 */
	
	
	public static void main(String[] argv) {
		test_Gleitpunktzahl();
	}

	public static void test_Gleitpunktzahl() {

		/**********************************/
		/* Test der Klasse Gleitpunktzahl */
		/**********************************/

		System.out.println("-----------------------------------------");
		System.out.println("Test der Klasse Gleitpunktzahl");

		/*
		 * Verglichen werden die BitFelder fuer Mantisse und Exponent und das
		 * Vorzeichen
		 */
		Gleitpunktzahl.setSizeMantisse(6);
		Gleitpunktzahl.setSizeExponent(3); 
                

		Gleitpunktzahl x;
		Gleitpunktzahl y;
                Gleitpunktzahl testVariable1;
                Gleitpunktzahl testVariable2;
                /*
                 * For mantissa size 6 and exponent size 3:
                 * Max Gleitpunktzahl ~ 16 and Min Gleitpunktzahl ~ 0.125
                 */
                testVariable1 = new Gleitpunktzahl (32);
                testVariable2 = new Gleitpunktzahl (1.0 / 8.0);
                System.out.println(testVariable1 + " ~ " + testVariable2);
		Gleitpunktzahl gleitref = new Gleitpunktzahl();
		Gleitpunktzahl gleiterg;

		/* Test von setDouble */
		System.out.println("Test von setDouble");
		try {
			// Test: setDouble
			x = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(0.5);

			// Test, ob Ergebnis korrekt
			if (x.compareAbsTo(gleitref) != 0
					|| x.vorzeichen != gleitref.vorzeichen) {
				printErg("" + x.toDouble(), "" + gleitref.toDouble());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			/*************
			 * Eigene Tests einfuegen
			 */
                         
			System.out.println("\n\nEIGENE TEST: set Double");

                        //Eigene Test: set Double
                        
                        //betragsmäßig kleinste Darstellbare Zahl
                        testVariable2 = new Gleitpunktzahl(1.0 / 8.0);
                        gleitref = new Gleitpunktzahl(1.0 / 8.0);
                        if(testVariable2.compareAbsTo(gleitref) != 0 
                                        || testVariable2.vorzeichen != gleitref.vorzeichen){
                                printErg("" + testVariable2.toDouble(), "" + gleitref.toDouble());
                        } else {
				System.out.println("    Richtiges Ergebnis\n");
			}
                        //betragsmäßig größte Darstellbare Mantisse
                        testVariable1= new Gleitpunktzahl (63);
                        gleitref = new Gleitpunktzahl (63);
                        if(testVariable1.compareAbsTo(gleitref) != 0 
                                        || testVariable1.vorzeichen != gleitref.vorzeichen){
                                printErg("" + testVariable1.toDouble(), "" + gleitref.toDouble());
                        } else {
				System.out.println("    Richtiges Ergebnis\n");
			}
                      

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Addition */
		System.out.println("Test der Addition mit Gleitpunktzahl");
		try {
			// Test: Addition
			System.out.println("Test: Addition  x + y");
			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(3.25 + 0.5);

			// Berechnung
			gleiterg = x.add(y);
                        System.out.println(x.toString() + y.toString()+ " = "+ gleiterg.toString());
			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			/*************
			 * Eigene Tests einfuegen
			 */
			System.out.println("\n\nEIGENE TESTS: Addition \n\n");
                        testVariable1 = new Gleitpunktzahl (63);
                        testVariable2 = new Gleitpunktzahl (1.0 / 8.0);
                        gleitref = new Gleitpunktzahl(63 + 1.0 / 8.0);
                        Gleitpunktzahl sum = testVariable1.add(testVariable2);
                        
                        //Test, ob Ergebnis korrekt ist
                                           
                        System.out.println("sum = "+ sum.toString());
                        System.out.println(testVariable1.toDouble()+" + "+testVariable2.toDouble()+" = " +sum.toDouble()+"\n");
                        
                        if (sum.compareAbsTo(gleitref) != 0
					|| sum.vorzeichen != gleitref.vorzeichen) {
				printAdd(testVariable1.toString(), testVariable2.toString());
				printErg(sum.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

                              

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Subtraktion */
		try {
			System.out.println("Test der Subtraktion mit Gleitpunktzahl");

			// Test: Addition
			System.out.println("Test: Subtraktion  x - y");
			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(2.75);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((3.25 - 2.75));

			// Berechnung
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			/*************
			 * Eigene Tests einfuegen
			 */

			System.out.println("\n\nEIGENE TESTS: Subtraktion\n\n");                       
                        testVariable1 = new Gleitpunktzahl (63);
                        testVariable2 = new Gleitpunktzahl (50.0 / 16.0);
                        gleitref = new Gleitpunktzahl(63 - 50.0 / 16.0);
                        Gleitpunktzahl sub = testVariable1.sub(testVariable2); 
                        
                        // Test, ob Ergebnis korrekt ist
                                            
                        System.out.println("sub = "+ sub.toString());
                        System.out.println(testVariable1.toDouble()+" - "+testVariable2.toDouble()+" = " +sub.toDouble()+"\n");
                        
			if (sub.compareAbsTo(gleitref) != 0
					|| sub.vorzeichen != gleitref.vorzeichen) {
				printSub(testVariable1.toString(), testVariable2.toString());
				printErg(sub.toString(), gleitref.toString());
			} else {
				System.out.println("  Richtiges Ergebnis\n");
			}

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Sonderfaelle */
		System.out.println("Test der Sonderfaelle mit Gleitpunktzahl");

		try {
			// Test: Sonderfaelle
			// 0 - inf
			System.out.println("Test: Sonderfaelle");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(1.0 / 0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(true);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}
			
			/*************
			 * Eigene Tests einfuegen
			 */
			System.out.println("\n\nEIGENE TESTS: Sonderfälle\n\n");
                        Gleitpunktzahl inf = new Gleitpunktzahl(63 / (1.0 / 8.0));
                        System.out.println("63 / (1 / 8) = " + inf);


		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis in der Klasse Gleitpunktzahl!!\n");
		}

	}


	private static void printAdd(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " + " + y);
	}

	private static void printSub(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " - " + y + " = \n");
	}

	private static void printErg(String erg, String checkref) {
		System.out.println("      Ihr Ergebnis lautet:           " + erg
				+ "\n      Das Korrekte Ergebnis lautet:  " + checkref + "\n");
	}
}