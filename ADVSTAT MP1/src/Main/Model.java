package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import dataGeneration.DataGenerator;
import dataGeneration.RandomDataGenerator;
import dataGeneration.UniformDataGenerator;

public class Model {
	private int lowerBound;
	private int upperBound;
	private int popSize; // N
	private int samSize; // n
	private int distType;
	private double mean; // Population Mean
	private double variance; // Population Variance
	private ArrayList<Integer> popXVal; // Population X
	private ArrayList<Double> popYVal; // Population f(x)
	private Map<Integer, Integer> xMap = new HashMap<Integer, Integer>();
	private DataGenerator datGen;

	public Model() {
		// INDICATE DEFAULT VALUES HERE
		lowerBound = 0;
		upperBound = 10;
		popSize = 30;
		samSize = 10;
		distType = 0;

		datGen = new UniformDataGenerator();
		popXVal = datGen.generateXData(lowerBound, upperBound, popSize);
		popYVal = datGen.generateYData(popXVal, popSize);

		mean = computePopMean();
		variance = computePopVariance();
		printData();

		// generatePopDistTable();
	}

	/**
	 * Prints the Population x, f(x), mean, and variance
	 */
	public void printData() {
		System.out.println("x : f(x)");
		for (int i = 0; i < popSize; i++) {
			System.out.println(popXVal.get(i) + " : " + popYVal.get(i));
		}
		System.out.println("MEAN: " + mean);
		System.out.println("VARIANCE: " + variance);
	}

	public void generatePopData() {
		switch (distType) {
		case 0: // UNIFORM
			datGen = new UniformDataGenerator();
			System.out.println("\nUNIFORM");
			break;
		case 1: // SKEWED
			System.out.println("\nSKEWED");
			break;
		case 2: // BIMODAL
			System.out.println("\nBIMODAL");
			break;
		case 3: // NORMAL
			System.out.println("\nNORMAL");
			break;
		case 4: // RANDOM
			System.out.println("\nRANDOM");
			datGen = new RandomDataGenerator();
			break;
		}
		popXVal = datGen.generateXData(lowerBound, upperBound, popSize);
		popYVal = datGen.generateYData(popXVal, popSize);
		mean = computePopMean();
		variance = computePopVariance();
		printData();
	}

	/**
	 * Rounds of value to 6 decimal places
	 * 
	 * @param value
	 *            - value to be rounded off
	 * @return value rounded off to 6 decimal places
	 */
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}

	/**
	 * 
	 * @return
	 */
	public double computePopMean() {
		double value = 0;

		for (int i = 0; i < popSize; i++) {
			value += 1.0 * popXVal.get(i) * popYVal.get(i);
		}

		return roundOff(value);
	}

	public double computePopVariance() {
		double value = 0;

		for (int i = 0; i < popSize; i++) {
			value += 1.0 * Math.pow(popXVal.get(i), 2) * popYVal.get(i);
		}

		value = value - Math.pow(mean, 2);

		return roundOff(value);
	}

	public DefaultTableModel generatePopDistTable() {
		DefaultTableModel popDistModel = new DefaultTableModel();

		String[] distributionTableColumn = { "x", "f(x)" };
		popDistModel.setColumnIdentifiers(distributionTableColumn);

		for (int i = 0; i < popSize; i++) {
			Object[] row = new Object[2];
			row[0] = popXVal.get(i);
			row[1] = popYVal.get(i);
			popDistModel.addRow(row);
		}

		// Get unique x values
		/*
		 * for(int i = 0; i < popSize; i++) { if
		 * (!xMap.containsKey(popXVal.get(i))) xMap.put(popXVal.get(i), 1); else
		 * xMap.put(popXVal.get(i), xMap.get(popXVal.get(i)) + 1); }
		 * 
		 * for (Map.Entry<Integer, Integer> i : xMap.entrySet()) { Object[] row
		 * = new Object[2]; row[0] = i.getKey(); row[1] = roundOff(1.0 *
		 * i.getValue() / popSize); popDistModel.addRow(row); }
		 */

		return popDistModel;
	}

	public DefaultCategoryDataset generatePopDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		/*
		 * Object[] keys = xMap.keySet().toArray();
		 * 
		 * for(int i = 0; i < keys.length; i++) { dataset.setValue((Number)
		 * keys[i], "b", Integer.toString(i)); }
		 */

		for (int i = 0; i < popSize; i++) {
			dataset.setValue(popYVal.get(i), "x",
					Integer.toString(popXVal.get(i)));
		}

		return dataset;
	}

	// GETTERS and SETTERS
	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

	public int getSamSize() {
		return samSize;
	}

	public void setSamSize(int samSize) {
		this.samSize = samSize;
	}

	public int getDistType() {
		return distType;
	}

	public void setDistType(int distType) {
		this.distType = distType;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public ArrayList<Integer> getPopXVal() {
		return popXVal;
	}

	public void setPopXVal(ArrayList<Integer> popXVal) {
		this.popXVal = popXVal;
	}
}
