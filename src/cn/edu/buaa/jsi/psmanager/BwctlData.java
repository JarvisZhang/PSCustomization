package cn.edu.buaa.jsi.psmanager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BwctlData{
	
	private List<BwctlPoint> bwctlPointList;
	
	public BwctlData(SOAPMessage soapMessage) {
		try {
			SOAPBody soapBody = soapMessage.getSOAPBody();
			Element message = (Element)soapBody.getElementsByTagName(
					"nmwg:message").item(0);
			Element data = 
					(Element)message.getElementsByTagName("nmwg:data").item(0);
			
			NodeList dataItems = data.getElementsByTagName("iperf:datum");
			this.bwctlPointList = new ArrayList<BwctlPoint>(dataItems.getLength());
			
			for (int i = 0; i < dataItems.getLength(); i++) {
				Element datum = (Element)dataItems.item(i);
				double throughput =
						Double.parseDouble(datum.getAttribute("throughput"));
				long unixtime = XMLParser.isoTimeParse(
						datum.getAttribute("timeValue"));
				this.bwctlPointList.add(new BwctlPoint(unixtime, throughput));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BwctlPoint> getPointData() {
		return this.bwctlPointList;
	}
}
