package numpro3;

import java.util.Arrays;

public class Test_Interpolation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testNewton();
		testSplines();
                testSplinesCustom();
	}

	private static void testNewton() {

		double[] x = { -1, 1, 3 };
		double[] y = { -3, 1, -3 };
		NewtonPolynom p = new NewtonPolynom(x, y);

		System.out.println(p.evaluate(0) + " sollte sein: 0.0");
		System.out.println("-------------------------------");
	}

	public static void testSplines() {
		CubicSpline spl = new CubicSpline();
		double[] y = { 2, 0, 2, 3 };
		spl.init(-1, 2, 3, y);
		spl.setBoundaryConditions(9, 0);
		System.out.println(Arrays.toString(spl.getDerivatives())
				+ " sollte sein: [9.0, -3.0, 3.0, 0.0].");
	}
        
        public static void testSplinesCustom() {
		CubicSpline spl = new CubicSpline();
		double[] y = { 3, 5, 1 };
		spl.init(-1.5, 0.5, 2, y);
		spl.setBoundaryConditions(6, 3);
		System.out.println(Arrays.toString(spl.getDerivatives())
				+ " sollte sein: [6.0, -2.25, 3.0].");
	}
}
