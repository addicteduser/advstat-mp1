package Main;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import popDataGeneration.BimodalDataGenerator;
import popDataGeneration.NormalDataGenerator;
import popDataGeneration.PopulationDataGenerator;
import popDataGeneration.RandomDataGenerator;
import popDataGeneration.SkewedDataGenerator;
import popDataGeneration.UniformDataGenerator;
import samDataGeneration.Sample;
import samDataGeneration.SampleDataGenerator;

public class Model {
	private int lowerBound;
	private int upperBound;
	private int popSize; // N
	private int samSize; // n
	private int distType;
	
	// POPULATION
	private PopulationDataGenerator popDataGen;
	private Map<Integer, Double> popXYVal;
	private ArrayList<Integer> popXVal;
	private ArrayList<Double> popYVal;
	private double popMean;
	private double popVariance;
	
	// SAMPLE
	private SampleDataGenerator samDataGen;
	private ArrayList<Sample> samList;
	private Map<Double, Integer> samXYVal;
	private ArrayList<Double> samXVal;
	private ArrayList<Double> samYVal;
	private double samMean;
	private double samVariance;

	public Model(int lb, int ub, int pop, int sam, int dist) {
		// INDICATE DEFAULT VALUES HERE
		lowerBound = lb;
		upperBound = ub;
		popSize = pop;
		samSize = sam;
		distType = dist;
	}

	/**
	 * Prints the Population x, f(x), mean, and variance
	 */
	public void printData() {
		System.out.println("x : f(x)");
		for (int i = 0; i < popXVal.size(); i++) {
			System.out.println(popXVal.get(i) + " : " + popYVal.get(i));
		}
		System.out.println("MEAN: " + popMean);
		System.out.println("VARIANCE: " + popVariance);
	}

	public void generateData() {
		switch (distType) {
		case 0: // UNIFORM
			popDataGen = new UniformDataGenerator();
			System.out.println("\nUNIFORM");
			break;
		case 1: // SKEWED
			popDataGen = new SkewedDataGenerator();
			System.out.println("\nSKEWED");
			break;
		case 2: // BIMODAL
			popDataGen = new BimodalDataGenerator();
			System.out.println("\nBIMODAL");
			break;
		case 3: // NORMAL
			popDataGen = new NormalDataGenerator();
			System.out.println("\nNORMAL");
			break;
		}
		
		generatePopData();
		generateSamData();
		
		/*switch(distType) {
		case 4: // RANDOM
			System.out.println("\nRANDOM");
			RandomDataGenerator popRanDataGen = new RandomDataGenerator();
			break;
		}*/
		
	}
	
	public void generatePopData() {
		popXVal = popDataGen.generateXData(lowerBound, upperBound, popSize);
		popYVal = popDataGen.generateYData(popXVal, popSize);
		//popXYVal = popDataGen.generateXYData(popXVal, popYVal, popSize);
		popMean = computePopMean();
		popVariance = computePopVariance();
		printData();
	}

	public void generateSamData() {
		samDataGen = new SampleDataGenerator(popXVal, samSize);
		samList = samDataGen.generateSamples();
		samXYVal = samDataGen.generateXYData(samList);
		samXVal = samDataGen.generateXData2(samXYVal);
		samYVal = samDataGen.generateYData2(samXYVal);
		samMean = computeSamMean();
		samVariance = computeSamVariance();
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

		for (int i = 0; i < popXVal.size(); i++) {
			System.out.println(i);
			value += 1.0 * popXVal.get(i)
					* popYVal.get(i);
			
		}

		return roundOff(value);
	}

	public double computePopVariance() {
		double value = 0;

		for (int i = 0; i < popXVal.size(); i++) {
			value += 1.0 * Math.pow(popXVal.get(i), 2) * popYVal.get(i);
		}

		value = value - Math.pow(popMean, 2);

		return roundOff(value);
	}
	
	public double computeSamMean() {
		double value = 0;

		for (int i = 0; i < samXVal.size(); i++) {
			value += 1.0 * samXVal.get(i) * samYVal.get(i);
		}

		return roundOff(value);
	}

	public double computeSamVariance() {
		double value = 0;

		for (int i = 0; i < samXVal.size(); i++) {
			value += 1.0 * Math.pow(samXVal.get(i), 2) * samYVal.get(i);
		}

		value = value - Math.pow(samMean, 2);

		return roundOff(value);
	}

	public DefaultTableModel generatePopDistTable() {
		DefaultTableModel popDistModel = new DefaultTableModel();

		String[] distributionTableColumn = { "x", "f(x)" };
		popDistModel.setColumnIdentifiers(distributionTableColumn);

		for (int i = 0; i < popXVal.size(); i++) {
			Object[] row = new Object[2];
			row[0] = popXVal.get(i);
			row[1] = popYVal.get(i);
			popDistModel.addRow(row);
		}
		
		/*for(Map.Entry<Integer, Double> i : popXYVal.entrySet()) {
			Object[] row = new Object[2];
			row[0] = i.getKey();
			row[1] = i.getValue();
			popDistModel.addRow(row);
		}*/

		return popDistModel;
	}

	public DefaultCategoryDataset generatePopDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < popYVal.size(); i++) {
			dataset.setValue(popYVal.get(i), "x",
					Integer.toString(popXVal.get(i)));
		}

		return dataset;
	}
	
	public DefaultTableModel generateSamDistTable() {
		DefaultTableModel samDistModel = new DefaultTableModel();

		String[] distributionTableColumn = { "x", "f(x)" };
		samDistModel.setColumnIdentifiers(distributionTableColumn);
		
		for (int i = 0; i < samXVal.size(); i++) {
			Object[] row = new Object[2];
			row[0] = samXVal.get(i);
			row[1] = samYVal.get(i);
			samDistModel.addRow(row);
		}

		return samDistModel;
	}
	
	public DefaultCategoryDataset generateSamDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i = 0; i < samXVal.size(); i++) {
			dataset.setValue(samYVal.get(i), "x",
					Double.toString(samXVal.get(i)));
		}

		return dataset;
	}
	
	public DefaultTableModel generateSamMeanTable() {	
		DefaultTableModel samMeanModel = new DefaultTableModel();

		String[] samplesAndMeanColumn = { "Samples", "Mean" };
		samMeanModel.setColumnIdentifiers(samplesAndMeanColumn);
		
		for (int i = 0; i < samList.size(); i++) {
			Object[] row = new Object[2];
			String str = "";
			for(int j = 0; j < samList.get(i).getSample().length; j++) {
				str = str.concat(samList.get(i).getSample()[j]+" ");
			}
			row[0] = str;
			row[1] = samList.get(i).getSamMean();
			samMeanModel.addRow(row);
		}

		return samMeanModel;
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
		return popMean;
	}

	public void setMean(double mean) {
		this.popMean = mean;
	}

	public double getVariance() {
		return popVariance;
	}

	public void setVariance(double variance) {
		this.popVariance = variance;
	}

	public ArrayList<Integer> getPopXVal() {
		return popXVal;
	}

	public void setPopXVal(ArrayList<Integer> popXVal) {
		this.popXVal = popXVal;
	}

	public double getPopMean() {
		return popMean;
	}

	public void setPopMean(double popMean) {
		this.popMean = popMean;
	}

	public double getPopVariance() {
		return popVariance;
	}

	public void setPopVariance(double popVariance) {
		this.popVariance = popVariance;
	}

	public double getSamMean() {
		return samMean;
	}

	public void setSamMean(double samMean) {
		this.samMean = samMean;
	}

	public double getSamVariance() {
		return samVariance;
	}

	public void setSamVariance(double samVariance) {
		this.samVariance = samVariance;
	}
}
