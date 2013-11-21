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
		samMean = roundOff(1.0 * samMean / sample.length);
	}

	public int[] getSample() {
		return sample;
	}

	public void setSample(int[] sample) {
		this.sample = sample;
	}

	public double getSamMean() {
		return samMean;
	}

	public void setSamMean(double samMean) {
		this.samMean = samMean;
	}
}
