import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Program {
    public static int percept(double[] W, double[] X) {
        double total = 0;
        for (int i = 0; i < X.length; i++) {
            total += W[i] * X[i];
            //System.out.println("total: "+total);
        }
        if (total > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static double[] PerceptLearn(double[][] X, double[] T, double[] W, double n) {
        double[] yOutput = new double[X.length];
        double error = 0;
        double sum=0;
        int count = 0;
        while (count < 3) {
            for (int k = 0; k < T.length; k++) { //t.length = 100
                int y = percept(W, X[k]);
                for (int i = 0; i < 3; i++) {
                    W[i] = W[i] + (n * (T[k] - y) * X[k][i]);
                }
                yOutput[k] = y;
            }
            
            error = errorFunction(T, yOutput);
            System.out.println("Error: " + error);
            count++;
        }
        return W;   
    }

    public static double errorFunction(double[] T, double[] yOut) {
        double sum =0;
        for (int i = 0; i < T.length; i++) {
            sum = (int) Math.abs(T[i] - yOut[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scan; // NEW FILE (PATH)
        File file = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\perceptron\\test\\H1dataset1.txt");
        try {
            scan = new Scanner(file);
            double x;
            double[][] X = new double[100][3];
            double[] T = new double[100];
            double[] W = new double[100];
            double[] B = new double[100];
            double n = 0.25;
            //int max_iter = 100;

            while (scan.hasNext()) {
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 3; j++) {
                        x = scan.nextDouble();
                        X[i][j] = x;
                        //System.out.println(X[i][j]);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    double randomNum = ThreadLocalRandom.current().nextDouble(-1, 2);
                    W[i] = randomNum;
                    System.out.println("weight: " + W[i]);
                }

                for (int i = 0; i < 100; i++) {
                    T[i] = X[i][2];
                    //System.out.println("target: " + T[i]);
                }

                for (int i = 0; i < 100; i++) {
                    X[i][2] = -1;
                    //System.out.println(X[i][2]);
                }
                //printing the input array after bias 
               // for (int i = 0; i < 100; i++) {
                   // for (int j = 0; j < 3; j++) {
                        //System.out.println(X[i][j]);
                   // }
                //}

                double[] output = PerceptLearn(X, T, W, n);
                for (int i = 0; i < output.length; i++) {
                    //System.out.println("output: "+output[i]);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
