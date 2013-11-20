package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

public class Model {
	private int lowerBound;
	private int upperBound;
	private int popSize;
	private int samSize;
	private int distType;
	private double mean;
	private double variance;
	private ArrayList<Integer> popXVal;
	private Map<Integer, Integer> xMap = new HashMap<Integer, Integer>();

	public Model() {
		// INDICATE DEFAULT VALUES HERE
		lowerBound = 0;
		upperBound = 10;
		popSize = 30;
		samSize = 10;
		distType = 0;
		defaultPopXVal();
		mean = computeMean(popXVal);
		System.out.println(mean);
		variance = computeVariance(popXVal);
		generatePopDistTable();
	}

	private void defaultPopXVal() {
		popXVal = new ArrayList<Integer>();
		int ctr1 = 0;
		int ctr2 = 1;
		for (int i = 0; i < popSize; i++) {
			if (ctr1 == 3) {
				ctr1 = 0;
				ctr2++;
			}
			popXVal.add(ctr2);
			ctr1++;
		}
	}
	
	/**
	 * Rounds of value to 6 decimal places
	 * @param value - value to be rounded off
	 * @return value rounded off to 6 decimal places
	 */
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}

	/**
	 * 
	 * @return
	 */
	public double computeMean(ArrayList<Integer> xVal) {
		double value = 0;

		for (int i = 0; i < xVal.size(); i++) {
			value += xVal.get(i) * (1.0 / popSize);
		}

		return roundOff(value);
	}

	public double computeVariance(ArrayList<Integer> xVal) {
		double value = 0;

		for (int i = 0; i < xVal.size(); i++) {
			value += xVal.get(i) * xVal.get(i) * (1.0 / popSize);
		}
		
		value = value - mean * mean;

		return roundOff(value);
	}
	
	public DefaultTableModel generatePopDistTable() {
		DefaultTableModel popDistModel = new DefaultTableModel();
		
		String[] distributionTableColumn = { "x", "f(x)" };
		popDistModel.setColumnIdentifiers(distributionTableColumn);
		
		for(int i = 0; i < popSize; i++) {			
			if (!xMap.containsKey(popXVal.get(i))) {
				 xMap.put(popXVal.get(i), 1);
			} else
				xMap.put(popXVal.get(i), xMap.get(popXVal.get(i)) + 1);
		}
		
		for(int i = 0; i < xMap.size(); i++) {
			Object[] row = new Object[2];
		}
		
		for (Map.Entry<Integer, Integer> i : xMap.entrySet()) {
			Object[] row = new Object[2];
			row[0] = i.getKey();
			row[1] = roundOff(1.0 * i.getValue() / popSize);
			popDistModel.addRow(row);
		}
		
		return popDistModel;
	}
	
	public DefaultCategoryDataset generatePopDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		Object[] keys = xMap.keySet().toArray();
		
		for(int i = 0; i < keys.length; i++) {
			dataset.setValue((Number) keys[i], "b", Integer.toString(i));
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
