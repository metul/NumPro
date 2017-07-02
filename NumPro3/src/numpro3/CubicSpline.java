package numpro3;

import java.util.Arrays;

/**
 * Die Klasse CubicSpline bietet eine Implementierung der kubischen Splines. Sie
 * dient uns zur effizienten Interpolation von aequidistanten Stuetzpunkten.
 * 
 * @author braeckle
 * 
 */
public class CubicSpline implements InterpolationMethod {

	/** linke und rechte Intervallgrenze x[0] bzw. x[n] */
	double a, b;

	/** Anzahl an Intervallen */
	int n;

	/** Intervallbreite */
	double h;

	/** Stuetzwerte an den aequidistanten Stuetzstellen */
	double[] y;

	/** zu berechnende Ableitunge an den Stuetzstellen */
	double yprime[];

	/**
	 * {@inheritDoc} Zusaetzlich werden die Ableitungen der stueckweisen
	 * Polynome an den Stuetzstellen berechnet. Als Randbedingungen setzten wir
	 * die Ableitungen an den Stellen x[0] und x[n] = 0.
	 */
	@Override
	public void init(double a, double b, int n, double[] y) {
		this.a = a;
		this.b = b;
		this.n = n;
		h = ((double) b - a) / (n);

		this.y = Arrays.copyOf(y, n + 1);

		/* Randbedingungen setzten */
		yprime = new double[n + 1];
		yprime[0] = 0;
		yprime[n] = 0;

		/* Ableitungen berechnen. Nur noetig, wenn n > 1 */
		if (n > 1) {
			computeDerivatives();
		}
	}

	/**
	 * getDerivatives gibt die Ableitungen yprime zurueck
	 */
	public double[] getDerivatives() {
		return yprime;
	}

	/**
	 * Setzt die Ableitungen an den Raendern x[0] und x[n] neu auf yprime0 bzw.
	 * yprimen. Anschliessend werden alle Ableitungen aktualisiert.
	 */
	public void setBoundaryConditions(double yprime0, double yprimen) {
		yprime[0] = yprime0;
		yprime[n] = yprimen;
		if (n > 1) {
			computeDerivatives();
		}
	}

	/**
	 * Berechnet die Ableitungen der stueckweisen kubischen Polynome an den
	 * einzelnen Stuetzstellen. Dazu wird ein lineares System Ax=c mit einer
	 * Tridiagonalen Matrix A und der rechten Seite c aufgebaut und geloest.
	 * Anschliessend sind die berechneten Ableitungen y1' bis yn-1' in der
	 * Membervariable yprime gespeichert.
	 * 
	 * Zum Zeitpunkt des Aufrufs stehen die Randbedingungen in yprime[0] und yprime[n].
	 * Speziell bei den "kleinen" Faellen mit Intervallzahlen n = 2
	 * oder 3 muss auf die Struktur des Gleichungssystems geachtet werden. Der
	 * Fall n = 1 wird hier nicht beachtet, da dann keine weiteren Ableitungen
	 * berechnet werden muessen.
	 */
	public void computeDerivatives() {
		/* TODO: diese Methode ist zu implementieren */
                // Build A and c
                int size = yprime.length - 2; 
                double[][] A = new double[size][size];
                double[] c = new double[size];
                // A
                for (int i = 0; i < size - 1; i++) {
                    // Main diagonal
                    A[i][i] = 4.0;
                    // Below and above
                    A[i + 1][i] = 1.0;
                    A[i][i + 1] = 1.0;
                }
                A[size - 1][size - 1] = 4.0;
                // c
                c[0] = y[2] - y[0] - (h / 3.0) * yprime[0];
                c[size - 1] = y[yprime.length - 1] - y[yprime.length - 3] - (h / 3.0) * yprime[yprime.length - 1];
                for (int i = 1; i < size - 1; i++){
                    c[i] = y[i + 2] - y[i]; 
                }
                for (int i = 0; i < size; i++) {
                    c[i] *= (3.0 / h);
                }
                // Thomas Algorithm (modified for given tridiagonal matrix)
                double[] aboveDiagonal = new double[size - 1];
                double[] cHelp = new double[size];
                double[] result = new double[size];
                aboveDiagonal[0] = 1.0 / 4.0;
                for (int i = 1; i < size - 1; i++) {
                    aboveDiagonal[i] = 1.0 / (4.0 - 1.0 * aboveDiagonal[i - 1]);
                }
                cHelp[0] = c[0] / 4.0;
                for (int i = 1; i < size; i++) {
                    cHelp[i] = (c[i] - 1.0 * cHelp[i - 1]) / (4.0 - 1.0 * aboveDiagonal[i - 1]);
                }
                // BackSub
                result[size - 1] = cHelp[size - 1];
                for (int i = size - 2; i >= 0; i--) {
                    result[i] = cHelp[i] - aboveDiagonal[i] * result[i + 1];
                }
            // Write result into yprime
            System.arraycopy(result, 0, yprime, 1, result.length);
	}

	/**
	 * {@inheritDoc} Liegt z ausserhalb der Stuetzgrenzen, werden die
	 * aeussersten Werte y[0] bzw. y[n] zurueckgegeben. Liegt z zwischen den
	 * Stuetzstellen x_i und x_i+1, wird z in das Intervall [0,1] transformiert
	 * und das entsprechende kubische Hermite-Polynom ausgewertet.
	 */
	@Override
	public double evaluate(double z) {
		/* TODO: diese Methode ist zu implementieren */          
                if (z < a) {
                    return y[0];
                }
                if (z > b) {
                    return y[y.length - 1];
                }		
                int interval = (int) ((z - a) / h);
                double prevIDX = a + interval * h;
                // Transform interval
                double t = (z - prevIDX) / h;
                // Calculate H_i(t)
                double H_0 = 1 - 3 * Math.pow(t, 2) + 2 * Math.pow(t, 3);
                double H_1 = 3 * Math.pow(t, 2) - 2 * Math.pow(t, 3);
                double H_2 = t - 2 * Math.pow(t, 2) + Math.pow(t, 3);
                double H_3 = Math.pow(t, 3) - Math.pow(t, 2);
                // Evaluate polynom q 
                double q = y[interval] * H_0 + y[interval + 1] * H_1 +
                        h * yprime[interval] * H_2 + h * y[interval + 1] * H_3;
		return q;
	}
}
