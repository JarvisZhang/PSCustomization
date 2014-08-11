package cn.edu.buaa.jsi.psmanager;

import java.util.HashMap;

public class PingerPoint {
	
	private long unixtime;
	private double minRtt, maxRtt, medianRtt, meanRtt, iqrIpd, maxIpd, meanIpd;
	
	public PingerPoint(long unixtime, double minRtt, double maxRtt,
			double medianRtt, double meanRtt, double iqrIpd, double maxIpd,
			double meanIpd) {
		this.unixtime = (unixtime >= 0) ? unixtime : 0;
		this.minRtt = (minRtt >= 0) ? minRtt : 0;
		this.maxRtt = (maxRtt >= 0) ? maxRtt : 0;
		this.medianRtt = (medianRtt >= 0) ? medianRtt : 0;
		this.meanRtt = (meanRtt >= 0) ? meanRtt : 0;
		this.iqrIpd = (iqrIpd >= 0) ? iqrIpd : 0;
		this.maxIpd = (maxIpd >= 0) ? maxIpd : 0;
		this.meanIpd = (meanIpd >= 0) ? meanIpd : 0;
	}
	
	public PingerPoint(HashMap<String, String> pointValueMap) {
		double minRtt =
				Double.parseDouble(pointValueMap.getOrDefault("minRtt", "0"));
		double maxRtt =
				Double.parseDouble(pointValueMap.getOrDefault("maxRtt", "0"));
		double medianRtt =
				Double.parseDouble(pointValueMap.getOrDefault("meanRtt", "0"));
		double meanRtt =
				Double.parseDouble(pointValueMap.getOrDefault("meanRtt", "0"));
		double iqrIpd =
				Double.parseDouble(pointValueMap.getOrDefault("iqrIpd", "0"));
		double maxIpd =
				Double.parseDouble(pointValueMap.getOrDefault("maxIpd", "0"));
		double meanIpd =
				Double.parseDouble(pointValueMap.getOrDefault("meanIpd", "0"));
		this.unixtime = 
				Long.parseLong(pointValueMap.getOrDefault("unixtime", "0"));;
		this.minRtt = (minRtt >= 0) ? minRtt : 0;
		this.maxRtt = (maxRtt >= 0) ? maxRtt : 0;
		this.medianRtt = (medianRtt >= 0) ? medianRtt : 0;
		this.meanRtt = (meanRtt >= 0) ? meanRtt : 0;
		this.iqrIpd = (iqrIpd >= 0) ? iqrIpd : 0;
		this.maxIpd = (maxIpd >= 0) ? maxIpd : 0;
		this.meanIpd = (meanIpd >= 0) ? meanIpd : 0;
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
