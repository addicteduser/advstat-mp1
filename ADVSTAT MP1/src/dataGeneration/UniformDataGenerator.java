package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UniformDataGenerator extends DataGenerator {

	ArrayList<Integer> genValues = new ArrayList<Integer>();
	
	@Override
	public ArrayList<Integer> generateData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();
		
		for(int a = lowerBound; a <= upperBound; a++) {
			genValues.add(a);
		}
		
		Collections.shuffle(genValues);
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		do{
			j = ran.nextInt(population) + 1;
		}while(population % j != 0);
		
		while (i < population) {
			for(int b = 0; b < j; b++) {
				xValues.add(genValues.get(k));
				i++;
				System.out.println(i);
			}
			k++;
		}
		
		sortData(xValues);

		return xValues;
	}

}
