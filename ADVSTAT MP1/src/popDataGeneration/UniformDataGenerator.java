package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UniformDataGenerator extends PopulationDataGenerator {

	int frequency;

	@Override
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();

		int x;
		int theX;
		
		do {
			x = ran.nextInt(population) + 2;
			frequency = population / x;
		} while(population % x != 0);

		for(int i = 0; i < population / frequency; i++) {
			theX = ran.nextInt(upperBound - lowerBound) + lowerBound;
			
			while(xValues.contains(theX)) {
				theX = ran.nextInt(upperBound - lowerBound) + lowerBound;
			}
			
			xValues.add(theX);
		}
		
		Collections.sort(xValues);
		
		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		ArrayList<Double> yData = new ArrayList<Double>();

		for (int i = 0; i < population / frequency; i++) {
			double value = roundOff(1.0 * frequency / population);
			yData.add(value);
		}

		return yData;
	}

}
