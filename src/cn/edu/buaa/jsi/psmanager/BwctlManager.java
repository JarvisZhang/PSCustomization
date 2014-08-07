package cn.edu.buaa.jsi.psmanager;

import java.util.List;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;

public class BwctlManager extends XMLManager {
	
	public BwctlManager(String srcIP, String destIP,
			long timeStart, long timeEnd) {
		try {
			super.init();
			SOAPPart soapPart = super.getSOAPRequestMsg().getSOAPPart();
			SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
			this.buildSOAPHeader(soapEnvelope);
			SOAPBody soapBody = soapEnvelope.getBody();
			Name bodyName = soapEnvelope.createName("nmwg:message");
			SOAPBodyElement msgElement = soapBody.addBodyElement(bodyName);
			this.buildBodyHeader(msgElement);
			this.buildIPElement(msgElement, srcIP, destIP);
			SOAPElement metaTimeElement =
					this.buildTimeElement(msgElement, timeStart, timeEnd);
			this.buildDataElement(msgElement, metaTimeElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BwctlPoint> getResult(String host) {
		BwctlData bwctlData = new BwctlData(this.sendRequest(host));
		return bwctlData.getPointData();
	}
	
	protected SOAPElement buildIPElement(SOAPBodyElement msgElement, String srcIP,
			String destIP) throws Exception {
		SOAPElement metaElement = super.buildIPElement(msgElement, srcIP, destIP);
		
		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/tools/iperf/2.0");
		
		SOAPElement paraElement = metaElement.addChildElement("parameters",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		paraElement.setAttribute("id", "parameters2");
		
		SOAPElement protoElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		protoElement.setAttribute("name", "protocol");
		protoElement.addTextNode("TCP");
		
		return metaElement;
	}
}
