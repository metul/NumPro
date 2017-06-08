package numpro2;

import java.util.Arrays;
import java.util.Comparator;

public class PageRank {

	/**
	 * Diese Methode erstellt die Matrix A~ fuer das PageRank-Verfahren
	 * PARAMETER: 
	 * L: die Linkmatrix (s. Aufgabenblatt) 
	 * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
	 *      zufaellig irgendeine Seite zu besuchen
	 */
	public static double[][] buildProbabilityMatrix(int[][] L, double rho) {
	
            int n = L.length;
            // Initialise probability matrix
            double[][] A = LinkMatrixToProbabilityMatrix(L);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = (1 - rho) * A[i][j] + (rho / n);
                }
            }
            return A;
	}
        
        public static double[][] LinkMatrixToProbabilityMatrix(int[][] L) {
            int n = L.length;
            double[][] A = new double[n][n];
            double[] sumVec = new double[n];
            double sum;
            // Calculate link count for each site
            for (int x = 0; x < n; x++) {
                sum = 0;
                for (int y = 0; y < n; y++) {
                    if (L[y][x] == 1) {
                        sum++;
                    }
                }
                sumVec[x] = sum;
            }
            // Calculate and write probabilities
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (L[y][x] == 1) {
                        A[y][x] = 1 / sumVec[x];
                    }
                }
            }
            return A;
        }
        
        public static double vectorsum(int k, int n, double[] vec){
            double result = 0;
            for (int i = k; i <= n; i++) {
                result += vec[i];
            }
            return result;
        } 

	/**
	 * Diese Methode berechnet die PageRanks der einzelnen Seiten,
	 * also das Gleichgewicht der Aufenthaltswahrscheinlichkeiten.
	 * (Entspricht dem p-Strich aus der Angabe)
	 * Die Ausgabe muss dazu noch normiert sein.
	 * PARAMETER:
	 * L: die Linkmatrix (s. Aufgabenblatt) 
	 * rho: Wahrscheinlichkeit, zufaellig irgendeine Seite zu besuchen
	 * ,anstatt einem Link zu folgen.
	 *      
	 */
	public static double[] rank(int[][] L, double rho) {
            int n = L.length;
            double[] p = new double[n];
            //Matrix(A-I) aufstellen
            double[][] A = new double[n][n];
            A = buildProbabilityMatrix(L, rho);
            int j = 0; // Indexvariable
            for (int i = 0; i < n; i++) {
                A[i][j] -= 1;
                j++;
            }
            //singuläre Matrix(A-I) lösen für (A-I)p=0
            p = Gauss.solveSing(A);
            
            if (!Gauss.isNullVector(p)) {     
                //p normieren
                double lambda = 1 / vectorsum(0, n - 1, p);
                for (int i = 0; i < n; i++) {
                    p[i] *= lambda;
                }
            }
            return p;
	}

	/**
	 * Diese Methode erstellt eine Rangliste der uebergebenen URLs nach
	 * absteigendem PageRank. 
 	 * PARAMETER:
 	 * urls: Die URLs der betrachteten Seiten
 	 * L: die Linkmatrix (s. Aufgabenblatt) 
 	 * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
 	 *      zufaellig irgendeine Seite zu besuchen
	 */ 
	public static String[] getSortedURLs(String[] urls, int[][] L, double rho) {
		int n = L.length;

		double[] p = rank(L, rho);

		RankPair[] sortedPairs = new RankPair[n];
		for (int i = 0; i < n; i++) {
			sortedPairs[i] = new RankPair(urls[i], p[i]);
		}

		Arrays.sort(sortedPairs, new Comparator<RankPair>() {

			@Override
			public int compare(RankPair o1, RankPair o2) {
				return -Double.compare(o1.pr, o2.pr);
			}
		});

		String[] sortedUrls = new String[n];
		for (int i = 0; i < n; i++) {
			sortedUrls[i] = sortedPairs[i].url;
		}

		return sortedUrls;
	}

	/**
	 * Ein RankPair besteht aus einer URL und dem zugehoerigen Rang, und dient
	 * als Hilfsklasse zum Sortieren der Urls
	 */
	private static class RankPair {
		public String url;
		public double pr;

		public RankPair(String u, double p) {
			url = u;
			pr = p;
		}
	}
}
