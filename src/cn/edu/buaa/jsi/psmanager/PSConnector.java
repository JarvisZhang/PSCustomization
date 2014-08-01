package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class PSConnector {
	
	private final String src, dest, host;
	private final long timeStart, timeEnd;
	
	public PSConnector (String host, String src, String dest, long timeStart, long timeEnd){
		this.host = host;
		this.src = src;
		this.dest = dest;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	
	public List<BasicPoint> getBwctlDataPoint(){
		XMLManager bwctlManager = new XMLManager(this.src, this.dest,
				this.timeStart, this.timeEnd);
		return bwctlManager.sendRequest(this.host).getPointData();
	}
	
}
