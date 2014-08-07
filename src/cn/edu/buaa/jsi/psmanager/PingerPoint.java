package cn.edu.buaa.jsi.psmanager;

import java.util.HashMap;

public class PingerPoint {
	
	private long unixtime;
	private double minRtt, maxRtt, medianRtt, meanRtt, iqrIpd, maxIpd, meanIpd;
	
	public PingerPoint(long unixtime, double minRtt, double maxRtt,
			double medianRtt, double iqrIpd, double maxIpd, double meanIpd) {
		this.unixtime = unixtime;
		this.minRtt = minRtt;
		this.maxRtt = maxRtt;
		this.medianRtt = medianRtt;
		this.iqrIpd = iqrIpd;
		this.maxIpd = maxIpd;
		this.meanIpd = meanIpd;
	}
	
	public PingerPoint(HashMap<String, String> pointValueMap) {
		this.unixtime = Long.parseLong(pointValueMap.get("unixtime"));
		this.minRtt = Double.parseDouble(pointValueMap.get("minRtt"));
		this.maxRtt = Double.parseDouble(pointValueMap.get("maxRtt"));
		this.medianRtt = Double.parseDouble(pointValueMap.get("meanRtt"));
		this.meanRtt = Double.parseDouble(pointValueMap.get("meanRtt"));
		this.iqrIpd = Double.parseDouble(pointValueMap.get("iqrIpd"));
		this.maxIpd = Double.parseDouble(pointValueMap.get("maxIpd"));
		this.meanIpd = Double.parseDouble(pointValueMap.get("meanIpd"));
	}
	
	public long getUnixtime() {
		return unixtime;
	}
	
	public double getMinRtt() {
		return minRtt;
	
	}
	public double getMaxRtt() {
		return maxRtt;
	}
	
	public double getMedianRtt() {
		return medianRtt;
	}
	
	public double getMeanRtt() {
		return meanRtt;
	}
	
	public double getIqrIpd() {
		return iqrIpd;
	}
	
	public double getMaxIpd() {
		return maxIpd;
	}
	
	public double getMeanIpd() {
		return meanIpd;
	}
	
	public void printPoint() {
		System.out.printf("unixtime: %d, ", this.unixtime);
		System.out.printf("minRtt: %f, ", this.minRtt);
		System.out.printf("maxRtt %f, ", this.maxRtt);
		System.out.printf("medianRtt: %f, ", this.medianRtt);
		System.out.printf("meanRtt: %f, ", this.meanRtt);
		System.out.printf("iqrIpd: %f, ", this.iqrIpd);
		System.out.printf("maxIpd: %f, ", this.maxIpd);
		System.out.printf("meanIpd: %f\n", this.meanIpd);
	}
}
