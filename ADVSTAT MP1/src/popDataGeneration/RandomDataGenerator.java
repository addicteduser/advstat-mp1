package popDataGeneration;

import java.util.ArrayList;
import java.util.Random;

public class RandomDataGenerator extends PopulationDataGenerator {

	public ArrayList<Integer> generateXData(int lowerBound, int upperBound,
			int population) {
		ran = new Random();
		xValues = new ArrayList<Integer>();

		int i = 0;
		int temp;

		while (i < population) {
			temp = ran.nextInt(upperBound + 1);

			if (lowerBound <= temp) {
				xValues.add(temp);
				i++;
			}
		}

		//sortData(xValues);

		return xValues;
	}

	@Override
	public ArrayList<Double> generateYData(ArrayList<Integer> xData,
			int population) {
		ArrayList<Double> yData = new ArrayList<Double>();

		for (int i = 0; i < xData.size(); i++) {
			double value = roundOff(1.0 * xData.get(i) / population);
			yData.add(value);
		}

		return yData;
	}

}
