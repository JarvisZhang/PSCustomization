package cn.edu.buaa.jsi.psmanager;

public class OwampPoint {

	private long unixtime;
	private double minDelay, maxError, maxDelay, duplicates, loss;
	
	public OwampPoint(long unixtime, double minDelay,double maxError,
			double maxDelay, double duplicates, double loss) {
		this.unixtime = unixtime;
		this.minDelay = minDelay;
		this.maxError = maxError;
		this.maxDelay = maxDelay;
		this.duplicates = duplicates;
		this.loss = loss;
	}

	public long getUnixtime() {
		return unixtime;
	}

	public double getMinDelay() {
		return minDelay;
	}

	public double getMaxError() {
		return maxError;
	}

	public double getMaxDelay() {
		return maxDelay;
	}

	public double getDuplicates() {
		return duplicates;
	}

	public double getLoss() {
		return loss;
	}
	
	public void printPoint() {
		System.out.printf("unixtime: %d, ", this.unixtime);
		System.out.printf("minDelay: %f, ", this.minDelay);
		System.out.printf("maxError: %f, ", this.maxError);
		System.out.printf("maxDelay: %f, ", this.maxDelay);
		System.out.printf("duplicates: %f, ", this.duplicates);
		System.out.printf("loss: %f\n", this.loss);
	}
}
