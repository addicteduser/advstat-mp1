package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NormalDataGenerator extends DataGenerator {

	ArrayList<Integer> genValues = new ArrayList<Integer>();
	ArrayList<Integer> midValues = new ArrayList<Integer>();
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
		Double midTrim = genValues.size() * .40;
		int theMidTrim = midTrim.intValue();
		
		// For generating the list containing all possible values for the middle
		for(int a = theMidTrim; a <= upperBound - theMidTrim; a++) {
			midValues.add(a);
		}
		
		//int k = midValues.size();
		System.out.println(midValues.toString() + "\n");
		
		// For selecting the middle and its frequency
		int middle = midValues.get(ran.nextInt(midValues.size()));
			if(population % 2 == 0) {
				middlefreq = ran.nextInt(population / 2) * 2;
			}
			else middlefreq = ran.nextInt(population) + theMidTrim;
		System.out.println("Middle point: " + middle + " / Frequency: " + middlefreq);
		
		
		// Add the middle to the list of value + frequency
		for(int c = 0; c < middlefreq; c++) {
			xValues.add(middle);
			theDataCount++;
		}
		
		// Adding the other data
		int step = ran.nextInt(theMidTrim) + 1; // to get the next set of points
		int low = middle - step; // the lower point
		int up = middle + step; // the upper point
		int frequency = ran.nextInt((middlefreq) - 1) + 1; // the frequency of both points
		System.out.println("Step: " + step + " / Points: " + low + " & " + up + " : frequency of each - " + frequency);
		
		while(theDataCount < population) {
			int x = 0;
			
			while(theDataCount < population && x < frequency) {
				xValues.add(low);
				xValues.add(up);
				theDataCount += 2;
				x++;
				
				System.out.println("<+> Step: " + step + " / Points: " + low + " & " + up + " : frequency of each - " + frequency);
			}
		
			
			
			step = ran.nextInt(step) + 1;
			
			if(low - step < 0)
				low = 1;
			else low = low - step;
			
			if(up + step > upperBound)
				up = upperBound;
			else up = up + step;
			
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
