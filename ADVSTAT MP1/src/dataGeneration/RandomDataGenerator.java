package dataGeneration;

import java.util.ArrayList;
import java.util.Random;

public class RandomDataGenerator extends DataGenerator {

	@Override
	public ArrayList<Integer> generateData(int lowerBound, int upperBound,
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

}
