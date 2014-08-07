package cn.edu.buaa.jsi.psmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PingerData {

	private List<PingerPoint> pingerPointList;
	
	public PingerData(SOAPMessage soapMessage) {
		try {
			SOAPBody soapBody = soapMessage.getSOAPBody();
			Element message = (Element)soapBody.getElementsByTagName(
					"nmwg:message").item(0);
			Element data =
					(Element)message.getElementsByTagName("nmwg:data").item(0);
			NodeList dataItems = data.getElementsByTagName("nmwg:commonTime");
			this.pingerPointList =
					new ArrayList<PingerPoint>(dataItems.getLength());
			for(int i = 0; i < dataItems.getLength(); i++) {
				Element dataItem = (Element)dataItems.item(i);
				this.pingerPointList.add(getDatumPoint(dataItem));
			}
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<PingerPoint> getPointData() {
		return this.pingerPointList;
	}
	
	private static PingerPoint getDatumPoint(Element dataItem) {
		HashMap<String, String> pointValueMap = new HashMap<String, String>();
		String unixtime = dataItem.getAttribute("value");
		pointValueMap.put("unixtime", unixtime);
		NodeList datumItems = dataItem.getElementsByTagName("pinger:datum");
		for (int i = 0; i < datumItems.getLength(); i++) {
			Element datumElement = (Element)datumItems.item(i);
			pointValueMap.put(datumElement.getAttribute("name"),
					datumElement.getAttribute("value"));
		}
		return new PingerPoint(pointValueMap);
	}
}
