package ode;

import java.util.Arrays;

/**
 * Der klassische Runge-Kutta der Ordnung 4
 * 
 * @author braeckle
 * 
 */
public class RungeKutta4 implements Einschrittverfahren {

	@Override
	/**
	 * {@inheritDoc}
	 * Bei der Umsetzung koennen die Methoden addVectors und multScalar benutzt werden.
	 */
	public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
                double[] y_next, k_1, k_2, k_3, k_4;
                k_1 = multScalar(ode.auswerten(t, y_k), delta_t);
                k_2 = multScalar(ode.auswerten(t + delta_t / 2.0, addVectors(y_k, multScalar(k_1, 0.5))), delta_t);
                k_3 = multScalar(ode.auswerten(t + delta_t / 2.0, addVectors(y_k, multScalar(k_2, 0.5))), delta_t);
                k_4 = multScalar(ode.auswerten(t + delta_t, addVectors(y_k, k_3)), delta_t);
                y_next = addVectors(y_k, multScalar(addVectors(addVectors(k_1, multScalar(k_2, 2.0)), addVectors(multScalar(k_3, 2.0), k_4)), 1.0 / 6.0));
                return y_next;
		//return Arrays.copyOf(y_k, y_k.length);
	}

	/**
	 * addiert die zwei Vektoren a und b
	 */
	private double[] addVectors(double[] a, double[] b) {
		double[] erg = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			erg[i] = a[i] + b[i];
		}
		return erg;
	}

	/**
	 * multipliziert den Skalar scalar auf den Vektor a
	 */
	private double[] multScalar(double[] a, double scalar) {
		double[] erg = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			erg[i] = scalar * a[i];
		}
		return erg;
	}

}
