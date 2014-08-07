package cn.edu.buaa.jsi.psmanager;

import java.util.List;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPPart;

public class OwampManager extends XMLManager {
	
	public OwampManager(String srcIP, String destIP, long timeStart,
			long timeEnd) {
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
			SOAPElement metaTimeElement = this.buildTimeElement(msgElement, timeStart, timeEnd);
			SOAPElement dataElement = msgElement.addChildElement("data",
					"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
			dataElement.setAttribute("id", "data2");
			dataElement.setAttribute("metadataIdRef", "meta2c");
			this.buildDataElement(msgElement, metaTimeElement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OwampPoint> getResult(String host) {
		OwampData owampData = new OwampData(this.sendRequest(host));
		return owampData.getPointData();
	}
	
	protected SOAPElement buildIPElement(SOAPBodyElement msgElement, String srcIP,
			String destIP) throws Exception {
		SOAPElement metaElement = super.buildIPElement(msgElement, srcIP, destIP);
		
		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/tools/owamp/2.0");
		
		return metaElement;
	}
}
