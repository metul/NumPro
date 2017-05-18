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
                Gleitpunktzahl max;
                Gleitpunktzahl min;
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
                        min = new Gleitpunktzahl(1/8);
                        gleitref = new Gleitpunktzahl(1/8);
                        if(min.compareAbsTo(gleitref) != 0 
                                        || min.vorzeichen != gleitref.vorzeichen){
                                printErg("" + min.toDouble(), "" + gleitref.toDouble());
                        }
                        //betragsmäßig größte Darstellbare Mantisse
                        max= new Gleitpunktzahl (63);
                        gleitref = new Gleitpunktzahl (63);
                        if(max.compareAbsTo(gleitref) != 0 
                                        || max.vorzeichen != gleitref.vorzeichen){
                                printErg("" + max.toDouble(), "" + gleitref.toDouble());
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
                        max = new Gleitpunktzahl (63);
                        min = new Gleitpunktzahl (1/8);
                        gleitref = new Gleitpunktzahl(63+1/8);
                        Gleitpunktzahl sum = max.add(min);
			System.out.println("\n\nEIGENE TESTS: Addition \n\n");
                        
                        //Test, ob Ergebnis korrekt ist
                        
                        if (sum.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(max.toString(), min.toString());
				printErg(sum.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}
                        System.out.println("sum = "+ sum.toString());
                        System.out.println(max.toDouble()+" + "+min.toDouble()+" = " +sum.toDouble()+"\n");

                              

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
                        max = new Gleitpunktzahl (63);
                        min = new Gleitpunktzahl (50/16);
                        gleitref = new Gleitpunktzahl(63-50/16);
                        Gleitpunktzahl sub = max.sub(min); 
                        
                        // Test, ob Ergebnis korrekt ist
			if (sub.compareAbsTo(gleitref) != 0
					|| sub.vorzeichen != gleitref.vorzeichen) {
				printSub(max.toString(), min.toString());
				printErg(sub.toString(), gleitref.toString());
			} else {
				System.out.println("  Richtiges Ergebnis\n");
			}
                        System.out.println("sub = "+ sub.toString());
                        System.out.println(max.toDouble()+" - "+min.toDouble()+" = " +sub.toDouble()+"\n");

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
                        Gleitpunktzahl inf= new Gleitpunktzahl(63/(1/8));
			System.out.println("\n\nEIGENE TESTS: Sonderfälle\n\n");
                        System.out.println("63/(1/8) = " + inf);


		} catch (Exception e) {
			System.out
					.print("Exception bei der Auswertung des Ergebnis in der Klasse Gleitpunktzahl!!\n");
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