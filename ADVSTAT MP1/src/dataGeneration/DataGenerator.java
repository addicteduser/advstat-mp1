package dataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class DataGenerator {
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
	public abstract ArrayList<Integer> generateData(int lowerBound,
			int upperBound, int population);

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
}