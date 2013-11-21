package samDataGeneration;

public class Sample {
	private int[] sample;
	private double samMean = 0;
	
	public Sample(int sampleSize) {
		sample = new int[sampleSize];
	}
	
	public double roundOff(double value) {
		return (double) Math.round(value * 1000000) / 1000000;
	}
	
	public void computeSampleMean() {
		for(int i = 0; i < sample.length; i++) {
			samMean += sample[i];
		}
		samMean = 1.0 * samMean / sample.length;
	}
}
