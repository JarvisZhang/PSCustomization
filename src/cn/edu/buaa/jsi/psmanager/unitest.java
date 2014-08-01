package cn.edu.buaa.jsi.psmanager;

import java.util.List;

public class unitest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String srcIP = "10.4.9.199", destIP = "10.4.9.202";
		String host = "http://10.4.9.202:8085/perfSONAR_PS/services/pSB";
		long timeStart = 1406271714, timeEnd = 1406275507;
		
		PSConnector conn = new PSConnector(host, srcIP, destIP, timeStart, timeEnd);
		
		List<BasicPoint> bwctlPoint = conn.getBwctlDataPoint();
		
		for(BasicPoint point : bwctlPoint) {
			System.out.printf("timestamp: %d, bandwidth: %f\n",
					point.getUnixtime(), point.getValue());
		}

	}

}
