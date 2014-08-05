package cn.edu.buaa.jsi.psmanager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class OwampData {
	
	private List<OwampPoint> owampPointList;
	
	public OwampData(SOAPMessage soapMessage) {
		SOAPBody soapBody;
		try {
			soapBody = soapMessage.getSOAPBody();
			Element message = (Element)soapBody.getElementsByTagName(
					"nmwg:message").item(0);
			Element data =
					(Element)message.getElementsByTagName("nmwg:data").item(0);
			NodeList dataItems = data.getElementsByTagName("summary:datum");
			this.owampPointList =
					new ArrayList<OwampPoint>(dataItems.getLength());
			for (int i = 0; i < dataItems.getLength(); i++) {
				Element datum = (Element)dataItems.item(i);
				double minDelay =
						Double.parseDouble(datum.getAttribute("min_delay"));
				double maxError =
						Double.parseDouble(datum.getAttribute("maxError"));
				double maxDelay =
						Double.parseDouble(datum.getAttribute("max_delay"));
				long unixtime = XMLParser.isoTimeParse(
						datum.getAttribute("endTime"));
				int duplicates =
						Integer.parseInt(datum.getAttribute("duplicates"));
				int loss =
						Integer.parseInt(datum.getAttribute("loss"));
				this.owampPointList.add(new OwampPoint(unixtime, minDelay,
						maxError, maxDelay, duplicates, loss));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OwampPoint> getPointData() {
		return this.owampPointList;
	}
	
}
