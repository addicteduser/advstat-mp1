package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BimodalDataGenerator extends PopulationDataGenerator {
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
		ArrayList<Integer> xData1 = new ArrayList<Integer>();
		ArrayList<Integer> xData2 = new ArrayList<Integer>();

		ArrayList<Double> yData = new ArrayList<Double>();

		// COMPUTE FOR THE GENERAL VARIANCE
		double variance = computeVariance(xData, population);

		int middle = xData.size() / 2;
		System.out.println(middle);

		// ------------------------------------------------------------------------

		// FILL xData1 WITH THE FIRST HALF OF xData
		for (int i = 0; i < middle; i++) {
			xData1.add(xData.get(i));
		}
		// COMPUTE FOR THE MEAN OF xData1
		double mean1 = computeMean(xData1, xData1.size());

		// GET THE 'y' value of the 'x' in xData1
		for (int j = 0; j < xData1.size(); j++) {
			int theX = xData1.get(j);
			double theY;

			theY = computeTheY(theX, mean1, variance);

			yData.add(theY);
		}

		// ------------------------------------------------------------------------

		// FILL xData2 WITH THE SECOND HALF OF xData
		for (int i = middle; i < xData.size(); i++) {
			xData2.add(xData.get(i));
		}
		// COMPUTE FOR THE MEAN OF xData2
		double mean2 = computeMean(xData2, xData2.size());

		// GET THE 'y' value of the 'x' in xData1
		for (int j = 0; j < xData2.size(); j++) {
			int theX = xData2.get(j);
			double theY;

			theY = roundOff(computeTheY(theX, mean2, variance));

			yData.add(theY);
		}

		System.out.println("xData:" + xData.toString());
		System.out.println("xData1:" + xData1.toString());
		System.out.println("xData2:" + xData2.toString());
		
		System.out.println("\nyData: " + yData.toString());

		return yData;
	}

	public double computeTheY(int theX, double mean, double variance) {
		double theY;

		theY = (1 / (Math.sqrt(variance * 2 * Math.PI)))
				* (Math.pow(Math.E,
						((-1) * (Math.pow((theX - mean), 2) / (2 * variance)))));

		return theY;
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
