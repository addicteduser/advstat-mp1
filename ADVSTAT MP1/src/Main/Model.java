package Main;

import java.util.ArrayList;

import dataGeneration.DataGenerator;
import dataGeneration.NormalDataGenerator;

public class Model {
	private int lowerBound;
	private int upperBound;
	private int popSize;
	private int samSize;
	private int distType;
	private double mean;
	private double variance;
	private ArrayList<Integer> popXVal;

	public Model() {
		// INDICATE DEFAULT VALUES HERE
		lowerBound = 0;
		upperBound = 10;
		popSize = 30;
		samSize = 10;
		distType = 0;
		defaultPopXVal();
		for (int j = 0; j < popSize; j++)
			System.out.println(popXVal.get(j));
		mean = computeMean(popXVal);
		System.out.println(mean);
		variance = computeVariance(popXVal);
		System.out.println(variance);
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

		for (int j = 0; j < popSize; j++)
			System.out.println(popXVal.get(j));
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

		return value;
	}

	public double computeVariance(ArrayList<Integer> xVal) {
		double value = 0;

		for (int i = 0; i < xVal.size(); i++) {
			value += xVal.get(i) * xVal.get(i) * (1.0 / popSize);
		}
		
		value = value - mean * mean;

		return value;
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
