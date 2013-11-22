package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NormalDataGenerator extends PopulationDataGenerator {

	@Override
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		// TODO Auto-generated method stub
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
		
		// Compute for the mean
		double mean = computeMean(xData, population);

		// Compute for the variance
		double variance = computeVariance(xData, population);

		// Get the 'y' value of the 'x'
		for (int d = 0; d < population; d++) {
			int theX = xData.get(d);
			double theY;

			theY = roundOff((1 / (Math.sqrt(variance * 2 * Math.PI)))
					* (Math.pow(
							Math.E,
							((-1) * (Math.pow((theX - mean), 2) / (2 * variance))))));

			yData.add(theY);
		}
		
		System.out.println("yData: " + yData.toString());

		return yData;
	}

	public double computeMean(ArrayList<Integer> xList, int population) {
		double theSum = 0;

		for (int b = 0; b < population; b++) {
			theSum += xList.get(b);
		}

		return (theSum / population);
	}

	public double computeVariance(ArrayList<Integer> xList, int population) {
		double mean = computeMean(xList, population);
		double theVariance = 0;

		for (int c = 0; c < population; c++) {
			theVariance += Math.pow(xList.get(c) - mean, 2);
		}

		theVariance = (theVariance * 1.0) / population;

		return theVariance;
	}

}
