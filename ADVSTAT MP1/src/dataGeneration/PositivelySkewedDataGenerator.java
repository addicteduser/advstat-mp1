package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PositivelySkewedDataGenerator extends DataGenerator {

	ArrayList<Integer> genValues = new ArrayList<Integer>();
	ArrayList<Integer> positiveValues = new ArrayList<Integer>();
	int middlefreq;
	
	@Override
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		System.out.println("Lowerbound: " + lowerBound);
		System.out.println("Upperbound: " + upperBound);
		System.out.println("Population: " + population + "\n");
		
		xValues = new ArrayList<Integer>();
		ran = new Random();
		int theDataCount = 0;
		
		// For generating the list containing all possible values from population
		for(int b = lowerBound; b <= upperBound; b++) {
			genValues.add(b);
		}
		
		// This is for the trimming of population for the values of the middle
		Double midTrim = genValues.size() * .70;
		int theMidTrim = midTrim.intValue();
		
		// For generating the list containing all possible values for the middle
		for(int a = theMidTrim; a <= upperBound; a++) {
			positiveValues.add(a);
		}
		
		//int k = midValues.size();
		System.out.println(positiveValues.toString() + "\n");
		
		// For selecting the middle and its frequency
		int upper = positiveValues.get(ran.nextInt(positiveValues.size()));
		Double upperFreq = population * .40;
		int theUpperFreq = upperFreq.intValue();
		
		middlefreq = ran.nextInt(population - theUpperFreq) + theUpperFreq;
		System.out.println("Middle point: " + upper + " / Frequency: " + middlefreq);
		
		
		// Add the middle to the list of value + frequency
		for(int c = 0; c < middlefreq; c++) {
			xValues.add(upper);
			theDataCount++;
		}
		
		// Adding the other data
		int step = ran.nextInt(theMidTrim) + 1; // to get the next set of points
		int low = upper - step; // the lower point
		int frequency = ran.nextInt((middlefreq) - 1) + 1; // the frequency of both points
		System.out.println("Step: " + step + " / Points: " + low + " : frequency of each - " + frequency);
		
		while(theDataCount < population) {
			int x = 0;
			
			while(theDataCount < population && x < frequency) {
				xValues.add(low);
				theDataCount++;
				x++;
				
				System.out.println("<+> Step: " + step + " / Point: " + low + " : frequency of point - " + frequency);
			}
		
			step = ran.nextInt(step) + 1;
			
			if(low - step < 0)
				low = 1;
			else low = low - step;
			
			frequency = ran.nextInt(frequency) + 1;
		}
		
		Collections.sort(xValues);
		
		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		// TODO Auto-generated method stub
		return null;
	}
}
