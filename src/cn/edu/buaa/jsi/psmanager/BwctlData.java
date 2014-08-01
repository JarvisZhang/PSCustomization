package cn.edu.buaa.jsi.psmanager;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BwctlData{
	
	private List<BasicPoint> bwctlPoint;
	
	public BwctlData(SOAPMessage soapMessage) {
		SOAPBody soapBody;
		try {
			soapBody = soapMessage.getSOAPBody();
			Element message =
					(Element)soapBody.getElementsByTagName("nmwg:message").item(0);
			Element data = (Element)message.getElementsByTagName("nmwg:data").item(0);
			List<Element> dataElements =
					elements(data.getElementsByTagName("iperf:datum"));
			this.bwctlPoint = new ArrayList<BasicPoint>(dataElements.size());
			for (Element datum : dataElements) {
				Double throughput =
						Double.parseDouble(datum.getAttribute("throughput"));
				long unixtime = timeStrParse(datum.getAttribute("timeValue"));
				this.bwctlPoint.add(new BasicPoint(unixtime, throughput));
			}
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BasicPoint> getPointData() {
		return this.bwctlPoint;
	}
	
	private static List<Element> elements(NodeList nodes) {
		List<Element> result = new ArrayList<Element>(nodes.getLength());
	    for (int i = 0; i < nodes.getLength(); i++) {
	      Node node = nodes.item(i);
	      if (node instanceof Element)
	        result.add((Element)node);
	    }
	    return result;
	}
	
	private static long timeStrParse(String timeValue) {
		Date date;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			StringBuilder sb = new StringBuilder(timeValue);
			sb.replace(19, sb.indexOf("UTC") - 1, "");
			date = simpleDateFormat.parse(sb.toString());
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
