package numpro2;



public class Gauss {
    
        /*Diese Methode führt eine Summe durch 
        *PARAMETER:
        * n: obere Grenze
        * i: laufvariable
        * R: obere Dreiecksmatrix
        * x: Lösungsvektor für Rücksubstitution
        */
        private static double sum(int n, int i, double[][]R, double[]x){
            double sum=0;
            for(int k=i+1;k<=n; k++){
                i++;
                sum+=R[i][k]*x[k]; //Teil der Formel für die Rückwärtssubstitution
            }
            return sum;
        }
        /*Diese Methode sucht nach dem Index des Betragsmäßig größten Element
        * innerhalb einer Spalte einer Matrix
        * PARAMETER: 
        * k: Startindex
        * A: nxn Matrix
        */
        private static int findmaxIndex(int k, double[][]A){
            int n=A.length;
            int maxindex=k;
            double max=Math.abs(A[k][k]);
            for(int i=k; i<n; i++){
                if(Math.abs(A[i][k])>max){
                    max=Math.abs(A[i][k]);
                    maxindex=i;
                }
            }
            return maxindex;
        }
        private static double[][] switchRow(int row1, int row2, double[][]src, double[][]dest){
            for(int i=0; i<src.length; i++){
                dest[row2][i]=src[row1][i];
                dest[row1][i]=src[row2][i];
            }
            return dest;
        }

	/**
	 * Diese Methode soll die Loesung x des LGS R*x=b durch
	 * Rueckwaertssubstitution ermitteln.
	 * PARAMETER: 
	 * R: Eine obere Dreiecksmatrix der Groesse n x n 
	 * b: Ein Vektor der Laenge n
	 */
	public static double[] backSubst(double[][] R, double[] b) {
		
                int n= b.length;
                double[]x= new double[n];
                for(int i=n-1; i>=0; n--){
                    x[i]=(1/R[i][i])*(b[i]-sum(n-1,i,R,x));
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
                int n=b.length;
                int j=0; //indexvariable
                double[][] solved = new double[n][n]; //A als obere Dreiecksmatrix
                
                //copy Array
                for(int i=0; i<n; i++){
                    for(int l=0; l<n; l++){
                        solved[i][l]=A[i][l];
                    }
                }
                //Spaltenpivotiesierung beginnt
                for(int k=0; k<n; k++){
                    //Betragsgrößtes Element ermitteln
                    j=findmaxIndex(k,A);
                    if(solved[k][j]==0){
                      double[] zero=new double[n];
                      for(int i=0; i<n; i++)
                          zero[i]=0;
                      return  zero;
                    }
                    if(j!=k)
                        solved=switchRow(j,k,A,solved);
                //Zeileneliminierung
                    for(int i=k+1; i<n; i++){
                        for(int l=0; l<n; l++){
                            solved[i][l]-=(solved[i][k]/solved[k][k])*solved[k][l];
                        }
                    }
                }
                //solved hat jetzt die Form einer oberen Dreiecksmatrix
		return backSubst(solved,b);
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
		
		return new double[2];
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
