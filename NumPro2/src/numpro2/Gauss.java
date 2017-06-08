package numpro2;



public class Gauss {

	/**
	 * Diese Methode soll die Loesung x des LGS R*x=b durch
	 * Rueckwaertssubstitution ermitteln.
	 * PARAMETER: 
	 * R: Eine obere Dreiecksmatrix der Groesse n x n 
	 * b: Ein Vektor der Laenge n
	 */
	public static double[] backSubst(double[][] R, double[] b) {
		//TODO: Diese Methode ist zu implementieren
                double[] x = new double[b.length];
                double tmp;
                for (int i = b.length - 1; i >= 0; i--) {
                    tmp = 0;
                    for (int j = i + 1; j < b.length; j++) {
                        tmp +=  R[i][j] *  x[j];
                    }
                    x[i] = (b[i] - tmp) / R[i][i];
                }
		return x;
	}

	/**
	 * Diese Methode soll die Loesung x des LGS A*x=b durch Gauss-Elimination mit
	 * Spaltenpivotisierung ermitteln. A und b sollen dabei nicht veraendert werden. 
	 * PARAMETER: A:
	 * Eine regulaere Matrix der Groesse n x n 
	 * b: Ein Vektor der Laenge n
	 */
	public static double[] solve(double[][] A, double[] b) {
		double[] x = new double[b.length];
                // Create augmented matrix
                double[][] augMat = new double[A.length][A[0].length + 1];
                int augMatRow = augMat.length;
                int augMatColumn = augMat[0].length;
                for (int i = 0; i < augMatRow; i++) {
                    for (int j = 0; j < augMatColumn - 1; j++) {
                        augMat[i][j] = A[i][j];
                    }
                    augMat[i][augMat[0].length - 1] = b[i];
                }
                // Put augmented matrix into upper triangle form
                for (int k = 0; k < augMatColumn - 1; k++) {
                    // Find pivot row
                    double biggestElement = augMat[k][k];
                    int pivotRow = k;
                    for (int j = 1; j < augMatRow; j++) {
                        if (Math.abs(augMat[j][k]) > Math.abs(biggestElement)) {
                            biggestElement = augMat[j][k];
                            pivotRow = j;
                        }
                    }
                    // Switch rows
                    double[] tmp = augMat[k];
                    augMat[k] = augMat[pivotRow];
                    augMat[pivotRow] = tmp;
                    // Subtraction
                    for (int i = k + 1; i < augMatRow; i++) {
                        if (augMat[k][k] != 0) {
                            augMat[i] = ArraySubtraction(augMat[i], ArrayMultiplication(augMat[k], augMat[i][k] / augMat[k][k]));
                        }                      
                    }
                }
                // Solve the system
                double[][] R = new double[A.length][A[0].length];
                double[] bNew = new double[b.length];
                // Write R
                for (int i = 0; i < R.length; i++) {
                    for (int j = 0; j < R[0].length; j++) {
                        R[i][j] = augMat[i][j];
                    }
                }
                // Write b
                for (int i = 0; i < b.length; i++) {
                    bNew[i] = augMat[i][augMatColumn - 1];
                }
                x = backSubst(R, b);
		return x;
	}
        
        public static double[] ArraySubtraction(double[] a, double[] b) {
            double[] result = new double[a.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = a[i] - b[i];
            }
            return result;
        }
        
        public static double[] ArrayMultiplication(double[] a, double multiplier) {
            double[] result = new double[a.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = a[i] * multiplier;
            }
            return result;
        }

	/**
	 * Diese Methode soll eine Loesung p!=0 des LGS A*p=0 ermitteln. A ist dabei
	 * eine nicht invertierbare Matrix. A soll dabei nicht veraendert werden.
	 * 
	 * Gehen Sie dazu folgendermassen vor (vgl.Aufgabenblatt): 
	 * -Fuehren Sie zunaechst den Gauss-Algorithmus mit Spaltenpivotisierung 
	 *  solange durch, bis in einem Schritt alle moeglichen Pivotelemente
	 *  numerisch gleich 0 sind (d.h. <1E-10) 
	 * -Betrachten Sie die bis jetzt entstandene obere Dreiecksmatrix T und
	 *  loesen Sie Tx = -v durch Rueckwaertssubstitution 
	 * -Geben Sie den Vektor (x,1,0,...,0) zurueck
	 * 
	 * Sollte A doch intvertierbar sein, kann immer ein Pivot-Element gefunden werden(>=1E-10).
	 * In diesem Fall soll der 0-Vektor zurueckgegeben werden. 
	 * PARAMETER: 
	 * A: Eine singulaere Matrix der Groesse n x n 
	 */
	public static double[] solveSing(double[][] A) {
		double[][] tmpA = A;
                int tmpARow = tmpA.length;
                int tmpAColumn = tmpA[0].length;
                // Put matrix into upper triangle form
                // TSize flag
                int TSize = -1;
                upperTriangle:
                for (int k = 0; k < tmpAColumn; k++) {
                    // Find pivot row
                    double biggestElement = tmpA[k][k];
                    int pivotRow = k;
                    for (int j = 1; j < tmpARow; j++) {
                        if (Math.abs(tmpA[j][k]) > Math.abs(biggestElement)) {
                            biggestElement = tmpA[j][k];
                            pivotRow = j;
                        }
                    }
                    // Switch rows
                    double[] tmp = tmpA[k];
                    tmpA[k] = tmpA[pivotRow];
                    tmpA[pivotRow] = tmp;
                    // Subtraction
                    for (int i = k + 1; i < tmpARow; i++) {
                        if (tmpA[k][k] != 0) {
                            tmpA[i] = ArraySubtraction(tmpA[i], ArrayMultiplication(tmpA[k], tmpA[i][k] / tmpA[k][k]));
                        } else {
                            TSize = k;
                            break upperTriangle;
                        }                      
                    }
                }
                double[] result = new double[A[0].length];
                // Return (x, 1, 0 ... 0)^T if A not invertible, else return null vector
                if (TSize != -1) {     
                    double[][] T = new double[TSize][TSize];
                    // Write T
                    for (int y = 0; y < TSize; y++) {
                        for (int x = 0; x < TSize; x++) {
                            T[y][x] = tmpA[y][x];
                        }
                    }
                    double[] v = new double [TSize];
                    // Write v
                    for (int i = 0; i < TSize; i++) {
                            v[i] = tmpA[i][TSize];
                    }
                    // Solve Tx = -v;
                    double[] negV = ArrayMultiplication(v, -1);
                    double[] x = backSubst(T, negV);
                    // Write x into result
                    for (int i = 0; i < x.length; i++) {
                        result[i] = x[i];
                    }
                    result[x.length] = 1;
                }
		return result;
	}

	/**
	 * Diese Methode berechnet das Matrix-Vektor-Produkt A*x mit A einer nxm
	 * Matrix und x einem Vektor der Laenge m. Sie eignet sich zum Testen der
	 * Gauss-Loesung
	 */
	public static double[] matrixVectorMult(double[][] A, double[] x) {
		int n = A.length;
		int m = x.length;

		double[] y = new double[n];

		for (int i = 0; i < n; i++) {
			y[i] = 0;
			for (int j = 0; j < m; j++) {
				y[i] += A[i][j] * x[j];
			}
		}

		return y;
	}
}
