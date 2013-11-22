package popDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class PopulationDataGenerator {
	protected ArrayList<Integer> xValues;
	protected Random ran;

	/**
	 * This is the generic look of the Distribution Generator
	 * 
	 * @param lowerBound
	 * @param upperBound
	 * @param population
	 * @return a list of x-values from lowerbound to upperbound where the number
	 *         of x-values is population
	 */
	public abstract ArrayList<Integer> generateXData(int lowerBound,
			int upperBound, int population);
	
	public abstract ArrayList<Double> generateYData(ArrayList<Integer> xData, int population);

	/**
	 * Sorts the currentList from smallest value to biggest value
	 * using insertion sort
	 * 
	 * @param currentList
	 *            list to be sorted
	 * @return the currentList sorted from smallest to biggest
	 */
	public ArrayList<Integer> sortData(ArrayList<Integer> currentList) {
		Collections.sort(currentList);
		return currentList;
	}
	
	public Map<Integer, Double> generateXYData(ArrayList<Integer> xVal, ArrayList<Double> yVal, int popSize) {
		Map<Integer, Integer> xMap = new TreeMap<Integer, Integer>();
		Map<Integer, Double> yMap = new TreeMap<Integer, Double>();
		Map<Integer, Double> xyMap = new TreeMap<Integer, Double>();
				
		for(int i = 0; i < xVal.size(); i++) {
			if(!xMap.containsKey(xVal.get(i))) {
				xMap.put(xVal.get(i), 1);
				yMap.put(xVal.get(i), yVal.get(i));
			} else {
				xMap.put(xVal.get(i), xMap.get(xVal.get(i))+1);
			}
			System.out.println("HELLOOOO: "+xMap.get(i)+1);
		}
		
		for(Map.Entry<Integer, Integer> i : xMap.entrySet()) {
			xyMap.put(i.getKey(), i.getValue() * yMap.get(i.getKey()));
		}
		
		System.out.println("XYMAP POP");
		for(Map.Entry<Integer, Double> i : xyMap.entrySet()) {
			System.out.println("X: "+i.getKey()+" | Y: "+i.getValue());
		}
		
		return xyMap;
	}
	
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}
}