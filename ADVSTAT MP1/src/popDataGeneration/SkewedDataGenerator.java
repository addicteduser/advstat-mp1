package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SkewedDataGenerator extends PopulationDataGenerator {
	int ub;
	int lb;
	
	@Override
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		// TODO Auto-generated method stub
		ub = upperBound;
		lb = lowerBound;
		
		xValues = new ArrayList<Integer>();
		ran = new Random();

		ArrayList<Integer> temp = new ArrayList<Integer>();

		for (int k = 0; k < population; k++) {
			temp.add((ran.nextInt(upperBound - lowerBound) + lowerBound));
		}

		Collections.sort(temp);
		System.out.println(temp.toString());

		// Fill the array with random x values
		for (int a = 0; a < population; a++) {
			xValues.add(temp.get(a));
		}

		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		// TODO Auto-generated method stub
		ArrayList<Double> yData = new ArrayList<Double>();
		
		double theE = 1.0 * ran.nextInt(ub - lb) + lb;
		double theW = 1.0 * ran.nextInt(ub - lb) + lb;
		
		double theA = ran.nextDouble();
		int theSign = ran.nextInt(2);
		
		if(theSign == 1)
			theA = theA * -1.0;
		
		// Get the 'y' value of the 'x'
		for (int d = 0; d < population; d++) {
			int theX = xData.get(d);
			double theY;
			
			double paramA = ((theX - theE) / theW) * 1.0;
			double paramB = (theA * ((theX - theE) / theW)) * 1.0;

			theY = roundOff(1.0 * ((2 / theW) * computeA(paramA) * computeB(paramB)));

			yData.add(theY);
		}

		return yData;
	}
	
	public double computeA(double val) {
		double a;
		
		a = 1.0 * (1 / (Math.sqrt(2 * Math.PI))) * (Math.pow(Math.E, ((-1) * (Math.pow((val), 2) / (2)))));
		
		return a;
		
	}
	
	public double computeB(double val) {
		double b;
		
		b = 1.0 * ((1.0 / 2.0) * (1.0 + (erf((val / Math.sqrt(2.0))))));
		
		return b;
	}
	
	public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // use Horner's method
        double ans = 1 - t * Math.exp( -z*z   -   1.26551223 +
                                            t * ( 1.00002368 +
                                            t * ( 0.37409196 + 
                                            t * ( 0.09678418 + 
                                            t * (-0.18628806 + 
                                            t * ( 0.27886807 + 
                                            t * (-1.13520398 + 
                                            t * ( 1.48851587 + 
                                            t * (-0.82215223 + 
                                            t * ( 0.17087277))))))))));
        if (z >= 0) return  ans;
        else        return -ans;
    }

}
