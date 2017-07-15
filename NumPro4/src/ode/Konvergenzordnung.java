package ode;
import java.util.Arrays;

/**
 * Numerische Berechnung von Konvergenzordnungen gegebener ODE-Solver.
 * 
 * @author dietrich
 * 
 */
public class Konvergenzordnung {
	
	/**
	 * Festgelegte Differentialgleichung, an der der Konvergenztest durchgef�hrt wird.
	 */
	private final ODE testODE;
	/**
	 * Startwert bei der L�sung der Differentialgleichung
	 */
	private final double[] y0;
	/**
	 * Wert der L�sung der Differentialgleichung nach T Sekunden.
	 */
	private final double[] ystar;
	/**
	 * Zeitpunkt bis zu dem integriert werden soll.
	 */
	private final double T;

	/**
	 * 
	 * @param testODE Test-Differentialgleichung
	 * @param y0 Test-Startwert
	 * @param ystar Exakter Wert nach Zeit T
	 * @param T Zeitpunkt, bis zu dem integriert werden soll
	 */
	public Konvergenzordnung(ODE testODE, double[] y0, double[] ystar, double T)
	{
		this.testODE = testODE;
		this.y0 = y0;
		this.ystar = ystar;
		this.T = T;
	}
	
	/**
	 * Integriert die Testgleichung bis zum Zeitpunkt T mit dem gegebenen Verfahren.
	 * 
	 * @param verfahren
	 * @param schrittweite
	 * @return y_k(T), berechnet mit dem Einschrittverfahren und der Schrittweite.
	 */
	private double[] integrate(Einschrittverfahren verfahren, double schrittweite)
	{
		double[] y_end = Arrays.copyOf(y0, y0.length);
		double t = 0;
		
		while(t < T)
		{
			y_end = verfahren.nextStep(y_end, t, schrittweite, testODE);
			t += schrittweite;
		}
		
		return y_end;
	}
	
	/**
	 * Berechnet den Fehler in der 2-Norm zwischen der numerischen L�sung yh und der exakten L�sung ystar.
	 * 
	 * @return e_h = ||yh - yexact||_2
	 */
	private double error(double[] yh)
	{
		double e = 0.0;
		
		for(int i=0; i<yh.length; i++)
		{
			e += (ystar[i]-yh[i])*(ystar[i]-yh[i]);
		}
		
		return Math.sqrt(e);
	}
	
	/**
	 * Diese Methode sch�tzt die Konvergenzordnung des gegebenen Verfahrens ab.
	 * 
	 * @param verfahren das zu testende Verfahren
	 * @param h Die Schrittweite h, f�r die die Absch�tzung der Ordnung durchgef�hrt werden soll.
	 * @return Ordnung p
	 */
	public double order(Einschrittverfahren verfahren, double h)
	{
		// double p = 0.0;
                double p;
                double h_1 = h;
                double h_2 = h / 2;
                double e_h_1 = error(integrate(verfahren, h_1));
                double e_h_2 = error(integrate(verfahren, h_2));
		p = Math.log(e_h_1 / e_h_2) / Math.log(h_1 / h_2);
                // p = (int) p; // Line used to round number down
		return p;
	}
}
