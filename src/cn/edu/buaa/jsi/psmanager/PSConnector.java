package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class PSConnector {
	
	private final String src, dest, host;
	private final long timeStart, timeEnd;
	
	public PSConnector (String host, String src, String dest,
			long timeStart, long timeEnd){
		this.host = host;
		this.src = src;
		this.dest = dest;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	
	public List<BwctlPoint> getBwctlDataPoint() {
		BwctlManager bwctlManager = new BwctlManager(this.src, this.dest,
				this.timeStart, this.timeEnd);
		return bwctlManager.getResult(this.host);
	}
	
	public List<OwampPoint> getOwampDataPoint() {
		OwampManager owampManager = new OwampManager(this.src, this.dest, this.timeStart, this.timeEnd);
		return owampManager.getResult(this.host);
	}
}
