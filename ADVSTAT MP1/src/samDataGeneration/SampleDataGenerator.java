package samDataGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class SampleDataGenerator {
	private int samSize;
	private ArrayList<Integer> popVal;

	public SampleDataGenerator(ArrayList<Integer> popValues, int sampleSize) {
		popVal = popValues;
		samSize = sampleSize;
	}

	public ArrayList<Sample> generateSamples() {
		ArrayList<Sample> sampleList = new ArrayList<Sample>();
		// Create dataset to be sampled
		ICombinatoricsVector<Integer> dataset = Factory.createVector(popVal);

		// Create a generator to generate samSize-combinations of the dataset
		Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(dataset, samSize);

		// Print all possible combinations		
		for (ICombinatoricsVector<Integer> combination : gen) {
			System.out.println(combination);
		}
		
		// Transfer to Sample data class
		List<ICombinatoricsVector<Integer>> list = gen.generateAllObjects();
		for(int i = 0; i < list.size(); i++) {
			Sample sam = new Sample(list.get(i).getSize());
			for(int j = 0; j < list.get(i).getSize(); j++) {
				sam.getSample()[j] = list.get(i).getValue(j);
			}
			sam.computeSampleMean();
			sampleList.add(sam);
		}
		
		return sampleList;
	}
	
	public ArrayList<Double> generateXData(ArrayList<Sample> list) {
		ArrayList<Double> xData = new ArrayList<Double>();
		
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
		
		for(int i = 0; i < list.size(); i++) {
			xData.add(list.get(i).getSamMean());
		}
		
		Collections.sort(xData);
		
		return xData;
	}
	
	public ArrayList<Double> generateYData(ArrayList<Double> xData) {
		ArrayList<Double> yData = new ArrayList<Double>();

		for (int i = 0; i < xData.size(); i++) {
			double value = roundOff(1.0 / xData.size());
			yData.add(value);
		}

		return yData;
	}
	
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}
}
