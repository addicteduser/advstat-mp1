package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TheNewNormalDataGenerator {
	
	public TheNewNormalDataGenerator() {
		
	}
	
	public Double[][] generateData(int lowerBound, int upperBound, int population) {
		Double[][] theValues = new Double[population][2];
		Random ran = new Random();
		
		ArrayList<Double> temp = new ArrayList<Double>();
		
		for(int k = 0; k < population; k++) {
			temp.add((ran.nextInt(upperBound - lowerBound) + lowerBound) * 1.0);
		}
		
		Collections.sort(temp); System.out.println(temp.toString());
		
		// Fill the array with random x values
		for(int a = 0; a < population; a++) {
			theValues[a][0] = temp.get(a);
		}
		
		// Compute for the mean
		double mean = computeMean(theValues, population);
		
		// Compute for the variance
		double variance = computeVariance(theValues, population);
		
		// Get the 'y' value of the 'x' 
		for(int d = 0; d < population; d++) {
			double theX = theValues[d][0];
			double theY;
			
			theY = (1 / (Math.sqrt(variance * 2 * Math.PI))) * (Math.pow(Math.E, ((-1) * (Math.pow((theX - mean), 2) / (2 * variance)))));
			
			theValues[d][1] = theY;
		}
 		
		return theValues;
	}
	
	public double computeMean(Double[][] theList, int population) {
		double theSum = 0;
		
		for(int b = 0; b < population; b++) {
			theSum += theList[b][0];
		}
		
		return (theSum / population);
	}
	
	public double computeVariance(Double[][] theList, int population) {
		double mean = computeMean(theList, population);
		double theVariance = 0;
		
		for(int c = 0; c < population; c++) {
			theVariance += Math.pow(theList[c][0] - mean, 2);
		}
		
		theVariance = (theVariance * 1.0) / population;
		
		return theVariance;
	}

}
