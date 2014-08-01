package cn.edu.buaa.jsi.psmanager;

public class BasicPoint {
	
	private long unixtime;
	private double value;
	
	public BasicPoint(long unixtime, double value){
		this.unixtime = unixtime;
		this.value = value;
	}

	public long getUnixtime(){
		return this.unixtime;
	}
	
	public double getValue(){
		return this.value;
	}
}
