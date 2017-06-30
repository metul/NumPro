/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numpro3;

/**
 *
 * @author Mert
 */
public class NumPro3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         double[]x = {0,1,2};
            double[]y = {3,0,1};
            NewtonPolynom newton = new NewtonPolynom(x,y);
            for(int i=0; i<newton.x.length; i++){
                System.out.println(newton.a[i]);
            }
            for(int i=0; i<newton.x.length; i++)
                System.out.print("f["+i+"]= "+newton.f[i]+"   ");
            System.out.print('\n');
            newton.addSamplingPoint(1.5, 0);
            for(int i=0; i<newton.x.length; i++){
                System.out.println(newton.a[i]);
            }
            for(int i=0; i<newton.x.length; i++)
                System.out.print("f["+i+"]= "+newton.f[i]+"   ");
            System.out.print('\n');
    }
    
}
