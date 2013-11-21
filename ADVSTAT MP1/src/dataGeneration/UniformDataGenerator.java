package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UniformDataGenerator extends DataGenerator {

	ArrayList<Integer> genValues = new ArrayList<Integer>();
	
	@Override
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();
		
		for(int a = lowerBound; a <= upperBound; a++) {
			genValues.add(a);
		}
		
		Collections.shuffle(genValues);
		
		int i = 0; // for population
		int j = 0;
		int k = 0;
		
		do{
			j = ran.nextInt(population) + 1;
		}while(population % j != 0);
		System.out.println(j);
		while (i < population) {
			for(int b = 0; b < j; b++) {
				xValues.add(genValues.get(k));
				System.out.println(i+": "+genValues.get(k));
				i++;
				//System.out.println(i);
			}
			k++;
		}
		
		sortData(xValues);

		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		ArrayList<Double> yData = new ArrayList<Double>();
		
		for(int i = 0; i < xData.size(); i++) {
			double value = roundOff(1.0 / population);
			yData.add(value);
		}
		
		return yData;
	}

}
