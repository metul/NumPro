package ode;

import java.util.Arrays;

/**
 * Das Einschrittverfahren "Expliziter Euler"
 * 
 * @author braeckle
 * 
 */
public class ExpliziterEuler implements Einschrittverfahren {

	public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
                double[] y_next = new double[y_k.length];
                for (int i = 0; i < y_k.length; i++) {
			y_next[i] = y_k[i] + delta_t * ode.auswerten(t, y_k)[i];
		}               
                return y_next;
		// return Arrays.copyOf(y_k, y_k.length);
	}

}
