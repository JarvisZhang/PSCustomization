package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class unitest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String srcIP = "10.4.9.199", destIP = "10.4.9.202";

		String bwctlHost = "http://10.4.9.199:8085/perfSONAR_PS/services/pSB";
		long bwctlTimeStart = 1407372530, bwctlTimeEnd = 1407374330;
		PSConnector bwctlConn = new PSConnector(
				bwctlHost, srcIP, destIP, bwctlTimeStart, bwctlTimeEnd);
		List<BwctlPoint> bwctlPoint = bwctlConn.getBwctlDataPoint();
		for(BwctlPoint point : bwctlPoint) {
			point.printPoint();
		}
		
		String owampHost = "http://10.4.9.199:8085/perfSONAR_PS/services/pSB";
		long owampTimeStart = 1406945400, owampTimeEnd = 1406946600;
		PSConnector owampConn = new PSConnector(
				owampHost, srcIP, destIP, owampTimeStart, owampTimeEnd);
		List<OwampPoint> owampPoint = owampConn.getOwampDataPoint();
		for(OwampPoint point : owampPoint) {
			point.printPoint();
		}
		
//		String pingerHost = "http://10.4.9.199:8075/perfSONAR_PS/services/pinger/ma";
		String pingerHost = "http://115.25.138.244:8075/perfSONAR_PS/services/pinger/ma";
		srcIP = "115.25.138.244";
		destIP = "159.226.15.235";
		long pingerTimeStart = 1407372530, pingerTimeEnd = 1407374330;
		PSConnector pingerConn = new PSConnector(
				pingerHost, srcIP, destIP, pingerTimeStart, pingerTimeEnd);
		List<PingerPoint> pingerPoint = pingerConn.getPingerDataPoint();
		for(PingerPoint point : pingerPoint) {
			point.printPoint();
		}
	}

}
