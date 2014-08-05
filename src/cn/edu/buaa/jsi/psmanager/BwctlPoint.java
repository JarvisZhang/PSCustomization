package cn.edu.buaa.jsi.psmanager;

public class BwctlPoint {
	
	private long unixtime;
	private double value;
	
	public BwctlPoint(long unixtime, double value){
		this.unixtime = unixtime;
		this.value = value;
	}

	public long getUnixtime(){
		return this.unixtime;
	}
	
	public double getValue(){
		return this.value;
	}
	
	public void printPoint() {
		System.out.printf("unixtime: %d, bandwidth:%f\n",
				this.unixtime, this.value);
	}
}
