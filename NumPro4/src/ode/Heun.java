package ode;

import java.util.Arrays;

/**
 * Das Einschrittverfahren von Heun
 * 
 * @author braeckle
 * 
 */
public class Heun implements Einschrittverfahren {

	@Override
	/**
	 * {@inheritDoc} 
	 * Nutzen Sie dabei geschickt den Expliziten Euler.
	 */
	public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
                ExpliziterEuler euler = new ExpliziterEuler();
                double[] y_next = new double[y_k.length];
                double[] y_eulerPoint = euler.nextStep(y_k, t, delta_t, ode);
                for (int i = 0; i < y_k.length; i++) {
			y_next[i] = y_k[i] + (delta_t / 2) * (ode.auswerten(t, y_k)[i] + ode.auswerten(t + delta_t, y_eulerPoint)[i]);
		}              
                return y_next;
		//return Arrays.copyOf(y_k, y_k.length);
	}

}
