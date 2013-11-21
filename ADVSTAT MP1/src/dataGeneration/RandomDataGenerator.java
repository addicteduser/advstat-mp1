package dataGeneration;

import java.util.ArrayList;
import java.util.Random;

public class RandomDataGenerator extends DataGenerator {

	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();
		
		int i = 0;
		int temp;
		
		while (i < population) {
			temp = ran.nextInt(upperBound+1);
			
			if(lowerBound <= temp) {
				xValues.add(temp);
				i++;
			}
		}
		
		sortData(xValues);

		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		// TODO Auto-generated method stub
		return null;
	}

}
