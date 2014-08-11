package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class unitest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		String srcIP = "10.4.9.199", destIP = "10.4.9.202";
//		long bwctlTimeStart = 1407372530, bwctlTimeEnd = 1407374330;
//		long owampTimeStart = 1406945400, owampTimeEnd = 1406946600;
//		long pingerTimeStart = 1407372530, pingerTimeEnd = 1407374330;
		
		String srcIP = "2001:da8:203:d406:16da:e9ff:fef9:b68f";
		String destIP = "2400:dd01:1011:1:92b1:1cff:fe0c:7c0a";
		String _srcIP = "115.25.138.244", _destIP = "159.226.15.235";
		long timeStart = 1407427200, timeEnd = 1407441600;
		
		String bwctlHost = "http://" + _srcIP + ":8085/perfSONAR_PS/services/pSB";
		String owampHost = "http://" + _srcIP + ":8085/perfSONAR_PS/services/pSB";
		String pingerHost = "http://" + _srcIP + ":8075/perfSONAR_PS/services/pinger/ma";
		
		PSConnector bwctlConn = new PSConnector(
				bwctlHost, srcIP, destIP, timeStart, timeEnd);
		List<BwctlPoint> bwctlPoint = bwctlConn.getBwctlDataPoint();
		for(BwctlPoint point : bwctlPoint) {
			point.printPoint();
		}
		
		PSConnector owampConn = new PSConnector(
				owampHost, srcIP, destIP, timeStart, timeEnd);
		List<OwampPoint> owampPoint = owampConn.getOwampDataPoint();
		for(OwampPoint point : owampPoint) {
			point.printPoint();
		}
		
		PSConnector pingerConn = new PSConnector(
				pingerHost, srcIP, destIP, timeStart, timeEnd);
		List<PingerPoint> pingerPoint = pingerConn.getPingerDataPoint();
		for(PingerPoint point : pingerPoint) {
			point.printPoint();
		}
	}

}
