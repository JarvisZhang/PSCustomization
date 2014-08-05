package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class unitest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String srcIP = "10.4.9.199", destIP = "10.4.9.202";

		String bwctlHost = "http://10.4.9.202:8085/perfSONAR_PS/services/pSB";
		long bwctlTimeStart = 1406271714, bwctlTimeEnd = 1406275507;
		PSConnector bwctlConn = new PSConnector(
				bwctlHost, srcIP, destIP, bwctlTimeStart, bwctlTimeEnd);
		List<BwctlPoint> bwctlPoint = bwctlConn.getBwctlDataPoint();
		for(BwctlPoint point : bwctlPoint) {
			point.printPoint();
		}
		
		String owampHost = "http://10.4.9.202:8085/perfSONAR_PS/services/pSB";
		long owampTimeStart = 1406945400, owampTimeEnd = 1406946600;
		PSConnector owampConn = new PSConnector(
				owampHost, srcIP, destIP, owampTimeStart, owampTimeEnd);
		List<OwampPoint> owampPoint = owampConn.getOwampDataPoint();
		for(OwampPoint point : owampPoint) {
			point.printPoint();
		}
		
	}

}
