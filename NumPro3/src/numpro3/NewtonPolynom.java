package numpro3;

import java.util.Arrays;

/**
 * Die Klasse Newton-Polynom beschreibt die Newton-Interpolation. Die Klasse
 * bietet Methoden zur Erstellung und Auswertung eines Newton-Polynoms, welches
 * uebergebene Stuetzpunkte interpoliert.
 * 
 * @author braeckle
 * 
 */
public class NewtonPolynom implements InterpolationMethod {

	/** Stuetzstellen xi */
	double[] x;

	/**
	 * Koeffizienten/Gewichte des Newton Polynoms p(x) = a0 + a1*(x-x0) +
	 * a2*(x-x0)*(x-x1)+...
	 */
	double[] a;

	/**
	 * die Diagonalen des Dreiecksschemas. Diese dividierten Differenzen werden
	 * fuer die Erweiterung der Stuetzstellen benoetigt.
	 */
	double[] f;

	/**
	 * leerer Konstruktore
	 */
	public NewtonPolynom() {
	};

	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            Stuetzstellen
	 * @param y
	 *            Stuetzwerte
	 */
	public NewtonPolynom(double[] x, double[] y) {
		this.init(x, y);
	}

	/**
	 * {@inheritDoc} Zusaetzlich werden die Koeffizienten fuer das
	 * Newton-Polynom berechnet.
	 */
	@Override
	public void init(double a, double b, int n, double[] y) {
		x = new double[n + 1];
		double h = (b - a) / n;

		for (int i = 0; i < n + 1; i++) {
			x[i] = a + i * h;
		}
		computeCoefficients(y);
	}

	/**
	 * Initialisierung der Newtoninterpolation mit beliebigen Stuetzstellen. Die
	 * Faelle "x und y sind unterschiedlich lang" oder "eines der beiden Arrays
	 * ist leer" werden nicht beachtet.
	 * 
	 * @param x
	 *            Stuetzstellen
	 * @param y
	 *            Stuetzwerte
	 */
	public void init(double[] x, double[] y) {
		this.x = Arrays.copyOf(x, x.length);
		computeCoefficients(y);
	}

	/**
	 * computeCoefficients belegt die Membervariablen a und f. Sie berechnet zu
	 * uebergebenen Stuetzwerten y, mit Hilfe des Dreiecksschemas der
	 * Newtoninterpolation, die Koeffizienten a_i des Newton-Polynoms. Die
	 * Berechnung des Dreiecksschemas soll dabei lokal in nur einem Array der
	 * Laenge n erfolgen (z.B. spaltenweise Berechnung). Am Ende steht die
	 * Diagonale des Dreiecksschemas in der Membervariable f, also f[0],f[1],
	 * ...,f[n] = [x0...x_n]f,[x1...x_n]f,...,[x_n]f. Diese koennen spaeter bei
	 * der Erweiterung der Stuetzstellen verwendet werden.
	 * 
	 * Es gilt immer: x und y sind gleich lang.
	 */
	private void computeCoefficients(double[] y) {
		/* TODO: diese Methode ist zu implementieren */
                if(y.length==0)
                    return;
                int n= y.length*y.length;
                
                double[] arr = new double[n];   //array für dreieckschema
                for(int k=0; k<y.length; k++){
                    for(int i=0; i<y.length; i++){
                        if(k==0)
                            arr[i]=y[i];
                        else
                            arr[k*y.length+i]=(arr[k*y.length+i-(y.length-1)]-arr[(k-1)*y.length+i])/(this.x[(k+i)%x.length]-this.x[i]);
                    }
                }
                this.a = new double[y.length];
                for(int i=0; i<y.length; i++){
                    this.a[i]=arr[i*y.length]; 
                }
                this.f = new double[y.length];
                for(int i=0; i<y.length; i++){
                    this.f[i]=arr[n-1-((i+1)*(y.length-1))];
                }
	}
       

	/**
	 * Gibt die Koeffizienten des Newton-Polynoms a zurueck
	 */
	public double[] getCoefficients() {
		return a;
	}

	/**
	 * Gibt die Dividierten Differenzen der Diagonalen des Dreiecksschemas f
	 * zurueck
	 */
	public double[] getDividedDifferences() {
		return f;
	}

	/**
	 * addSamplintPoint fuegt einen weiteren Stuetzpunkt (x_new, y_new) zu x
	 * hinzu. Daher werden die Membervariablen x, a und f vergoessert und
	 * aktualisiert . Das gesamte Dreiecksschema muss dazu nicht neu aufgebaut
	 * werden, da man den neuen Punkt unten anhaengen und das alte
	 * Dreiecksschema erweitern kann. Fuer diese Erweiterungen ist nur die
	 * Kenntnis der Stuetzstellen und der Diagonalen des Schemas, bzw. der
	 * Koeffizienten noetig. Ist x_new schon als Stuetzstelle vorhanden, werden
	 * die Stuetzstellen nicht erweitert.
	 * 
	 * @param x_new
	 *            neue Stuetzstelle
	 * @param y_new
	 *            neuer Stuetzwert
	 */
	public void addSamplingPoint(double x_new, double y_new) {
		/* TODO: diese Methode ist zu implementieren */
                boolean x_is_new=true;
                for(int i=0; i<this.x.length; i++){
                    if(this.x[i]==x_new)
                        x_is_new=false;
                }
               
                if(x_is_new){
                    //x[] aktualisieren
                    double[]tmp = new double[this.x.length+1];
                    tmp=Arrays.copyOf(this.x, this.x.length+1);
                    tmp[this.x.length]=x_new;
                    this.x= new double[tmp.length];
                    this.x= Arrays.copyOf(tmp, tmp.length);
                    
                    //f[] aktualisieren
                    //tmp= new double[this.f.length+1];                   
                    for(int i=tmp.length-1; i>=0; i--){
                        if(i==tmp.length-1)
                             tmp[tmp.length-1]=y_new;
                        else
                            tmp[i]=(tmp[i+1]-this.f[i])/(this.x[this.x.length-1]-this.x[i]); 
                    }
                    this.f= new double[tmp.length];
                    this.f= Arrays.copyOf(tmp, tmp.length);
                    
                    //a[] aktualisieren
                    tmp=Arrays.copyOf(this.a, a.length+1);
                    tmp[tmp.length-1]=this.f[0];        //neuer Koeffizent
                    this.a= new double[tmp.length];
                    this.a= Arrays.copyOf(tmp, tmp.length);
                } 
                
                
	}

	/**
	 * {@inheritDoc} Das Newton-Polynom soll effizient mit einer Vorgehensweise
	 * aehnlich dem Horner-Schema ausgewertet werden. Es wird davon ausgegangen,
	 * dass die Stuetzstellen nicht leer sind.
	 */
	@Override
	public double evaluate(double z) {
		/* TODO: diese Methode ist zu implementieren */
		return 0.0;
	}
}
