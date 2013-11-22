package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandomDataGenerator {
	private Random ran;
	private ArrayList<Integer> xValues;
	private Map<Integer, Integer> xMap;
	
	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();

		int i = 0;
		int temp;

		while (i < population) {
			temp = ran.nextInt(upperBound - lowerBound) + lowerBound;

			if (lowerBound <= temp) {
				xValues.add(temp);
				i++;
			}
		}

		sortData(xValues);
		xMap = new TreeMap<Integer, Integer>();
		for(int j = 0; j < xValues.size(); j++) {
			if(!xMap.containsKey(xValues.get(j))) {
				xMap.put(xValues.get(j), 1);
			} else {
				xMap.put(xValues.get(j), xMap.get(xValues.get(j))+1);
			}
		}
		
		xValues = (ArrayList<Integer>) xMap.keySet();

		return xValues;
	}

	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		ArrayList<Double> yData = new ArrayList<Double>();		

		
		
		for (int i = 0; i < xMap.size(); i++) {
			double value = roundOff(1.0 * xMap.get(i) / population);
			yData.add(value);
		}

		return yData;
	}
	
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}
	
	public ArrayList<Integer> sortData(ArrayList<Integer> currentList) {
		Collections.sort(currentList);
		return currentList;
	}

}
